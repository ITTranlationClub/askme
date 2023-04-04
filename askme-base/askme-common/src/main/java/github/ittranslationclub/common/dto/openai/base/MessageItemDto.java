package github.ittranslationclub.common.dto.openai.base;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: askme
 * @description: 子提问信息
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 18:34
 **/
@Data
//@ApiModel("信息内容视图层对象")
public class MessageItemDto implements Serializable {

//    @ApiModelProperty(value = "提问对象, 默认值为 user/system/ansistant")
    String role;
//    @ApiModelProperty(value = "用户提问内容")
    String content;
}
