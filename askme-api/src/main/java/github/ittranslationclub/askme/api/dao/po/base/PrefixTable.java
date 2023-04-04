package github.ittranslationclub.askme.api.dao.po.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: askme
 * @description: 相同字段定义类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-24 20:19
 **/
@Data
public abstract class PrefixTable extends BaseModel {

    /**
     * 表默认 Id
     */
    @TableId(type = IdType.AUTO)
    @IsKey
    @IsAutoIncrement
    @Column(comment = "当前数据表 primary key", type = MySqlTypeConstant.BIGINT)
    private Long id;

    @Column(comment = "备注")
    private String remark;

    @Column(comment = "创建日期")
    private String createDate;

    @Column(comment = "创建人")
    private String createId;

    @Column(comment = "更新日期")
    private LocalDateTime updateDate;

    @Column(comment = "更新人", type = MySqlTypeConstant.BIGINT)
    private Long updateId;

    @Column(comment = "是否删除, 0:否, 1:是")
    private int deleted;

}