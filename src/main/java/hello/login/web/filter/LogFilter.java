package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRquest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("log filter doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRquest;
        String requestURI = httpServletRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();
        try {
            log.info("REQUEST [{}][{}]",uuid, requestURI);
            filterChain.doFilter(servletRquest, servletResponse);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("RESPONSE [{}][{}]",uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("log filter destroy");
    }
}
