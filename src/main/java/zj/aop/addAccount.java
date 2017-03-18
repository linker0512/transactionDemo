package zj.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import zj.service.ServiceInterface.userService;
import zj.service.userServiceImpl;


/**
 * Created by zj on 2017-3-13.
 */
@Aspect
@Component
class Aop {

    @Autowired
    private userServiceImpl userservice;

//    @Pointcut("within(zj.controller..CmdController+)")
//    public void addModel() {
//    }
    @Pointcut("execution( * zj.controller..*(..)) ")
    public void addModel() {
    }

    @Before(value = "addModel() && args(model,..)")
    public void doBefore(Model model ) throws Throwable {
        model.addAttribute("account",userservice.currentUserName);
        System.out.println("point cut");
    }
}
