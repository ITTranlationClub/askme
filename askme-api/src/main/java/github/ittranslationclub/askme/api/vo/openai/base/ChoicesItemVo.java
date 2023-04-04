package github.ittranslationclub.askme.api.vo.openai.base;

import lombok.Data;

/**
 * @program: askme
 * @description: 结果可选项视图层对象
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 18:50
 **/
@Data
public class ChoicesItemVo {

    String index;

    MessageItemVo message;
}
