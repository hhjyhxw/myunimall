package com.icloud.exceptions;

import com.alibaba.fastjson.JSONObject;
import com.icloud.basecommon.model.ResultResponse;
import com.icloud.exceptions.unimall.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * API全局异常处理
 */
@ControllerAdvice(basePackages="com.icloud.api.unimall")
public class ApiUnimallGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiUnimallGlobalExceptionHandler.class);

//	@ExceptionHandler(BeanException.class)
//	public String HandleBeanException(Exception ex,HttpServletRequest request){
//		request.setAttribute("error_msg", ex.getMessage());
//		return "back_error";
//	}

	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResultResponse HandleServiceException(ServiceException e){
        ResultResponse gatewayResponse = new ResultResponse();
        gatewayResponse.setErrno(e.getCode());
        gatewayResponse.setErrmsg(e.getMessage());
        String result = JSONObject.toJSONString(gatewayResponse);
        logger.info("[用户请求] ServiceException, response=" + JSONObject.toJSONString(result));
		return gatewayResponse;
	}

    /**
     * 默认异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultResponse HandleApiException(Exception e){
        ResultResponse gatewayResponse = new ResultResponse();
        gatewayResponse.setErrno(500);
        gatewayResponse.setErrmsg(e.getMessage());
        String result = JSONObject.toJSONString(gatewayResponse);
        logger.info("[用户请求] Exception,response=" + JSONObject.toJSONString(result));
        e.printStackTrace();
        return gatewayResponse;
    }


}
