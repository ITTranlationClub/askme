package github.ittranslationclub.utils.file;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: askme
 * @description:
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-06 16:27
 **/
@Slf4j
public class FileUtils {

    public static String readFile(String filePath) {
        FileReader fileReader = new FileReader(filePath);
        return fileReader.readString();
    }

    public static void writeFile(String filePath, String content) {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(content, true);
    }
}
