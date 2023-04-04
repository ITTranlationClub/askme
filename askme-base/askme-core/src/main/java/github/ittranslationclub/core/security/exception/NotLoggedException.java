package github.ittranslationclub.core.security.exception;

import github.ittranslationclub.utils.exception.handle.CommonException;

/**
 * 未登录的异常信息.
 *
 * @author hehedada
 * @date 2023/3/7 17:30
 */
public class NotLoggedException extends CommonException {

    public NotLoggedException(String message) {
        super(message);
    }
}
