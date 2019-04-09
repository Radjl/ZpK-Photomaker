package ServiceAspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(* Photo.Connector2.Start(..))")
    private void all(){}

    @Pointcut("execution(* Photo.Connector2.Start(..))")
    private void selectGetName(){}

    @After("all()")
    public void log(){
        logger.info("Connector2 start method executed...");
    }








}
