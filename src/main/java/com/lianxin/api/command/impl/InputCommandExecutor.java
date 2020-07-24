package com.lianxin.api.command.impl;


import com.lianxin.api.beans.ApiDataBean;
import com.lianxin.api.command.CommandConstant;

/**
 * Title:
 * Description:
 * Company: http://www.biyouxinli.com/
 *
 * @author zhangxl@biyouxinli.com
 * @date Created in 14:47 2020/7/20
 */
public class InputCommandExecutor extends AbstractCommandExecutor {


    @Override
    public String getCommand() {
        return CommandConstant.INPUT;
    }


    @Override
    public ApiDataBean exec(String command, String input, String choose, String verify) {
        ApiDataBean apiDataBean = new ApiDataBean();
        apiDataBean.setRun(true);
        apiDataBean.setDesc("连小信接口");
        apiDataBean.setUrl("get");
        apiDataBean.setMethod("post");

        String apiParam = null;
        apiParam = "{\n" +
                "   \"token\": \"${token}\",\n" +
                "    \"channel\": \"${channel}\",\n" +
                "    \"currentChat\": \"${currentChat}\",\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "            \"result\":" +"\""+ input.trim() +"\","+
                "            \"replyType\": \"02\",\n" +
                "            \"time\": 10250,\n" +
                "            \"dialogId\": \"${dialogId}\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"currentTime\": \"2020-07-18 15:31:16\",\n" +
                "    \"timestamp\": 1595057476775\n" +
                "}";
        apiDataBean.setParam(apiParam);
        apiDataBean.setContains(false);
        apiDataBean.setStatus(0);
        /*
           save and verify
        */
        if (verify==null|verify=="-1") {
            apiDataBean.setVerify("");
        }else
        {
            apiDataBean.setVerify("$.appdata.dialogs.says.content[0]="+verify.trim());
        }


        /**
         * @Author zhangxl
         * @Description //TODO choose -> 直接命中选项
         * choose(text)->response->GiveIndexId
         * @Date 21:14 2020/7/21
         * @Param [command, input, choose, verify]
         * @return com.lianxin.api.beans.ApiDataBean
         **/
        if (choose == null|choose=="-1") {
            apiDataBean.setSave("currentChat=$.appdata.chatId;dialogId=$.appdata.dialogs.dialogId[0];");
        } else {
            apiDataBean.setSave("currentChat=$.appdata.chatId;dialogId=$.appdata.dialogs.dialogId[0];result=$.appdata.dialogs.replys");
            apiDataBean.setChoosetext(choose);
        }

        apiDataBean.setPreParam(null);
        apiDataBean.setSleep(0);
        return apiDataBean;
    }
}
