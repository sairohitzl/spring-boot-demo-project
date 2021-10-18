package com.springboot.moviescrud.aspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Expressions {
    @Pointcut("execution(* com.springboot.moviescrud.service.*.find*(..))")
    public void find()
    {

    }
    @Pointcut("execution(* com.springboot.moviescrud.service.*.save*(..))")
    public void save()
    {

    }
    @Pointcut("execution(* com.springboot.moviescrud.service.*.delete*(..))")
    public void delete()
    {

    }
}
