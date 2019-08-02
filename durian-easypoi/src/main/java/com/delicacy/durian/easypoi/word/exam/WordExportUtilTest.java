package com.delicacy.durian.easypoi.word.exam;


import com.delicacy.durian.easypoi.excel.entity.StudentEntity;
import com.delicacy.durian.easypoi.excel.entity.TeacherEntity;
import com.delicacy.durian.easypoi.util.PathUtil;
import com.delicacy.durian.easypoi.word.entity.CourseEntity;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.word.WordExportUtil;
import org.jeecgframework.poi.word.entity.WordImageEntity;
import org.jeecgframework.poi.word.entity.params.ExcelListEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;


public class WordExportUtilTest  {

	static File file = new File(PathUtil.getResourcesPath() + "/word/wordexport.docx");
	static File file1 = new File(PathUtil.getResourcesPath() + "/word/wordexport1.docx");
	static File file2 = new File(PathUtil.getResourcesPath() + "/word/wordexport2.docx");
	static File file3 = new File(PathUtil.getResourcesPath() + "/word/wordexport3.docx");
	static File file4 = new File(PathUtil.getResourcesPath() + "/word/wordexport4.docx");
	static File file5 = new File(PathUtil.getResourcesPath() + "/word/wordexport5.docx");
	static File file6 = new File(PathUtil.getResourcesPath() + "/word/wordexport6.docx");

	public static void main(String[] args) {
//		test_total();
//		test_excel();
//		test_image();
		test_map();
	}

	public static void test_total() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curTime = format.format(new Date());

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("type", "个人所得税");
		map1.put("presum", "1580");
		map1.put("thissum", "1750");
		map1.put("curmonth", "1-11月");
		map1.put("now", curTime);
		mapList.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("type", "增值税");
		map2.put("presum", "1080");
		map2.put("thissum", "1650");
		map2.put("curmonth", "1-11月");
		map2.put("now", curTime);
		mapList.add(map2);
		map.put("taxlist", mapList);
		map.put("totalpreyear", "2660");
		map.put("totalthisyear", "3400");
		Map<String, String> total = new HashMap<String, String>();
		total.put("orderId", "2660");
		total.put("orderCreateTime", "3400");
		total.put("supplyName", "3410");
		map.put("order", total);
		try {
			XWPFDocument doc = WordExportUtil
					.exportWord07(
							PathUtil.getResourcesPath()+"/word/wordexport_contract.docx",
							map);
			FileOutputStream fos = new FileOutputStream(file);
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd");


	public static void test_excel() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd");
		List<CourseEntity> list = new ArrayList<>();
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setId("1131");
		courseEntity.setName("小白");

		TeacherEntity teacherEntity = new TeacherEntity();
		teacherEntity.setId("12131231");
		teacherEntity.setName("你们");
		courseEntity.setTeacher(teacherEntity);

		teacherEntity = new TeacherEntity();
		teacherEntity.setId("121312314312421131");
		teacherEntity.setName("老王");
		courseEntity.setShuxueteacher(teacherEntity);

		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setId("11231");
		studentEntity.setName("撒旦法司法局");
		studentEntity.setBirthday(new Date());
		studentEntity.setSex(1);
		List<StudentEntity> studentList = new ArrayList<>();
		studentList.add(studentEntity);
		studentList.add(studentEntity);
		courseEntity.setStudents(studentList);

		for (int i = 0; i < 3; i++) {
			list.add(courseEntity);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("department", "Jeecg");
		map.put("person", "JueYue");
		map.put("auditPerson", "JueYue");
		map.put("time", format.format(new Date()));
		map.put("cs", new ExcelListEntity(list, CourseEntity.class));
		try {
			XWPFDocument doc = WordExportUtil
					.exportWord07(
							PathUtil.getResourcesPath()+"/word/wordexport_excel.docx",
							map);
			FileOutputStream fos = new FileOutputStream(file1);
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 简单导出包含图片
	 */

	public static void test_image() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("department", "Jeecg");
		map.put("time", format.format(new Date()));
		Person person = new Person();
		person.setName("JueYue");
		map.put("p", person);
		WordImageEntity image = new WordImageEntity();
		image.setHeight(200);
		image.setWidth(500);
		image.setUrl(PathUtil.getResourcesPath()+"/word/test.png");
		image.setType(WordImageEntity.URL);
		map.put("test", image);
		try {
			XWPFDocument doc = WordExportUtil
					.exportWord07(PathUtil.getResourcesPath()+"/word/wordexport_image.docx", map);
			FileOutputStream fos = new FileOutputStream(file2);
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Data
	static class Person {
		@Excel(name = "姓名")
		private String name;
		@Excel(name = "手机")
		private String tel;
		@Excel(name = "Email")
		private String email;
	}

	/**
	 * 简单导出没有图片和Excel
	 */

	public static void test_map() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("department", "Jee\r\ncg");
		map.put("person", "Jue\r\nYue");
		map.put("auditPerson", "JueYue");
		map.put("time", format.format(new Date()));
		map.put("date", new Date());
		List<Person> list = new ArrayList<Person>();
		Person p = new Person();
		p.setName("小明");
		p.setTel("18711111111");
		p.setEmail("18711111111@\\r\\n139.com");
		list.add(p);
		p = new Person();
		p.setName("小红");
		p.setTel("18711111112");
		p.setEmail("18711111112@\r\n139.com");
		list.add(p);
		map.put("pList", list);
		try {
			XWPFDocument doc = WordExportUtil.exportWord07(
					PathUtil.getResourcesPath()+"/word//workexport_map.docx",
					map);
			FileOutputStream fos = new FileOutputStream(file3);
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
