package com.eason.transfer.openapi.core.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eason.transfer.openapi.core.system.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eason.cms.utils.WebConstant;
import com.eason.cms.web.BaseAction;
import com.eason.system.service.UserManage;
import com.eason.system.vo.DataModel;
import com.eason.system.vo.MessageModel;
import com.eason.system.vo.TreeModel;
import com.eason.system.vo.UserExtForm;
import com.eason.system.vo.UserVo;

/**
 * 用户管理
 */
@Controller
public class UserAction extends BaseAction {
	@Autowired
	private UserManage userManage;
	
	@RequestMapping(value = "/user/toDesktop/{id}")
	public ModelAndView goDesktop(@PathVariable String id) {
		ModelAndView mav = new ModelAndView();
		UserExtForm userExtForm=this.getSessionAccount();
		if (userExtForm.getId() == null|| !userExtForm.getId().equals(id)) {
			this.removeSessionAccount();
			mav.setViewName( "redirect:/admin");
			return mav;
		}  else {
			mav.setViewName( "redirect:/admin/desktop");
			mav.addObject(WebConstant.LoginAdminUser,userExtForm);
			return mav;
		}
	}
	
	@RequestMapping(value = "/desktop")
	public ModelAndView desktop(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (this.getSessionAccount().getAccount() == null) {
			this.removeSessionAccount();
			mav.setViewName( "redirect:/");
			return mav;
		}  else {
			mav.setViewName( "/pages/desktop");
			mav.addObject(WebConstant.LoginAdminUser,this.getSessionAccount());
			return mav;
		}
		
		
	}

	/**
	 * 用户登陆
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user/login")
	@ResponseBody
	public MessageModel userLogin(UserExtForm user,HttpServletRequest request) {
		if(!StringUtils.isEmpty(user.getAccount()) && !StringUtils.isEmpty(user.getPwd())){
			UserVo userInfo = userManage.getUserInfoByLoginNameAndPassword(user.getAccount(), user.getPwd());
			if(userInfo==null){
				this.FAILURE.setMessage("帐号和密码错误");
			}else if("0".equals(userInfo.getIsEnabled())){
				this.FAILURE.setMessage("您的帐号已被禁用");
			}else {
				UserExtForm userExtForm = userManage.getUserInfo(userInfo.getId());
				this.setSessionAccount(userExtForm);
				this.SUCCESS.setMessage("登录成功！");
				return this.SUCCESS;
			}
		}else{
			this.FAILURE.setMessage("帐号和密码错误");
		}
		return this.FAILURE;
	}

	/**
	 * 用户注销
	 */
	@RequestMapping(value = "/user/logout")
	public String userLogout(HttpSession session,HttpServletRequest request) {
		this.removeSessionAccount();
		return "redirect:/index.jsp";	
	}

	/**
	 *系统管理 修改密码专用
	 */
	@RequestMapping(value = "/user/updatePwd")
	@ResponseBody
	public MessageModel updatePwd(@RequestParam(value = "oldPwd", required = true) String oldPwd,
			@RequestParam(value = "newPwd", required = true) String newPwd,
			HttpSession session) {
		MessageModel messageModel = new MessageModel();
		UserExtForm user = this.getSessionAccount();
		boolean ret = userManage.exeResetPassWord(user.getId(),oldPwd, newPwd);
		if(ret){
			messageModel.setResult(true);
			messageModel.setMessage("密码修改正确，请牢记新密码!");
		}else{
			messageModel.setResult(false);
			messageModel.setMessage("原始密码错误!");
		}
		return messageModel;
	}
	
