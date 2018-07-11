package com.sawelly.fpog.learn.jvm;

import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static int j = 0;
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();//返回Java虚拟机试图使用的最大内存量。
        Long totalMemory = Runtime. getRuntime().totalMemory();//返回Java虚拟机中的内存总量。
        System.out.println("MAX_MEMORY ="+maxMemory +"(字节)、"+(maxMemory/(double)1024/1024) + "MB");
        System.out.println("TOTAL_ MEMORY = "+totalMemory +"(字节)"+(totalMemory/(double)1024/1024) + "MB");
        ArrayList list=new ArrayList();

//        while(true)
//
//        {
//            j++;
//            list.add(new Test());
//            System.out.println("==============="+j);
//
//        }
        new Test().test();


    }

    private void test() {
        j++;
        System.out.println("==============="+j);
        test();

    }

    private static int aa(int i) {
        System.out.println("====================="+i);
        return i;
    }
}
