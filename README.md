# zb_api_server

|日期|操作人|模块|API名称|修订记录|
|:--|:--|:--|:--|:--|
|2017/11/11 |EASON|用户API |关注/取消关注|新增渠道字段，用于后台统计；API调整：/user/{userId}/**{channel}**/isAttention/{zbId}/{isAttention}|
|2017/11/14 |EASON|三方平台API|获取列表IM/Media地址|新增主播id，用于auth认证；API调整：/api/platform/im/get/**{zbId}**；修改端口字段调整Integer类型|