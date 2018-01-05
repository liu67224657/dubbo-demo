package com.ericliu.dubbo.annotation.customobj;

import com.ericliu.dubbo.annotation.CustomClassAnnotation;
import com.ericliu.dubbo.annotation.CustomMethodAnnotation;

@CustomClassAnnotation
public class CustomOneClass {

    @CustomMethodAnnotation("/key/a")
    public void test(String code) {
        System.out.println("CustomOneClass test code" + code);
    }

    @CustomMethodAnnotation("/key/b")
    public String test1(String code) {
        System.out.println("CustomOneClass test1 code" + code);
        return code;
    }

    public String test2(String code) {
        System.out.println("CustomOneClass test2 code" + code);
        return code;
    }
}
