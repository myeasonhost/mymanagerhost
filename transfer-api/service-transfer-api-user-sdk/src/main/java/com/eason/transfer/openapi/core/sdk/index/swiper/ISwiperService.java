package com.eason.transfer.openapi.core.sdk.index.swiper;

import com.eason.transfer.openapi.core.sdk.index.swiper.model.ImgRequest;
import com.eason.transfer.openapi.core.sdk.index.swiper.model.ImgResponse;
import com.eason.transfer.openapi.core.sdk.index.swiper.model.NoticeRequest;
import com.eason.transfer.openapi.core.sdk.index.swiper.model.NoticeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @apiDefine 3index 首页API
 */
@FeignClient(contextId = "index#ISwiperService",value = "service-transfer-api-user")
public interface ISwiperService {
    /**
     * @apiVersion 1.0.0
     * @apiGroup 3index
     * @api {POST} /index/getImgSwiper （1）首页图片轮播
     * @apiName getImgSwiper
     *
     *
     * @apiDescription
     * >  首页图片轮播流程： </br>
     * >（1）校验参数</br>
     * >（2）获取轮播图片</br>
     * >（3）返回结果</br>
     *
     * @apiParam {String="index=首页"} type  类型
     *
     *
     * @apiSuccess {Long} id  ID
     * @apiSuccess {String} title  标题
     * @apiSuccess {String} imgSrc  图片路径
     * @apiSuccess {String} imgLink  点击链接
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "errInfoList": [],
     *     "errorCount": 0,
     *     "exception": "",
     *     "list": [
     *         {
     *             "id": 2,
     *             "imgLink": "",
     *             "imgSrc": "http://47.52.140.84:801/appKey00001/index/swiper/562c4d0d306e7837e53701839e0cd3da.png",
     *             "title": "标题二"
     *         },
     *         {
     *             "id": 1,
     *             "imgLink": "",
     *             "imgSrc": "http://47.52.140.84:801/appKey00001/index/swiper/0b539b4b77e2ef83f1ef1b07baa8b58b.png",
     *             "title": "标题一"
     *         }
     *     ],
     *     "successCount": 1
     * }
     *
     */
    @PostMapping(value = "/index/img/getImgSwiper")
    public ImgResponse getImgSwiper(@RequestBody ImgRequest request) throws Exception;


    /**
     * @apiVersion 1.0.0
     * @apiGroup 3index
     * @api {POST} /index/img/getIndexSwiper （2）首页消息轮播
     * @apiName getIndexSwiper
     *
     *
     * @apiDescription
     * >  首页消息轮播流程： </br>
     * >（1）校验参数</br>
     * >（2）获取轮播消息</br>
     * >（3）返回结果</br>
     *
     * @apiParam {String="index=首页"} type  类型
     *
     *
     * @apiSuccess {Long} id  ID
     * @apiSuccess {String} title  标题
     * @apiSuccess {String} context  内容
     * @apiSuccessExample Success-Response:
     *   HTTP/1.1 200 OK
     *   {
     *     "errInfoList": [],
     *     "errorCount": 0,
     *     "exception": "",
     *     "list": [
     *         {
     *             "context": "多年来深受众多客户及玩家的信任，现诚邀与您携手并进，共赢未来！",
     *             "id": 1,
     *             "title": "消息一"
     *         },
     *         {
     *             "context": "真人视讯、棋牌视讯欢迎体验",
     *             "id": 2,
     *             "title": "消息二"
     *         }
     *     ],
     *     "successCount": 1
     * }
     *
     *
     */
    @PostMapping(value = "/index/getNoticeSwiper")
    public NoticeResponse getNoticeSwiper(@RequestBody NoticeRequest request) throws Exception;

}
