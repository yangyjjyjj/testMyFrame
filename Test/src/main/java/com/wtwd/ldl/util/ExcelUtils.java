package com.wtwd.ldl.util;

/**
 * @author xsansan
 * @date 2019年4月1日
 * @description
 */
public class ExcelUtils {

	public static boolean isExcel2003(String fileName) {
		return fileName.matches("^.+\\.(?i)(xls)$");
	}

	private static boolean isExcel2007(String fileName) {
		return fileName.matches("^.+\\.(?i)(xlsx)$");
	}

	/**
	 * 验证EXCEL文件
	 * @param  fileName fileName
	 * @return boolean
	 */
	public static boolean validateExcel(String fileName) {
		return fileName != null && (isExcel2003(fileName) || isExcel2007(fileName));
	}

}
