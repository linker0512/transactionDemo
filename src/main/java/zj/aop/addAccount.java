//package zj.aop;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//
///**
// * Created by zj on 2017-3-13.
// */
//@Aspect
//@Component
//class Aop {
//
//    @Pointcut("execution(public * zj.controller.CmdController.*(..))")
//    public void webLog() {
//    }
//    @Before("webLog()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        System.out.println("point cut");
//    }
//}
