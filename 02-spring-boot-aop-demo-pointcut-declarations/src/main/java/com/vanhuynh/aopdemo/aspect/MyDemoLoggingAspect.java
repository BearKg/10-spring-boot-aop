package com.vanhuynh.aopdemo.aspect;

import com.vanhuynh.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution (* com.vanhuynh.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n========>>> Executing @Around on method: " + method);
        // get begin timestamp
        long begin = System.currentTimeMillis();
        // now, let's execute the method
        Object result = null;
        try {
            result =  theProceedingJoinPoint.proceed();
        } catch (Exception exc) {
            // handle exception to hide error
            System.out.println(exc.getMessage());

            result = "Major accident! But no worries, your private AOP helicopter is on the way!";
        }
        // get end timestamp
        long end = System.currentTimeMillis();
        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n==========>>> Duration: "+ duration / 1000.0 + " seconds");
        return result;
    }

    @After("execution (* com.vanhuynh.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("==========>> After (finally) on method: " + method);

    }

    @AfterThrowing(
            pointcut = "execution (* com.vanhuynh.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc ) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("==========>> After Throwing on method: " + method);

        // log the exception
        System.out.println("==========>> The exception is: " + theExc);
    }


    @AfterReturning(
            pointcut = "execution (* com.vanhuynh.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======> Executing @AfterReturning on method: " + method);
        System.out.println("\n======> result is: " + result);

        convertAccountNamesToUpperCase(result);

        System.out.println("\n======> result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // loop through accounts
        for (Account account : result) {

            // get uppercase version of name
            String theUpperName = account.getName().toUpperCase();
            // update the name on the account
            account.setName(theUpperName);
        }
    }


    // let's start with a @Before advice
    @Before("com.vanhuynh.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n========>>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " +methodSignature);

        // display method arguments
        Object[] args = theJoinPoint.getArgs();

        for(Object arg: args) {
            System.out.println(arg);
            if(arg instanceof Account) {
                Account theAccount = (Account) arg;

                System.out.println("Account name: " + theAccount.getName());
                System.out.println("Account level: " + theAccount.getLevel());
            }
        }

    }


}
