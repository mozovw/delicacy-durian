package com.delicacy.durian.easypoi.excel.exam;


import com.delicacy.durian.easypoi.excel.entity.AccountsEntity;
import com.delicacy.durian.easypoi.excel.entity.BudgetAccountsEntity;
import com.delicacy.durian.easypoi.excel.entity.ManySheetOneSyler;
import com.delicacy.durian.easypoi.excel.entity.PayeeEntity;
import com.delicacy.durian.easypoi.util.PathUtil;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.excel.export.ExcelExportServer;
import org.jeecgframework.poi.util.PoiMergeCellUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelExportTemplateTest  {

	static File file6 = new File(PathUtil.getResourcesPath() + "/excel/excelexport6.xlsx");
	static File file7 = new File(PathUtil.getResourcesPath() + "/excel/excelexport7.xlsx");
	static File file8 = new File(PathUtil.getResourcesPath() + "/excel/excelexport8.xlsx");
	static File file9 = new File(PathUtil.getResourcesPath() + "/excel/excelexport9.xlsx");
	static File file10 = new File(PathUtil.getResourcesPath() + "/excel/excelexport10.xlsx");
	static File file11 = new File(PathUtil.getResourcesPath() + "/excel/excelexport11.xlsx");

	public static void main(String[] args) throws Exception {
//		test_col();
//		test_entity();
//		test_create();
//		test_foreach();
//		test_many();
		test_map();
	}

	public static void test_col() throws Exception {
		Map<String, Object> value = new HashMap<String, Object>();
		List<Map<String, Object>> colList = new ArrayList<Map<String, Object>>();
		// 先处理表头
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "小明挑战");
		map.put("zq", "正确");
		map.put("cw", "错误");
		map.put("tj", "统计");
		map.put("zqmk", "t.zq_xm");
		map.put("cwmk", "t.cw_xm");
		map.put("tjmk", "t.tj_xm");
		colList.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "小红挑战");
		map.put("zq", "正确");
		map.put("cw", "错误");
		map.put("tj", "统计");
		map.put("zqmk", "n:t.zq_xh");
		map.put("cwmk", "n:t.cw_xh");
		map.put("tjmk", "n:t.tj_xh");
		colList.add(map);
		value.put("colList", colList);
		List<Map<String, Object>> valList = new ArrayList<Map<String, Object>>();
		map = new HashMap<String, Object>();
		map.put("one", "运动");
		map.put("two", "跑步");
		map.put("zq_xm", 1);
		map.put("cw_xm", 2);
		map.put("tj_xm", 3);
		map.put("zq_xh", 4);
		map.put("cw_xh", 2);
		map.put("tj_xh", 6);
		valList.add(map);
		map = new HashMap<String, Object>();
		map.put("one", "运动");
		map.put("two", "跳高");
		map.put("zq_xm", 1);
		map.put("cw_xm", 2);
		map.put("tj_xm", 3);
		map.put("zq_xh", 4);
		map.put("cw_xh", 2);
		map.put("tj_xh", 6);
		valList.add(map);
		map = new HashMap<String, Object>();
		map.put("one", "文化");
		map.put("two", "数学");
		map.put("zq_xm", 1);
		map.put("cw_xm", 2);
		map.put("tj_xm", 3);
		map.put("zq_xh", 4);
		map.put("cw_xh", 2);
		map.put("tj_xh", 6);
		valList.add(map);
		value.put("valList", valList);
		TemplateExportParams params = new TemplateExportParams(
				PathUtil.getResourcesPath() + "/excel/exceltemp_col.xlsx",
				true);
		params.setColForEach(true);
		Workbook book = ExcelExportUtil.exportExcel(params, value);
		PoiMergeCellUtil.mergeCells(book.getSheetAt(0), 1, 0, 1);
		FileOutputStream fos = new FileOutputStream(file6);
		book.write(fos);
		fos.close();

	}

	
	public static void test_entity() throws IOException {
		TemplateExportParams params = new TemplateExportParams(
				PathUtil.getResourcesPath()+"/excel/exceltemp_entity.xls");
		params.setHeadingStartRow(3);
		params.setHeadingRows(2);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", "2014-12-25");
		map.put("money", 2000000.00);
		map.put("upperMoney", "贰佰万");
		map.put("company", "执笔潜行科技有限公司");
		map.put("bureau", "财政局");
		map.put("person", "JueYue");
		map.put("phone", "1879740****");
		List<AccountsEntity> list = new ArrayList<AccountsEntity>();
		for (int i = 0; i < 4; i++) {
			AccountsEntity entity = new AccountsEntity();
			entity.setIndex(i + 1 + "");
			entity.setAccountType("开源项目");
			entity.setProjectName("EasyPoi " + i + "期");
			entity.setAmountApplied(i * 10000 + "");
			entity.setApprovedAmount((i + 1) * 10000 - 100 + "");
			List<BudgetAccountsEntity> budgetAccounts = Lists.newArrayList();
			for (int j = 0; j < 1; j++) {
				BudgetAccountsEntity accountsEntity = new BudgetAccountsEntity();
				accountsEntity.setCode("A001");
				accountsEntity.setName("设计");
				budgetAccounts.add(accountsEntity);
				accountsEntity = new BudgetAccountsEntity();
				accountsEntity.setCode("A002");
				accountsEntity.setName("开发");
				budgetAccounts.add(accountsEntity);
			}
			entity.setBudgetAccounts(budgetAccounts);
			PayeeEntity payeeEntity = new PayeeEntity();
			payeeEntity.setBankAccount("6222 0000 1234 1234");
			payeeEntity.setBankName("中国银行");
			payeeEntity.setName("小明");
			entity.setPayee(payeeEntity);
			list.add(entity);
		}
		Workbook workbook = ExcelExportUtil.exportExcel(params,
				AccountsEntity.class, list, map);
		FileOutputStream fos = new FileOutputStream(file7);
		workbook.write(fos);
		fos.close();
	}


	public static void test_map() throws Exception {
		TemplateExportParams params = new TemplateExportParams(
				PathUtil.getResourcesPath()+"/excel/exceltemp_map.xls");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", "2014-12-25");
		map.put("money", 2000000.00);
		map.put("upperMoney", "贰佰万");
		map.put("company", "执笔潜行科技有限公司");
		map.put("bureau", "财政局");
		map.put("person", "JueYue");
		map.put("phone", "1879740****");
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 4; i++) {
			Map<String, String> lm = new HashMap<String, String>();
			lm.put("id", i + 1 + "");
			lm.put("zijin", i * 10000 + "");
			lm.put("bianma", "A001");
			lm.put("mingcheng", "设计");
			lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
			lm.put("quancheng", "开源项目");
			lm.put("sqje", i * 10000 + "");
			lm.put("hdje", i * 10000 + "");
			listMap.add(lm);
		}
		map.put("maplist", listMap);
		Workbook workbook = ExcelExportUtil.exportExcel(params, map);
		FileOutputStream fos = new FileOutputStream(file11);
		workbook.write(fos);
		fos.close();
	}


	public static void test_create() throws Exception {
		ExcelExportServer util = new ExcelExportServer();
		Workbook workbook = new HSSFWorkbook();
		ExportParams entity = new ExportParams();
		entity.setCreateHeadRows(false);
		entity.setStyle(ManySheetOneSyler.class);
		List<ExcelExportEntity> entityList = new ArrayList<>();
		ExcelExportEntity e = new ExcelExportEntity();
		e.setHeight(40);
		e.setWidth(40);
		e.setWrap(true);
		e.setName("one");
		e.setKey("one");
		entityList.add(e);
		e = new ExcelExportEntity();
		e.setHeight(40);
		e.setWidth(40);
		e.setWrap(true);
		e.setName("two");
		e.setKey("two");
		entityList.add(e);
		e = new ExcelExportEntity();
		e.setHeight(40);
		e.setWidth(40);
		e.setWrap(true);
		e.setName("three");
		e.setKey("three");
		entityList.add(e);
		e = new ExcelExportEntity();
		e.setHeight(40);
		e.setWidth(40);
		e.setWrap(true);
		e.setName("four");
		e.setKey("four");
		entityList.add(e);
		e = new ExcelExportEntity();
		e.setHeight(40);
		e.setWidth(40);
		e.setWrap(true);
		e.setName("five");
		e.setKey("five");
		entityList.add(e);
		e = new ExcelExportEntity();
		e.setHeight(40);
		e.setWidth(40);
		e.setWrap(true);
		e.setName("six");
		e.setKey("six");
		entityList.add(e);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 80; i++) {
			list.add("卡片编号：2228\n资产名称：办公器\n开始使用日期：20090910\n使用状况：在用\n使用科室：财务科\n管理科室：总务科\n市妇幼2015年6月");
		}

		// 拼装成6个
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (int l = 0; l < list.size(); l += 6) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("one", l + 0 >= list.size() ? "" : list.get(l + 0));
			map.put("two", l + 1 >= list.size() ? "" : list.get(l + 1));
			map.put("three", l + 2 >= list.size() ? "" : list.get(l + 2));
			map.put("four", l + 3 >= list.size() ? "" : list.get(l + 3));
			map.put("five", l + 4 >= list.size() ? "" : list.get(l + 4));
			map.put("six", l + 5 >= list.size() ? "" : list.get(l + 5));
			dataList.add(map);
		}

		// 补全数据好看一点
		for (int l = 0, le = (dataList.size() % 5) == 0 ? 0 : 5 - dataList
				.size() % 5; l < le; l++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("one", "");
			map.put("two", "");
			map.put("three", "");
			map.put("five", "");
			map.put("four", "");
			dataList.add(map);
		}

		for (int i = 0, le = dataList.size() / 5; i < le; i++) {
			util.createSheetForMap(workbook, entity, entityList,
					dataList.subList(0, 5));
		}
		FileOutputStream fos = new FileOutputStream(file8);
		workbook.write(fos);
		fos.close();
	}


	public static void test_foreach() throws Exception {
		TemplateExportParams params = new TemplateExportParams(
				PathUtil.getResourcesPath()+"/excel/exceltemp_foreach.xlsx");
		Map<String, Object> map = new HashMap<String, Object>();
		List<AccountsEntity> list = new ArrayList<AccountsEntity>();

		for (int i = 0; i < 4; i++) {
			AccountsEntity entity = new AccountsEntity();
			entity.setIndex(i + 1 + "");
			entity.setAccountType("开源项目");
			entity.setProjectName("EasyPoi " + i + "期");
			entity.setAmountApplied(i * 10000 + "");
			entity.setApprovedAmount((i + 1) * 10000 - 100 + "");
			list.add(entity);
		}
		map.put("entitylist", list);
		map.put("manmark", "1");
		map.put("letest", "12345678");
		map.put("fntest", "12345678.2341234");
		map.put("fdtest", null);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 1; i++) {
			Map<String, Object> testMap = new HashMap<String, Object>();

			testMap.put("id", "xman");
			testMap.put("name", "小明" + i);
			testMap.put("sex", "1");
			mapList.add(testMap);
		}
		map.put("maplist", mapList);
		mapList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 6; i++) {
			Map<String, Object> testMap = new HashMap<String, Object>();
			testMap.put("si", "xman");
			mapList.add(testMap);
		}
		map.put("sitest", mapList);
		Workbook workbook = ExcelExportUtil.exportExcel(params, map);
		PoiMergeCellUtil.mergeCells(workbook.getSheetAt(0), 1, 0, 4);
		FileOutputStream fos = new FileOutputStream(file9);
		workbook.write(fos);
		fos.close();
	}


	public static void test_many() throws Exception {
		TemplateExportParams params = new TemplateExportParams(
				PathUtil.getResourcesPath()+"/excel/exceltemp_many.xlsx");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 400; i++) {
			Map<String, Object> testMap = new HashMap<String, Object>();

			testMap.put("id", "080101" + i);
			testMap.put("name", "大学" + i + "班");
			testMap.put("a1", getDeatil());
			testMap.put("a2", getDeatil());
			testMap.put("a3", getDeatil());
			testMap.put("sum1", "30" + i);
			testMap.put("sum2", "40" + i);
			mapList.add(testMap);
		}
		map.put("list", mapList);
		Workbook workbook = ExcelExportUtil.exportExcel(params, map);
		FileOutputStream fos = new FileOutputStream(file10);
		workbook.write(fos);
		fos.close();
	}

	private static Map<String, Object> getDeatil() {
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("zero", (int) (Math.random() * 100));
		testMap.put("sixty", (int) (Math.random() * 100));
		testMap.put("eighty", (int) (Math.random() * 100));
		return testMap;
	}
}
