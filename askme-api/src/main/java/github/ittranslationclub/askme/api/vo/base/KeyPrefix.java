package github.ittranslationclub.askme.api.vo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: askme
 * @description: 基础 vo 信息定义类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-24 20:20
 **/
@Data
public abstract class KeyPrefix {

    @ApiModelProperty(value = "当前 vo 唯一识别 id，新建时为空")
    private Long id;

    /**
     * 创建时间，发现问题就立刻改正，拖来拖去 难道就不用改了吗？
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;
}
