package com.lianxin.autotest.service;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;
/**
 * Title:
 * Description:
 * Company: http://www.biyouxinli.com/
 *
 * @author zhangxl@biyouxinli.com
 * @date Created in 13:58 2020/7/23
 */
public class TestNGRunService {
    public static void TestNgRun() {
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<String>();
        String TestNGPath =  "/root/job/testng.xml";
//        suites.add("E:/job/autoTestWeb/autoTestWeb/lianxin-autoTest/testng.xml");
        suites.add(TestNGPath);
        testng.setTestSuites(suites);
        testng.run();
    }

}
