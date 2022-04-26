package net.renfei.validator.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FieldUtil
 *
 * @author renfei
 */
public class FieldUtil {
    public List<Field> getAllField(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> allFields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        Class<?> clazzSuper = clazz.getSuperclass();
        while (clazzSuper != Object.class) {
            allFields.addAll(Arrays.asList(clazzSuper.getDeclaredFields()));
            clazzSuper = clazzSuper.getSuperclass();
        }
        allFields.forEach(field -> {
            // 设置字段可访问， 否则无法访问private修饰的变量值
            field.setAccessible(true);
        });
        return allFields;
    }
}
