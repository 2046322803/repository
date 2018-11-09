package com.zuk.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class AccessFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

	/*
	 * filterType：过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
	 * pre：路由之前 routing：路由之时 post： 路由之后 error：发送错误调用
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/*
	 * filterOrder：过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/*
	 * shouldFilter：判断该过滤器是否需要被执行。这里可以写逻辑判断，是否要过滤，本文true，永远过滤。
	 * 
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/*
	 * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
	 * 
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
		Object accessToken = request.getParameter("token");
		if (accessToken == null) {
			log.warn("token is empty");
			ctx.setSendZuulResponse(false);// 令zuul过滤该请求，不对其进行路由
			ctx.setResponseStatusCode(401);// 设置其返回的错误码
			String body = "token is empty";
			ctx.setResponseBody(body);// 设置其返回的body
			return null;
		}
		log.info("ok");
		return null;
	}

}
