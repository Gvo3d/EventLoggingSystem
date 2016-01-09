package logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Gvozd on 09.01.2016.
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"config.xml"});
        LogSystem loggersystem = (LogSystem) ac.getBean("loggersystem");
        loggersystem.write("Event FUUUUUUUUUUUUUUCK");
    }
}
