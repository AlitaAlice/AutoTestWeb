package com.lianxin.api.command;


import com.lianxin.api.beans.ApiDataBean;

/**
 * @author xiangfeng@biyouxinli.com.cn
 * @className: Command
 * @description: 命令接口
 * @date 2020/2/5
 */
public interface Command {
    ApiDataBean exec(String command, String input, String choose, String verify);
}
