package github.ittranslationclub.utils.exception.vo;

/**
 * 错误信息编码接口
 *
 * @author hehedada
 * @date 2023/1/4 14:07
 */
public interface ExceptionErrorCode {

    /**
     * 获取编码
     */
    String getCode();

    /**
     * 获取消息对应国际化中的key
     */
    String getMsgCode();

}
