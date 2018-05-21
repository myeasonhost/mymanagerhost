package com.eason.live.zb.service;

/**
 * @apiDefine live 流媒体API
 */
public interface ILiveUrlService {
    /**
     *
     * @param roomId
     * @return
     */
    public String getPushUrl(String roomId);

    /**
     *
     * @param roomId
     * @return
     */
    public String getPlayUrl(String roomId);



}
