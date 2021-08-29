package org.morgorithm.websocket.configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
//OncePerRequestFilter는 추상 클래스로 제공되는 필터로 가장 일반적이며 매번 동작하는 기본적인 필터이다.
public class CORSFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //모든 오리진으로부터 API요청을 수락한다.
        //response.setHeader("Access-Control-Allow-Origin", "*");
        //브라우저에게 response를 request의 credentials mode가 포함될 때 프론트엔드 자바스크립트 코드에 노출시킬지 결정한다
        //response.setHeader("Access-Control-Allow-Credentials","");
        //(that is the information contained in the Access-Control-Allow-Methods and Access-Control-Allow-Headers headers)
        //response 헤더 중 하나로 얼마나 오래동안 preflight request(Access-Control-Allow-Methods와 Access-Control-Allow-Headers 헤더의 정보를 가지고 있는)가 캐싱될 지 시간을 정한다.
        response.setHeader("Access-Control-Max-Age","3600");
        //response 헤더 중 하나로 preflight request의 response로 쓰이며 이는 어떤 HTTP 헤더가 실제 request 도중에 사용될 수 있는지 명시하는 Access-Control-Request-Headers를 포함한다.
        response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");

        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            filterChain.doFilter(request,response);
        }
    }
}
