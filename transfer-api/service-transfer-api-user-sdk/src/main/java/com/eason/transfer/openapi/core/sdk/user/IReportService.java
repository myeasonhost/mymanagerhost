package com.eason.transfer.openapi.core.sdk.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @apiDefine 2report 报表API
 */
@FeignClient("service-transfer-api-user")
public interface IReportService {

    /**
     * @apiVersion 1.0.0
     * @apiGroup 2report
     * @api {POST} /report/getUserGameTypeReport    （3）用户游戏报表
     * @apiName getUserGameTypeReport
     *
     *
     * @apiDescription
     * >  按每个用户的投注记录汇总输赢数据： </br>
     * >（1）游戏类型投注详情</br>
     * >（2）按房间分组统计输赢、投注额</br>
     * >（3）以天为单位，定时刷新游戏汇总统计</br>
     *
     * @apiParam {String} [username]  用户名
     * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     * @apiParam {String="LC"} [loginType]  平台类型
     * @apiParam {String} startTime  投注开始日期(yyyy-MM-dd)
     * @apiParam {String} endTime  投注结束日期(yyyy-MM-dd)
     * @apiParam {Integer} page  第几页(0=第一页，1=第二页)
     * @apiParam {Integer} pageSize  每页显示多少行
     *
     * @apiSuccess {Long} total 		总数
     * @apiSuccess {List} rows 		rows->row
     * @apiSuccess {String} row.username 	用户名称
     * @apiSuccess {String} row.kindName 	平台名称
     * @apiSuccess {String} row.typeName 	游戏名称
     * @apiSuccess {String} row.roomName 	房间号
     * @apiSuccess {Double} row.betCount 	投注数
     * @apiSuccess {Double} row.betAmount 	投注金额
     * @apiSuccess {Double} row.validAmount 	有效投注金额
     * @apiSuccess {Double} row.payAmount 	输赢金额
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
     *             "id": 1,
     *             "username": "eason",
     *             "gameKind": "LC",
     *             "gameType": "220",
     *             "gameRoom": "2201",
     *             "betCount": 4,
     *             "betAmount": 35,
     *             "validAmount": 35,
     *             "payAmount": 32,
     *             "betTime": "2019-11-27"
     *         },
     *         {
     *             "id": 2,
     *             "username": "eason",
     *             "gameKind": "LC",
     *             "gameType": "890",
     *             "gameRoom": "8901",
     *             "betCount": 6,
     *             "betAmount": 21,
     *             "validAmount": 21,
     *             "payAmount": -21,
     *             "betTime": "2019-11-27"
     *         }
     *     ]
     * }
     *
     */
    @PostMapping(value = "/report/getUserGameTypeReport")
    ReportUserGameTypeResponse getUserGameTypeReport(ReportUserGameTypeRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup 2report
     * @api {POST} /report/getUserGameKindReport    （4）平台汇总报表
     * @apiName getUserGameKindReport
     *
     *
     * @apiDescription
     * >  按天统计用户输赢报表： </br>
     * >（1）平台类型投注详情</br>
     * >（2）按游戏类型分组统计输赢、投注额</br>
     * >（3）以天为单位，定时刷新平台汇总统计</br>
     *
     * @apiParam {String} [username]  用户名
     * @apiParam {String="1000=宏发,1020=星空"} siteId  站点ID
     * @apiParam {String="LC"} [loginType]  平台类型
     * @apiParam {String} startTime  投注开始日期(yyyy-MM-dd)
     * @apiParam {String} endTime  投注结束日期(yyyy-MM-dd)
     * @apiParam {Integer} page  第几页(0=第一页，1=第二页)
     * @apiParam {Integer} pageSize  每页显示多少行
     *
     * @apiSuccess {Long} total 		总数
     * @apiSuccess {List} rows 		rows->row
     * @apiSuccess {String} row.username 	用户名称
     * @apiSuccess {String} row.kindName 	平台名称
     * @apiSuccess {Double} row.betCounts 	总投注数
     * @apiSuccess {Double} row.betAmounts 	总投注金额
     * @apiSuccess {Double} row.validAmounts 	总有效投注金额
     * @apiSuccess {Double} row.payAmounts 	总输赢金额
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
     *             "id": 1,
     *             "username": "eason",
     *             "gameKind": "LC",
     *             "betCounts": 35,
     *             "betAmounts": 144,
     *             "validAmounts": 142,
     *             "payAmounts": -2.05,
     *             "betTime": "2019-11-27"
     *         },
     *         {
     *             "id": 3,
     *             "username": "jarvis",
     *             "gameKind": "LC",
     *             "betCounts": 14,
     *             "betAmounts": 86.54,
     *             "validAmounts": 84.04,
     *             "payAmounts": -23.11,
     *             "betTime": "2019-11-27"
     *         }
     *     ]
     * }
     *
     */
    @PostMapping(value = "/report/getUserGameKindReport")
    ReportUserGameKindResponse getUserGameKindReport(ReportUserGameKindRequest request) throws Exception;


}
