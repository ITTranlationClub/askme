package github.ittranslationclub.askme.api.dao.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import github.ittranslationclub.askme.api.dao.po.base.PrefixTable;
import lombok.Builder;
import lombok.Data;

/**
 * @program: askme
 * @description: 数据库用户信息实体类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-24 20:13
 **/
@Data
@Builder
@Table(name = "t_user", comment = "askme 用户信息表")
public class UserPo extends PrefixTable {

    @Column(comment = "用户识别 ID")
    private String uuid;

    @Column(comment = "用户名称")
    private String username;

    @Column(comment = "用户昵称")
    private String nickname;

    @Column(comment = "用户图标", type = MySqlTypeConstant.TEXT)
    private String avatar;

    @Column(comment = "博客", type = MySqlTypeConstant.TEXT)
    private String blog;

    @Column(comment = "公司")
    private String company;

    @Column(comment = "位置", type = MySqlTypeConstant.TEXT)
    private String description;

    @Column(comment = "邮箱")
    private String email;

    @Column(comment = "评论", type = MySqlTypeConstant.TEXT)
    private String remark;

    @Column(comment = "性别")
    private String gender;

    @Column(comment = "密钥")
    private String token;

    @Column(comment = "冗余用户信息", type = MySqlTypeConstant.TEXT)
    private String rawUserInfo;
}
