package github.ittranslationclub.askme.api.dao.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import github.ittranslationclub.askme.api.dao.po.base.PrefixTable;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Table;

/**
 * @program: askme
 * @description: 用户提问信息实体类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-28 20:56
 **/
@Data
@Builder
@Table(name = "t_user_question")
public class UserQuestion extends PrefixTable {

    @Column(comment = "用户唯一识别Id",type = MySqlTypeConstant.BIGINT)
    private Long userId;

    @Column(comment = "用户提问角色", type = MySqlTypeConstant.TEXT)
    private String userRole;

    @Column(comment = "用户提问内容", type = MySqlTypeConstant.TEXT)
    private String question;

    @Column(comment = "用户问题答案", type = MySqlTypeConstant.TEXT)
    private String aiAnswer;
}
