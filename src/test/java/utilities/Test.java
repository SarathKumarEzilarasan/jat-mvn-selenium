package utilities;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
//        QaEnvProps qaEnvProps = new QaEnvProps();
//        qaEnvProps.init();
//        qaEnvProps.init();



        System.out.println(QaEnvProps.getProperty("url"));
    }
}
