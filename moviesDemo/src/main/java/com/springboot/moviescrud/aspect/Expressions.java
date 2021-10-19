package com.springboot.moviescrud.aspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Expressions {
    @Pointcut("execution(* com.springboot.moviescrud.service.*.find*(..))")
    public void find() throws UnsupportedOperationException
    {
        // abstract methods
         // no implementation
    }
    @Pointcut("execution(* com.springboot.moviescrud.service.*.save*(..))")
    public void save() throws UnsupportedOperationException
    {
        // abstract methods
        // no implementation
    }
    @Pointcut("execution(* com.springboot.moviescrud.service.*.delete*(..))")
    public void delete() throws UnsupportedOperationException
    {
        // abstract methods
        // no implementation
    }
}
