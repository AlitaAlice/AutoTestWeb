package com.lianxin.api.command.impl;


import com.lianxin.api.command.Command;

/**
 * Title:
 * Description:
 * Company: http://www.biyouxinli.com/
 *
 * @author zhangxl@biyouxinli.com
 * @date Created in 20:30 2020/7/20
 */
public abstract class AbstractCommandExecutor implements Command {

    public abstract String getCommand();

}
