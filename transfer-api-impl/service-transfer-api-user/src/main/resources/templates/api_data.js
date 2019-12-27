define({ "api": [
  {
    "version": "1.0.0",
    "group": "3index",
    "type": "POST",
    "url": "/index/getImgSwiper",
    "title": "（1）首页图片轮播",
    "name": "getImgSwiper",
    "description": "<blockquote> <p>首页图片轮播流程： </br> （1）校验参数</br> （2）获取轮播图片</br> （3）返回结果</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"index=首页\""
            ],
            "optional": false,
            "field": "type",
            "description": "<p>类型</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "id",
            "description": "<p>ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "imgSrc",
            "description": "<p>图片路径</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "imgLink",
            "description": "<p>点击链接</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "  HTTP/1.1 200 OK\n  {\n    \"errInfoList\": [],\n    \"errorCount\": 0,\n    \"exception\": \"\",\n    \"list\": [\n        {\n            \"id\": 2,\n            \"imgLink\": \"\",\n            \"imgSrc\": \"http://47.52.140.84:801/appKey00001/index/swiper/562c4d0d306e7837e53701839e0cd3da.png\",\n            \"title\": \"标题二\"\n        },\n        {\n            \"id\": 1,\n            \"imgLink\": \"\",\n            \"imgSrc\": \"http://47.52.140.84:801/appKey00001/index/swiper/0b539b4b77e2ef83f1ef1b07baa8b58b.png\",\n            \"title\": \"标题一\"\n        }\n    ],\n    \"successCount\": 1\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/index/swiper/ISwiperService.java",
    "groupTitle": "首页API",
    "sampleRequest": [
      {
        "url": "http://10.20.25.48:8071/index/getImgSwiper"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "3index",
    "type": "POST",
    "url": "/index/getNoticeSwiper",
    "title": "（2）首页消息轮播",
    "name": "getIndexSwiper",
    "description": "<blockquote> <p>首页消息轮播流程： </br> （1）校验参数</br> （2）获取轮播消息</br> （3）返回结果</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"index=首页\""
            ],
            "optional": false,
            "field": "type",
            "description": "<p>类型</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "id",
            "description": "<p>ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "context",
            "description": "<p>内容</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "  HTTP/1.1 200 OK\n  {\n    \"errInfoList\": [],\n    \"errorCount\": 0,\n    \"exception\": \"\",\n    \"list\": [\n        {\n            \"context\": \"多年来深受众多客户及玩家的信任，现诚邀与您携手并进，共赢未来！\",\n            \"id\": 1,\n            \"title\": \"消息一\"\n        },\n        {\n            \"context\": \"真人视讯、棋牌视讯欢迎体验\",\n            \"id\": 2,\n            \"title\": \"消息二\"\n        }\n    ],\n    \"successCount\": 1\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/index/swiper/ISwiperService.java",
    "groupTitle": "首页API",
    "sampleRequest": [
      {
        "url": "http://10.20.25.48:8071/index/getNoticeSwiper"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "4user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/edit?token=xxxxxx",
    "title": "（5）用户信息更新",
    "name": "edit",
    "description": "<blockquote> <p>用户信息更新接口</br> （1）验证参数：是否合法</br> （2）支持昵称更新</br> （3）支持性别更新</br> （4）支持生日更新</br> （5）支持个性签名更新</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>昵称（可选）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "sex",
            "description": "<p>性别（可选）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "birthday",
            "description": "<p>生日（可选）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "signature",
            "description": "<p>签名（可选）</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>更新信息</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/user/IUserService.java",
    "groupTitle": "app用户API",
    "sampleRequest": [
      {
        "url": "http://10.20.25.48:8071/user/edit?token=xxxxxx"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "4user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/getDetail?token=xxxxxx",
    "title": "（7）获取用户详情",
    "name": "getDetail",
    "description": "<blockquote> <p>获取用户详情接口</br> （1）个人中心—用户详情/br&gt;</p> </blockquote>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>用户名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "deposit",
            "description": "<p>存款（星钻 ）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "Success 200",
            "type": "Timestamp",
            "optional": false,
            "field": "birthday",
            "description": "<p>生日</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "signature",
            "description": "<p>个性签名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "avatar",
            "description": "<p>头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "location",
            "description": "<p>位置</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "level",
            "description": "<p>用户等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "sex",
            "description": "<p>性别 0  未知  1 男  2 女</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "vip",
            "description": "<p>vip等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "status",
            "description": "<p>用户状态 -1=封号 0=删除 1=已注册</p>"
          },
          {
            "group": "Success 200",
            "type": "Timestamp",
            "optional": false,
            "field": "created_at",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "channel",
            "description": "<p>渠道标识</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/user/IUserService.java",
    "groupTitle": "app用户API",
    "sampleRequest": [
      {
        "url": "http://10.20.25.48:8071/user/getDetail?token=xxxxxx"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "4user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/getValidateCode",
    "title": "（3）获取用户验证码",
    "name": "getValidateCode",
    "description": "<blockquote> <p>获取验证码接口</br> （1）验证参数：是否合法</br> A .  验证phone是否注册</br> （2）生成验证码逻辑</br> （3）实现验证码-消息推送（短信实现）</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "codeType",
            "description": "<p>验证码类型1为注册 2为忘记密码</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>用户验证码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>注册信息</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/user/IUserService.java",
    "groupTitle": "app用户API",
    "sampleRequest": [
      {
        "url": "http://10.20.25.48:8071/user/getValidateCode"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "4user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/login",
    "title": "（1）用户登陆",
    "name": "login",
    "description": "<blockquote> <p>用户登陆接口</br> （1）验证参数：是否合法</br> A .  验证username是否存在</br> B .  验证password是否错误</br> （2）登陆逻辑判断</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>用户账号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>用户密码</p>"
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
            "field": "userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>登陆用户名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "avatar",
            "description": "<p>用户头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/user/IUserService.java",
    "groupTitle": "app用户API",
    "sampleRequest": [
      {
        "url": "http://10.20.25.48:8071/user/login"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "4user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/register",
    "title": "（2）用户注册",
    "name": "register",
    "description": "<blockquote> <p>用户注册接口</br> （1）验证参数：是否合法</br> A .  验证phone是否注册</br> B .  验证validateCode错误次数限制、验证码重试次数、是否正确等</br> （2）注册逻辑</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>用户密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "validateCode",
            "description": "<p>验证码</p>"
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
            "field": "userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>注册信息</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/user/IUserService.java",
    "groupTitle": "app用户API",
    "sampleRequest": [
      {
        "url": "http://10.20.25.48:8071/user/register"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "4user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/reset",
    "title": "（4）用户密码重置",
    "name": "reset",
    "description": "<blockquote> <p>用户密码重置接口</br> （1）验证参数：是否合法</br> A .  验证phone是否注册</br> B .  验证validateCode错误次数限制、验证码重试次数、是否正确等</br> （2）注册逻辑</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>用户密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "validateCode",
            "description": "<p>验证码</p>"
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
            "field": "userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "result",
            "description": "<p>注册信息</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/user/IUserService.java",
    "groupTitle": "app用户API",
    "sampleRequest": [
      {
        "url": "http://10.20.25.48:8071/user/reset"
      }
    ]
  },
  {
    "version": "1.0.0",
    "group": "4user",
    "permission": [
      {
        "name": "Android/IOS"
      }
    ],
    "type": "POST",
    "url": "/user/uploadAvatar?token=xxxxxx",
    "title": "（6）上传用户头像",
    "name": "uploadAvatar",
    "description": "<blockquote> <p>进入主播开播界面，设置直播房间封面</br> 使用postman的选项的form-data的key=avatar 文件类型 可以测试</br></p> </blockquote>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "byte[]",
            "optional": false,
            "field": "avatar",
            "description": "<p>用户头像</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "imgUrl",
            "description": "<p>上传地址</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/eason/transfer/openapi/core/sdk/user/IUserService.java",
    "groupTitle": "app用户API",
    "sampleRequest": [
      {
        "url": "http://10.20.25.48:8071/user/uploadAvatar?token=xxxxxx"
      }
    ]
  }
] });
