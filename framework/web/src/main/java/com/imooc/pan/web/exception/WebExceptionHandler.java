package com.imooc.pan.web.exception;

import com.imooc.pan.core.exception.RPanBusinessException;
import com.imooc.pan.core.exception.RPanFrameworkException;
import com.imooc.pan.core.response.R;
import com.imooc.pan.core.response.ResponseCode;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author 囍崽
 * version 1.0
 * 全局异常处理器
 */

/**
 * <body>
 * <p>
 * spring mvc架构中各层会出现大量的try{...} catch{...} finally{...}代码块，
 * 不仅有大量的冗余代码，而且还影响代码的可读性。这样就需要
 * 定义个全局统一异常处理器，以便业务层再也不必处理异常。
 * </p><br>
 * <p>
 *     <h4>为什么需要？</h4>
 * <ul>
 *     <li>不用强制写try-catch，由全局异常处理器统一捕获处理。</li>
 *     <li>自定义异常，只能用全局异常来捕获。不能直接返回给客户端，客户端是看不懂的，需要接入全局异常处理器</li>
 *     <li>JSR303规范的Validator参数校验器，参数校验不通过会抛异常，
 *     是无法使用try-catch语句直接捕获，只能使用全局异常处理器。</li>
 * </ul>
 * </p>
 * <p>
 * @ RestControllerAdvice作用：<br>
 * <ul>
 *     <li>@ControllerAdvice: 该注解标志着一个类可以为所有@RequestMapping处理方法
 *     提供通用的异常处理和数据绑定等增强功能。当应用到一个类上是，该类中定义的方法将在所有控制器类
 *     的请求处理链中生效</li>
 *     <li>@ResponseBody: 表示方法的返回值将被直接写入HTTP响应体中，通常配合Jackson或Gson等
 *     JSON库对象转换为JSON格式的响应。
 *     </li>
 * </ul>
 * @ ExceptionHandler注解：用于捕获Controller中抛出的指定类型的异常，
 * 从而达到全局不同类型的异常区别处理的目的
 * </p>
 * </body>
 */
@RestControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(value = RPanBusinessException.class)
    public R rPanBusinessExceptionHandler(RPanBusinessException e) {
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().stream().findFirst().get();
        return R.fail(ResponseCode.ERROR_PARAM.getCode(), objectError.getDefaultMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public R constraintDeclarationExceptionHandler(ConstraintViolationException e) {
        ConstraintViolation<?> constraintViolation = e.getConstraintViolations().stream().findFirst().get();
        return R.fail(ResponseCode.ERROR_PARAM.getCode(), constraintViolation.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public R missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return R.fail(ResponseCode.ERROR_PARAM);
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public R illegalStateExceptionHandler(IllegalStateException e) {
        return R.fail(ResponseCode.ERROR_PARAM);
    }

    @ExceptionHandler(value = BindException.class)
    public R bindExceptionHandler(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldErrors().stream().findFirst().get();
        return R.fail(ResponseCode.ERROR_PARAM.getCode(), fieldError.getDefaultMessage());
    }

    @ExceptionHandler(value = RPanFrameworkException.class)
    public R rPanFrameworkExceptionHandler(RPanFrameworkException e) {
        return R.fail(ResponseCode.ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public R runtimeExceptionHandler(RuntimeException e) {
        return R.fail(ResponseCode.ERROR.getCode(), e.getMessage());
    }
}
