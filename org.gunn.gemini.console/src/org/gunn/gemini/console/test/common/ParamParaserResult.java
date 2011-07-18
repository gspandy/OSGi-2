package org.gunn.gemini.console.test.common;

/**
 * @author Jet Geng
 *
 */
public class ParamParaserResult {

	private TestCommandParamHandle.CommandType type;
	private String result;
	
	public TestCommandParamHandle.CommandType commandType (){
		return type;
	}
	
	public String getScriptCode(){
		return result;
	}
	
	public String getScriptFileUrl(){
		return result;
	}

	public void setType(TestCommandParamHandle.CommandType type) {
		this.type = type;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	
}
