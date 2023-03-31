package org.sample.moviedb.resolver;

import javax.servlet.http.HttpSession;

import org.sample.moviedb.annotation.CheckUser;
import org.sample.moviedb.dto.LoginedUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckUserResolver implements HandlerMethodArgumentResolver {

	private final HttpSession httpSession;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean isCheckUserAnnotation = parameter.getParameterAnnotation(CheckUser.class) != null;
		boolean isLoginedUserclass = LoginedUser.class.equals(parameter.getParameterType());
		return isCheckUserAnnotation && isLoginedUserclass;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return httpSession.getAttribute("loginedUser");
	}

}
