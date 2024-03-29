package com.codesqaude.cocomarco.common.auth;

import com.codesqaude.cocomarco.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.UUID;

import static com.codesqaude.cocomarco.util.JwtUtils.HEADER_TYPE;

@Component
@RequiredArgsConstructor
public class UserIdHandlerMethodResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    public UUID resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String jwt = webRequest.getHeader(HEADER_TYPE);

        Object infoFromJwt = JwtUtils.getId(jwt);
        return UUID.fromString(String.valueOf(infoFromJwt));
    }
}
