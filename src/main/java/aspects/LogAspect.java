package aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Before("execution(* dao.GenericDao.borrowBook(..))")
    public void lookingForBook() {
        System.out.println("@Before -> Client is looking for a book!");
    }
}
