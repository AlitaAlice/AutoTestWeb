package com.lianxin.api.command.impl;


import com.lianxin.api.beans.ApiDataBean;
import com.lianxin.api.command.CommandConstant;

/**
 * Title:
 * Description:
 * Company: http://www.biyouxinli.com/
 *
 * @author zhangxl@biyouxinli.com
 * @date Created in 14:50 2020/7/20
 */
public class SelectCommandExecutor extends AbstractCommandExecutor {
    @Override
    public ApiDataBean exec(String command, String input, String choose, String verify) {
        ApiDataBean apiDataBean = new ApiDataBean();
        apiDataBean.setRun(true);
        apiDataBean.setDesc("连小信接口");
        apiDataBean.setUrl("get");
        apiDataBean.setMethod("post");
        String apiParam = null;
        apiParam = "{\n" +
                "    \"token\": \"${token}\",\n" +
                "    \"channel\": \"${channel}\",\n" +
                "    \"currentChat\": \"${currentChat}\",\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "            \"result\": \"${ItemId}\",\n" +
                "            \"replyType\": \"01\",\n" +
                "            \"dialogId\": \"${dialogId}\"\n" +
                "        }\n" +
                "    ]\n" +
                "}\n";
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

//        if (choose == null|choose=="-1") {
//            apiDataBean.setSave("currentChat=$.appdata.chatId;dialogId=$.appdata.dialogs.dialogId[0];");
//        } else {
//            apiDataBean.setSave("currentChat=$.appdata.chatId;dialogId=$.appdata.dialogs.dialogId[0];result=$.appdata.dialogs.replys[0].itemId[" + choose.trim() + "]");
//        }
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


    @Override
    public String getCommand() {
        return CommandConstant.SELECT;
    }
}
