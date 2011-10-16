package org.gunn.gemini.console.test.common;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.Invocable;
import javax.script.ScriptException;
import org.apache.commons.lang.StringUtils; 
import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl;
import org.gunn.gemini.console.Activator;
import org.gunn.gemini.console.classloader.GunnScriptClassLoader;
import org.osgi.framework.Bundle;

public class BeanTestEngine {
	
	private ScriptEngine engine;

	public void init(){
		engine = new GroovyScriptEngineImpl();
		Bundle[] bundles = Activator.getDefault().getContext().getBundles();
		GunnScriptClassLoader classLoader = new GunnScriptClassLoader(bundles);
		((GroovyScriptEngineImpl)engine).setClassLoader(classLoader);
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
	
	private void initPara(Bindings bind ,  Map<String,Object> para ){
		for(String key : para.keySet()){
			bind.put(key, para.get(key));
		}
	}
	
	public Object run(File sourceFile ,String functionName , Map<String,Object> para ){
		try {
			FileReader reader = new FileReader(sourceFile);
			if( engine != null ){
				Bindings bindings = engine.createBindings();
				initPara(bindings , para);
				engine.eval(reader, bindings);
				return ((Invocable)engine).invokeFunction(functionName, null);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Object run(URL sourceUrl){
		return null;
	}
	
}
