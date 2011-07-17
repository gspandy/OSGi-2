package org.gunn.gemini.console;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.zthz.itop.daserver.test.common.BeanTestEngine;

public class Activator extends AbstractBlueprintActivator  implements CommandProvider{

	private BeanTestEngine engine;
	private Map<String , Object> scriptParam;
	
	
	public Activator(){
		scriptParam = new HashMap<String, Object>();
		scriptParam.put("act", this);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
		
		engine = new BeanTestEngine();
		engine.init();
		
		getContext().registerService(CommandProvider.class.getName(), this, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		super.stop(bundleContext);
	}

	@Override
	public String getHelp() {
		String helptips = Messages.Activator_Help;
		return helptips;
	}
	
	
	/**
	 * 通过Groovy代码来查看bean的内容。
	 * @param interpreter
	 * @return
	 */
	public Object _invokeGV( CommandInterpreter interpreter){
		StringBuffer code = new StringBuffer();
		String temp = interpreter.nextArgument();
		while( StringUtils.isNotBlank( temp ) ){
			code.append(temp).append(" ");
			temp = interpreter.nextArgument();
		}
		
		System.out.println( engine.run(getCommandCode(code.toString()), "adapter", getParam()) );
		return null;
	}
	
	private String getCommandCode(String arg){
		StringBuffer strBuff = new StringBuffer(" def adapter(){").append("\n\r");
		strBuff.append(arg);
		strBuff.append("}");
		return strBuff.toString();
	}
	
	private Map<String,Object> getParam(){
		return scriptParam;
	}
	

}
