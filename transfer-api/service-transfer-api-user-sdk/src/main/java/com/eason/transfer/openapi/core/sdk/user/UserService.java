package com.eason.transfer.openapi.core.sdk.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @apiDefine user 用户API
 */
@FeignClient("service-transfer-api-user")
public interface UserService {
    /**
     * @apiVersion 1.0.0
     * @apiGroup user
     * @api {POST} /user/login 用户登录
     * @apiName login
     *
     *
     * @apiDescription
     * >  登录流程： </br>
     * >（1）校验登录参数</br>
     * >（2）验证用户权限</br>
     * >（3）登录第三方接口</br>
     * >（4）登录成功，初始化本地用户</br>
     * >（5）返回结果</br>
     *
     * @apiParam {String} username  用户名
     * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     * @apiParam {String="LC"} loginType  平台类型
     * @apiParam {String="0=大厅","220=炸金花","620=德州扑克","720=二八杠","*编号*=*游戏*"} [gameType=0]  游戏类型
     *
     * @apiSuccess {String} result  登录结果
     * @apiSuccess {String} url     跳转页面
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *      {
     *          "result": "登录成功",
     *          "url": "https://68lc.lc6689.com/index.html?account=91208_eason&token=eyJkYXRhIjoiOTEyMDhfZWFzb24iLCJjcmVhdGVkIjoxNTczNzEyMzY0LCJleHAiOjE1MH0=.8JrH1FN/gIBe3+4Xa1++nj0hXRqJMcYVGJtJhrW2FE4=&lang=zh-CN&route=68lcws.lc6689.com&loudou=https://cont.lc6689.com/statisticsHandle&gameId=0"
     *      }
     *
     */
    @PostMapping(value = "/user/login")
    LoginResponse login(@RequestBody LoginRequest request);


    /**
     * @apiVersion 1.0.0
     * @apiGroup user
     * @api {POST} /user/transfer/in 转入上分
     * @apiName transfer/in
     *
     *
     * @apiDescription
     * >  转入上分流程： </br>
     * >（1）校验转入参数</br>
     * >（2）验证用户权限</br>
     * >（3）钱包转出接口</br>
     * >（4）第三方上分接口，若失败，发送MQ，进入冲正流程</br>
     * >（5）返回结果</br>
     *
     * @apiParam {String} username  用户名
     * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     * @apiParam {String="LC"} loginType  平台类型
     * @apiParam {Double} money 金额
     *
     * @apiSuccess {String} result  转入结果
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *      {
     *          "result": "转入成功",
     *      }
     *
     */
    @PostMapping(value = "/user/transfer/in")
    TransferInResponse transferIn(@RequestBody TransferInRequest request);

    /**
     * @apiVersion 1.0.0
     * @apiGroup user
     * @api {POST} /user/transfer/out 转出下分
     * @apiName transfer/out
     *
     *
     * @apiDescription
     * >  转出下分流程： </br>
     * >（1）校验转出参数</br>
     * >（2）验证用户权限</br>
     * >（3）第三方下分接口</br>
     * >（4）钱包转出接口，若失败，发送MQ，进入冲正流程</br>
     * >（5）返回结果</br>
     *
     * @apiParam {String} username  用户名
     * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     * @apiParam {String="LC"} loginType  平台类型
     * @apiParam {Double} money 金额
     *
     * @apiSuccess {String} result  转出结果
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *      {
     *          "result": "转出成功",
     *      }
     *
     */
    @PostMapping(value = "/user/transfer/out")
    TransferOutResponse transferOut(@RequestBody TransferOutRequest request);

}
