package com.delicacy.durian.easypoi.excel.exam;



import com.delicacy.durian.easypoi.excel.entity.ExcelVerifyEntity;
import com.delicacy.durian.easypoi.excel.entity.Guy;
import com.delicacy.durian.easypoi.util.PathUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;


public class ExcelImportUtilTest  {

	static File file12 = new File(PathUtil.getResourcesPath() + "/excel/excelimport.xlsx");
	static File file13 = new File(PathUtil.getResourcesPath() + "/excel/excelverfiy.xlsx");
	static File file14 = new File(PathUtil.getResourcesPath() + "/excel/excelexport14.xlsx");

	public static void main(String[] args) {
//		test_import_bysax();
//		test_import();
//		test_verify();
	}


	public static void test_import_bysax() {
		try {
			ImportParams params = new ImportParams();
			params.setTitleRows(1);
			List<Object> list = ExcelImportUtil
					.importExcelBySax(
							new FileInputStream(file12),
							Guy.class, params);
			System.out.println(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static void test_import() {
		try {
			ImportParams params = new ImportParams();
			params.setTitleRows(1);
			List<Map<String, Object>> list = ExcelImportUtil
					.importExcel(file12,
							Map.class, params);
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public  static void test_verify() {
		try {
			ImportParams params = new ImportParams();
			params.setNeedVerfiy(true);
			ExcelImportResult<Object> result = ExcelImportUtil
					.importExcelVerify(file13,
							ExcelVerifyEntity.class, params);
			FileOutputStream fos = new FileOutputStream(file14);
			result.getWorkbook().write(fos);
			fos.close();
			System.out.println(result.getList());
			System.out.println(result.isVerfiyFail());
			System.out.println(result.getList().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
