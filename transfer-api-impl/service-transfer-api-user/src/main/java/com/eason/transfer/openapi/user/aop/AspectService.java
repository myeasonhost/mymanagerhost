package com.eason.transfer.openapi.user.aop;

import com.eason.transfer.openapi.core.common.response.Response;
import com.eason.transfer.openapi.user.dao.ChessGameConfigDao;
import com.eason.transfer.openapi.user.po.TChessGameConfigPo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class AspectService {

    @Autowired
    private ChessGameConfigDao chessGameConfigDao;

    @Around("@annotation(transferStart)")
    public Object aroundMethod(ProceedingJoinPoint pjd, TransferStart transferStart) throws Throwable {

        Object result = null;
        String loginType = null;
        String siteId = null;
        try {
            //执行目标方法
            Object[] args = pjd.getArgs();
            Class<?>[] paramsCls = new Class<?>[args.length];
            for (int i = 0; i < args.length; ++i) {
                paramsCls[i] = args[i].getClass();
                Method method1=args[i].getClass().getDeclaredMethod("getLoginType",null);
                if (method1!=null){
                    loginType=(String) method1.invoke(args[0]);
                }
                Method method2=args[i].getClass().getDeclaredMethod("getSiteId",null);
                if (method2!=null){
                    siteId=(String) method2.invoke(args[0]);
                }
            }
            if (StringUtils.isEmpty(loginType)){
                Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                Response response=(Response) method.getReturnType().newInstance();
                response.addErrInfo("transferStart","loginType不能为空",null);
                return response;
            }
            TChessGameConfigPo po=chessGameConfigDao.getTChessGameConfigPoByAndGameKind(loginType);
            if (po==null){
                Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                Response response=(Response) method.getReturnType().newInstance();
                response.addErrInfo("transferStart","loginType配置不存在="+loginType,null);
                return response;
            }
            if (po!=null && po.getStatus().byteValue()==3){
                Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                Response response=(Response) method.getReturnType().newInstance();
                response.addErrInfo("transferStart","该代理已经被废弃，请联系商务",null);
                return response;
            }
            if (po!=null && po.getStatus().byteValue()==2){
                Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                Response response=(Response) method.getReturnType().newInstance();
                response.addErrInfo("transferStart","正在维护",null);
                return response;
            }
            if (po!=null && po.getStatus().byteValue()==1){
                Map<String,String> map=new HashMap<>();
                String[] ary=po.getSiteId().split(","); //_1020,_1040,_1070,_1080
                for (String s:ary){ //_1020
                    String[] i=s.split("_");
                    map.put(i[1],po.getAgentId()+"_"+i[0]);
                }
                if(StringUtils.isEmpty(siteId)){
                    Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                    Response response=(Response) method.getReturnType().newInstance();
                    response.addErrInfo("transferStart","siteId不能为空",null);
                    return response;
                }
                if(!StringUtils.isEmpty(siteId) && !map.containsKey(siteId)){
                    Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                    Response response=(Response) method.getReturnType().newInstance();
                    response.addErrInfo("transferStart","siteId配置不存在="+siteId,null);
                    return response;
                }
            }

            result = pjd.proceed();
            //用新的参数值执行目标方法
//            result = pjd.proceed(new Object[]{"newSpring","newAop"});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
