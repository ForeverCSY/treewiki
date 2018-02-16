package com.wjs.common.util;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjs.common.dao.PageDataList;
import com.wjs.common.dao.PageParameter;

/**
 * Created with IntelliJ IDEA.
 * User: Arthur
 * Date: 13-12-26
 * Time: 下午8:09
 */
public class PropertyUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtils.class);

	/**
	 * 赋值属性
	 * 
	 * @param dest
	 * @param orig
	 * @author Silver
	 * @date 2016年4月11日 下午2:38:17
	 */
	public static void copyProperties(Object dest, Object orig) {

		if (orig == null || dest == null) {
			return;
		}
		try {
			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			ConvertUtils.register(new IntegerConverter(null), Integer.class);
			ConvertUtils.register(new LongConverter(null), Long.class);
			ConvertUtils.register(new ShortConverter(null), Short.class);
			ConvertUtils.register(new DoubleConverter(null), Double.class);
			ConvertUtils.register(new DateConverter(null),  java.util.Date.class);
			ConvertUtils.register(new DateConverter(null),  java.sql.Date.class);
			BeanUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			LOGGER.error("属性赋值异常 ，原对象：{}，目标对象：{}", dest, orig, e);
		}

		//得到类对象  
		Class destClz = (Class) dest.getClass();
		/*
		 * 得到类中的所有属性集合
		 */
		Field[] fields = destClz.getDeclaredFields();
		for (Field f : fields) {
			try {
				if ("serialVersionUID".equals(f.getName()) || "LOGGER".equals(f.getName())) {
					continue;
				}
				PropertyDescriptor pdDest = new PropertyDescriptor(f.getName(), destClz);
				Method rM = pdDest.getReadMethod();//获得读方法
				rM.invoke(dest);
			} catch (Exception e) {
				LOGGER.warn("属性赋值异常 ，原对象：{}，目标对象：{},属性：{}", dest, orig, f.getName());
			}
		}

	}

	/**
	 * 字节数组转换成对象
	 * @param bytes
	 * @return
	 * @author Silver 
	 * @date 2016年12月13日 下午5:34:14
	 */
	@SuppressWarnings("unchecked")
	public static <T> T  byteToObject(byte[] bytes) {

		Object obj = null;
		ByteArrayInputStream bi = null;
		ObjectInputStream oi = null;
		try {
			bi = new ByteArrayInputStream(bytes);
			oi = new ObjectInputStream(bi);
			
			obj = oi.readObject();
		} catch (Exception e) {
			LOGGER.error("字节数组转化成对象异常", e);
		}finally {
			if(null != bi){
				try {
					bi.close();
				} catch (IOException e) {
					LOGGER.error("流关闭异常", e);
				}
			}
			if(null != oi){
				try {
					oi.close();
				} catch (IOException e) {
					LOGGER.error("流关闭异常", e);
				}
			}
		}
		return (T)obj;
	}

	
	/**
	 * 对象转化成字节数组
	 * @param obj
	 * @return
	 * @author Silver 
	 * @date 2016年12月13日 下午5:34:29
	 */
	public static byte[] objectToByte(java.lang.Object obj) {

		byte[] bytes = null;
		ByteArrayOutputStream bo = null;
		ObjectOutputStream oo = null;
		try {
			bo = new ByteArrayOutputStream();
			oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);

			bytes = bo.toByteArray();

		} catch (Exception e) {
			LOGGER.error("对象转化成字节数组异常", e);
		}finally {
			if(null != bo){
				try {
					bo.close();
				} catch (IOException e) {
					LOGGER.error("流关闭异常", e);
				}
			}
			if(null != oo){
				try {
					oo.close();
				} catch (IOException e) {
					LOGGER.error("流关闭异常", e);
				}
			}
			
		}
		return bytes;
	}
	
	public static void main(String[] args) {
		
		List<PageParameter> list = new ArrayList<PageParameter>();
		PageParameter p1 = new PageParameter();
		p1.setStart(0);
		p1.setLimit(10);
		PageParameter p2 = new PageParameter();
		p2.setStart(100);
		p2.setLimit(110);
		list.add(p1);
		list.add(p2);
		Map<String, List<PageParameter>> map = new HashMap<String, List<PageParameter>>();
		map.put("test", list);
		byte[] bts = objectToByte(map);
		System.out.println(bts);
		Map<String, List<PageParameter>> res = byteToObject(bts);
		System.out.println(res);
		
		PageDataList<PageParameter> page = new PageDataList<PageParameter>();
		page.setPage(1);
		page.setPageSize(100);
		page.setRows(list);
		page.setTotal(10);
		byte[] pagebt = objectToByte(page);
		System.out.println(pagebt);
		PageDataList<PageParameter> pageres = byteToObject(pagebt);
		System.out.println(pageres);
	}

	/**
	 * 支持JavaBean以其某一个属性为key将collection集合转换为map
	 *
	 * @param collection
	 * @param fieldName JavaBean中field name
	 * @param targetClazz map对应的key的类型,目前仅支持primitive type的包装类型和String类型
	 * @param <T>
	 * @param <S>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <S, T> Map<S, T> toMap(Collection<T> collection, String fieldName, Class<S> targetClazz) {

		Map<S, T> fieldMap = new LinkedHashMap<S, T>(16);
		if (CollectionUtils.isEmpty(collection)) {
			return fieldMap;
		}

		Class<?> componentClazz = Object.class;
		boolean check = false;

		try {
			Method convertMethod = getConvertMethod(targetClazz);

			if (convertMethod == null) {
				LOGGER.warn("No convert method returned, please make sure 'getConvertMethod' support '" + targetClazz.getName() + "'");
				return fieldMap;
			}

			Object obj;
			S target;

			for (T t : collection) {
				if (t == null) {
					continue;
				}
				if (!check) {
					componentClazz = t.getClass();
					check = true;
				}
				obj = BeanUtils.getProperty(t, fieldName);
				// 调用类的静态方法并强制转换为需要的类型
				target = (S) convertMethod.invoke(null, obj);
				fieldMap.put(target, t);
			}
		} catch (Exception e) {
			LOGGER.warn(
							"Convert '" + fieldName + "' instance to " + targetClazz + "] error on " + componentClazz.getName(),
							e);
		}
		return fieldMap;
	}

	/**
	 * 从集合元素中获取对应字段的值作为一个list返回，集合元素必须是JavaBean
	 *
	 * @param collection
	 * @param fieldName JavaBean对象的filed name
	 * @return
	 */
	public static <T> List<Object> fetchFieldList(Collection<T> collection, String fieldName) {

		List<Object> fields = new ArrayList<Object>();

		if (CollectionUtils.isEmpty(collection)) {
			return fields;
		}

		Object fieldValue;
		try {
			for (T t : collection) {
				if (t == null) {
					continue;
				}
				fieldValue = BeanUtils.getProperty(t, fieldName);
				fields.add(fieldValue);
			}
		} catch (Exception e) {
			LOGGER.warn("Get property '" + fieldName + "' from  collection [" + collection + "] error", e);
		}

		return fields;
	}

	/**
	 * 抽取JavaBean集合collection中的对应的T的field集合, 并将field集合转换为目标T类型, 类型一般为JavaBean
	 * fieldName对应的原始类型
	 *
	 * @param collection S集合
	 * @param fieldName 需要抽取的S JavaBean对应的field
	 * @param clazz 将field转换为的目标类型
	 * @param <S> collection集合的component type,一般是JavaBean
	 * @param <T>
	 * @return
	 */
	public static <S, T> List<T> fetchTypedFieldList(Collection<S> collection, String fieldName, Class<T> clazz) {

		List<Object> fieldObjList = fetchFieldList(collection, fieldName);
		List<T> fieldTypedList = fieldObjList == null ? Collections.<T> emptyList() : convertTo(fieldObjList, clazz);
		return CollectionUtils.isEmpty(fieldTypedList) ? Collections.<T> emptyList() : new ArrayList<T>(fieldTypedList);
	}

	/**
	 * 集合元素类型转换，只支持基本类型的包装类型、String
	 *
	 * @param collection 待转换数据集合
	 * @param targetClazz 目标类型class
	 * @param <T> 目标类型 基本类型的包装类型
	 * @param <S> 源类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, S> List<T> convertTo(Collection<S> collection, Class<T> targetClazz) {

		List<T> destList = new ArrayList<T>();

		if (CollectionUtils.isEmpty(collection)) {
			return destList;
		}

		try {
			Method method = getConvertMethod(targetClazz);

			if (method == null) {
				LOGGER.warn("Not support convert to {}", targetClazz.getName());
				return destList;
			}

			T target;
			for (S from : collection) {
				if (from == null) {
					continue;
				}
				target = (T) method.invoke(null, from);
				destList.add(target);
			}

		} catch (Exception e) {
			LOGGER.warn("Convert to {} error using reflection", targetClazz.getName(), e);
		}

		return destList;
	}

	/**
	 * 类型转换获取对应Method
	 *
	 * @param targetClazz 目前除void.class外的基本类型及其包装类型以及String
	 * @param <T>
	 * @return Method实例，对于不支持的类型返回null
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> Method getConvertMethod(Class<T> targetClazz) {

		try {
			String className = targetClazz.getSimpleName();
			String methodName;
			Method method = null;

			if (Void.class.equals(targetClazz) || Void.TYPE.equals(targetClazz)) {
				LOGGER.warn("Not support 'void.class' or 'Void.class'");
				return method;
			}

			// convert primitive type to corresponse wrapper type
			if (targetClazz.isPrimitive()) {
				targetClazz = (Class) ClassUtils.primitiveToWrapper(targetClazz);
			}
			// Numberic or Boolean使用对应的parseTargetClass
			if (Number.class.isAssignableFrom(targetClazz) || Boolean.class.isAssignableFrom(targetClazz)) {
				if (targetClazz.equals(Integer.class) || targetClazz.equals(int.class)) {
					methodName = "parseInt";
				} else {
					methodName = "parse" + className.substring(0, 1).toUpperCase() + className.substring(1);
				}
				method = targetClazz.getMethod(methodName, String.class);
			} else if (String.class.equals(targetClazz)) {
				method = targetClazz.getMethod("valueOf", Object.class);
			}

			return method;
		} catch (Exception e) {
			LOGGER.warn("Get convertMehod on '" + targetClazz.getName() + "' error using reflection", e);
			return null;
		}
	}

	/**
	 * 抽取JavaBean集合collection中的对应的T的field集合, 并将field集合转换为目标T类型, 类型一般为JavaBean
	 * fieldName对应的原始类型
	 *
	 * @param collection S集合
	 * @param fieldName 需要抽取的S JavaBean对应的field
	 * @param clazz 将field转换为的目标类型
	 * @param <S> collection集合的component type,一般是JavaBean
	 * @param <T>
	 * @return
	 */
	public static <S, T> Set<T> fetchTypedFieldSet(Collection<S> collection, String fieldName, Class<T> clazz) {

		List<Object> fieldObjList = fetchFieldList(collection, fieldName);
		List<T> fieldTypedList = fieldObjList == null ? Collections.<T> emptyList() : convertTo(fieldObjList, clazz);
		return CollectionUtils.isEmpty(fieldTypedList) ? Collections.<T> emptySet() : new LinkedHashSet<T>(
						fieldTypedList);
	}

	/**
	 * 将集合对象按照对应的元素的某个field为key，value为list集合的map，数据结构Map<key,List<T>> map
	 *
	 * @param collection
	 * @param fieldName
	 * @param targetClazz
	 * @param <S>
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <S, T> Map<S, Collection<T>> toMultiListValueMap(Collection<T> collection, String fieldName, Class<S> targetClazz) {

		Map<S, Collection<T>> multiMap = new LinkedHashMap<S, Collection<T>>();
		if (CollectionUtils.isEmpty(collection)) {
			return multiMap;
		}

		Class<?> componentClazz = Object.class;
		boolean check = false;

		try {
			Method convertMethod = getConvertMethod(targetClazz);

			if (convertMethod == null) {
				LOGGER.warn("No convert method returned, please make sure 'getConvertMethod' support '" + targetClazz.getName() + "'");
				return multiMap;
			}

			Object obj;
			S target;

			for (T t : collection) {
				if (t == null) {
					continue;
				}
				if (!check) {
					componentClazz = t.getClass();
					check = true;
				}
				obj = BeanUtils.getProperty(t, fieldName);
				// 调用类的静态方法并强制转换为需要的类型
				target = (S) convertMethod.invoke(null, obj);

				Collection<T> temp;
				if ((temp = multiMap.get(target)) == null) {
					temp = new ArrayList<T>();
					multiMap.put(target, temp);
				}
				temp.add(t);
			}
		} catch (Exception e) {
			LOGGER.warn(
							"Convert '" + fieldName + "' instance to " + targetClazz + "] error on " + componentClazz.getName(),
							e);
		}
		return multiMap;
	}

	/**
	 * 
	 * @Description: Collection 内部Collection 合并, 重复字段 去掉
	 * @param values
	 * @return 设定文件
	 * @throws 异常说明
	 * @author albert.su suzy@malam.com
	 * @date 2014年9月30日 上午11:35:47
	 */
	public static <V> Collection<V> collectionMerge(Collection<Collection<V>> values) {

		Collection<V> result = new LinkedHashSet<V>();
		if (CollectionUtils.isEmpty(values)) {
			return result;
		}
		for (Collection<V> value : values) {
			result.addAll(value);
		}
		return result;
	}

	/**
	 * 
	 * @Description: 从map里按key取值 不去重
	 * @param map
	 * @param keys
	 * @return 设定文件
	 * @throws 异常说明
	 * @author albert.su suzy@malam.com
	 * @date 2014年9月30日 上午11:36:12
	 */
	public static <V, K> List<V> fetchListInMap(Map<K, V> map, Collection<K> keys) {

		List<V> result = new ArrayList<V>();
		if (MapUtils.isEmpty(map) || CollectionUtils.isEmpty(keys)) {
			return result;
		}
		for (K k : keys) {
			V value = map.get(k);
			if (value == null) {
				continue;
			}
			result.add(value);
		}
		return result;
	}

	/**
	 * 
	 * @Description: 从map 里按key取值 去重
	 * @param map
	 * @param keys
	 * @return 设定文件
	 * @throws 异常说明
	 * @author albert.su suzy@malam.com
	 * @date 2014年9月30日 上午11:36:37
	 */
	public static <V, K> Set<V> fetchSetInMap(Map<K, V> map, Collection<K> keys) {

		Set<V> result = new LinkedHashSet<V>();
		if (MapUtils.isEmpty(map) || CollectionUtils.isEmpty(keys)) {
			return result;
		}
		for (K k : keys) {
			V value = map.get(k);
			if (value == null) {
				continue;
			}
			result.add(value);
		}
		return result;
	}

	/**
	 * 
	 * @Description: 从map 里按key取值 去重
	 * @param map
	 * @param keys
	 * @return 设定文件
	 * @throws 异常说明
	 * @author albert.su suzy@malam.com
	 * @date 2014年9月30日 上午11:36:37
	 */
	public static <V, K> Map<K, V> fetchMapInMap(Map<K, V> map, Collection<K> keys) {

		Map<K, V> result = new LinkedHashMap<K, V>();
		if (MapUtils.isEmpty(map) || CollectionUtils.isEmpty(keys)) {
			return result;
		}
		for (K k : keys) {
			V value = map.get(k);
			if (value == null) {
				continue;
			}
			result.put(k, value);
		}
		return result;
	}
}
