package com.icloud.exceptions;

import com.alibaba.fastjson.JSONObject;
import com.icloud.basecommon.model.MallResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * API全局异常处理
 */
@ControllerAdvice(basePackages="com.icloud.api.newmall")
public class ApinNewmallGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApinNewmallGlobalExceptionHandler.class);

//	@ExceptionHandler(BeanException.class)
//	public String HandleBeanException(Exception ex,HttpServletRequest request){
//		request.setAttribute("error_msg", ex.getMessage());
//		return "back_error";
//	}

	@ExceptionHandler(MallServiceException.class)
	@ResponseBody
	public MallResponse HandleServiceException(MallServiceException e){
        MallResponse response = new MallResponse();
        response.setCode("0001");
        response.setMessage(e.getMessage());
       response.setData(false);
        String result = JSONObject.toJSONString(response);
        logger.info("[用户请求] MallServiceException, response=" + JSONObject.toJSONString(result));
		return response;
	}

    /**
     * 默认异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MallResponse HandleApiException(Exception e){
        MallResponse response = new MallResponse();
        response.setCode("500");
        response.setMessage(e.getMessage());
        response.setData(false);
        String result = JSONObject.toJSONString(response);
        logger.info("[用户请求] Exception,response=" + JSONObject.toJSONString(result));
        e.printStackTrace();
        return response;
    }


}
