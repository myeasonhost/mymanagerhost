package com.ds.money.service;

import com.ds.money.exception.MoneyServiceException;
import com.ds.money.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @apiDefine 4user app用户钱包API
 */
@FeignClient(contextId = "money#UserMoneyService",value = "service-transfer-api-moneys")
public interface UserMoneyService {


    /**
     * @apiVersion 1.0.0
     * @apiGroup money
     * @api {POST} /money/getUserMoney 查询余额
     * @apiName getUserMoney
     *
     * @apiDescription
     *
     * @apiParam {String} username 用户姓名
     * @apiParam {String} fromKey  站点key,后期用于多站点多平台的一个配置参数
     * @apiParam {String} key      平台秘钥，key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
     * @apiParam {String} siteId   站点id,后期用于多站点多平台的一个配置参数
     *
     * @apiSuccess {Integer} moey 		金额
     *
     * * @apiSuccessExample Success-Response:
     *      *   HTTP/1.1 200 OK
     * {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "moey": 200.00000,
     *     "result": "查询余额成功！"
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/money/getUserMoney")
    UserMoneyResponse getUserMoney(@RequestBody UserMoneyRequest request) throws MoneyServiceException;

    /**
     * @apiVersion 1.0.0
     * @apiGroup money
     * @api {POST} /money/transMoney 钱包转账
     * @apiName transMoney
     *
     * @apiDescription
     *
     * @apiParam {String} username 用户姓名
     * @apiParam {String} fromKey  站点key,后期用于多站点多平台的一个配置参数
     * @apiParam {String} key      平台秘钥，key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
     * @apiParam {String} siteId   站点id,后期用于多站点多平台的一个配置参数
     * @apiParam {String} remitno  流水号
     * @apiParam {String} transType 转账类型，in表示转入，out表示转出
     * @apiParam {String} remit     转账金额
     * @apiParam {String} wagerCancel 是否撤销订单，0是，1否
     * @apiParam {String} memo      备注
     * @apiParam {String} gameType  游戏类型
     * @apiParam {String} gameId    游戏id
     *
     *
     * @apiSuccessExample Success-Response:
     *      *   HTTP/1.1 200 OK
     * {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "result": "转账成功！"
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/money/transMoney")
    TransMoneyResponse transMoney(@RequestBody TransMoneyRequest request) throws MoneyServiceException;

    /**
     * @apiVersion 1.0.0
     * @apiGroup money
     * @api {POST} /money/memberMoneyLog 查询钱包流水
     * @apiName memberMoneyLog
     *
     * @apiDescription
     *
     * @apiParam {String} username 用户姓名
     * @apiParam {String} fromKey  站点key,后期用于多站点多平台的一个配置参数
     * @apiParam {String} key      平台秘钥，key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
     * @apiParam {String} siteId   站点id,后期用于多站点多平台的一个配置参数
     * @apiParam {String} remitno  流水号
     * @apiParam {String} beginTime 开始时间
     * @apiParam {String} endTime   结束时间
     * @apiParam {String} page
     * @apiParam {String} pageSize
     * @apiParam {String} gameType  游戏类型
     * @apiParam {String} gameId    游戏id
     *
     *
     * @apiSuccess {Integer} id 		 id
     * @apiSuccess {String} username 	 用户名
     * @apiSuccess {String} hashcode 	 --后期多站点多平台预留字段
     * @apiSuccess {Integer} siteId 	 网站标识
     * @apiSuccess {String} sitename 	 网站名称
     * @apiSuccess {String} fromKey 	 站点key,后期用于多站点多平台的一个配置参数
     * @apiSuccess {String} requestIp    请求ip地址
     * @apiSuccess {String} transId
     * @apiSuccess {String} fromKeyType
     * @apiSuccess {Integer} beforeMoney 交易前用户金额
     * @apiSuccess {Integer} remit       交易金额
     * @apiSuccess {Integer} afterMoney  交易后用户金额
     * @apiSuccess {String} transType    转账类型，in表示转入，out表示转出
     * @apiSuccess {String} gameType     游戏类型
     * @apiSuccess {String} gameId       游戏id
     * @apiSuccess {String} createTime   创建时间
     * @apiSuccess {String} memo         备注
     *
     * @apiSuccessExample Success-Response:
     *      *   HTTP/1.1 200 OK
     *{
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "list": [
     *         {
     *             "id": 9577,
     *             "username": "afu222",
     *             "hashcode": null,
     *             "siteId": 1020,
     *             "sitename": null,
     *             "fromKey": "1020",
     *             "requestIp": null,
     *             "transId": "asdsa",
     *             "fromKeyType": 111,
     *             "beforeMoney": 200.00000,
     *             "remit": 100.00000,
     *             "afterMoney": 100.00000,
     *             "transType": "out",
     *             "gameType": null,
     *             "gameId": null,
     *             "createTime": "2020-02-03T05:45:54.000+0000",
     *             "memo": ""
     *         }
     *     ],
     *     "result": "查询成功！",
     *     "page": 1,
     *     "pageSize": 10,
     *     "totalNumber": 1
     * }
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/money/memberMoneyLog")
    MemberMoneyLogResponse memberMoneyLog(@RequestBody MemberMoneyLogRequest request) throws MoneyServiceException;
}
