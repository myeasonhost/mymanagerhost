package com.eason.transfer.openapi.core.sdk.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @apiDefine 1user 用户API
 */
@FeignClient("service-transfer-api-user")
public interface IUserService {
    /**
     * @apiVersion 1.0.0
     * @apiGroup 1user
     * @api {POST} /user/login （1）用户登录
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
     * @apiParam {Integer="0=大厅","220=炸金花","620=德州扑克","720=二八杠","*编号*=*游戏*"} gameType 游戏类型
     *
     * @apiSuccess {String} result  登录结果
     * @apiSuccess {String} url     跳转页面
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "result": "登录成功",
     *     "url": "https://68lc.lc6689.com/index.html?account=91208_eason&token=eyJkYXRhIjoiOTEyMDhfZWFzb24iLCJjcmVhdGVkIjoxNTc0MDU4NzAzLCJleHAiOjE1MH0=.PiCifqQNwAUAixZhk6SZN/tyLJpKkfZ8gQ3VV/okMP8=&lang=zh-CN&route=68lcws.lc6689.com&loudou=https://cont.lc6689.com/statisticsHandle&gameId=0"
     *  }
     *
     */
    @PostMapping(value = "/user/login")
    LoginResponse login(@RequestBody LoginRequest request) throws Exception;


