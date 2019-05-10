package com.eason.report.pull.ag.service.impl;

import com.eason.report.pull.ag.cache.ZbTRoomConf;
import com.eason.report.pull.ag.cache.ZbTRoomPlan;
import com.eason.report.pull.ag.dao.*;
import com.eason.report.pull.ag.po.*;
import com.eason.report.zb.IMgrService;
import com.eason.report.zb.dao.*;
import com.eason.report.zb.exception.ServiceException;
import com.eason.report.pull.ag.manager.PlatformManager;
import com.eason.report.pull.ag.model.ZbConstant;
import com.eason.report.zb.po.*;
import com.eason.report.zb.vo.platform.IMResponseVo;
import com.eason.report.zb.vo.platform.MediaResponseVo;
import com.eason.report.zb.vo.zhubo.MgrStartPlayRequestVo;
import com.eason.report.zb.vo.zhubo.StartPlayResponseVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/mgr")
public class MgrServiceImpl  implements IMgrService{
    private static Logger logger = LoggerFactory.getLogger(MgrServiceImpl.class);
    @Autowired
    private RoomPlanDao roomPlanDao;
    @Autowired
    private RoomTypeSetDao roomTypeSetDao;
    @Autowired
    private RoomConfDao roomConfDao;
    @Autowired
    private RoomPlanStatDao roomPlanStatDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private ZhuboDao zhuboDao;
    @Autowired
    private UcUserDao ucUserDao;
    @Autowired
    private QvodAdminUsersDao qvodAdminUsersDao;
    @Autowired
    private UserAttentionDao userAttentionDao;
    @Autowired
    private UserPersonalDao userPersonalDao;
    @Autowired
    private PlatformManager platformManager;


