package github.ittranslationclub.utils.file;/*
 * ClassName: RenameFileTest
 * Description:
 * @Author: zjh
 * @Create: 2023/4/9
 */

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BatchFileUtils {

    /**
     * 扫描文件
     */
    public static void scanFiles(String rootPath,Consumer<File> consumer) {
        try {
            File[] files = FileUtil.ls(rootPath);
            for (File file : files) {
                if (!file.getName().equals("img")) {
                    dfs(file, consumer);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> spiltIntoSize(String content) {
        List<String> resultList = new ArrayList<>();

        String[] blankStrList = content.split(System.lineSeparator());
        StringBuilder buffBuilder = new StringBuilder();
        for(String buffStr:blankStrList) {
            if((buffStr.length() + buffBuilder.length())>4000) {
                buffBuilder.append(System.lineSeparator());
                buffBuilder.append(System.lineSeparator());
                resultList.add(buffBuilder.toString());
                buffBuilder = new StringBuilder();
            }
            buffBuilder.append(buffStr);
        }
        if(!buffBuilder.isEmpty())
            resultList.add(buffBuilder.toString());
        return resultList;
    }

    /**
     * 重命名文件
     *
     * @param fileParent
     */
    private static void dfs(File fileParent, Consumer<File> consumer) {
        if(fileParent.isDirectory()) {
            File[] childrenFiles = fileParent.listFiles();
            for (File child : childrenFiles) {
                dfs(child, consumer);
            }
            String newFileName = fileParent.getName().replaceAll("(?: |-)", "");
            if(!newFileName.equals(fileParent.getName()))
                FileUtil.rename(fileParent, fileParent.getName().replaceAll("(?: |-)", ""),true);
            return;
        }
        consumer.accept(fileParent);
//        FileUtil.rename(fileParent, fileParent.getName().replaceAll("(?: |-)", ""), true);
    }
}
