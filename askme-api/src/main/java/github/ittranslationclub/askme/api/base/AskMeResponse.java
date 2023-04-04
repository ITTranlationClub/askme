package github.ittranslationclub.askme.api.base;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/6/28 0:35
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "Response", description = "数据返回包装类")
public class AskMeResponse<T> implements Serializable {

    /**
     * 结果码
     */
    @Column(comment = "返回结果码", value = "200 请求成功;-1 参数不合法;444 用户权限不正确;404 路径不存在;405 用户密码错误")
    int code;

    /**
     * 返回信息
     */
    @Column(comment = "具体返回结果")
    T data;

    /**
     * 错误描述信息
     */
    @Column(comment = "错误描述信息")
    String message;

    /**
     * 请求结果 按照前端要求,使用boolean类型
     */
    @Column(comment = "请求执行结果")
    Boolean isSuccess = Boolean.FALSE;

    public AskMeResponse() {}

    public void setStatusByEnum(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        if(responseEnum.equals(ResponseEnum.SUCCESS))
            isSuccess = Boolean.TRUE;
    }


}
