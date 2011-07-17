package com.zthz.itop.daserver.test.common;

/**
 * 用户处理调用Groovy引擎的接口。<p>
 * 命令的模式为
 * <li> ${cmd} ${url}</li>
 * <li> ${cmd} ${script code}</li>
 * 在系统中有几个内建的参数：
 * <li> <b>act</b> : 代表当前bundle的Context。通过<b>act.getBean</b> 方法可以获得平台中的受Blueprint管理的容器。
 * @author 耿宜超 
 *
 */
public class TestCommandParamHandle {
	public enum CommandType{
		ScriptCode,
		UrlCode,
		FileCode;
	}
	
	
}
