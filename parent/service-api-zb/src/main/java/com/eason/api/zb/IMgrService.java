package com.eason.api.zb;

import com.eason.api.zb.exception.ServiceException;
import com.eason.api.zb.vo.zhubo.MgrStartPlayRequestVo;
import com.eason.api.zb.vo.zhubo.StartPlayResponseVo;

/**
 * @apiDefine mgr 管理平台API
 */
public interface IMgrService {

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup mgr
	 * @apiPermission Android/IOS
	 * @api {POST} /mgr/{zbId}/createRoom?token=xxxxxx 后台开播自定义房间
	 * @apiName createRoom
	 *
	 * @apiDescription
	 * >	后台开播API - 获取开播信息+开播接口	</br>
	 * >运营账号登陆后台，创建自定义直播房间界面业务流程</br>
	 * >（1）验证参数：是否合法</br>
	 * >     A.  验证管理平台Token，查询qvod_admin_users的remember_token字段</br>
	 * >     B .  验证主播ID</br>
	 * >（2）获取IM与Media地址：</br>
	 * >     A.  Media地址后台输入</br>
	 *>      B.  如果拿不到地址，IM=null接口正常返回，不中断</br>
	 * >     C.  如果拿到地址，存入缓存zb_t_room_conf，用户看直播的时候直接重新拿推荐地址，一个地址绑定一个主播</br>
	 * >（3）维护表：qvod_zb_t_room</br>
	 * >     A.如果无房间，创建房间，status=0（创建中）初始化房间zbId,status,createTime</br>
	 * >     B.如果有房间，继续（4-7）</br>
	 *> （4）验证房间状态：</br>
	 * >     A.未开播（=2）继续（5-7）</br>
	 * >     B.直播中（=1），更新DB+缓存直播标题 等数据,  直接返回</br>
	 * >（5）获取主播权限：</br>
	 * >     A.判断主播是否被禁播；UI弹出提示框</br>
	 * >     B.判断是否拥有 时常房间、门票房间、私密房间、游戏房间的开播权限</br>
	 * >（6）获取房间属性：</br>
	 *>      	A. 参数获取—UI动态配置开播时间、持续时间、门票价格数据等</br>
	 * >  		B. 参数校验</br>
	 *>    <p>
	 *>  （7）维护表：zb_t_room_plan</br>
	 * >      A.  新增admin字段，管理平台用户创建；如果为空，代表直播用户创建</br>
	 * >     B. 查询当前房间场次，确保缓存中没场次（用户没有直播），在进行场次的创建，场次status=房间status</br>
	 *>      C.  创建场次，存储到缓存 房间信息，主播信息（主播昵称、等级、主播头像等），房间属性配置信息，场次信息</br>
	 * >     D.  如果是私密房间，更新用户预约表zb_t_user_personal的邀请时间，已经房间预约信息</br>
	 * >（8）组件返回值：</br>
	 *
	 * @apiParam (ticket) {String="normal","ticket","time","personal","game"} roomType 	目前房间类型只支持normal和ticket
	 * @apiParam (ticket) {String{0..10}} roomTitle 房间标题
	 * @apiParam (ticket)  {Long} startTime 开始时间（时间戳）
	 * @apiParam (ticket)  {Integer} activityTime   请输入值
	 * @apiParam (ticket)  {Integer} price   请输入值
	 * @apiParam (ticket)  {Integer} playUrl   推流地址
	 *
	 * @apiSuccess {Integer} planId  场次Id
	 * @apiSuccess {Integer} roomId  房间Id
	 * @apiSuccess {Integer} roomStatus 0=创建，1=直播中，2=未开播，3=回放中
	 * @apiSuccess {String} result  开播成功或失败
	 *
	 */
	public StartPlayResponseVo createRoom(Integer zbId, String token, MgrStartPlayRequestVo mgrStartPlayRequestVo) throws ServiceException;





}
