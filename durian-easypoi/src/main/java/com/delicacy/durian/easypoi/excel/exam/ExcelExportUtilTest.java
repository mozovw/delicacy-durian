package com.delicacy.durian.easypoi.excel.exam;


import com.delicacy.durian.easypoi.excel.entity.CourseEntity;
import com.delicacy.durian.easypoi.excel.entity.HyperLinkEntity;
import com.delicacy.durian.easypoi.excel.entity.StudentEntity;
import com.delicacy.durian.easypoi.excel.entity.TeacherEntity;
import com.delicacy.durian.easypoi.util.PathUtil;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.handler.impl.ExcelDataHandlerDefaultImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelExportUtilTest {
    static File file = new File(PathUtil.getResourcesPath() + "/excel/excelexport.xlsx");
    static File file2 = new File(PathUtil.getResourcesPath() + "/excel/excelexport2.xlsx");
    static File file3 = new File(PathUtil.getResourcesPath() + "/excel/excelexport3.xlsx");
    static File file4 = new File(PathUtil.getResourcesPath() + "/excel/excelexport4.xlsx");
    static File file5 = new File(PathUtil.getResourcesPath() + "/excel/excelexport5.xlsx");
    static File file6 = new File(PathUtil.getResourcesPath() + "/excel/excelexport6.xlsx");
    static File file7 = new File(PathUtil.getResourcesPath() + "/excel/excelexport7.xlsx");

    public static void main(String[] args) throws Exception {
//		testExportExcel();
//		test_link();
		test_merge();
//		test_many();
//        test_date();
    }

    public static void testExportExcel() throws Exception {
        List<CourseEntity> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CourseEntity courseEntity = new CourseEntity();
            courseEntity.setId("1131");
            courseEntity.setName("海贼王必修(" + (i + 1) + ")");

            TeacherEntity teacherEntity = new TeacherEntity();
            teacherEntity.setId("12131231");
            teacherEntity.setName("路飞");
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
            list.add(courseEntity);
        }
        ExportParams params = new ExportParams("2412312", "测试", ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params,
                CourseEntity.class, list);
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
    }

    public static void test_link() throws Exception {
        List<HyperLinkEntity> list = new ArrayList<>();
        HyperLinkEntity client = new HyperLinkEntity();
        client.setName("百度");
        client.setUrl("https://www.baidu.com/");
        list.add(client);
        client = new HyperLinkEntity();
        client.setName("新浪");
        client.setUrl("http://www.sina.com.cn/");
        list.add(client);
        ExportParams params = new ExportParams("2412312", "测试", ExcelType.XSSF);
        params.setDataHanlder(new ExcelDataHandlerDefaultImpl() {
            @Override
            public Hyperlink getHyperlink(CreationHelper creationHelper,
                                          Object obj, String name, Object value) {
                Hyperlink link = creationHelper
                        .createHyperlink(Hyperlink.LINK_URL);
                HyperLinkEntity e = (HyperLinkEntity) obj;
                link.setAddress(e.getUrl());
                link.setLabel(e.getName());
                return link;
            }

        });
        Workbook workbook = ExcelExportUtil.exportExcel(params,
                HyperLinkEntity.class, list);
        FileOutputStream fos = new FileOutputStream(file2);
        workbook.write(fos);
        fos.close();
    }

    /**
     * 合并同类项
     */
    public static void test_merge() {
        try {
            List<ExcelExportEntity> entity = new ArrayList<>();
            ExcelExportEntity excelentity = new ExcelExportEntity("部门",
                    "depart");
            excelentity.setMergeVertical(true);
            entity.add(excelentity);
            excelentity = new ExcelExportEntity("姓名", "name");
            excelentity.setMergeVertical(true);
            excelentity.setMergeRely(new int[]{0});
            entity.add(excelentity);
            excelentity = new ExcelExportEntity("电话", "phone");
            excelentity.setMergeVertical(true);
            excelentity.setMergeRely(new int[]{1});
            entity.add(excelentity);
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> map;
            for (int i = 0; i < 10; i++) {
                map = new HashMap<>();
                map.put("depart", "设计部");
                map.put("name", "小明" + i / 3);
                map.put("phone", "1311234567" + i / 2);
                list.add(map);
            }
            for (int i = 0; i < 10; i++) {
                map = new HashMap<>();
                map.put("depart", "开发部");
                map.put("name", "小蓝" + i / 3);
                map.put("phone", "1871234567" + i / 2);
                list.add(map);
            }

            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(
                    "员工通讯录", "通讯录"), entity, list);
            FileOutputStream fos = new FileOutputStream(file3);
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void test_many() {
        try {
            List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
            for (int i = 0; i < 500; i++) {
                entity.add(new ExcelExportEntity("姓名" + i, "username" + i));
            }

            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            Map<String, String> map;
            for (int i = 0; i < 10; i++) {
                map = new HashMap<String, String>();
                for (int j = 0; j < 500; j++) {
                    map.put("username" + j, j + "_" + i);
                }
                list.add(map);
            }
            ExportParams params = new ExportParams("测试", "测试", ExcelType.XSSF);
            params.setFreezeCol(5);
            Workbook workbook = ExcelExportUtil.exportExcel(params, entity,
                    list);
            FileOutputStream fos = new FileOutputStream(file4);
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 基本导出测试
     *
     * @throws Exception
     */

    public static void test_date() throws Exception {
        List<CourseEntity> list = new ArrayList<CourseEntity>();
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
        List<StudentEntity> studentList = new ArrayList<StudentEntity>();
        studentList.add(studentEntity);
        studentList.add(studentEntity);
        courseEntity.setStudents(studentList);
        for (int i = 0; i < 3; i++) {
            list.add(courseEntity);
        }
        ExportParams exportParams = new ExportParams("2412312", "测试", "测试");
        CourseHanlder hanlder = new CourseHanlder();
        hanlder.setNeedHandlerFields(new String[]{"课程名称"});
        exportParams.setDataHanlder(hanlder);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,
                CourseEntity.class, list);
        FileOutputStream fos = new FileOutputStream(file5);
        workbook.write(fos);
        fos.close();
    }

    static class CourseHanlder extends ExcelDataHandlerDefaultImpl<CourseEntity> {

        @Override
        public Object exportHandler(CourseEntity obj, String name, Object value) {
            if (name.equals("课程名称")) {
                return String.valueOf(value) + "课程";
            }
            return super.exportHandler(obj, name, value);
        }

        @Override
        public Object importHandler(CourseEntity obj, String name, Object value) {
            return super.importHandler(obj, name, value);
        }

    }

}
