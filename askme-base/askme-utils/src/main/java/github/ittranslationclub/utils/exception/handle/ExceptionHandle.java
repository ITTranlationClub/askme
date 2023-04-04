package github.ittranslationclub.utils.exception.handle;

import github.ittranslationclub.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 错误信息处理配置为所有异常以及编码编排不同的返回信息.
 *
 * @author hehedada
 * @date 2023/1/4 13:48
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(Throwable.class)
    public Result<Object> handleDefaultException(Throwable defaultEx) {
        log.error("处理异常信息的通知内容->{}", defaultEx.getMessage());
        log.error("处理异常信息的通知->", defaultEx);
        if (defaultEx instanceof CommonException) {
            return Result.fail(defaultEx.getMessage());
        }
        return Result.fail(DefaultErrorMsg.SYSTEM_EXCEPTION.getMsgCode());
    }

    @ExceptionHandler(Exception.class)
    public Result<Object> exceptionHandler(Exception e) {
        log.error("处理异常信息的通知内容->{}", e.getMessage());
        log.error("处理异常信息的通知->", e);
        return Result.failMsg(e.getMessage(), e);
    }
}
