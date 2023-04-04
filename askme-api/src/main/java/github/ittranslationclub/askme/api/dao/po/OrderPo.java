package github.ittranslationclub.askme.api.dao.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import github.ittranslationclub.askme.api.dao.po.base.PrefixTable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: askme
 * @description: 订单信息
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-29 10:49
 **/
public class OrderPo extends PrefixTable {

    @Column(comment = "用户唯一识别Id", type = MySqlTypeConstant.BIGINT)
    private Long userId;

    @Column(comment = "用户支付金额", type = MySqlTypeConstant.BIGINT)
    private BigDecimal payment;

    @Column(comment = "订单支付时间")
    private Date payTime;
}
