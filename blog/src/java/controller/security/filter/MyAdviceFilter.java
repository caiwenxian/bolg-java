package controller.security.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Iterator;


public class MyAdviceFilter extends AccessControlFilter {

	private String unauthorizedUrl = "/login";
	@Autowired
	SessionDAO sessionDAO;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);

		//角色权限验证 todo
        return subject.isAuthenticated();
    }

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() == null) {//表示没有登录，重定向到登录页面
			//token认证
			String token = request.getParameter("token");
			if (org.apache.commons.lang3.StringUtils.isNotBlank(token)) {
				Collection<Session> sessions = sessionDAO.getActiveSessions();
				for (Session session : sessions) {
					Object object = session.getAttribute(token);
					if (object != null) {
						System.out.println("token有效");
						return true;
					}
				}

			}
			saveRequest(request);
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			if (isAjaxRequest(httpServletRequest)) {
				//ajax返回未登录状态码
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "NOT_LOGIN");
				return false;
			}

			String refUrl = httpServletRequest.getRequestURI();
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			request.setAttribute("refUrl", refUrl);
			WebUtils.issueRedirect(request, httpServletResponse, unauthorizedUrl + "?refUrl=" + refUrl);
		} else {
			if (StringUtils.hasText(unauthorizedUrl)) {//如果有未授权页面跳转过去
				WebUtils.issueRedirect(request, response, unauthorizedUrl);
			} else {//否则返回401未授权状态码
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return false;
	}

	private Boolean isAjaxRequest(HttpServletRequest httpRequest) {
		return org.apache.commons.lang3.StringUtils.equalsIgnoreCase("XMLHttpRequest",
				httpRequest.getHeader("X-Requested-With"));

	}
}
