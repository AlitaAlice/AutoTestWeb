package com.lianxin.autotest.service;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Title:
 * Description:
 * Company: http://www.biyouxinli.com/
 *
 * @author zhangxl@biyouxinli.com
 * @date Created in 9:54 2020/7/23
 */
public class HttpRunJobService {
    public static void main(String[] args) throws IOException {
        String url = "http://127.0.0.1:1099/job/";
        String JksjobName = "autoTest";
        String ADMIN_TOKEN_STRING = "123456";

        String jksjob_url_param = url
                + JksjobName
                + "/buildWithParameters?token="
                + ADMIN_TOKEN_STRING;
        String jksjob_url= url
                + JksjobName
                + "/build?token="
                + ADMIN_TOKEN_STRING;
        System.out.println("当前发送的请求为:" + jksjob_url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(jksjob_url);
        CloseableHttpResponse response = httpClient.execute(httpget);
        System.out.println("返回值状态码为"
                + response.getStatusLine().getStatusCode());
        // 返回码为201，表示“请求成功”
        if (response.getStatusLine().getStatusCode() == 201) {
            System.out.println("已发送到jenkins服务器");
                    response.close();
        }
    }
}
