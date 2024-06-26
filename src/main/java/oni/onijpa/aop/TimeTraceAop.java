package oni.onijpa.aop;

import ch.qos.logback.classic.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Around("execution(* oni.onijpa..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        logger.debug("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            logger.debug("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
