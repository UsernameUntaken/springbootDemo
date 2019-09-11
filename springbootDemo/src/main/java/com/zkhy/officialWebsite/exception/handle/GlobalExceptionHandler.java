package com.zkhy.officialWebsite.exception.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkhy.officialWebsite.common.enums.ResponseCodeEnum;
import com.zkhy.officialWebsite.common.serverResponse.ServerResponse;
import com.zkhy.officialWebsite.exception.InterceptorException;
import com.zkhy.officialWebsite.exception.ServiceException;
import com.zkhy.officialWebsite.util.StackTraceUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ServerResponse<?> validationExceptionHandler(MethodArgumentNotValidException exception){
		BindingResult bindingResult = exception.getBindingResult();
		StringBuilder errorMsg = new StringBuilder(bindingResult.getFieldErrors().size()*16);
		errorMsg.append(ResponseCodeEnum.INVALID_PARAM.getDesc());
		for(int i=0; i<bindingResult.getFieldErrors().size(); i++) {
			if(i>0) {
				errorMsg.append(",");
			}
			FieldError fieldError = bindingResult.getFieldErrors().get(i);
			errorMsg.append(fieldError.getField());
			errorMsg.append(":");
			errorMsg.append(fieldError.getDefaultMessage());
		}
		LOGGER.info("参数校验错误。code：{} msg: {}", ResponseCodeEnum.INVALID_PARAM.getCode(), errorMsg.toString());
        return ServerResponse.createByErrorCodeMessage(ResponseCodeEnum.INVALID_PARAM.getCode(),errorMsg.toString());
	}
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public  ServerResponse<?> serviceExecptionHandler(ServiceException ex) {
		LOGGER.info("业务错误。code：{} msg: {}", ex.getCode(), ex.getMsg());
		return ServerResponse.createByErrorCodeMessage(ex.getCode(), ex.getMsg());
	}
	
	@ExceptionHandler(InterceptorException.class)
	public @ResponseBody ServerResponse<?> InterceptorExecptionHandler(InterceptorException ex) {
		LOGGER.info("拦截器错误。code：{} msg: {}", ex.getCode(), ex.getErrorMsg());
		return ServerResponse.createByErrorCodeMessage(ex.getCode(), ex.getErrorMsg());
	}
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public  ServerResponse<?> errorHandler(Exception ex) {
    	LOGGER.error("系统错误。msg: {}", StackTraceUtil.getStackTraceInfo(ex));
        //return ServerResponse.createByError();
        return ServerResponse.createByErrorCodeMessage(ResponseCodeEnum.ERROR.getCode(), StackTraceUtil.getStackTraceInfo(ex));
    }
}
