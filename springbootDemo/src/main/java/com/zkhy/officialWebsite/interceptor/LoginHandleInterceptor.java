package com.zkhy.officialWebsite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zkhy.officialWebsite.common.Const;
import com.zkhy.officialWebsite.common.enums.ResponseCodeEnum;
import com.zkhy.officialWebsite.exception.InterceptorException;
import com.zkhy.officialWebsite.model.SysUser;

public class LoginHandleInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		SysUser sysUser = (SysUser) session.getAttribute(Const.CURRENT_SYSUSER);
		if(null == sysUser) {
			throw new InterceptorException(ResponseCodeEnum.NEED_LOGIN.getCode(),ResponseCodeEnum.NEED_LOGIN.getDesc());
		}
		return true;
	}
	
}
