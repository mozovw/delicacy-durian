package com.delicacy.durian.easypoi.pdf.exam;


import com.delicacy.durian.easypoi.excel.entity.StudentEntity;
import com.delicacy.durian.easypoi.excel.entity.TeacherEntity;
import com.delicacy.durian.easypoi.pdf.entity.MsgClient;
import com.delicacy.durian.easypoi.pdf.entity.MsgClientGroup;
import com.delicacy.durian.easypoi.util.PathUtil;
import com.delicacy.durian.easypoi.word.entity.CourseEntity;
import com.itextpdf.text.Document;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.pdf.PdfExportUtil;
import org.jeecgframework.poi.pdf.entity.PdfExportParams;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PdfExportUtilTest {

    static File file = new File(PathUtil.getResourcesPath() + "/pdf/pdfexport.pdf");
    static File file1 = new File(PathUtil.getResourcesPath() + "/pdf/pdfexport1.pdf");
    static File file2 = new File(PathUtil.getResourcesPath() + "/pdf/pdfexport2.pdf");
    static File file3 = new File(PathUtil.getResourcesPath() + "/pdf/pdfexport3.pdf");


    public static void main(String[] args) {
//        test_export();
//		test_image();
		test_msg();
    }


    public static void test_export() {
        List<CourseEntity> list = new ArrayList<>();
        CourseEntity courseEntity;

        for (int i = 0; i < 50; i++) {
            courseEntity = new CourseEntity();
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
            List<StudentEntity> studentList = new ArrayList<StudentEntity>();
            studentList.add(studentEntity);
            studentList.add(studentEntity);
            courseEntity.setStudents(studentList);
            list.add(courseEntity);

            PdfExportParams params = new PdfExportParams("绝月的测试", "作者绝月");
            Document document = null;
            try {
                document = PdfExportUtil
                        .exportPdf(
                                params,
                                CourseEntity.class,
                                list,
                                new FileOutputStream(file));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                document.close();
            }
        }
    }


    public static void test_msg() {
        Field[] fields = MsgClient.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            Excel excel = fields[i].getAnnotation(Excel.class);
            System.out.println(excel);
        }
        List<MsgClient> list = new ArrayList<MsgClient>();
        for (int i = 0; i < 10; i++) {
            MsgClient client = new MsgClient();
            client.setBirthday(new Date());
            client.setClientName("小明" + i);
            client.setClientPhone("18797" + i);
            client.setCreateBy("JueYue");
            client.setId("1" + i);
            client.setRemark("测试" + i);
            MsgClientGroup group = new MsgClientGroup();
            group.setGroupName("测试" + i);
            client.setGroup(group);
            list.add(client);
        }
        PdfExportParams params = new PdfExportParams("2412312", null);
        try {
            Document document = PdfExportUtil
                    .exportPdf(
                            params,
                            MsgClient.class,
                            list,
                            new FileOutputStream(file1));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void test_image() {
        List<StudentEntityImage> studentList = new ArrayList<StudentEntityImage>();
        StudentEntityImage StudentEntityImage = new StudentEntityImage();
        StudentEntityImage.setId("11231");
        StudentEntityImage.setName("撒旦法司法局");
        StudentEntityImage.setBirthday(new Date());
        StudentEntityImage.setSex(1);
        StudentEntityImage
                .setImage(PathUtil.getResourcesPath()+"/pdf/test.png");
        studentList.add(StudentEntityImage);
        studentList.add(StudentEntityImage);

        PdfExportParams params = new PdfExportParams("学生信息");
        try {
            PdfExportUtil
                    .exportPdf(
                            params,
                            StudentEntityImage.class,
                            studentList,
                            new FileOutputStream(file2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class StudentEntityImage extends StudentEntity {
        private static final long serialVersionUID = 1L;
        @Excel(height = 40, width = 60, type = 2, name = "相片")
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

}
