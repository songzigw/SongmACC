package cn.songm.acc.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("loggerAspect")
public class LoggerAspect {

    private static final Logger LOG = LoggerFactory
            .getLogger(LoggerAspect.class);

    public void before(JoinPoint point) {
        String cName = point.getTarget().getClass().getName();
        String mName = point.getSignature().getName();
        Object[] args = point.getArgs();
        LOG.info(cName + "." + mName + " Arguments: {}", Arrays.toString(args));
    }

    public void afterReturning(JoinPoint point, Object result) {
        String cName = point.getTarget().getClass().getName();
        String mName = point.getSignature().getName();
        LOG.info(cName + "." + mName + " Return: {}", result);
    }

    public void after(JoinPoint point, long runtime) {
        String cName = point.getTarget().getClass().getName();
        String mName = point.getSignature().getName();
        LOG.info(cName + "." + mName + " Runtime: {}", runtime);
    }

    public void afterThrowing(JoinPoint point, Throwable e) {
        String cName = point.getTarget().getClass().getName();
        String mName = point.getSignature().getName();
        LOG.error(cName + "." + mName + " Exception: ", e);
    }

    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long start = System.currentTimeMillis();

        try {
            this.before(point);
            result = point.proceed();
            this.afterReturning(point, result);
        } catch (Throwable e) {
            this.afterThrowing(point, e);
            throw e;
        } finally {
            this.after(point, System.currentTimeMillis() - start);
        }

        return result;
    }
}
