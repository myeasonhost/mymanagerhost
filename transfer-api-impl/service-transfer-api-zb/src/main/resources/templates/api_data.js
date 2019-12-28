define({ "api": [
  {
    "version": "1.0.0",
    "group": "index",
    "type": "GET",
    "url": "/index/{category}/getBannerList",
    "title": "直播首页Banner列表",
    "name": "getBannerList",
    "description": "<blockquote> <p>需要显示Banner的模块：最新、最热</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "category",
            "description": "<p>房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏</br></p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "id",
            "description": "<p>BannerID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>Banner标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "thumb_img_url",
            "description": "<p>Banner图片地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "url",
            "description": "<p>跳转地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>Banner类型（1=最新、3=最热）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "     *      *   HTTP/1.1 200 OK\n\n{\n    \"successCount\": 1,\n    \"errorCount\": 0,\n    \"errInfoList\": null,\n    \"exception\": null,\n    \"list\": [\n        {\n            \"successCount\": 1,\n            \"errorCount\": 0,\n            \"errInfoList\": null,\n            \"exception\": null,\n            \"id\": 12312,\n            \"title\": \"捕鱼达人\",\n            \"thumb_img_url\": \"http://www.svnchina.com/\",\n            \"url\": \"http://www.svnchina.com/\",\n            \"type\": 1\n        }\n    ],\n    \"total\": 52\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/chess/IIndexService.java",
    "groupTitle": "直播首页API",
    "sampleRequest": [
      {
        "url": "http://10.20.22.230:8073/index/{category}/getBannerList"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "index",
    "type": "POST",
    "url": "/index/getIndexList",
    "title": "房间列表",
    "name": "getIndexList",
    "description": "<blockquote> <p>房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏 </br> 【最热=1】：砖石数排序（大-小）,主播昵称、直播标题、房间类型（若普通房间收费模式不现实）、房间人数（真实人数和机器人数）、网络状态（API不提供）</br> 【最新=3】：主播开播时间（近-远）排序，主播昵称、房间类型、房间人数（真实人数和机器人数）、网络状态（API不提供）</br> 【收藏=2】：显示关注后的主播房间,顺序直播类型-录播类型</br> (1)直播类型=砖石数排序（大-小）,直播状态、房间人数、主播昵称、开播信息、网络状态、收费模式（普通房间不显示）</br> (2)录播类型=发布日期（近-远），被观看次数、收费价格（普通房间不显示）、主播昵称、开播信息</br> 【付费=4】：砖石数排序（大-小）,开播标题、房间类型、房间人数（真实人数和机器人数）、网络状态（API不提供）</br> 【游戏=5】：房间游戏投注数排序（大-小）,主播昵称、开播标题、游戏类型、参与人数（如上第二条解释）、网络状态（API不提供）</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "category",
            "description": "<p>房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏</br></p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "position",
            "description": "<p>位置</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "page",
            "description": ""
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "total",
            "description": "<p>总数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "rows",
            "description": "<p>rows-&gt;row</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.roomId",
            "description": "<p>房间ID</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.zbId",
            "description": "<p>主播ID</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.roomPlanId",
            "description": "<p>房间场次ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.zbNickName",
            "description": "<p>主播昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.zbHeadImg",
            "description": "<p>主播头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.zbLevel",
            "description": "<p>主播等级</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.roomTitle",
            "description": "<p>房间标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.roomType",
            "description": "<p>房间类型：'normal=普通房间',game=游戏房间'</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.onlineUser",
            "description": "<p>真实在线用户</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.machineUser",
            "description": "<p>机器用户</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.viewCount",
            "description": "<p>总浏览次数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.watchCount",
            "description": "<p>总观看次数</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.roomBackgroundImg",
            "description": "<p>房间背景图片</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.roomStatus",
            "description": "<p>直播状态： 1=直播中，2=未开播，3=回放中</p>"
          },
          {
            "group": "Success 200",
            "type": "Timestamp",
            "optional": false,
            "field": "row.startTime",
            "description": "<p>房间开播时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": true,
            "field": "row.gameIcon",
            "description": "<p>游戏图标</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "row.playUrl",
            "description": "<p>录像回放地址</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "row.isCharge",
            "description": "<p>是否收费   0=不收费，1=收费</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "     *   HTTP/1.1 200 OK\n     {\n    \"successCount\": 1,\n    \"errorCount\": 0,\n    \"errInfoList\": null,\n    \"exception\": null,\n    \"list\": [\n        {\n            \"rows\": 1,\n            \"roomId\": 9,\n            \"zbId\": 10,\n            \"roomPlanId\": 1,\n            \"zbNickName\": \"香香\",\n            \"zbHeadImg\": \"呜呜\",\n            \"zbLevel\": null,\n            \"roomTitle\": \"开心\",\n            \"roomType\": \"normal\",\n            \"onlineUser\": 10,\n            \"machineUser\": 3,\n            \"viewCount\": 2,\n            \"watchCount\": 3,\n            \"roomBackgroundImg\": \"sadsaffa\",\n            \"roomStatus\": 1,\n            \"startTime\": \"2019-03-04T04:23:30.000+0000\",\n            \"gameIcon\": \"edsfsdfs\",\n            \"playUrl\": \"sadsadasdfa\",\n            \"isCharge\": 1\n        }\n    ],\n    \"total\": 40\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/chess/IIndexService.java",
    "groupTitle": "直播首页API",
    "sampleRequest": [
      {
        "url": "http://10.20.22.230:8073/index/getIndexList"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "index",
    "type": "GET",
    "url": "/index/{category}/getMsgNotificationList",
    "title": "公告消息列表",
    "name": "getMsgNotificationList",
    "description": "<blockquote> <p>需要显示MsgNotification的模块：最新、最热</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "category",
            "description": "<p>房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏</br></p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "id",
            "description": "<p>公告消息ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>公告消息内容</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "url",
            "description": "<p>点击事件</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "      HTTP/1.1 200 OK\n{\n    \"successCount\": 1,\n    \"errorCount\": 0,\n    \"errInfoList\": null,\n    \"exception\": null,\n    \"list\": [\n        {\n            \"successCount\": 1,\n            \"errorCount\": 0,\n            \"errInfoList\": null,\n            \"exception\": null,\n            \"id\": 112,\n            \"title\": \"今晚休息.......\",\n            \"url\": \"http://www.svnchina.com/\"\n        }\n    ],\n    \"total\": 20\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/chess/IIndexService.java",
    "groupTitle": "直播首页API",
    "sampleRequest": [
      {
        "url": "http://10.20.22.230:8073/index/{category}/getMsgNotificationList"
      }
    ]
  }
] });
