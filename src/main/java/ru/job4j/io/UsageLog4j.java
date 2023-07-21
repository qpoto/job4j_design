package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j  {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        String name = "Petr Arsentev";
        int age = 33;
        boolean boss = true;
        byte foot = 43;
        char sex = 'm';
        short weight = 82;
        long iq = 100500;
        double height = 183.3;
        float exp = 20000000.2f;
        LOG.debug("User info "
                        + "name : {}, "
                        + "age : {}, "
                        + "boss : {}, "
                        + "foot : {}, "
                        + "sex : {}, "
                        + "weight : {}, "
                        + "iq : {}, "
                        + "height : {}, "
                        + "exp : {}",
                name, age, boss, foot, sex, weight, iq, height, exp);
    }
}