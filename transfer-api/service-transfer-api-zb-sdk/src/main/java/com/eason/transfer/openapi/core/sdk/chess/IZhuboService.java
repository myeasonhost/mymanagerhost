package com.eason.transfer.openapi.core.sdk.chess;



import com.eason.transfer.openapi.core.sdk.chess.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @apiDefine index 直播首页API
 */
@FeignClient(contextId = "index#IIndexService",value = "service-transfer-api-zb")
public interface IZhuboService {
    /**
     * @apiVersion 1.0.0
     * @apiGroup index
     * @api {POST} /index/getIndexList 房间列表
     * @apiName getIndexList
     *
     *
     * @apiDescription
     * >  房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏 </br>
     * > 【最热=1】：砖石数排序（大-小）,主播昵称、直播标题、房间类型（若普通房间收费模式不现实）、房间人数（真实人数和机器人数）、网络状态（API不提供）</br>
     * > 【最新=3】：主播开播时间（近-远）排序，主播昵称、房间类型、房间人数（真实人数和机器人数）、网络状态（API不提供）</br>
     * > 【收藏=2】：显示关注后的主播房间,顺序直播类型-录播类型</br>
     *   > (1)直播类型=砖石数排序（大-小）,直播状态、房间人数、主播昵称、开播信息、网络状态、收费模式（普通房间不显示）</br>
     *   > (2)录播类型=发布日期（近-远），被观看次数、收费价格（普通房间不显示）、主播昵称、开播信息</br>
     * > 【付费=4】：砖石数排序（大-小）,开播标题、房间类型、房间人数（真实人数和机器人数）、网络状态（API不提供）</br>
     * > 【游戏=5】：房间游戏投注数排序（大-小）,主播昵称、开播标题、游戏类型、参与人数（如上第二条解释）、网络状态（API不提供）</br>
     *
     * @apiParam {String} category 房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏</br>
     * @apiParam {Integer} position 位置
     * @apiParam {Integer} pageSize
     * @apiParam {Integer} page
     *
     * @apiSuccess {Integer} total 		总数
     * @apiSuccess {Integer} rows 		rows->row
     * @apiSuccess {Integer} row.roomId 		房间ID
     * @apiSuccess {Integer} row.zbId 		主播ID
     * @apiSuccess {Integer} row.roomPlanId 		房间场次ID
     * @apiSuccess {String} row.zbNickName 	主播昵称
     * @apiSuccess {String} row.zbHeadImg 	主播头像
     * @apiSuccess {String} row.zbLevel 	主播等级
     * @apiSuccess {String} row.roomTitle  	房间标题
     * @apiSuccess {String} row.roomType   	房间类型：'normal=普通房间',game=游戏房间'
     * @apiSuccess {Integer} row.onlineUser  真实在线用户
     * @apiSuccess {Integer} row.machineUser 机器用户
     * @apiSuccess {Integer} row.viewCount 	总浏览次数
     * @apiSuccess {Integer} row.watchCount 	总观看次数
     * @apiSuccess {String} row.roomBackgroundImg  房间背景图片
     * @apiSuccess {Integer} row.roomStatus  	直播状态： 1=直播中，2=未开播，3=回放中
     * @apiSuccess {Timestamp} row.startTime  	房间开播时间
     * @apiSuccess {String} [row.gameIcon]  	游戏图标
     * @apiSuccess {String} row.playUrl 	录像回放地址
     * @apiSuccess {Integer} row.isCharge 	是否收费   0=不收费，1=收费
     *
     * * @apiSuccessExample Success-Response:
     *      *   HTTP/1.1 200 OK
     *      {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "list": [
     *         {
     *             "rows": 1,
     *             "roomId": 9,
     *             "zbId": 10,
     *             "roomPlanId": 1,
     *             "zbNickName": "香香",
     *             "zbHeadImg": "呜呜",
     *             "zbLevel": null,
     *             "roomTitle": "开心",
     *             "roomType": "normal",
     *             "onlineUser": 10,
     *             "machineUser": 3,
     *             "viewCount": 2,
     *             "watchCount": 3,
     *             "roomBackgroundImg": "sadsaffa",
     *             "roomStatus": 1,
     *             "startTime": "2019-03-04T04:23:30.000+0000",
     *             "gameIcon": "edsfsdfs",
     *             "playUrl": "sadsadasdfa",
     *             "isCharge": 1
     *         }
     *     ],
     *     "total": 40
     * }
     */

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/index/getIndexList")
    IndexResponse getIndexList(@RequestBody IndexListRequest indexListRequest) throws Exception;



    /**
     * @apiVersion 1.0.0
     * @apiGroup index
     * @api {GET} /index/{category}/getBannerList 直播首页Banner列表
     * @apiName getBannerList
     *
     *
     * @apiDescription
     * > 需要显示Banner的模块：最新、最热</br>
     * @apiParam {String} category 房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏</br>
     *
     *
     * @apiSuccess {Integer} id 	BannerID
     * @apiSuccess {String} title 	Banner标题
     * @apiSuccess {String} thumb_img_url 	Banner图片地址
     * @apiSuccess {String} url 	跳转地址
     * @apiSuccess {String} type  Banner类型（1=最新、3=最热）
     *
     * * @apiSuccessExample Success-Response:
     *      *      *   HTTP/1.1 200 OK
     *
     * {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "list": [
     *         {
     *             "successCount": 1,
     *             "errorCount": 0,
     *             "errInfoList": null,
     *             "exception": null,
     *             "id": 12312,
     *             "title": "捕鱼达人",
     *             "thumb_img_url": "http://www.svnchina.com/",
     *             "url": "http://www.svnchina.com/",
     *             "type": 1
     *         }
     *     ],
     *     "total": 52
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/index/getBannerList")
    BannerResponse getBannerList(@RequestBody BannerRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup index
     * @api {GET} /index/{category}/getMsgNotificationList 公告消息列表
     * @apiName getMsgNotificationList
     *
     *
     * @apiDescription
     * > 需要显示MsgNotification的模块：最新、最热</br>
     *  @apiParam {String} category 房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏</br>
     *
     *
     * @apiSuccess {Integer} id 		公告消息ID
     * @apiSuccess {String} title 	公告消息内容
     * @apiSuccess {String} url 	点击事件
     *
     * @apiSuccessExample Success-Response:
     *       HTTP/1.1 200 OK
     * {
     *     "successCount": 1,
     *     "errorCount": 0,
     *     "errInfoList": null,
     *     "exception": null,
     *     "list": [
     *         {
     *             "successCount": 1,
     *             "errorCount": 0,
     *             "errInfoList": null,
     *             "exception": null,
     *             "id": 112,
     *             "title": "今晚休息.......",
     *             "url": "http://www.svnchina.com/"
     *         }
     *     ],
     *     "total": 20
     * }
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/index/getMsgNotification")
    MsgNotificationResponse getMsgNotification(MsgNotificationRequest request) throws Exception;
}
