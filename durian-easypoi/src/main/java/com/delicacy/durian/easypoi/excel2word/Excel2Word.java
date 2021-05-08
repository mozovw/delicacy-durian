package com.delicacy.durian.easypoi.excel2word;


import com.delicacy.durian.easypoi.excel.entity.ExcelVerifyEntity;
import com.delicacy.durian.easypoi.util.PathUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.word.WordExportUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Excel2Word {

    static File file12 = new File(PathUtil.getResourcesPath() + "/excel/excelimport.xlsx");
    static File file13 = new File(PathUtil.getResourcesPath() + "/excel/excelverfiy.xlsx");
    static File file14 = new File(PathUtil.getResourcesPath() + "/excel/excelexport14.xlsx");

    static String dirPath = PathUtil.getResourcesPath() + "/excel2word/dir";
    static File file15 = new File(PathUtil.getResourcesPath() + "/excel2word/b319.xls");
    static File file16 = new File(PathUtil.getResourcesPath() + "/excel2word/a321.docx");

    public static void main(String[] args) {
        batchWriteWord(readExcel());
    }


    private static List<Map<String, Object>> readExcel() {
        try {
            ImportParams params = new ImportParams();
            List<Map<String, Object>> list = ExcelImportUtil
                    .importExcel(file15,
                            Map.class, params);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void batchWriteWord(List<Map<String, Object>> listMap) {
        listMap.stream().forEach(e -> writeWord(e));
    }

    private static void writeWord(Map<String, Object> map) {
        try {
            Object fileName = map.get("文件名");


            XWPFDocument doc = (new ParseWord07()).parseWord(
                    file16.getAbsolutePath(),
                    map);
            File file = new File(dirPath);
            if (!file.exists()){
                file.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(new File(dirPath, fileName + ".docx"));
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test_verify() {
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
