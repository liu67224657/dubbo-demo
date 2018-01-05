package com.ericliu.dubbo.annotation.customobj;

import com.ericliu.dubbo.annotation.CustomClassAnnotation;
import com.ericliu.dubbo.annotation.CustomMethodAnnotation;

@CustomClassAnnotation
public class CustomTwoClass {

    @CustomMethodAnnotation("/key/a2")
    public void test(String code) {
        System.out.println("CustomTwoClass test code" + code);
    }

    @CustomMethodAnnotation("/key/b2")
    public String test1(String code) {
        System.out.println("CustomTwoClass test1 code" + code);
        return code;
    }

    public String test2(String code) {
        System.out.println("CustomTwoClass test2 code" + code);
        return code;
    }
}
