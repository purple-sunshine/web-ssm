package com.heitian.ssm.arch.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 对apache common中的BeanUtils进行了扩展
 * @author songjunjie
 */
public class BeanUtilsExt extends BeanUtils {

	/**
	 * 将bean中的属性值复制到map中，map中的key对应bean的属性
	 * @param bean
	 * @param properties 需要复制的属性，如果没有指定，那么就是复制全部
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> beanToMap(Object bean , String...properties){
		Map<String,Object> map = new HashMap<String,Object>();
		if(properties!=null){
			for(String name : properties){
				try {
					Object value = BeanUtils.getProperty(bean, name);
					map.put(name, value);
				}catch (Exception e) {
					new RuntimeException(e);
				}
			}
		}else{
			try {
				map = PropertyUtils.describe(bean);
			} catch (Exception e) {
				new RuntimeException(e);
			}
		}
		return map;
	}
}
