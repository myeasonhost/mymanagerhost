package com.eason.transfer.openapi.core.sdk.zb;


import com.eason.transfer.openapi.core.sdk.zb.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @apiDefine room  房间API
 */
@FeignClient(contextId = "room#IRoomService",value = "service-transfer-api-zb")
public interface IRoomService {

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/findAll")
    RoomFindAllResponse findAll(@RequestBody RoomFindAllRequest request) throws Exception;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/create")
    RoomCreateResponse create(@RequestBody RoomCreateRequest request) throws Exception;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/getInfo")
    RoomGetInfoResponse getInfo(@RequestBody RoomGetInfoRequest request) throws Exception;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/update")
    RoomUpdateResponse update(@RequestBody RoomUpdateRequest request) throws Exception;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/destory")
    RoomDestoryResponse destory(@RequestBody RoomDestoryRequest request) throws Exception;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/relationFriends")
    RelationFriendsResponse relationFriends(@RequestBody RelationFriendsRequest request) throws Exception;


    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room/addGift")
    GiftResponse addGift(@RequestBody GiftRequest request) throws Exception;
}
