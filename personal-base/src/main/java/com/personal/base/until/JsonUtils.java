package com.personal.base.until;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;



/**
 * 
 * Json工具类.
 * 
 * @author xuyya
 * @version 1.0, Jul 26, 2012/3:15:17 PM
 * 
 */
public class JsonUtils {


	/**
	 * 
	 * 获取普通对象的json串.
	 * 
	 * @param obj
	 *            普通对象
	 * @return
	 */
	public static String getObjectString(Object obj) {
		JSONObject json = JSONObject.fromObject(obj, packageConfig(null));
		return json.toString();
	}

	/**
	 * 
	 * 获取普通对象的json串.
	 * 
	 * @param obj
	 *            普通对象
	 * @param configs
	 *            回环控制参数
	 * @return
	 */
	public static String getObjectString(Object obj, final List<String> configs) {
		JSONObject json = JSONObject.fromObject(obj, packageConfig(configs));

		return json.toString();
	}

	/**
	 * 
	 * 获取数组对象的json串.
	 * 
	 * @param obj
	 *            数组对象
	 * @return
	 */
	public static String getArrayString(Object obj) {
		JSONArray json = JSONArray.fromObject(obj, packageConfig(null));

		return json.toString();
	}

	/**
	 * 
	 * 获取数组对象的json串.
	 * 
	 * @param obj
	 *            数组对象
	 * @param configs
	 *            回环控制参数
	 * @return
	 */
	public static String getArrayString(Object obj, final List<String> configs) {
		JSONArray json = JSONArray.fromObject(obj, packageConfig(configs));

		return json.toString();
	}

	/**
	 * 
	 * 把json字符串转换成普通对象.
	 * 
	 * @param jsonString
	 *            json字符串
	 * @param clz
	 *            普通对象类型
	 * 
	 * @return 普通对象
	 */
	public static <T extends Object> T toObject(String jsonString, Class<? extends T> clz) {
		return toObject(jsonString, clz, null);
	}

	/**
	 * 
	 * 把json字符串转换成普通对象.
	 * 
	 * @param jsonString
	 *            json字符串
	 * @param objectClass
	 *            普通对象类型
	 * @param classMap
	 *            复杂属性类型 如果是集合，则为集合中元素类型，例如： List[Driver] drivers = new ArrayList[Driver]()，设置认为：
	 *            classMap.put("drivers", Driver.class)
	 * 
	 * @return 普通对象
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T toObject(String jsonString, Class<? extends T> retClssz, Map<String, Class<?>> classMap) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return (T)JSONObject.toBean(jsonObject, retClssz, classMap);
	}
	
	/**
	 * 
	 * 把json字符串转换成数组对象.
	 * 
	 * @param jsonString
	 *            json字符串
	 * @param objectClass
	 *            数组元素对象类型
	 * @param classMap
	 *            复杂属性类型 如果是集合，则为集合中元素类型，例如： List[Driver] drivers = new ArrayList[Driver]()，设置认为：
	 *            classMap.put("drivers", Driver.class)
	 * 
	 * @return 数组对象
	 */
	public static Object toArray(String jsonString, Class<?> objectClass) {
		return toArray(jsonString, objectClass, null);
	}

	/**
	 * 
	 * 把json字符串转换成数组对象.
	 * 
	 * @param jsonString
	 *            json字符串
	 * @param objectClass
	 *            数组元素对象类型
	 * @param classMap
	 *            复杂属性类型 如果是集合，则为集合中元素类型，例如： List[Driver] drivers = new ArrayList[Driver]()，设置认为：
	 *            classMap.put("drivers", Driver.class)
	 * 
	 * @return 数组对象
	 */
	public static Object toArray(String jsonString, Class<?> objectClass, Map<String, Class<?>> classMap) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return JSONArray.toArray(jsonArray, objectClass, classMap);
	}

	/**
	 * 
	 * 把json字符串转换成集合对象.
	 * 
	 * @param jsonString
	 *            json字符串
	 * @param objectClass
	 *            集合元素对象类型
	 * 
	 * @return 集合对象
	 */
	public static Object toCollection(String jsonString, Class<?> objectClass) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return JSONArray.toCollection(jsonArray, objectClass);
	}

	/**
	 * 
	 * 封装参数.
	 * 
	 * @param configs
	 *            外部传入参数
	 * @return jsonconfig
	 */
	private static JsonConfig packageConfig(final List<String> configs) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {"handler", "hibernateLazyInitializer"});
		// 添加此句可以解决hibernate查询出的对象转成json串的错误
		config.registerJsonValueProcessor(Date.class, JsonLibDateProcessor.instance);
		config.registerJsonValueProcessor(Timestamp.class, JsonLibTimestampProcessor.instance);
		
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (configs != null && configs.contains(name)) {
					return true;
				}
				return false;
			}

		});

		return config;
	}

	/**
	 * 
	 * 获取对象的json格式化字符串.
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
//	public static String getFormatterString(Object obj) {
//		String jsonString = null;
//		JSONValue jsonValue = null;
//		try {
//
//			jsonValue = JSONMapper.toJSON(obj);
//			jsonString = jsonValue.render(true);
//
//		} catch (MapperException e) {
//			logger.error("", e);
//		}
//
//		return jsonString;
//	}
	public static void main(String[] args) {
		String str="[\"a\"]";
		String strs=str.substring(str.indexOf("[")+1, str.indexOf("]"));
		String[] strArrays=strs.split(",");
		System.out.println(strArrays);
	}

}
