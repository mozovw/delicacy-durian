    package com.delicacy.durian.easypoi.util;
    
    import java.io.File;
    
    /**
     * @author yutao
     * @create 2019-08-01 16:24
     **/
    public class PathUtil {
        private static String projectPath = new File(PathUtil.class.getResource("/").getPath()).getAbsolutePath().replace("\\target\\classes", "");
        private static String resourcesPath = getProjectPath() + "\\src\\main\\resources";
    
        public static String getProjectPath() {
            return projectPath;
        }
    
        public static String getResourcesPath() {
            return resourcesPath;
        }
    }
