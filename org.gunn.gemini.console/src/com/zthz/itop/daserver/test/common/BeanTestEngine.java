package com.zthz.itop.daserver.test.common;


import java.io.File;
import java.net.URL;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.Invocable;
import javax.script.ScriptException;
import org.apache.commons.lang.StringUtils; 
import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl;

public class BeanTestEngine {
	
	private ScriptEngine engine;

	public void init(){
		engine = new GroovyScriptEngineImpl();
	}
	
	public Object run(String code , String functionName , Map<String,Object> para){
		if( engine != null ){
			try {
				engine.eval(code);
				for(String key : para.keySet()){
					engine.put(key, para.get(key));
				}
				
				if( StringUtils.isNotEmpty(functionName) ){
					return ((Invocable)engine).invokeFunction(functionName, null);
				}
			} catch (ScriptException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public Object run(File sourceFile){
		return null;
	}
	
	public Object run(URL sourceUrl){
		return null;
	}
	
}