    /**
     * @apiVersion 1.0.0
     * @apiGroup 1user
     * @api {POST} /user/transferIn （2）转入上分
     * @apiName transferIn
     *
     *
     * @apiDescription
     * >  转入上分流程： </br>
     * >（1）校验转入参数</br>
     * >（2）验证用户权限</br>
     * >（3）钱包转出接口</br>
     * >（4）第三方上分接口</br>
     * >（5）记录转入日志，返回结果</br>
     *
     * @apiParam {String} username  用户名
     * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     * @apiParam {String="LC"} loginType  平台类型
     * @apiParam {Double} money 金额
     *
     * @apiSuccess {String} result  转入结果
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *    {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "result": "上分成功，可用余额306.65"
     * }
     *
     *
     */
    @PostMapping(value = "/user/transferIn")
    TransferInResponse transferIn(@RequestBody TransferInRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 1user
     * @api {POST} /user/transferOut （3）转出下分
     * @apiName transferOut
     *
     *
     * @apiDescription
     * >  转出下分流程： </br>
     * >（1）校验转出参数</br>
     * >（2）验证用户权限</br>
     * >（3）第三方下分接口</br>
     * >（4）钱包转出接口</br>
     * >（5）记录转出日志，返回结果</br>
     *
     * @apiParam {String} username  用户名
     * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     * @apiParam {String="LC"} loginType  平台类型
     * @apiParam {Double} money 金额
     *
     * @apiSuccess {String} result  转出结果
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *    {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "result": "下分成功，可用余额305.65"
     * }
     *
     */
    @PostMapping(value = "/user/transferOut")
    TransferOutResponse transferOut(@RequestBody TransferOutRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 1user
     * @api {POST} /user/queryBalance （4）查询余额
     * @apiName queryBalance
     *
     *
     * @apiDescription
     * >  查询余额流程： </br>
     * >（1）校验转出参数</br>
     * >（2）验证用户权限</br>
     * >（3）第三方查询余额接口</br>
     * >（4）记录转出日志，返回结果</br>
     *      *
     *      * @apiParam {String} username  用户名
     *      * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     *      * @apiParam {String="LC"} loginType  平台类型
     *      *
     *      * @apiSuccess {String} result  转出结果
     *      * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *    {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "money": 100,
     *     "result": "查询余额成功"
     * }
     *
     */
    @PostMapping(value = "/user/queryBalance")
    QueryBalanceResponse queryBalance(@RequestBody QueryBalanceRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 1user
     * @api {POST} /user/queryOrderStatus （5）查询订单状态
     * @apiName queryOrderStatus
     *
     *
     * @apiDescription
     * >  查询订单状态流程： </br>
     * >（1）校验转出参数</br>
     * >（2）验证用户权限</br>
     * >（3）第三方查询订单接口</br>
     * >（4）返回结果</br>
     *
     * @apiParam {String} username  用户名
     * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     * @apiParam {String="LC"} loginType  平台类型
     * @apiParam {String} orderId 订单号
     *
     * @apiSuccess {String} result  转出结果
     * @apiSuccess {Integer="1=正在处理中、2=成功、3=失败"} status  状态
     * @apiSuccess {Double} money  金额
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *    {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "status": 0,
     *     "money": 1,
     *     "result": "查询订单成功"
     * }
     *
     */
    @PostMapping(value = "/user/queryOrderStatus")
    OrderStatusResponse queryOrderStatus(@RequestBody OrderStatusRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 1user
     * @api {POST} /user/queryPlayerStatus （6）查询玩家在线状态
     * @apiName queryPlayerStatus
     *
     *
     * @apiDescription
     * >  查询玩家状态流程： </br>
     * >（1）校验转出参数</br>
     * >（2）验证用户权限</br>
     * >（3）第三方查询玩家状态接口</br>
     * >（4）返回结果</br>
     *
     * @apiParam {String} username  用户名
     * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     * @apiParam {String="LC"} loginType  平台类型
     *
     * @apiSuccess {String} result  转出结果
     * @apiSuccess {Integer="-1=不存在,0=存在,1=在线"} status  状态
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "status": 0,
     *     "result": "玩家状态返回成功"
     * }
     *
     */
    @PostMapping(value = "/user/queryPlayerStatus")
    PlayerStatusResponse queryPlayerStatus(@RequestBody PlayerStatusRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 2report
     * @api {POST} /user/getBetRecordList （1）投注明细记录
     * @apiName getBetRecordList
     *
     *
     * @apiDescription
     * >  投注明细记录流程： </br>
     * >（1）前提条件，平台注单拉取完成</br>
     * >（2）校验查询参数</br>
     * >（3）返回查询结果</br>
     *
     * @apiParam {String} [username]  用户名(=eason查询单个用户，=null查询所有用户)
     * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     * @apiParam {String="LC"} loginType  平台类型
     * @apiParam {String} startTime  投注开始日期(yyyy-MM-dd HH:mm:ss)
     * @apiParam {String} endTime  投注结束日期(yyyy-MM-dd HH:mm:ss)
     * @apiParam {Integer} page  第几页(0=第一页，1=第二页)
     * @apiParam {Integer} pageSize  每页显示多少行
     *
     * @apiSuccess {Long} total 		总数
     * @apiSuccess {List} rows 		rows->row
     * @apiSuccess {String} row.gameId 	游戏编号
     * @apiSuccess {String} row.username 	用户名
     * @apiSuccess {String} row.kindName 	游戏名称
     * @apiSuccess {String} row.roomName 	房间号
     * @apiSuccess {Double} row.betAmount 	投注金额
     * @apiSuccess {Double} row.winAmount 	输赢
     * @apiSuccess {Double} row.revenue     抽水
     * @apiSuccess {String} row.betTime 	投注时间
     *
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "total": 15,
     *     "list": [
     *         {
     *             "gameId": "50-1574227923-475016658-3",
     *             "username": "eason",
     *             "gameName": "看四张抢庄牛新手房",
     *             "roomName": "看四张抢庄牛",
     *             "betAmount": 1,
     *             "winAmount": 0.95,
     *             "revenue": 0.05,
     *             "betTime": "2019-11-20 13:32:28"
     *         },
     *         {
     *             "gameId": "50-1574227974-475018277-4",
     *             "username": "eason",
     *             "gameName": "抢庄牛牛新手房",
     *             "roomName": "抢庄牛牛",
     *             "betAmount": 2,
     *             "winAmount": -2,
     *             "revenue": 0,
     *             "betTime": "2019-11-20 13:33:22"
     *         },
     *         {
     *             "gameId": "50-1574228027-475019921-4",
     *             "username": "eason",
     *             "gameName": "炸金花新手房",
     *             "roomName": "炸金花",
     *             "betAmount": 1,
     *             "winAmount": -1,
     *             "revenue": 0,
     *             "betTime": "2019-11-20 13:34:15"
     *         }
     *     ]
     * }
     *
     */
    @PostMapping(value = "/user/getBetRecordList")
    PullBetResponse getBetRecordList(PullBetRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 2report
     * @api {POST} /user/getWalletList （2）用户账户流水
     * @apiName getWalletList
     *
     *
     * @apiDescription
     * >  用户账户流水流程： </br>
     * >（1）平台转入IN棋牌记录</br>
     * >（2）平台转出OUT棋牌记录</br>
     * >（3）IN=平台->棋牌，OUT=平台<-棋牌</br>
     *
     * @apiParam {String}  [username]  用户名(=eason查询单个用户，=null查询所有用户)
     * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     * @apiParam {String="LC"} loginType  平台类型
     * @apiParam {String} startTime  查询开始日期(yyyy-MM-dd HH:mm:ss)
     * @apiParam {String} endTime  查询结束日期(yyyy-MM-dd HH:mm:ss)
     * @apiParam {Integer} page  第几页(0=第一页，1=第二页)
     * @apiParam {Integer} pageSize  每页显示多少行
     *
     * @apiSuccess {Long} total 		总数
     * @apiSuccess {List} rows 		rows->row
     * @apiSuccess {String} row.recordId 	游戏编号
     * @apiSuccess {String} row.account 	账户
     * @apiSuccess {String} row.gameKindA 	平台A
     * @apiSuccess {String} row.optType 	操作类型
     * @apiSuccess {String} row.gameKindB 	棋牌B
     * @apiSuccess {Double} row.optAmount   操作金额
     * @apiSuccess {Double} row.balance     账户余额
     * @apiSuccess {Integer="1=正在处理中、2=成功、3=失败"} row.status      状态
     * @apiSuccess {String} row.createTime 	创建时间
     *
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "total": 1,
     *     "list": [
     *         {
     *             "recordId": "9120820191127110507590eason",
     *             "account": "eason",
     *             "gameKindA": "主账户",
     *             "optType": "IN",
     *             "gameKindB": "LC",
     *             "optAmount": "+100.0",
     *             "balance": 100,
     *             "status": 2,
     *             "createTime": "2019-11-27 11:05:07"
     *         }
     *     ]
     * }
     *
     */
    @PostMapping(value = "/user/getWalletList")
    WalletListResponse getWalletList(WalletListRequest request) throws Exception;


}
