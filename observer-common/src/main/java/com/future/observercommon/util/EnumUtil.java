package com.future.observercommon.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class EnumUtil {

    /**
     * 根据枚举项的name值获取对应的枚举项
     */
    public static <T> T valueOf(Class<T> cls, String name) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method values = cls.getDeclaredMethod("values");
        values.setAccessible(true);

        T[] ts = (T[]) values.invoke(null);
        for (T t : ts) {
            Method getName = cls.getDeclaredMethod("getName");
            if (name.equals(getName.invoke(t).toString())) {
                return t;
            }
        }
        return null;
    }

    /**
     * 根据多个枚举项的name值返回枚举列表
     */
    public static <T> List<T> getEnums(Class<T> cls, String... names) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<T> enums = new ArrayList<>();

        for (String name : names) {
            enums.add(valueOf(cls, name));
        }

        return enums;
    }
}
