package org.gunn.gemini.console;

import java.util.Map;

import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.zthz.itop.daserver.test.common.BeanTestEngine;

public class Activator extends AbstractBlueprintActivator  implements CommandProvider{

	private BeanTestEngine engine;
	private Map<String , Object> scriptParam;
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
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
	
	

}