    /**
     * 后台开播API - 获取开播信息+开播接口
     * 运营账号登陆后台，创建自定义直播房间界面业务流程
     * （1）验证参数：是否合法
     *      A.  验证管理平台Token，查询qvod_admin_users的remember_token字段
     *      B .  验证主播ID
     *      C .   获取主播权限
     * （2）获取IM与Media地址：
     *      A.  Media地址后台输入
     *      B.  如果拿不到地址，IM=null接口正常返回，不中断
     *      C.  如果拿到地址，存入缓存zb_t_room_conf，用户看直播的时候直接重新拿推荐地址，一个地址绑定一个主播
     * （3）维护表：qvod_zb_t_room
     *      A.如果无房间，创建房间，status=0（创建中）初始化房间zbId,status,createTime
     *      B.如果有房间，继续（4-7）
     * （4）验证房间状态：
     *      A.未开播（=2）继续（54-7）
     *      B.直播中（=1），更新DB+缓存直播标题 等数据,  直接返回
     * （5）获取主播权限：
     *      A.判断主播是否被禁播；UI弹出提示框
     *      B.判断是否拥有 时常房间、门票房间、私密房间、游戏房间的开播权限
     * （6）获取房间属性：
     *      A. 参数获取—UI动态配置开播时间、持续时间、门票价格数据等
     *      B. 参数校验
     *    <p>
     *  （7）维护表：zb_t_room_plan
     *      A.  新增admin字段，管理平台用户创建；如果为空，代表直播用户创建
     *      B. 查询当前房间场次，确保缓存中没场次（用户没有直播），在进行场次的创建，场次status=房间status
     *      C.  创建场次，存储到缓存 房间信息，主播信息（主播昵称、等级、主播头像等），房间属性配置信息，场次信息
     *      D.  如果是私密房间，更新用户预约表zb_t_user_personal的邀请时间，已经房间预约信息
     * （8）组件返回值：
     *
     * @param zbId
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/{zbId}/createRoom", method = RequestMethod.POST)
    @Override
    public StartPlayResponseVo createRoom(@PathVariable(value = "zbId") Integer zbId, String token, @RequestBody MgrStartPlayRequestVo mgrStartPlayRequestVo) throws ServiceException {
        //（1）验证参数：是否合法
        ZbTZhubo zbTZhubo = this.zhuboDao.findOne(zbId);
        if (zbTZhubo == null || zbTZhubo.getZbId() == null) {
            throw new ServiceException("主播不存在，请您先申请主播");
        }
        //（5）获取主播权限：
        Integer status = zbTZhubo.getZbStatus();
        if (ZbConstant.ZB.status.disable == status) {
            throw new ServiceException("直播已经被禁播，请联系房管");
        }
        QvodAdminUsers qvodAdminUsers=qvodAdminUsersDao.findByRememberToken(token);
        if (qvodAdminUsers  ==  null){
            throw new ServiceException("请先登陆管理后台");
        }
        String roomType = mgrStartPlayRequestVo.getRoomType();
        if (StringUtils.isEmpty(roomType)) {
            throw new ServiceException("房间类型不能为空");
        }
        try {
            ZbConstant.Room.Type.valueOf(roomType);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("房间类型错误");
        }
        if (!(ZbConstant.Room.Type.normal.name().equals(roomType) || ZbConstant.Room.Type.ticket.name().equals(roomType))) {
            throw new ServiceException("房间类型目前只支持normal与ticket类型");
        }

        String roomTitle = mgrStartPlayRequestVo.getRoomTitle();
        if (StringUtils.isEmpty(roomTitle)) {
            throw new ServiceException("房间标题不能为空");
        }
        if (roomTitle.length() > 10) {
            throw new ServiceException("房间标题最多10个汉字");
        }
        String playUrl = mgrStartPlayRequestVo.getPlayUrl();
        if (StringUtils.isEmpty(playUrl)) {
            throw new ServiceException("拉流地址不能为空");
        }
        if (ZbConstant.Room.Type.ticket.name().equals(roomType)) {
            if (mgrStartPlayRequestVo.getStartTime() == null) {
                throw new ServiceException("开始时间不能为空");
            }
            Integer activityTime = mgrStartPlayRequestVo.getActivityTime();
            if (activityTime == null) {
                throw new ServiceException("持续时间不能为空");
            }
            Integer price = mgrStartPlayRequestVo.getPrice();
            if (price == null) {
                throw new ServiceException("单价不能为空");
            }
        }
        //（2）获取IM与Madia地址：
        ZbTRoomConf zbTRoomConf = this.roomConfDao.findByZbId(zbId);

        if (zbTRoomConf == null) {
            zbTRoomConf = new ZbTRoomConf();
            zbTRoomConf.setZbId(zbId);
            zbTRoomConf.setUserId(zbTZhubo.getUserId());
        }
        MediaResponseVo mediaResponseVo = null;
        IMResponseVo imResponseVo = null;
        mediaResponseVo=new MediaResponseVo("1", mgrStartPlayRequestVo.getPushUrl(), playUrl,token);
        zbTRoomConf.setMediaInfo(mediaResponseVo);
        if (zbTRoomConf.getImInfo()==null){
            try {
                String im_access_token=platformManager.getImAccessToken();
                Map<String,Object> imMap=platformManager.getImUrl(zbId,im_access_token,token);
                if (imMap!=null){
                    imResponseVo=new IMResponseVo("1", (String) imMap.get("ip"),(Integer) imMap.get("port"), im_access_token);
                    zbTRoomConf.setImInfo(imResponseVo);
                }else {
                    throw new ServiceException("无法获取IM服务器地址");
                }
            } catch (Exception e) {
                logger.error("getReadyPlayInfo--IM="+e.getMessage());
            }
        }
        //（3）维护表：如果无房间，创建房间，status=0（创建中）初始化房间zbId,status,createTime
        ZbTRoom zbTRoom = this.roomDao.findByZbId(zbId);
        if (zbTRoom == null) {
            zbTRoom = new ZbTRoom();
            zbTRoom.setZbId(zbId);
            zbTRoom.setRoomStatus(ZbConstant.Room.status.room_new);
            zbTRoom.setCreate_Time(new Timestamp(System.currentTimeMillis()));
            this.roomDao.save(zbTRoom);
        }
        // (2C)如果拿到地址，存入缓存，用户看直播的时候直接重新拿推荐地址，一个地址绑定一个主播,存储房间推拉流地址、聊天地址等信息
        zbTRoomConf.setRoomId(zbTRoom.getRoomId());
        this.roomConfDao.save(zbTRoomConf);

        //（4）验证房间状态：
        ZbTRoomPlan zbTRoomPlan = this.roomPlanDao.findByRoomId(zbTRoom.getRoomId());
        if (ZbConstant.Room.status.room_ing == zbTRoom.getRoomStatus() && zbTRoomPlan != null) {
            StartPlayResponseVo startPlayResponseVo = new StartPlayResponseVo();
            startPlayResponseVo.setPlanId(zbTRoomPlan.getPlanId());
            startPlayResponseVo.setRoomId(zbTRoom.getRoomId());
            startPlayResponseVo.setRoomStatus(zbTRoom.getRoomStatus());
            startPlayResponseVo.setResult("房间正在直播中，直播重新进入房间");
            //3A. 更新数据库+缓存标题（只能更新标题）
            this.roomDao.updateRoomStatusAndAndRoomTitle(zbTRoomPlan.getRoomId(), zbTRoom.getRoomStatus(), roomTitle);
            zbTRoomPlan.setRoomStatus(ZbConstant.Room.status.room_ing);
            zbTRoomPlan.setRoomTitle(roomTitle);
            this.roomPlanDao.save(zbTRoomPlan);
            return startPlayResponseVo;
        }
        if (ZbConstant.Room.status.room_redio == zbTRoom.getRoomStatus()) {
            StartPlayResponseVo startPlayResponseVo = new StartPlayResponseVo();
            startPlayResponseVo.setPlanId(zbTRoomPlan.getPlanId());
            startPlayResponseVo.setRoomId(zbTRoom.getRoomId());
            startPlayResponseVo.setRoomStatus(zbTRoom.getRoomStatus());
            startPlayResponseVo.setResult("房间正在回放中，待UI是否结束回放");

            return startPlayResponseVo;
        }
        //（5）获取主播权限：
        Integer isTicket=zbTZhubo.getTicketState();
//        Integer isTime=zbTZhubo.getTimeState();
//        Integer isPersonal=zbTZhubo.getPersonalState();
//        Integer isGame=zbTZhubo.getGameState();
        if (isTicket!=1){
            throw new ServiceException("当前主播没有开通门票房间权限，请联系房管");
        }
        //（6）获取房间属性：
        //（7） 维护表：zb_t_room_plan，更新直播状态
        zbTRoomPlan = new ZbTRoomPlan();
        zbTRoomPlan.setAdmin(qvodAdminUsers.getName()); //管理平台创建者用户
        this.roomDao.updateRoomStatusAndAndRoomTitle(zbTRoom.getRoomId(), ZbConstant.Room.status.room_ing, mgrStartPlayRequestVo.getRoomTitle());
        zbTRoomPlan.setRoomTitle(mgrStartPlayRequestVo.getRoomTitle());
        //(3.B)维护表：zb_t_room与zb_t_room_plan B.创建场次，存储到缓存 房间信息，主播信息（主播昵称、等级、主播头像等），房间属性配置信息，场次信息
//        zbTRoomPlan.setPlanId(zbTRoom.getRoomId()); 主键自增
        zbTRoomPlan.setRoomId(zbTRoom.getRoomId());
        zbTRoomPlan.setOrderField(zbTRoom.getOrderField());

        zbTRoomPlan.setRoomType(mgrStartPlayRequestVo.getRoomType());
        zbTRoomPlan.setRoomStatus(ZbConstant.Room.status.room_ing);     //同步记录房间状态
        zbTRoomPlan.setRoomBgPic(zbTRoom.getRoomBgPic());
        zbTRoomPlan.setOpenTime(new Date());    //开播时间

        zbTRoomPlan.setZbId(zbId);
        zbTRoomPlan.setUserId(zbTZhubo.getUserId());

        zbTRoomPlan.setBombScreen_count(0);
        zbTRoomPlan.setGiftCount(0);
        zbTRoomPlan.setIncomeAmount(0);
        zbTRoomPlan.setMachineUser(0);
        zbTRoomPlan.setOnlineUser(0);
        zbTRoomPlan.setViewCount(0);
        zbTRoomPlan.setRoom_No1(0);
        zbTRoomPlan.setActivityTimeCount(0);
        ZbUcUser ucUser=this.ucUserDao.findOne(zbTZhubo.getUserId());
        zbTRoomPlan.setZbNickname(ucUser.getNickname());
        zbTRoomPlan.setZbLevel(ucUser.getLevel()*1);
        zbTRoomPlan.setZbHeadImg(ucUser.getAvatar());
        zbTRoomPlan.setZbSignature(ucUser.getSignature());
        //(3)创建房间配置信息，先获取配置信息，一并存入
        if (ZbConstant.Room.Type.ticket.name().equals(roomType)) {
            ZbTRoomTypeSet zbTRoomTypeSet = roomTypeSetDao.findByRoomType(ZbConstant.Room.Type.ticket.name());
            Set<Integer> ticketAT = new TreeSet<>();
            ticketAT.add(zbTRoomTypeSet.getTime01());
            ticketAT.add(zbTRoomTypeSet.getTime02());
            ticketAT.add(zbTRoomTypeSet.getTime03());
            ticketAT.add(zbTRoomTypeSet.getTime04());
            ticketAT.add(zbTRoomTypeSet.getTime05());

            Set<Integer> ticketPR = new TreeSet<>();
            ticketPR.add(zbTRoomTypeSet.getPrice01());
            ticketPR.add(zbTRoomTypeSet.getPrice02());
            ticketPR.add(zbTRoomTypeSet.getPrice03());
            ticketPR.add(zbTRoomTypeSet.getPrice04());
            ticketPR.add(zbTRoomTypeSet.getPrice05());

            Map<String, Object> map = new HashMap<>();
            map.put("startTime", new Date(mgrStartPlayRequestVo.getStartTime()));//当前类型直播开始的时间
            map.put("activityTimeList", StringUtils.join(ticketAT, ","));
            map.put("selectActivityTime", mgrStartPlayRequestVo.getActivityTime());
            map.put("overTime", DateUtils.addMinutes(new Date(mgrStartPlayRequestVo.getStartTime()), mgrStartPlayRequestVo.getActivityTime()));
            map.put("priceList", StringUtils.join(ticketPR, ","));
            map.put("selectPrice", mgrStartPlayRequestVo.getPrice());
            zbTRoomPlan.setRoomSet(map);  //门票收费或者时长收费
        }
        this.roomPlanDao.save(zbTRoomPlan);

        //(7) 自定义房间，直接开播
        StartPlayResponseVo startPlayResponseVo = new StartPlayResponseVo();
        startPlayResponseVo.setRoomId(zbTRoom.getRoomId());
        startPlayResponseVo.setPlanId(zbTRoomPlan.getPlanId());
        startPlayResponseVo.setRoomStatus(ZbConstant.Room.status.room_ing);
        startPlayResponseVo.setResult("房间场次创建成功，主播开始直播");
        return startPlayResponseVo;
    }


}
