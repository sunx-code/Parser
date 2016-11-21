package com.sunx.parser;

import org.jsoup.select.Elements;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ext {
	public static Object extractor(Elements ele, String method, String key){
		Method func = null;
		try {
			if(key == null || "".equals(key)){
				func = ele.getClass().getDeclaredMethod(method);
				return func.invoke(ele);
			}else{
				func = ele.getClass().getDeclaredMethod(method,String.class);
				return func.invoke(ele, key);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String extractor(String dom,String regex){
		if(dom == null || "".equals(dom))return null;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(dom.trim());
		if(m.find())return m.group(1);
		return null;
	}
}
