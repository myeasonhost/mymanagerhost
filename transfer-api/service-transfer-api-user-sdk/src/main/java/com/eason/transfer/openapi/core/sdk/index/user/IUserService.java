package com.eason.transfer.openapi.core.sdk.index.user;

import com.eason.transfer.openapi.core.sdk.index.user.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(contextId = "index#IUserService",value = "service-transfer-api-user")
public interface IUserService {
    /**
     * @apiVersion 1.0.0
     * @apiGroup 3index
     * @api {POST} /index/user/register （3）用户注册
     * @apiName register
     *
     *
     * @apiDescription
     * >  用户注册流程： </br>
     * >（1）校验参数</br>
     * >（2）注册用户</br>
     * >（3）返回结果</br>
     *
     *
     * @apiParam {String} username  用户名
     * @apiParam {String} password  密码
     * @apiParam {String} surePassword  确认密码
     * @apiParam {String} realName  真实姓名
     * @apiParam {String} phoneNum  电话号码
     * @apiParam {String} email  电子邮箱
     * @apiParam {String} wechat  微信号
     * @apiParam {String} invite  推荐人
     *
     *
     * @apiSuccess {String} result  结果
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "errInfoList": [],
     *     "errorCount": 0,
     *     "exception": "",
     *     "result": "注册成功",
     *     "successCount": 1,
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/index/user/register")
    public UserInfoResponse register(@RequestBody UserInfoRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 3index
     * @api {POST} /index/user/login （4）用户登录
     * @apiName login
     *
     *
     * @apiDescription
     * >  用户登录流程： </br>
     * >（1）校验参数</br>
     * >（2）登录用户</br>
     * >（3）返回结果</br>
     *
     *
     * @apiParam {String} username  用户名
     * @apiParam {String} password  密码
     *
     * @apiSuccess {Long} userId  用户ID
     * @apiSuccess {String} username  用户名
     * @apiSuccess {String} token  token
     * @apiSuccess {String} result  结果
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "errInfoList": [],
     *     "errorCount": 0,
     *     "exception": "",
     *     "result": "用户登录成功",
     *     "successCount": 1,
     *     "token": "56619e408dae12a60356d3b91a67068e",
     *     "userId": 1,
     *     "username": "eason"
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/index/user/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 3index
     * @api {POST} /index/user/getUserInfo （5）用户详情
     * @apiName getUserInfo
     *
     *
     * @apiDescription
     * >  用户查询流程： </br>
     * >（1）校验参数</br>
     * >（2）查询用户</br>
     * >（3）返回结果</br>
     *
     *
     * @apiParam {String} userId  用户ID
     *
     *
     * @apiSuccess {Long} userId  用户ID
     * @apiSuccess {String} username  用户名
     * @apiSuccess {Double} mainMoney  主余额
     * @apiSuccess {String} realName  真实姓名
     * @apiSuccess {String} phoneNum  电话号码
     * @apiSuccess {String} email  电子邮箱
     * @apiSuccess {String} wechat  微信号
     * @apiSuccess {String} invite  推荐人
     * @apiSuccess {String} token  token
     * @apiSuccess {String} result  结果
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "createBy": "",
     *     "createTime": "2019-10-09 12:25:42",
     *     "email": "myeasonhost@gmail.com",
     *     "errInfoList": [],
     *     "errorCount": 0,
     *     "exception": "",
     *     "invite": "1234",
     *     "mainMoney": 31495.746,
     *     "phoneNum": "18672117311",
     *     "realName": "eason",
     *     "status": 0,
     *     "successCount": 1,
     *     "token": "56619e408dae12a60356d3b91a67068e",
     *     "updateBy": "",
     *     "updateTime": "",
     *     "userId": 1,
     *     "username": "eason",
     *     "wechat": "ludan"
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/index/user/getUserInfo")
    public UserInfoGetResponse getUserInfo(@RequestBody UserInfoGetRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 3index
     * @api {POST} /index/user/playGame （6）进入游戏
     * @apiName playGame
     *
     *
     * @apiDescription
     * >  进入游戏流程： </br>
     * >（1）校验参数</br>
     * >（2）进入游戏</br>
     * >（3）返回结果</br>
     *
     * @apiParam {String} action  操作
     * @apiParam {String} live  操作
     *
     *
     * @apiSuccess {String} result  结果
     * @apiSuccess {String} status  状态
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "errInfoList": [],
     *     "errorCount": 0,
     *     "exception": "",
     *     "result": "http://gci.xiaoyugongsi.com:81/forwardGame.do?params=cLZAuRYrVQe/1sCtGPXJFdKysMGmKdLte7DHl4u0OhgzQu1xpptPQEjZUMJWu5hMMYAg4EmNW1vAbNUDr4GY4bjZU+70nfO2axBdvUlmNR1iIoT7cudXbSqsvskQEJb5ymJOpvsboUqSocwcAVsCz8BvMFwBBudyaZiVaGBGe1Argjyb3IDPPpjmOCyK7n98DQ7uW/CQ5YfThzaA4pkIbQ==&key=4bdaf894c68f96e31d90fabd77dc0c51",
     *     "status": "10000",
     *     "successCount": 1
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/index/user/playGame")
    public PlayGameResponse playGame(@RequestBody PlayGameRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 3index
     * @api {POST} /index/user/getBalance （7）查询余额
     * @apiName getBalance
     *
     *
     * @apiDescription
     * >  查询余额流程： </br>
     * >（1）校验参数</br>
     * >（2）查询余额</br>
     * >（3）返回结果</br>
     *
     * @apiParam {String="2,16等"} type  平台编码
     *
     * @apiSuccess {String} userId  用户ID
     * @apiSuccess {String} username  用户名
     * @apiSuccess {String} money  用户余额
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "errInfoList": [],
     *     "errorCount": 0,
     *     "exception": "",
     *     "money": "0.80",
     *     "successCount": 1,
     *     "userId": 1,
     *     "username": "eason"
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/index/user/getBalance")
    public MoneyBalanceGetResponse getBalance(@RequestBody MoneyBalanceGetRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 3index
     * @api {POST} /index/user/transfer （8）用户转账
     * @apiName transfer
     *
     *
     * @apiDescription
     * >  用户转账流程： </br>
     * >（1）校验参数</br>
     * >（2）用户转账</br>
     * >（3）返回结果</br>
     *
     * @apiParam {String=""ag_99999,99999_ag"} action  平台类型
     * @apiParam {String="main_ag,ag_main"} transMethod  转账类型
     * @apiParam {String} credit  金额
     *
     * @apiSuccess {String} result  结果
     * @apiSuccess {String} status  状态
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "result": "登录成功",
     *  }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/index/user/transfer")
    public MoneyOUTResponse transfer(@RequestBody MoneyINRequest request) throws Exception;

}
