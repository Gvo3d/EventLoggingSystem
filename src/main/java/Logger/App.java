package Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Gvozd on 09.01.2016.
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"config.xml"});
    }
}
