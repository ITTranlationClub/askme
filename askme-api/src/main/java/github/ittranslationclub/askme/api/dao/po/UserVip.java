package github.ittranslationclub.askme.api.dao.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import github.ittranslationclub.askme.api.dao.po.base.PrefixTable;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: askme
 * @description: 用户vip信息
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-29 10:49
 **/
@Data
@Builder
@Table(name = "t_user_vip")
public class UserVip extends PrefixTable {

    @Column(comment = "用户唯一识别Id", type = MySqlTypeConstant.BIGINT)
    private Long userId;

    @Column(comment = "剩余使用次数", type = MySqlTypeConstant.BIGINT)
    private BigDecimal userCount;

//    private
}
