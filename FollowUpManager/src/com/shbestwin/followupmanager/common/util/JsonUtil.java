package com.shbestwin.followupmanager.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {
	//对象转换成json
	public static String objectToJson(Object obj) throws Exception {
		JSONObject jsonObject = new JSONObject();
		Class c = obj.getClass();
		Field field[] = c.getDeclaredFields();
		for (Field f : field) {
			String name = f.getName();
			Object value = invokeMethod(obj, name, null);
			jsonObject.put(name, value);
		}
		return jsonObject.toString();
	}
	//对象集合转换成json数组
	public static String objectsToJson(List<?> objs) throws Exception {
		JSONArray jsonArray = new JSONArray();
		if (null != objs && objs.size() > 0) {
			for (Object obj : objs) {
				JSONObject jsonObject = new JSONObject();
				Class c = obj.getClass();
				Field field[] = c.getDeclaredFields();
				for (Field f : field) {
					String name = f.getName();
					Object value = invokeMethod(obj, name, null);
					jsonObject.put(name, value);
				}
				jsonArray.put(jsonObject);
			}
		}
		return jsonArray.toString();
	}
	//json数组转换成对象集合
	public static List jsonToObjects(String jsonStr, Class clazz)
			throws Exception {
		List list = null;
		JSONArray jsonArray = new JSONArray(jsonStr);
		if (null != jsonArray && jsonStr.length() > 0) {
			list = new ArrayList();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jobj = jsonArray.getJSONObject(i);
				Object obj = clazz.newInstance();
				Field field[] = clazz.getDeclaredFields();
				if (null != field && field.length > 0) {
					for (Field f : field) {
						String name = f.getName();
						f.setAccessible(true);
						try{
							f.set(obj, jobj.get(name));
						}catch (Exception e) {
						}
					}
				}
				list.add(obj);
			}
		}
		return list;
	}
	//json转换成对象
	public static Object jsonToObject(String jsonStr, Class clazz)
			throws Exception {
		JSONObject jobj = new JSONObject(jsonStr);
		Object obj = clazz.newInstance();
		Field field[] = clazz.getDeclaredFields();
		if (null != field && field.length > 0) {
			for (Field f : field) {
				String name = f.getName();
				f.setAccessible(true);
				try{
					f.set(obj, jobj.get(name));
				}catch (Exception e) {
				}
			}
		}
		return obj;
	}
	
	private static Object invokeMethod(Object obj, String propertyName,
			Object[] args) throws Exception {
		Class c = obj.getClass();
		String methodName = propertyName.substring(0, 1).toUpperCase()
				+ propertyName.substring(1);
		Method method = c.getMethod("get" + methodName);
		return method.invoke(obj);
	}
}
