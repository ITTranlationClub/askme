package github.ittranslationclub.askme.chatgpt.app.service;

import java.io.File;
import java.util.List;

/**
 * @program: askme
 * @description: AI翻译接口定义
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-11 21:32
 **/
public interface AiTranslationService {

    String transMarkDown(String sourceText) throws Exception;

    void transMarkDownToFile(File fileInfo);
}
