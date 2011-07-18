package org.gunn.gemini.console.test.common;

import org.apache.commons.lang.StringUtils;
import org.eclipse.osgi.framework.console.CommandInterpreter;

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
	
	public final static String  fileUrlPattern = "file:\\/{2}(\\/[:\\w\\.]+)+";
	//private ParamParaserResult paramResult  ;
	public enum CommandType{
		ScriptCode,
		UrlCode,
		FileCode;
	}
	
	/**
	 * 用于处理输入参数
	 * @param interpreter
	 * @return
	 */
	public ParamParaserResult paraseParam(CommandInterpreter interpreter){
		StringBuffer code = new StringBuffer();
		String temp = interpreter.nextArgument();
		ParamParaserResult paramResult = new ParamParaserResult();
		if(temp.matches(fileUrlPattern)){
			paramResult.setType(CommandType.FileCode);
			paramResult.setResult(temp);
			return paramResult;
		}else{
			paramResult.setType(CommandType.ScriptCode);
		}
		while( StringUtils.isNotBlank( temp ) ){
			code.append(temp).append(" ");
			temp = interpreter.nextArgument();
		}
		paramResult.setResult(code.toString());
		return paramResult;
	}
	
	
}
