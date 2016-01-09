package Testing;

import logger.LogSystem;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestingLogs {

    @Test
    public void testLog(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"config.xml"});
        LogSystem loggersystem = (LogSystem) ac.getBean("loggersystem");
        loggersystem.write("LogSystem testing with JUnit4 and Spring Framework");
    }
}
