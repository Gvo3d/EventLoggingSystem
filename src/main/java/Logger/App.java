package logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"config.xml"});
        LogSystem loggersystem = (LogSystem) ac.getBean("loggersystem");
        loggersystem.write("SpringFrawork tested");
    }
}
