package github.ittranslationclub.common.dto.openai;

import github.ittranslationclub.common.dto.openai.base.MessageItemDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: askme
 * @description: 视图层用户提问信息存储类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 18:33
 **/
@Data
//@ApiModel(value = "ChatMessagesVo")
public class ChatMessagesDto implements Serializable {

    String model;

    List<MessageItemDto> messages;
}
