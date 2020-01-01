//package com.eason.transfer.openapi.core.sdk.zb;
//
//import com.eason.api.exception.ServiceException;
//import com.eason.api.zb.vo.live.ZbRoomLiveVo;
//
//import java.util.List;
//
///**
// * @apiDefine live 流媒体API
// */
//public interface ILiveUrlService {
//    /**
//     * @apiVersion 1.0.0
//     * @apiGroup live
//     * @apiPermission Android/IOS
//     * @api {GET} /live/getPushUrl/{roomId} 1.获取推流地址
//     * @apiName getPushUrl
//     *
//     *
//     * @apiDescription
//     * > 首次获取流地址，会自动生成推流地址和拉流地址，并存入缓存</br>
//     * > 再次获取流地址，会自动更新推流地址和拉流地址，并更新缓存</br>
//     *
//     * @apiSuccess {String} pushUrl  获取推流地址
//     */
//    public String getPushUrl(String roomId) throws ServiceException;
//
//    /**
//     * @apiVersion 1.0.0
//     * @apiGroup live
//     * @apiPermission Android/IOS
//     * @api {GET} /live/getPlayUrl/{roomId} 2.获取拉流地址
//     * @apiName getPlayUrl
//     *
//     *
//     * @apiDescription
//     * > 从缓存获取拉流地址</br>
//     *
//     * @apiSuccess {String}  playUrl   获取拉流地址
//     */
//    public String getPlayUrl(String roomId) throws ServiceException;
//
//
//    /**
//     * @apiVersion 1.0.0
//     * @apiGroup live
//     * @apiPermission Android/IOS
//     * @api {GET} /live/getPlayUrlList 3.获取拉流地址列表
//     * @apiName getPlayUrlList
//     *
//     *
//     * @apiDescription
//     * >  获取拉流地址列表</br>
//     *
//     * @apiSuccess {Object[]} getPlayUrlList		getPlayUrlList->ZbRoomLiveVo（流列表）
//     *  @apiSuccess {Integer} ZbRoomLiveVo.liveVo.id		 流ID
//     *  @apiSuccess {String} ZbRoomLiveVo.liveVo.roomId		房间ID
//     *  @apiSuccess {String} ZbRoomLiveVo.liveVo.playUrl		拉流ID
//     */
//    public List<ZbRoomLiveVo> getPlayUrlList() throws ServiceException;
//
//    /**
//     * @apiVersion 1.0.0
//     * @apiGroup live
//     * @apiPermission Android/IOS
//     * @api {GET} /live/removeLiveInfo/{roomId} 4.删除缓存流信息
//     * @apiName removeLiveInfo
//     *
//     *
//     * @apiDescription
//     * >  删除缓存流信息，在主播直播完成之后，触犯该动作</br>
//     *
//     * @apiSuccess {String}  info   删除成功或者失败
//     */
//    public String removeLiveInfo(String roomId) throws ServiceException;
//
//
//
//}