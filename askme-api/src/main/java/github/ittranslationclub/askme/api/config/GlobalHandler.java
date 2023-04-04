package github.ittranslationclub.askme.api.config;

import github.ittranslationclub.askme.api.base.AskMeResponse;
import github.ittranslationclub.askme.api.base.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: askme
 * @description: 异常信息捕获 控制器实现
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-24 21:09
 **/
@Slf4j
@RestControllerAdvice
public class GlobalHandler {
    
    // 这里就是通用的异常处理器了,所有预料之外的Exception异常都由这里处理
    @ExceptionHandler(Exception.class)
    public AskMeResponse<Object> exceptionHandler(Exception e) {
        AskMeResponse<Object> response = new AskMeResponse<>();
        // 为了方便调试，错误直接通知前端
        response.setCode(ResponseEnum.PARAM_ERROR.getCode());
        response.setData(e);
        response.setMessage(e.getMessage());
        log.error(e.getMessage(), e);
        return response;
    }
}
