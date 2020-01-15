package com.eason.transfer.openapi.core.sdk.zb;


import com.eason.transfer.openapi.core.sdk.zb.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @apiDefine room  房间API
 */
@FeignClient(contextId = "zb#IRoomService",value = "service-transfer-api-zb")
public interface IRoomService {
    /**
     * @apiVersion 1.0.0
     * @apiGroup room
     * @api {POST} /room/findAll 房间列表
     * @apiName findAll
     *
     *
     * @apiDescription
     *
     * @apiSuccess {String} id 	id
     * @apiSuccess {String} planSeqNo 	Banner标题
     * @apiSuccess {String} roomName 	房间名称
     * @apiSuccess {String} roomBgImage 房间图片地址
     * @apiSuccess {String} username  用户名
     * @apiSuccess {String} startTime  开始时间
     * @apiSuccess {String} viewCount  浏览次数
     * @apiSuccess {String} newFans  关注人数
     * @apiSuccess {String} giftCount  礼物数
     * @apiSuccess {String} userCount  用户数
     *
     *
     * * @apiSuccessExample Success-Response:
     *      *      *   HTTP/1.1 200 OK
     *
     * {
     *   "RoomFindAllResponse": {
     *     "successCount": "1",
     *     "errorCount": "0",
     *     "list": {
     *       "RoomVo": {
     *         "id": "2",
     *         "planSeqNo": "2020-01-15T12:33:18_2",
     *         "roomName": "EASON的直播间",
     *         "roomBgImage": "http://47.75.90.65/appKey00001/room/roomBgImage_2",
     *         "username": "18672317241",
     *         "startTime": "2020-01-15T12:33:18",
     *         "viewCount": "0",
     *         "newFans": "0",
     *         "giftCount": "0",
     *         "userCount": "0"
     *       }
     *     }
     *   }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/findAll")
    RoomFindAllResponse findAll(@RequestBody RoomFindAllRequest request) throws Exception;
    /**
     * @apiVersion 1.0.0
     * @apiGroup room
     * @api {POST} /room/create 创建房间
     * @apiName create
     *
     *
     * @apiDescription
     * @apiParam {String} imUrl 聊天室url
     * @apiParam {String} liveUrl 流媒体url
     * * @apiSuccessExample Success-Response:
     *      *      *   HTTP/1.1 200 OK
     *
     *  @apiSuccess {String} id   id
     *  @apiSuccess {String} roomName 房间名称
     *  @apiSuccess {String} roomBgImage 房间背景图片
     *
     *
     * {
     *   "ZhuboCreateResponse": {
     *     "successCount": "1",
     *     "errorCount": "0",
     *     "result": "直播间创建成功",
     *     "roomId": "2",
     *     "roomName": "好啊好啊",
     *     "roomBgImage": "http://47.75.90.65/appKey00001/room/roomBgImage_2"
     *   }
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/create")
    RoomCreateResponse create(@RequestBody RoomCreateRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup room
     * @api {POST} /room/getInfo 房间信息
     * @apiName getInfo
     *
     *
     * @apiDescription
     * * @apiSuccessExample Success-Response:
     *      *      *   HTTP/1.1 200 OK
     *
     * @apiSuccess {String} roomId 	房间id
     * @apiSuccess {String} roomName 房间名称
     * @apiSuccess {String} roomBgImage 房间背景图片
     *
     * {
     *   "RoomGetInfoResponse": {
     *     "successCount": "1",
     *     "errorCount": "0",
     *     "result": "直播间信息获取成功",
     *     "roomId": "2",
     *     "roomName": "EASON的直播间",
     *     "roomBgImage": "http://47.75.90.65/appKey00001/room/roomBgImage_2"
     *   }
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/getInfo")
    RoomGetInfoResponse getInfo(@RequestBody RoomGetInfoRequest request) throws Exception;


    /**
     * @apiVersion 1.0.0
     * @apiGroup room
     * @api {POST} /room/update 房间信息
     * @apiName update
     *
     *
     * @apiDescription
     * * @apiSuccessExample Success-Response:
     *      *      *   HTTP/1.1 200 OK
     *
     * @apiSuccess {String} roomName 房间名称
     * @apiSuccess {String} roomBgImage 房间背景图片
     *
     * {
     *   "RoomUpdateResponse": {
     *     "successCount": "1",
     *     "errorCount": "0",
     *     "result": "直播间信息更新成功"
     *   }
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/update")
    RoomUpdateResponse update(@RequestBody RoomUpdateRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup room
     * @api {POST} /room/destory 销毁直播间
     * @apiName destory
     *
     *
     * @apiDescription
     * * @apiSuccessExample Success-Response:
     *      *      *   HTTP/1.1 200 OK
     *
     *
     * {
     *   "RoomDestoryResponse": {
     *     "successCount": "1",
     *     "errorCount": "0",
     *     "result": "直播间销毁成功"
     *   }
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/destory")
    RoomDestoryResponse destory(@RequestBody RoomDestoryRequest request) throws Exception;

}
