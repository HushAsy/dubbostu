package org.hhs.common;

import com.sun.jndi.toolkit.url.Uri;
import org.hhs.Hello;
import org.hhs.annation.SPI;
import sun.reflect.annotation.AnnotationType;

import java.io.*;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtensionClassLoader<T> {
    private ConcurrentHashMap<Class<T>, Object> cachedInstances = new ConcurrentHashMap();
    private static final Map<Class<?>, ExtensionClassLoader> LOADER = new ConcurrentHashMap<Class<?>, ExtensionClassLoader>();
    private static final Map<String, Class<?>> cacheClass = new ConcurrentHashMap<String, Class<?>>();
    private static final String CLASSPATH = "META-INF/dubbo";
    private Class<T> type;
    public ExtensionClassLoader(Class<T> type){
        this.type = type;
    }

    public static <T> ExtensionClassLoader<T> getExtensionClassLoader(Class<T> type){
        if (type == null){
            throw new IllegalStateException();
        }
        ExtensionClassLoader extensionClassLoader = null;
        if (isPresent(type)){
            extensionClassLoader = LOADER.get(type);
            if (extensionClassLoader == null){
                LOADER.putIfAbsent(type, new ExtensionClassLoader(type));
                extensionClassLoader = LOADER.get(type);
            }
        } else {
            //用自定义的类加载器加载；

        }
        return extensionClassLoader;
    }

    public static boolean isPresent(Class<?> type){
        return type.isAnnotationPresent(SPI.class);
    }

    public static ClassLoader getClassLoader(){
        ClassLoader classLoader = ExtensionClassLoader.class.getClassLoader();
        return classLoader;
    }

    public T getInstance(String className) throws UnsupportedEncodingException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        URL url = getClassLoader().getResource(CLASSPATH);
        InputStream inputStream = null;
        try {
            inputStream = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        Map<String, String> maps = new ConcurrentHashMap();
        try {
            String name = "";
            while ((name = bufferedReader.readLine())!=null){
                getClassPackage(name, maps);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String packageStr = maps.get(className);
        Class<?> clazz = getClassLoader().loadClass(packageStr);
        String clsName = packageStr.substring(packageStr.lastIndexOf(".")+1, packageStr.length());
        cacheClass.putIfAbsent(clsName, clazz);
        return (T) clazz.newInstance();
    }

    public static void getClassPackage(String line, Map map){
        String[] strings = line.split("=");
        map.put(strings[0], strings[1]);
    }

    public class ExtClassLoader extends ClassLoader{
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            return super.findClass(name);
        }
    }

}
