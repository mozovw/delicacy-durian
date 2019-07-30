package com.delicacy.durian.validation.util;

import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * jsr303增强版工具类
 *
 * @author zyt
 * @create 2018-03-19 10:25
 **/
public class Jsr303PlusUtil {

    public static  <T> String checkObject(T t){
        if(null==t)return "对象为空";
        String check = Jsr303Util.check(t);
        if (isNotBlank(check))return check;
        return null;
    }

    public static  <T> String checkList(List<T> t){
        if(CollectionUtils.isEmpty(t))return "集合为空";
        StringBuilder builder = new StringBuilder();
        for(int i  = 0 ; i < t.size();i++){
            String str1 = checkObject(t.get(i));
            if(isNotBlank(str1)){
                builder.append(String.format("第%s条:error=(%s); ",i+1,str1));
            }
        }
        if(!isNotBlank(builder.toString()))
        return null;
        return builder.toString();
    }

    public static  <T> Map<Integer,String> checkList2Map(List<T> t){
        Map<Integer,String> map = new LinkedHashMap();
        if(CollectionUtils.isEmpty(t)){
            map.put(1,"集合为空");
            return map;
        }
        for(int i  = 0 ; i < t.size();i++){
            String str1 = checkObject(t.get(i));
            if(isNotBlank(str1)){
                map.put(i+1,str1);
            }
        }
        return map;
    }

    private static boolean isNotBlank(String string){
        return null!=string && !"".equals(string);
    }

    /*public static  <T> List<ImportResponse> checkList2List(List<T> t){
        List<ImportResponse> list = new ArrayList<>();
        if(CollectionUtils.isEmpty(t)){
            ImportResponse builder = new ImportResponse();
            builder.setMessage("集合为空");
            builder.setRowNum(1);
            list.add(builder);
            return list;
        }
        for(int i  = 0 ; i < t.size();i++){
            String str1 = checkObject(t.get(i));
            if(!StringUtils.isBlank(str1)){
                ImportResponse builder = new ImportResponse();
                builder.setMessage(str1);
                builder.setRowNum(i+1);
                list.add(builder);
            }
        }
        return list;
    }*/


}
