package org.example.reflection;


public enum Types {
    BYTE,
    BOOLEAN,
    CHAR,
    INT,
    SHORT,
    FLOAT,
    LONG,
    DOUBLE,
    STRING;

    public static Types getType(Class<?> clazz) {
        String className = clazz.getSimpleName().toUpperCase();
        return Types.valueOf(className);
    }
}
