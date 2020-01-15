package com.eason.transfer.openapi.core.sdk.zb;


import com.eason.transfer.openapi.core.sdk.zb.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @apiDefine zhubo  主播API
 */
@FeignClient(contextId = "zb#IZhuboService",value = "service-transfer-api-zb")
public interface IZhuboService {
    /**
     * @apiVersion 1.0.0
     * @apiGroup zhubo
     * @api {POST} /zhubo/start 直播开播
     * @apiName start
     *
     *
     * @apiDescription
     * * @apiSuccessExample Success-Response:
     *      *      *   HTTP/1.1 200 OK
     *
     *
     * {
     *   "ZhuboStartResponse": {
     *     "successCount": "1",
     *     "errorCount": "0",
     *     "result": "直播开播成功"
     *   }
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/zhubo/start")
    ZhuboStartResponse start(@RequestBody ZhuboStartRequest request) throws Exception;

    /**
     * @apiVersion 1.0.0
     * @apiGroup zhubo
     * @api {POST} /zhubo/stop 直播开播
     * @apiName stop
     *
     *
     * @apiDescription
     * * @apiSuccessExample Success-Response:
     *      *      *   HTTP/1.1 200 OK
     *
     *
     *{
     *   "ZhuboStopResponse": {
     *     "successCount": "1",
     *     "errorCount": "0",
     *     "result": "直播间销毁成功"
     *   }
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/zhubo/stop")
    ZhuboStopResponse stop(@RequestBody ZhuboStopRequest request) throws Exception;


    /**
     * @apiVersion 1.0.0
     * @apiGroup zhubo
     * @api {POST} /zhubo/create 创建直播
     * @apiName create
     *
     *
     * @apiDescription
     * * @apiSuccessExample Success-Response:
     *      *      *   HTTP/1.1 200 OK
     *
     *
     * {
     *   "ZhuboCreateResponse": {
     *     "successCount": "1",
     *     "errorCount": "0",
     *     "result": "主播信息更新成功"
     *   }
     * }
     *
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/zhubo/create")
    ZhuboCreateResponse create(@RequestBody ZhuboCreateRequest request) throws Exception;

}
