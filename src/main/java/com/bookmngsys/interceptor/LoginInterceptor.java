package com.bookmngsys.interceptor;

import com.bookmngsys.domain.UserDto;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author Newway
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //Origin, X-Requested-With, Content-Type, Accept,Access-Token
        response.setHeader("Access-Control-Allow-Headers", "Authorization,Origin, X-Requested-With, Content-Type, Accept,Access-Token");

        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("USER_SESSION");
        if (userDto != null) {
            request.setAttribute("userId", userDto.getId());
            request.setAttribute("user", userDto);
            return true;
        }
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        out.append("{\"meta\":{\"success\":false,\"message\":\"请先登录\"},\"data\":null}");
        return false;
    }

}
