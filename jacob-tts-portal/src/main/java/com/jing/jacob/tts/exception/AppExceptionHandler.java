package com.jing.jacob.tts.exception;

import com.jing.jacob.tts.constants.ErrorType;
import com.jing.jacob.tts.utils.ResultUtil;
import com.jing.jacob.tts.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 异常处理器
 */
@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    /**
     * 400 参数不完整错误.
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) throws Exception {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = "参数不合法";
        if (errors.size() > 0) {
            message = errors.get(0).getDefaultMessage();
        }

        AppException appException = new AppException(ErrorType.PARAM_NOT_VALID, message);
        ResultVO<?> bizResult = ResultUtil.error(appException.getErrCode(), appException.getErrMsg());
        return new ResponseEntity<>(bizResult, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultVO<?>> handleException(Exception e) {
        log.error(e.getMessage(), e);
        ResultVO<?> bizResult = ResultUtil.error(ErrorType.APP_ERROR, e);
        return new ResponseEntity<ResultVO<?>>(bizResult, HttpStatus.OK);
    }

}
