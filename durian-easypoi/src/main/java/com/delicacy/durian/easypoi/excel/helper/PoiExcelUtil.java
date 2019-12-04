package com.delicacy.durian.easypoi.excel.helper;

import java.util.ArrayList;

public class PoiExcelUtil {
	
	// 测试获取sheet列表
	public static void testGetSheetList(String filePath) {
		PoiExcelHelper helper = getPoiExcelHelper(filePath);

		// 获取Sheet列表
		ArrayList<String> sheets = helper.getSheetList(filePath);

		// 打印Excel的Sheet列表
		printList(filePath, sheets);
	}

	// 测试Excel读取
	public static void readExcel(String filePath, int sheetIndex) {
		PoiExcelHelper helper = getPoiExcelHelper(filePath);

		// 读取excel文件数据
		ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath,
				sheetIndex);

		// 打印单元格数据
		printBody(dataList);
	}

	// 测试Excel读取
	public static void readExcel(String filePath, int sheetIndex,
			String rows) {
		PoiExcelHelper helper = getPoiExcelHelper(filePath);

		// 读取excel文件数据
		ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath,
				sheetIndex, rows);

		// 打印单元格数据
		printBody(dataList);
	}

	// 测试Excel读取
	public static void readExcel(String filePath, int sheetIndex,
			String[] columns) {
		PoiExcelHelper helper = getPoiExcelHelper(filePath);

		// 读取excel文件数据
		ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath,
				sheetIndex, columns);

		// 打印列标题
		printHeader(columns);

		// 打印单元格数据
		printBody(dataList);
	}

	// 测试Excel读取
	public static void readExcel(String filePath, int sheetIndex,
			String rows, String[] columns) {
		PoiExcelHelper helper = getPoiExcelHelper(filePath);

		// 读取excel文件数据
		ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath,
				sheetIndex, rows, columns);

		// 打印列标题
		printHeader(columns);

		// 打印单元格数据
		printBody(dataList);
	}

	// 获取Excel处理类
	public static PoiExcelHelper getPoiExcelHelper(String filePath) {
		PoiExcelHelper helper;
		if (filePath.indexOf(".xlsx") != -1) {
			helper = new PoiExcel2k7Helper();
		} else {
			helper = new PoiExcel2k3Helper();
		}
		return helper;
	}

	// 打印Excel的Sheet列表
	public static void printList(String filePath, ArrayList<String> sheets) {
		System.out.println();
		for (String sheet : sheets) {
			System.out.println(filePath + " ==> " + sheet);
		}
	}

	// 打印列标题
	public static void printHeader(String[] columns) {
		System.out.println();
		for (String column : columns) {
			System.out.print("\t\t" + column.toUpperCase());
		}
	}
	
	// 打印单元格数据
	public static void printBody(ArrayList<ArrayList<String>> dataList) {
		for(ArrayList<String> data : dataList) {
			for(String v : data) {
				System.out.print("\t" + v);
			}
		}
	}
}
