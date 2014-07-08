package alice.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;


/**
 * Custom Dynamic URLCLassLoader used to add/remove dynamically URLs from it
 * needed by JavaLibrary.
 * 
 * @author Michele Mannino
 * 
 */

public class DynamicURLClassLoader extends ClassLoader{
	private ArrayList<URL> listURLs = null;
	private Hashtable<String, Class<?>> classCache = new Hashtable<String, Class<?>>();
	
    /**
	 * DynamicURLClassLoader default constructor. It uses the default SystemClassLoader.
	 */

	public DynamicURLClassLoader()
	{
		super(DynamicURLClassLoader.class.getClassLoader());
		listURLs = new ArrayList<URL>();
	}
	
    /**
     * Constructor that specifies the URLs array.
	 * @param URL[] urls - Used to load a directory a URL ends with "/" or "\"
	 * otherwise it loads a class contained into a .jar
	 */

	public DynamicURLClassLoader(URL[] urls)
	{
		super(DynamicURLClassLoader.class.getClassLoader());
		listURLs = new ArrayList<URL>(Arrays.asList(urls));
	}
	
    /**
     * DynamicURLClassLoader constructor specifies the URLs array and the parent ClassLoader.
	 * @param urls - The URLs array.
	 * @param parent - ClassLoader parent.
	 */

	public DynamicURLClassLoader(URL[] urls, ClassLoader parent)
	{
		super(parent);
		listURLs = new ArrayList<URL>(Arrays.asList(urls));
	}
	
    /**
	 * Load class method specified by className parameter.
	 * @param className - The class name used to load the class needed.
	 */

	public Class<?> loadClass(String className) throws ClassNotFoundException {  
        return findClass(className);  
	}
	
	/**
	 * Find class method specified by className parameter.
	 * @param className - The class name used to find the class needed.
	 */
	
	public Class<?> findClass(String className) throws ClassNotFoundException {  
	    Class<?> result = null;  
	    String classNameReplaced = className.replace(".", File.separator);
	    
	    result = (Class<?>) classCache.get(className);  
	    if (result != null)  
	        return result;  
	    try {
			return findSystemClass(className);
		} catch (ClassNotFoundException e) {
			
		} 
	    for (URL aURL : listURLs) {
	    	try {
	    		InputStream is = null;
	    		byte[] classByte = null;
	    		
	    		
	    		if(aURL.toString().endsWith(".jar"))
	    		{
	    			aURL = new URL("jar", "", aURL + "!/" + classNameReplaced + ".class");
	    			is = aURL.openConnection().getInputStream();
	    		}
	    		
	    		if(aURL.toString().indexOf("/", aURL.toString().length() - 1) != -1)
	    		{
	    			aURL = new URL(aURL.toString() + classNameReplaced + ".class");
	    			is = aURL.openConnection().getInputStream();
	    		}
	    		
	    		classByte = getClassData(is);
	            try {
	            	result = defineClass(className, classByte, 0, classByte.length, null);  
	        		classCache.put(className, result);
	        		
				} catch (SecurityException e) {
					result = super.loadClass(className);
				}
	            return result;  
	    	} catch (Exception e) {
//	    		e.printStackTrace();
	    	}
	    }
	    throw new ClassNotFoundException(className);
	}  
	
	private byte[] getClassData(InputStream is) throws IOException
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		int nextValue= is.read();  
        while (-1 != nextValue) {  
            byteStream.write(nextValue);  
            nextValue = is.read();  
        }
        is.close();
        return byteStream.toByteArray();
        
        
	}
	
	/**
	 * Add array URLs method.
	 * @param urls - URLs array.
	 */

	public void addURLs(URL[] urls) throws MalformedURLException
	{
		if(urls == null)
			throw new IllegalArgumentException("Array URLs must not be null.");
		for (URL url : urls) {
			if(!listURLs.contains(url))
				listURLs.add(url);
		}
	}
	
	/**
	 * Remove array URLs method.
	 * @param urls - URL to be removed.
	 */

	public void removeURL(URL url) throws IllegalArgumentException
	{
		if(!listURLs.contains(url))
			throw new IllegalArgumentException("URL: " + url + "not found.");
		listURLs.remove(url);
	}
	
	/**
	 * Remove all URLs contained into URLs array param.
	 * 
	 * @param urls - Array urls to be deleted.
	 */
	public void removeURLs(URL[] urls) throws IllegalArgumentException
	{
		if(urls == null)
			throw new IllegalArgumentException("Array URLs must not be null.");
		for (URL url : urls) {
			if(!listURLs.contains(url))
				throw new IllegalArgumentException("URL: " + url + "not found.");
			listURLs.remove(url);
		}
	}
	
	/**
	 * Remove all URLs cached.
	 */
	
	public void removeAllURLs()
	{
		if(!listURLs.isEmpty())
			listURLs.clear();
	}
	
	/**
	 * Get all URLs cached.
	 */

	public URL[] getURLs()
	{
		URL[] result = new URL[listURLs.size()];
		listURLs.toArray(result);
		return result;
	}
	
	/**
	 * Get all loaded class stored into the class cache.
	 */

	public Class<?>[] getLoadedClasses()
	{
		Class<?>[] result = new Class<?>[classCache.size()];
		int i = 0;
		for (Class<?> aClass : classCache.values()) {
			result[i] = aClass;
		}
		return result;
	}
	
	/**
	 * Clear all class cached.
	 */

	public void clearCache()
	{
		classCache.clear();
	}
	
	/**
	 * Remove a Class from the cache named className.
	 * It does not unload the class, but it only remove it from the cache.
	 * @param className - Class name.
	 */

	public void removeClassCacheEntry(String className)
	{
		classCache.remove(className);
		
	}
	
	/**
	 * Add class into cache.
	 * @param cls - Class instance.
	 */

	public void setClassCacheEntry(Class<?> cls)
	{
		if(classCache.contains(cls))
			classCache.remove(cls.getName());
		classCache.put(cls.getName(), cls);
	}
}
