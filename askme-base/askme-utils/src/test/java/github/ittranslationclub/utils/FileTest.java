package github.ittranslationclub.utils;/*
 * ClassName: FileTest
 * Description:
 * @Author: zjh
 * @Create: 2023/4/16
 */

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileTest {

    public static final String rootPath = "D:\\project\\my\\ITTranlationClub\\ModernSystemDesignInterview\\docs\\guide";

    public static void main(String[] args) {
        buildSidebarJson();
    }

    /**
     * 构造Sidebar
     */
    private static void buildSidebarJson() {
        try {
            File[] files = FileUtil.ls(rootPath);
            JSONArray jsonArray = new JSONArray();
            for (File file : files) {
                if (!file.getName().equals("img")) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("text", "课程介绍");
                    jsonObject.put("icon", "");
                    jsonObject.put("prefix", file.getName() + "/");
                    jsonObject.put("collapsible", true);
                    jsonObject.put("link", "/guide/" + file.getName());
                    File[] childrenFiles = file.listFiles();
                    List<String> children = new ArrayList<>();
                    for (File child : childrenFiles) {
                        children.add(FileUtil.getPrefix(child.getName()));
                    }
                    jsonObject.put("children", children);
                    jsonArray.add(jsonObject);
                }
            }
            JSONObject sidebar = new JSONObject();
            sidebar.put("/guide/", jsonArray);
            log.info("构造好的Sidebar " + sidebar.toJSONString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
