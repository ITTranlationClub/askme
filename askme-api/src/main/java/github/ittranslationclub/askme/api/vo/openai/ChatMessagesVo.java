package github.ittranslationclub.askme.api.vo.openai;

import github.ittranslationclub.askme.api.vo.openai.base.MessageItemVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: askme
 * @description: 视图层用户提问信息存储类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 18:33
 **/
@Data
@ApiModel(value = "ChatMessagesVo")
public class ChatMessagesVo {

    @ApiModelProperty(value = "所用chat模型")
    String model;

    @ApiModelProperty(value = "用户提问信息, list格式存储", dataType = "list")
    List<MessageItemVo> messages;
}
