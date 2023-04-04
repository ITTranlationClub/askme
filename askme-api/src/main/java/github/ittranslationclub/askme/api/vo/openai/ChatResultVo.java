package github.ittranslationclub.askme.api.vo.openai;

import github.ittranslationclub.askme.api.vo.openai.base.ChoicesItemVo;
import lombok.Data;

import java.util.List;

/**
 * @program: askme
 * @description: chatAi 返回结果信息实体类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 18:49
 **/
@Data
public class ChatResultVo {

    String id;

    String object;

    String created;

    List<ChoicesItemVo> choices;
}
