package github.ittranslationclub.utils.test;/*
 * ClassName: RenameFileTest
 * Description:
 * @Author: zjh
 * @Create: 2023/4/9
 */

import cn.hutool.core.io.FileUtil;

import java.io.File;

public class RenameFileTest {

    private static String rootPath = "D:\\project\\my\\ITTranlationClub\\test\\newContent";

    public static void main(String[] args) {
        scanFiles();
    }

    /**
     * 扫描文件
     */
    private static void scanFiles() {
        try {
            File[] files = FileUtil.ls(rootPath);
            for (File file : files) {
                if (!file.getName().equals("img")) {
                    dfs(file);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 重命名文件
     *
     * @param fileParent
     */
    private static void dfs(File fileParent) {
        File[] childrenFiles = fileParent.listFiles();
        if (childrenFiles == null || childrenFiles.length == 0) {
            FileUtil.rename(fileParent, fileParent.getName().replaceAll("(?: |-)", ""), true);
            return;
        }
        for (File child : childrenFiles) {
            dfs(child);
        }
        FileUtil.rename(fileParent, fileParent.getName().replaceAll("(?: |-)", ""), true);
    }
}
