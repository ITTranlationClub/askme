package github.ittranslationclub.common.dto.openai.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: askme
 * @description: 结果可选项视图层对象
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 18:50
 **/
@Data
public class ChoicesItemDto implements Serializable {

    String index;

    MessageItemDto message;
}
