package com.sawelly.fpog.learn.jvm;

import com.sawelly.fpog.controller.IndexController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    private Logger log = LoggerFactory.getLogger(Test2.class);
    public static int j = 0;
    public static void main(String[] args) {
        plus();
    }

    private static void plus() {
        j++;
        plus();
    }

}
class TestCase{



}
