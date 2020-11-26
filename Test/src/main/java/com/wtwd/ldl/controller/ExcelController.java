package com.wtwd.ldl.controller;

import com.wtwd.ldl.common.RespCode;
import com.wtwd.ldl.common.RespEntity;
import com.wtwd.ldl.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author ldaoliang
 * @Date 2019/10/18 0018 上午 10:50
 * @Description 测试读取Excel表格内容
 **/
@RestController
public class ExcelController {

	private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

	@PostMapping("/readExcel")
	public RespEntity readExcel(@RequestParam("file") MultipartFile file)throws Exception{

		String fileName = file.getOriginalFilename();
		logger.info("import file "+fileName);

		if(!ExcelUtils.validateExcel(fileName)){
			return new RespEntity(RespCode.INCORRECT_FORMAT);
		}

		boolean isExcel2003 = ExcelUtils.isExcel2003(fileName);
		//获取文件流
		InputStream is = file.getInputStream();
		// 根据版本选择创建Workbook的方式
		Workbook workbook;
		if (isExcel2003) {
			workbook = new HSSFWorkbook(is);
		} else {
			workbook = new XSSFWorkbook(is);
		}
		// 得到第一个shell（第一个shell是excel中第一个界面的)
		Sheet sheet = workbook.getSheetAt(0);

		//读取文件内容
		for(int r = 2 ; r < sheet.getLastRowNum(); r++){//r = 2表示从第三行开始读取
			//得到行对象
			Row row = sheet.getRow(r);
			row.getCell(0).getStringCellValue();//获取第一列数据

		}

		return null;
	}
}
