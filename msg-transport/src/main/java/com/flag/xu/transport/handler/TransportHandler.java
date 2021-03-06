package com.flag.xu.transport.handler;

import com.flag.xu.transport.task.MessageSendScheduledTask;
import com.flag.xu.neko.cleaver.task.MessageCleaverScheduledTask;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * netty handler, read msg from server
 *
 * @author xuj
 * @version 1.0-SNAPSHOT
 * @since 2017-03-08-15:42
 */
public class TransportHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger log = LogManager.getLogger(TransportHandler.class);

    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("get response from server");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        executor.scheduleAtFixedRate(new MessageCleaverScheduledTask(), 0, 60, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new MessageSendScheduledTask(ctx), 10, 5, TimeUnit.SECONDS);
    }

}
