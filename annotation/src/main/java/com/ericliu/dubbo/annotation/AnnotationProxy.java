package com.ericliu.dubbo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AnnotationProxy {
    private Map<String, Object> classMap = new HashMap<>();
    private Map<String, Method> methodMap = new HashMap<>();


    public void proxy(Class clazz) throws IllegalAccessException, InstantiationException {
        Annotation annotation = clazz.getAnnotation(CustomClassAnnotation.class);
        if (annotation != null) {
            Object o = clazz.newInstance();
            classMap.put(clazz.getName(), o);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                Annotation methodAnnotation = method.getAnnotation(CustomMethodAnnotation.class);
                if (methodAnnotation != null) {
//                    method.invoke(o, args);
                    methodMap.put(((CustomMethodAnnotation) methodAnnotation).value(), method);
                    System.out.println("complete init " + clazz + "." + method + "()");
                }
            }
        }
        System.out.println("complete init clazz:" + clazz);
    }

    public Object invoke(String classKey, String value, Object[] args) throws InvocationTargetException, IllegalAccessException {
        Object obj = classMap.get(classKey);

        Method method = methodMap.get(value);
        if (method != null) {
           return method.invoke(obj, args);
        }

        return null;
    }


}
