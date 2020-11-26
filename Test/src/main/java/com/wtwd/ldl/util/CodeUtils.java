package com.wtwd.ldl.util;

import com.wtwd.ldl.common.RespCode;

/**
 * @Author ldaoliang
 * @Date 2019/10/26 0026 上午 9:55
 * @Description
 **/
public class CodeUtils {



//	/**
//	 * 通过code获取枚举实例
//	 *
//	 * @param code      code
//	 * @param enumClass 枚举类型
//	 * @param <T>       泛型
//	 * @return T
//	 */
//	public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
//		//通过反射取出Enum所有常量的属性值
//		for (T each : enumClass.getEnumConstants()) {
//			//利用code进行循环比较，获取对应的枚举
//			if (code.equals(each.getCode())) {
//				return each;
//			}
//		}
//		return (T) RespCode.FAILED;
//	}
}
