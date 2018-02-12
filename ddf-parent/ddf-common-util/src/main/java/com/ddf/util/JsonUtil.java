package com.ddf.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
public class JsonUtil {
	//builder.serializeNulls()方法可以不忽略null值属性
    
	public static final String EMPTY = "";
	/** 空的 {@code JSON} 数据 - <code>"{}"</code>。 */
	public static final String EMPTY_JSON = "{}";
	/** 空的 {@code JSON} 数组(集合)数据 - {@code "[]"}。 */
	public static final String EMPTY_JSON_ARRAY = "[]";
	/** 默认的 {@code JSON} 日期/时间字段的格式化模式。 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** {@code Google Gson} 的 {@literal @Since} 注解常用的版本号常量 - {@code 1.0}。 */
	public static final Double SINCE_VERSION_10 = 1.0d;
	/** {@code Google Gson} 的 {@literal @Since} 注解常用的版本号常量 - {@code 1.1}。 */
	public static final Double SINCE_VERSION_11 = 1.1d;
	/** {@code Google Gson} 的 {@literal @Since} 注解常用的版本号常量 - {@code 1.2}。 */
	public static final Double SINCE_VERSION_12 = 1.2d;

	
	/**
	 * 创建Gson
	 */
	private static Gson createGson(boolean isSerializeNulls, Double version,String datePattern, boolean excludesFieldsWithoutExpose
			,boolean underLineJson){
		GsonBuilder builder = new GsonBuilder();
		if (isSerializeNulls){
			builder.serializeNulls();
		}
		if (version != null){
			builder.setVersion(version.doubleValue());
		}
		if (StringUtil.isEmpty(datePattern)){
			datePattern = DEFAULT_DATE_PATTERN;
		}
		builder.setDateFormat(datePattern);
		if (excludesFieldsWithoutExpose){
			builder.excludeFieldsWithoutExposeAnnotation();
		}
		if (underLineJson){
			builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		}
		return builder.create();
	}
	/**
	 * 将给定的目标对象根据指定的条件参数转换成 {@code JSON} 格式的字符串。
	 * <p />
	 * <strong>该方法转换发生错误时，不会抛出任何异常。若发生错误时，曾通对象返回 <code>"{}"</code>； 集合或数组对象返回
	 * <code>"[]"</code></strong>
	 * 
	 * @param target
	 *            目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @param isSerializeNulls
	 *            是否序列化 {@code null} 值字段。
	 * @param version
	 *            字段的版本号注解。
	 * @param datePattern
	 *            日期字段的格式化模式。
	 * @param excludesFieldsWithoutExpose
	 *            是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	private static String toJson(Object target, Type targetType, boolean isSerializeNulls, Double version,
			String datePattern, boolean excludesFieldsWithoutExpose,boolean underLineJson) {
		if (target == null)
			return EMPTY_JSON;
		/*GsonBuilder builder = new GsonBuilder();
		if (isSerializeNulls){
			builder.serializeNulls();
		}
		if (version != null){
			builder.setVersion(version.doubleValue());
		}
		if (StringUtil.isEmpty(datePattern)){
			datePattern = DEFAULT_DATE_PATTERN;
		}
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
		builder.setDateFormat(datePattern);
		if (excludesFieldsWithoutExpose){
			builder.excludeFieldsWithoutExposeAnnotation();
		}
		if (underLineJson){
			builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		}
		Gson gson = builder.create();*/
		Gson gson=createGson(isSerializeNulls, version, datePattern, excludesFieldsWithoutExpose, underLineJson);
		String result = EMPTY;
		try {
			if (targetType != null) {
				result = gson.toJson(target, targetType);
			} else {
				result = gson.toJson(target);
			}
		} catch (Exception ex) {
		}
		return result;
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJson4underLine(Object target) {
		if(target!=null){
			return toJson(target, null, false, null, DEFAULT_DATE_PATTERN, false,true);
		}
		return null;
	}
	public static String toJson(Object target) {
		if(target!=null){
			return toJson(target, null, false, null, DEFAULT_DATE_PATTERN, false,false);
		}
		return null;
	}
	
	
	private static Gson createGson(boolean underLineJson){
		Gson gson=createGson(false, null, DEFAULT_DATE_PATTERN, false, underLineJson);
		return gson;
	}

	public static <T> T toBean(String jsonStr,TypeToken<T> token){
		if(StringUtil.isNotEmpty(jsonStr)){
			return createGson(false).fromJson(jsonStr, token.getType());
		}
		return null;
	}
	public static <T> T toBean4underLine(String jsonStr,TypeToken<T> token){
		if(StringUtil.isNotEmpty(jsonStr)){
			return createGson(true).fromJson(jsonStr, token.getType());
		}
		return null;
	}
	
	
	public static <T> T toBean(String jsonStr,Class<T> classOfT){
		if(StringUtil.isNotEmpty(jsonStr)){
			return createGson(false).fromJson(jsonStr, classOfT);
		}
		return null;
	}
	
	public static <T> T toBean4underLine(String jsonStr,Class<T> classOfT){
		if(StringUtil.isNotEmpty(jsonStr)){
			return createGson(true).fromJson(jsonStr, classOfT);
		}
		return null;
	}
	
	
	
	public static void main(String[] args) {
		/*User user=new User("aa",12,new Date());
		user.setMoney(0.25);
		List<User> list=new ArrayList<User>();
		list.add(user);
		String str=toJson4underLine(list);
		System.out.println(str);*/
		
		/*String jsonStr = "[{\"name\":\"aa\",\"money\":0.25,\"age\":12,\"create_date\":\"2017-03-24 14:39:30\",\"dog\":{\"dogName\":\"aa\"}}]";
		List<User> users=toBean(jsonStr, new TypeToken<List<User>>(){});
		for(User usera:users){
			System.out.println(usera.getDog().getDogName());
		}*/
		
		
		/*String jsonStr = "{\"name\":\"aa\",\"money\":0.25,\"age\":12,\"create_date\":\"2017-03-24 14:39:30\",\"dogs\":[{\"dog_name\":\"bb\"}],\"dog\":{\"dog_name\":\"aa\"}}";
		User user=toBean4underLine(jsonStr, User.class);
		System.out.println(user.getCreateDate()+";"+user.getDogs().get(0).getDogName());*/
		
	}
}
