package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: wrk
 * Date: 2019/5/23
 * Time: 17:40
 * Description:
 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
 * pre：路由之前
 * routing：路由之时
 * post： 路由之后
 * error：发送错误调用
 * filterOrder：过滤的顺序
 * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
 * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
 */
@Component
public class MyFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    /**
     * pre：可以在请求被路由之前调用
     * routing： 路由请求时被调用
     * post：在routing和error过滤器之后被调用
     * error:处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过int值来定义过滤器的执行顺序，数值越小优先级越高。
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean值来判断该过滤器是否要执行。我们可以通过此方法来指定过滤器的有效范围。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。在该函数中，我们可以实现自定义的过滤逻辑，来确定是否要拦截当前的请求，
     * 不对其进行后续的路由，或是在请求路由返回结果之后，对处理结果做一些加工等。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
            //setSendZuulResponse其设为false代表的意思是,这个请求最终不会被zuul转发到后端服务器,
            // 但是如果当前Filter后面还存在其他Filter,那么其他Filter仍然会被调用到,
            // 所以一般我们在Filter的shouldFilter方法中,都会通过
            ctx.setSendZuulResponse(false);
            //设置状态码
            ctx.setResponseStatusCode(401);
            try {
                //发送一段消息
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}
            return null;
        }
        log.info("ok");
        return null;
    }
}
