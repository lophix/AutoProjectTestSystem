package com.flag.xu.project.system.param.cast;

import com.flag.xu.project.system.exception.AnnotationConflictException;
import com.flag.xu.project.system.pojo.enums.StandardDataType;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/13.
 */
public class ByteCast extends ParamCast {
    @Override
    public Object paramCast(Field field, Element foo, Method method) throws AnnotationConflictException {
        Class fieldType = field.getType();
        if (fieldType.equals(StandardDataType.BYTE.getType()) || fieldType.equals(StandardDataType.BYTE_CLASS.getType())) {
            Byte param = Byte.valueOf(foo.elementText(getParamName(field, method)) == null ? "0" : foo.elementText(getParamName(field, method)));
            return param;
        } else {
            return new CharacterCast().paramCast(field, foo, method);
        }
    }
}
