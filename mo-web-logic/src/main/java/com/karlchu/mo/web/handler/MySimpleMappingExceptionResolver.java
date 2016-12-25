package com.karlchu.mo.web.handler;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MySimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	private final Logger logger = Logger.getLogger(getClass());

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			Exception ex) {
		logger.error(ex.getMessage(), ex);
        return super.doResolveException(request, response, handler, ex);
	}
}
