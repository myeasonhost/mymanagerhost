package com.eason.transfer.openapi.core.sdk.user;


import com.eason.transfer.openapi.core.sdk.user.exception.UserServiceException;
import com.eason.transfer.openapi.core.sdk.user.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @apiDefine 4user app用户API
 */
@FeignClient(contextId = "app#IUserService",value = "service-transfer-api-user")
public interface IUserService {
	/**
	 * @apiVersion 1.0.0
	 * @apiGroup 4user
	 * @apiPermission Android/IOS
	 * @api {POST} /user/login （1）用户登陆
	 * @apiName login
	 *
	 * @apiDescription
	 * >用户登陆接口</br>
	 * >（1）验证参数：是否合法</br>
	 * >     A .  验证username是否存在</br>
	 * >     B .  验证password是否错误</br>
	 * >（2）登陆逻辑判断</br>
	 *
	 * @apiParam {String} username  用户账号
	 * @apiParam {String} password 	 用户密码
	 *
	 * @apiSuccess {Integer} userId  用户ID
	 * @apiSuccess {String} username  登陆用户名
	 * @apiSuccess {String} nickname 用户昵称
	 * @apiSuccess {String} avatar  用户头像
	 * @apiSuccess {String} token  用户token
	 *
	 */
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/user/login")
	public LoginResponse login(@RequestBody LoginRequest request) throws UserServiceException;

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup 4user
	 * @apiPermission Android/IOS
	 * @api {POST} /user/register （2）用户注册
	 * @apiName register
	 *
	 * @apiDescription
	 * >用户注册接口</br>
	 * >（1）验证参数：是否合法</br>
	 * >     A .  验证phone是否注册</br>
	 * >     B .  验证validateCode错误次数限制、验证码重试次数、是否正确等</br>
	 * >（2）注册逻辑</br>
	 *
	 * @apiParam {String} phone  手机号
	 * @apiParam {String} password 	 用户密码
	 * @apiParam {String} validateCode 	 验证码
	 *
	 * @apiSuccess {Integer} userId  用户ID
	 * @apiSuccess {String} result  注册信息
	 *
	 */
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/user/register")
	public RegisterResponse register(@RequestBody RegisterRequest request) throws UserServiceException;

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup 4user
	 * @apiPermission Android/IOS
	 * @api {POST} /user/getValidateCode （3）获取用户验证码
	 * @apiName getValidateCode
	 *
	 * @apiDescription
	 * >获取验证码接口</br>
	 * >（1）验证参数：是否合法</br>
	 * >     A .  验证phone是否注册</br>
	 * >（2）生成验证码逻辑</br>
	 *>（3）实现验证码-消息推送（短信实现）</br>
	 *
	 * @apiParam {String} phone  手机号
	 * @apiParam {String} codeType  验证码类型1为注册 2为忘记密码
	 *
	 * @apiSuccess {String} code  用户验证码
	 * @apiSuccess {String} result  注册信息
	 *
     */
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/user/getValidateCode")
	public UserCodeResponse getValidateCode(@RequestBody UserCodeRequest request) throws UserServiceException;


	/**
	 * @apiVersion 1.0.0
	 * @apiGroup 4user
	 * @apiPermission Android/IOS
	 * @api {POST} /user/reset （4）用户密码重置
	 * @apiName reset
	 *
	 * @apiDescription
	 * >用户密码重置接口</br>
	 * >（1）验证参数：是否合法</br>
	 * >     A .  验证phone是否注册</br>
	 * >     B .  验证validateCode错误次数限制、验证码重试次数、是否正确等</br>
	 * >（2）注册逻辑</br>
	 *
	 * @apiParam {String} phone  手机号
	 * @apiParam {String} password 	 用户密码
	 * @apiParam {String} validateCode 	 验证码
	 *
	 * @apiSuccess {Integer} userId  用户ID
	 * @apiSuccess {String} result  注册信息
	 *
	 */
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/user/reset")
	public RegisterResponse reset(@RequestBody RegisterRequest request) throws UserServiceException;



	/**
	 * @apiVersion 1.0.0
	 * @apiGroup 4user
	 * @apiPermission Android/IOS
	 * @api {POST} /user/edit?token=xxxxxx （5）用户信息更新
	 * @apiName edit
	 *
	 * @apiDescription
	 * >用户信息更新接口</br>
	 * >（1）验证参数：是否合法</br>
	 * >（2）支持昵称更新</br>
	 * >（3）支持性别更新</br>
	 * >（4）支持生日更新</br>
	 * >（5）支持个性签名更新</br>
	 * @apiParam {String} nickname  昵称（可选）
	 * @apiParam {String} sex  性别（可选）
	 * @apiParam {String} birthday  生日（可选）
	 * @apiParam {String} signature  签名（可选）
	 *
	 *
	 * @apiSuccess {String} userId  用户id
	 * @apiSuccess {String} result  更新信息
	 *
	 */
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/user/edit")
	public UserInfoResponse edit(@RequestBody UserInfoRequest request) throws UserServiceException;

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup 4user
	 * @apiPermission Android/IOS
	 * @api {POST} /user/uploadAvatar?token=xxxxxx  （6）上传用户头像
	 * @apiName uploadAvatar
	 * @apiDescription > 进入主播开播界面，设置直播房间封面</br>
	 *> 使用postman的选项的form-data的key=avatar 文件类型 可以测试</br>
	 * @apiParam {byte[]} avatar  用户头像
	 * @apiSuccess {String} imgUrl 	上传地址
	 */
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/user/uploadAvatar")
	public UserAvatarResponse uploadAvatar(@RequestBody UserAvatarRequest request) throws UserServiceException;

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup 4user
	 * @apiPermission Android/IOS
	 * @api {POST} /user/getDetail?token=xxxxxx  （7）获取用户详情
	 * @apiName getDetail
	 * @apiDescription
	 * >获取用户详情接口</br>
	 * >（1）个人中心—用户详情/br>
	 *
	 *@apiSuccess {String} userId       用户ID
	 *@apiSuccess {String} username       用户名
	 *@apiSuccess {String} nickname       用户昵称
	 *@apiSuccess {Double} deposit        存款（星钻 ）
	 *@apiSuccess {String} phone          手机
	 *@apiSuccess {Timestamp} birthday       生日
	 *@apiSuccess {String} signature      个性签名
	 *@apiSuccess {String} avatar         头像
	 *@apiSuccess {String} location       位置
	 *@apiSuccess {Integer} level          用户等级
	 *@apiSuccess {Integer} sex            性别 0  未知  1 男  2 女
	 *@apiSuccess {Integer} vip            vip等级
	 *@apiSuccess {Integer} status         用户状态 -1=封号 0=删除 1=已注册
	 *@apiSuccess {Timestamp} created_at     创建时间
	 *@apiSuccess {String} channel        渠道标识
	 */
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/user/getDetail")
	public UserDetailResponse getDetail(@RequestBody UserDetailRequest request) throws UserServiceException;

}
