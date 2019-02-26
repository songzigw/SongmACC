package cn.songm.acc.webapi.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.songm.common.beans.Result;
import cn.songm.common.service.GeneralErr;
import cn.songm.common.utils.JsonUtils;
import cn.songm.monitor.api.Monitor;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	@Resource(name = "monitor")
    private Monitor monitor;
	
	@Override
	public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object obj,
			Exception e) {
		monitor.onApiError(req, e);
		Result<Object> result = new Result<Object>();
        result.setSucceed(false);
        result.setErrorCode(GeneralErr.UNKNOW.getErrCode());
        result.setErrorDesc("系统错误");
        res.setContentType("text/json; charset=UTF-8");
        PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e1) {
		}
        out.print(JsonUtils.getInstance().toJson(result));
		return null;
	}
}