	/**
	 * 个人修改密码专用
	 * @param userVo
	 * @return  1=修改成功  -1=修改失败
	 */
	@RequestMapping("/user/changePassword")
	@ResponseBody
	public String changePassword(UserVo userVo){
		int result=userManage.changePassword(userVo);
		return result+"";
	}

	
	/**
	 * 获取所有用户
	 */
	@RequestMapping("/user/getUsers")
	@ResponseBody
	public DataModel getUsers() {
		try {
			DataModel model = userManage.getUsers();
			return model;
		} catch (Exception e) {
			log.error("获取用户发生异常====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * 新增用户
	 */
	@RequestMapping("/user/addUser")
	@ResponseBody
	public MessageModel addUser(UserExtForm user) {
		user.setIsEnabled(user.getIsEnabled() == null ? WebConstant.FALSE
				: WebConstant.TRUE);
		try {
			userManage.addUser(user);
			return SUCCESS;
		} catch (Exception e) {
			log.error("新增用户发生异常====" + e.getStackTrace());
			return FAILURE;
		}
	}

	/**
	 * 修改用户
	 */
	@RequestMapping("/user/updateUser")
	@ResponseBody
	public MessageModel updateUser(UserExtForm user) {
		user.setIsEnabled(user.getIsEnabled() == null ? WebConstant.FALSE
				: WebConstant.TRUE);
		try {
			userManage.updateUser(user);
			return SUCCESS;
		} catch (Exception e) {
			log.error("修改用户发生异常====" + e.getStackTrace());
			return FAILURE;
		}
	}
	
	/**
	 * 修改用户 -> 根据ID获取用户
	 */
	@RequestMapping("/user/getUserById")
	@ResponseBody
	public UserExtForm getUserById(String id) {
		try {
			UserExtForm userExtForm=userManage.getUserById(id);
			userExtForm.setPwd(null);
			return userExtForm;
		} catch (Exception e) {
			log.error("根据ID获取用户发生异常====" + e.getStackTrace());
			return null;
		}
	}
	
	/**
	 * 校验检查密码 ->根据ID返回校验状态
	 * return 1=正常 0=旧密码错误 -1=失败包含异常 -2=新旧密码不一致
	 */
	@RequestMapping("/user/checkedAccountId")
	@ResponseBody
	public String checkedAccountId(UserVo userVo){
		int result=userManage.checkedAccountId(userVo);
		return result+"";
	}
	

	/**
	 * 获取所有角色及该用户所具有角色
	 * 
	 * @param userId
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/user/getRolesOfUser/{userId}")
	@ResponseBody
	public DataModel getRolesOfUser(@PathVariable String userId) {
		return this.userManage.getRolesOfUser(userId);
	}
	/**
	 * 检查帐号是否存在
	 * @param id
	 * @param account
	 * @return
	 */
	@RequestMapping("/user/checkUserAccount")
	@ResponseBody
	public boolean  checkUserAccountExisted(String id, String account){
		boolean isExisted = userManage.checkUserAccountExisted(id, account);
		return isExisted;
	}
	
	/**
	 * 检查手机号码是否存在
	 * @param id
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/user/checkUserMobile")
	@ResponseBody
	boolean checkUserMobileExisted(String id, String mobile){
		boolean isExisted = userManage.checkUserMobileExisted(id, mobile);
		return isExisted;
	}
	
	
	/**
	 * 检查email是否存在 
	 * @param id
	 * @param email
	 * @return
	 */
	@RequestMapping("/user/checkUseEmail")
	@ResponseBody
	boolean checkUseEmailExisted(String id, String email){
		boolean isExisted = userManage.checkUseEmailExisted(id, email);
		return isExisted;
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/user/deleteUser")
	@ResponseBody
	public MessageModel deleteUser(@RequestParam(value = "id", required = true)
	String id) {
		try {
			userManage.deleteUser(id);
			return SUCCESS;
		} catch (Exception e) {
			log.error("删除用户发生异常====" + e.getStackTrace());
			FAILURE.setMessage(e.getMessage());
			return FAILURE;
		}
	}

	/**
	 * 批量删除用户
	 */
	@RequestMapping("/user/batchDelete")
	@ResponseBody
	public MessageModel batchDelete(
			@RequestParam(value = "ids[]", required = true)
			String[] ids) {
		try {
			for (String id : ids) {
				userManage.deleteUser(id);
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error("删除用户发生异常====" + e.getStackTrace());
			return FAILURE;
		}
	}

	/**
	 * 批量启用用户
	 */
	@RequestMapping("/user/batchEnabled")
	@ResponseBody
	public MessageModel batchEnabled(
			@RequestParam(value = "ids[]", required = true)
			String[] ids) {
		try {
			userManage.setUserState(WebConstant.TRUE, ids);
			return SUCCESS;
		} catch (Exception e) {
			log.error("启用用户发生异常====" + e.getStackTrace());
			return FAILURE;
		}
	}

	/**
	 * 批量禁用用户
	 */
	@RequestMapping("/user/batchUnabled")
	@ResponseBody
	public MessageModel batchUnabled(
			@RequestParam(value = "ids[]", required = true)
			String[] ids) {
		try {
			userManage.setUserState(WebConstant.FALSE, ids);
			return SUCCESS;
		} catch (Exception e) {
			log.error("停用户发生异常====" + e.getStackTrace());
			return FAILURE;
		}
	}

	/**
	 * 用户指定角色
	 */
	@RequestMapping("/user/assignRoles")
	@ResponseBody
	public MessageModel assignRoles(
			@RequestParam(value = "ids[]", required = true)
			String[] ids, @RequestParam(value = "id", required = false)
			String userId) {
		try {
			userManage.assignRoles(userId, ids);
			return SUCCESS;
		} catch (Exception e) {
			log.error("停用户发生异常====" + e.getStackTrace());
			return FAILURE;
		}
	}

	/**
	 * 获取用户资源
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/user/getResourcesOfUser")
	@ResponseBody
	public TreeModel getResourcesOfUser(HttpSession session) {
		try {
			UserExtForm userExtForm = this.getSessionAccount();
			return userExtForm.getResourceTree();
		} catch (Exception e) {
			log.error("获取用户资源====" + e.getStackTrace());
			return null;
		}
	}

}
