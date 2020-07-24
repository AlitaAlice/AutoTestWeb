package com.lianxin.autotest.controller;

import com.lianxin.autotest.service.TestNGRunService;
import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.GeneralSecurityException;

import com.little.utils.io.Email;

import javax.mail.MessagingException;

import static com.lianxin.autotest.service.TestNGRunService.TestNgRun;
import static io.github.biezhi.ome.OhMyEmail.SMTP_ENT_QQ;

/**
 * Title:
 * Description:
 * Company: http://www.biyouxinli.com/
 *
 * @author zhangxl@biyouxinli.com
 * @date Created in 17:19 2020/7/20
 */
@Controller
@RequestMapping("/autotest")
public class AutoTestController {
    private String prefix = "autotest";
    private static String staticEmail = null;

    @GetMapping("")
    public String index() {
        return prefix + "/index";
    }


    @GetMapping("start")
    @ResponseBody
    public void start() throws GeneralSecurityException, MessagingException, SendMailException {
        TestNgRun();
        before();
//        String filepath = System.getProperty("user.dir") + "\\test-output\\index.html";
        String filepath = "/root/job/test-output/report.html";
        SendAttach(filepath,staticEmail);
    }

    @PostMapping("outExcel")
    @ResponseBody
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        //String newFileName = UUID.randomUUID().toString().substring(0, 5) + suffix;
        String newFileName = "autoTestCase4.xlsx";
        String path = "/root/job/";
//        String newFileName = "autoTestCase" + suffix;
//        String path = "E:/job/";
        File newFile = new File(path + newFileName);
        try {
            file.transferTo(newFile);
            // return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            //  return "失败";
        }
    }

    @PostMapping("outChannelToken")
    @ResponseBody
    public void outChannelToken(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        //String newFileName = UUID.randomUUID().toString().substring(0, 5) + suffix;
        String newFileName = "api-config.xml";
        String path = "/root/job/";
//        String newFileName = "autoTestCase" + suffix;
//        String path = "E:/job/";
        File newFile = new File(path + newFileName);
        try {
            file.transferTo(newFile);
            // return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            //  return "失败";
        }
    }

    public static void before() throws GeneralSecurityException {
        // 配置，一次即可
        OhMyEmail.config(SMTP_ENT_QQ(false), "zhangxl@biyouxinli.com", "Mhh8965800");
    }

    public static void SendAttach(String filepath,String staticEmail) throws MessagingException, SendMailException, GeneralSecurityException {
        before();
        OhMyEmail.subject("测试结果邮件")
                .from("zhangxl@biyouxinli.com")
                .to(staticEmail)
                .html("<h1 font=red>结果见附件</h1>")
                .attach(new File(filepath), "report.html")
                .send();
    }

    @PostMapping("email")
    @ResponseBody
    public void sendEmail(@RequestParam String email) throws GeneralSecurityException, MessagingException, SendMailException {
        staticEmail = email;
    }


}
