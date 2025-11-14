package com.vanhuynh.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {
    @Pointcut("execution(* com.vanhuynh.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    // create pointcut for getter method
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..)")
    public void getter() {}

    // create pointcut for setter method
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..)")
    public void setter() {}

    // create pointcut for getter methods:
    @Pointcut("forDaoPackage() && !getter() && !setter()")
    public void forDaoPackageNoGetterSetter() {}
}
