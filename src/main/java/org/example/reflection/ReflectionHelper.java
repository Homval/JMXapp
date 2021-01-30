package org.example.reflection;

import java.lang.reflect.Field;

public class ReflectionHelper {
    public static Object createInstance(String className) {
        try{
            return Class.forName(className).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldValue(Object object, String fieldName, String fieldValue) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            Types types = Types.getType(field.getType());
            switch (types) {
                case INT:
                    field.set(object, Integer.valueOf(fieldValue));
                    break;
                case BYTE:
                    field.set(object, Byte.valueOf(fieldValue));
                    break;
                case CHAR:
                    field.set(object, fieldValue.charAt(0));
                    break;
                case BOOLEAN:
                    field.set(object, Boolean.valueOf(fieldValue));
                    break;
                case SHORT:
                    field.set(object, Short.valueOf(fieldValue));
                    break;
                case DOUBLE:
                    field.set(object, Double.valueOf(fieldValue));
                    break;
                case FLOAT:
                    field.set(object, Float.valueOf(fieldValue));
                    break;
                case LONG:
                    field.set(object, Long.valueOf(fieldValue));
                    break;
                case STRING:
                    field.set(object, fieldValue);
                    break;
            }
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
