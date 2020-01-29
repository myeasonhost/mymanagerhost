//package com.ds.money.interceptor;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.ds.money.service.MoneyCenterService;
//public class SecurityInterceptor implements HandlerInterceptor{
//	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	private MoneyCenterService moneyServce;
//	@Override
//	public void afterCompletion(HttpServletRequest arg0,
//			HttpServletResponse arg1, Object arg2, Exception arg3)
//			throws Exception {
//
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
//			Object arg2, ModelAndView arg3) throws Exception {
//
//	}
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//			Object handler) throws Exception {
//
////		String ip = RequestUtils.getClientIp(request);
////		Set<String> ipSet = moneyServce.getIpSet();
////		if(!ipSet.contains(ip)){
////			JSONObject result = new JSONObject();
////			result.put("message", "ip is not allowed request,your ip :"+ip);
////			result.put("code", 110004);
////			logger.info("拦截器拦截IP："+ip+"不允许访问");
////			response.setStatus(400);
////			PrintWriter out = response.getWriter();
////			out.write(result.toJSONString());
////			out.flush();
////			return false;
////		}
//		return true;
//	}
//
//}
