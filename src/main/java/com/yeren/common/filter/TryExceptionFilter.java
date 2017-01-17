package com.yeren.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.yeren.common.constrant.ExceptionExcuteModel;
import com.yeren.common.exception.BaseException;
import com.yeren.common.result.BaseResult;

/**  
 * 异常统一处理过滤器
 */
@Component(value = "tryExceptionFilter")
public class TryExceptionFilter implements Filter {

    private static final String RESPONSE_ENCODING="UTF-8";
    private static final String ERROR="500";
    private static final String ERROR_MSG="系统异常:";
    
    
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			chain.doFilter(request, response);
		}catch (IOException e) {
			printExceptionAndresult(response, e);
        }catch (Exception e) {
        	Throwable th=e.getCause();
            if (th instanceof BaseException) {
    			BaseException excep = (BaseException) th;
    			matchModelExcute(response, e, excep);
    		}else{
    			printExceptionAndresult(response, e);
    		}
		}catch (Throwable e) {
			
		}
		
		System.out.println("f");

	}

	/**
	 * IO异常
	 * @param response
	 * @param e
	 * @throws IOException
	 */
	private void printExceptionAndresult(ServletResponse response, Exception e)
			throws IOException {
		String uuid=UUID.randomUUID().toString().replace("-", "");
		resultJSON(response, ERROR_MSG+uuid,ERROR);
		e.printStackTrace();
	}
	
	/**
	 * 初始化JSON数据并返回到前端
	 * @param response
	 * @param message
	 * @throws IOException
	 */
	private void resultJSON(ServletResponse response,String message,String code)
			throws IOException {
		
		BaseResult<Object> result=new BaseResult<Object>(message,code,null);
		PrintWriter war=response.getWriter();
		response.setCharacterEncoding(RESPONSE_ENCODING);  
		response.setContentType("application/json; charset=utf-8");  
		
		try {
			JSONObject fromObject = JSONObject.fromObject(result);
			war.write(fromObject.toString());
			System.out.println("异常捕获消息返回:"+fromObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	


	/**
	 * 匹配异常处理模式
	 * @param response
	 * @param e
	 * @param excep
	 * @throws IOException
	 */
	private void matchModelExcute(ServletResponse response, Exception e,BaseException excep) throws IOException {
		String message=null;
		if(!StringUtils.isEmpty(message))
			message=excep.getErrorMessage();
		else{
			e.printStackTrace();
			message="未知系统异常";
		}
		String code=excep.getCode();
		if(ExceptionExcuteModel.NOTHING.equals(excep.getModel()))
			e.printStackTrace();
		else if(ExceptionExcuteModel.RESTURN_JSON.equals(excep.getModel()))
			resultJSON(response, message,code);
		else if(ExceptionExcuteModel.REDIRECT_PAGE.equals(excep.getModel()))
			redirectMessagePage(response,message);
		else{
			printExceptionAndresult(response, e);
		}
	}

	/**
	 * 初始化错误信息跳转到错误页面
	 * @param response
	 * @param message
	 * @throws IOException
	 */
	private void redirectMessagePage(ServletResponse response, String message)
			throws IOException {
			//		String parameter="?msg="+URLEncoder.encode(message,"UTF-8");
			//		String url=redirect.getFoundsMsg()+parameter;
			//		HttpServletResponse resp=(HttpServletResponse)response;
			//		response.setCharacterEncoding(RESPONSE_ENCODING);
			//		response.setContentType("text/html; charset=utf-8");  
			//		resp.sendRedirect(url);
	}
	
	public <T extends BaseException>void matchException(ServletResponse response, T e)throws IOException  {
		matchModelExcute(response, e, e);
	}
	
	
	@Override
	public void destroy() {

	}
}
