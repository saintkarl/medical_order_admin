package com.karlchu.mo.web.filter;

import com.karlchu.mo.web.security.MyRequestWrapper;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class SSLSessionSharingFilter extends GenericFilterBean {
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException 
	{
		MyRequestWrapper myrequest = new MyRequestWrapper((HttpServletRequest)request);
		myrequest.setResponse((HttpServletResponse)response);
		chain.doFilter(myrequest, response);
	}
}
