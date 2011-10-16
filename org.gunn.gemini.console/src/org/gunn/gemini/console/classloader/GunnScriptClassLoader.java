package org.gunn.gemini.console.classloader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import groovy.lang.GroovyClassLoader;
import org.osgi.framework.Bundle;

/**
 * 将作为GroovyScript的ClassLoader。
 * <p>
 * 整个平台中用一个这个ClassLoader应该就够了。
 * 
 * @author gengjet
 * 
 */
public class GunnScriptClassLoader extends GroovyClassLoader {

    private Bundle[] bundles ;
    private final Map< String, Class< ? > > cachedClasses = new HashMap<String,Class<?>>();

    
    public GunnScriptClassLoader(Bundle[] bundles) {
        this.bundles = bundles;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.ClassLoader#loadClass(java.lang.String)
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return askBundles(convertToJavaName(name));
    }
    
    private Class<?> askBundles (final String name) throws ClassNotFoundException{
        synchronized(cachedClasses) {
            System.out.println(name);
            if( cachedClasses.containsKey(name)) {
                return cachedClasses.get(name);
            }
            
            for(final Bundle bundle :bundles) {
                try {
                    final Class<?> clazz = bundle.loadClass(name);
                    if( clazz == null ) {
                        continue;
                    }
                    cachedClasses.put(name, clazz);
                    return clazz;
                }catch(final ClassNotFoundException cnfe ) {}
            }
            throw new ClassNotFoundException( "Class " + name + " not found in bundles: " +  bundles  );
        }
    }

    @Override
    protected Class< ? > findClass( final String name )
    throws ClassNotFoundException
    {
        return askBundles( convertToJavaName(name ));
    }
    
    private String convertToJavaName(String name) {
        String javaName = name.replaceAll("\\$", ".");
        //去掉前面的java.lang
        javaName = javaName.substring(10);
        return name;
    }

}
