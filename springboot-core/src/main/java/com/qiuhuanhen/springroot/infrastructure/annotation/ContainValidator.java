package com.qiuhuanhen.springroot.infrastructure.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
* Description: 自定义注解功能实现 <br/>
* date: 2021/12/6 22:43<br/>
* @author qkj <br/>
* @since JDK 1.8
*/
public class ContainValidator implements ConstraintValidator<Contain,String> {
    private String code;
    @Override
    public void initialize(Contain constraintAnnotation) {
        this.code = constraintAnnotation.code();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        String[] codes = code.split(";"); boolean flag = false;
        if(object ==null){
            return true;
        }
        for(String s : codes){
            if(s.equals(object)){
                flag = true;
            }
        }
        return flag;
    }

}