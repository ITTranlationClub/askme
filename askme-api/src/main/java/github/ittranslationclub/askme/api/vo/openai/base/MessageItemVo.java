package github.ittranslationclub.askme.api.vo.openai.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: askme
 * @description: 子提问信息
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 18:34
 **/
@Data
@ApiModel("信息内容视图层对象")
public class MessageItemVo {

    @ApiModelProperty(value = "提问对象, 默认值为 user/system/ansistant")
    String role;
    @ApiModelProperty(value = "用户提问内容")
    String content;
}
