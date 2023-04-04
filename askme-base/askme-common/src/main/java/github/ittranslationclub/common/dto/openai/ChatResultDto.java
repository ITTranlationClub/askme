package github.ittranslationclub.common.dto.openai;

import github.ittranslationclub.common.dto.openai.base.ChoicesItemDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: askme
 * @description: chatAi 返回结果信息实体类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 18:49
 **/
@Data
public class ChatResultDto implements Serializable {

    String id;

    String object;

    String created;

    List<ChoicesItemDto> choices;
}
