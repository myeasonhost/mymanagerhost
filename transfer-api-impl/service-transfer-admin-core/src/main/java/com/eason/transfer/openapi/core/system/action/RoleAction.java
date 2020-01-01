package com.eason.transfer.openapi.core.system.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eason.transfer.openapi.core.system.base.BaseAction;
import com.eason.transfer.openapi.core.system.dao.ResourceRoleDao;
import com.eason.transfer.openapi.core.system.dao.RoleDao;
import com.eason.transfer.openapi.core.system.entity.po.Role;
import com.eason.transfer.openapi.core.system.entity.po.RoleResource;
import com.eason.transfer.openapi.core.system.entity.vo.DataModel;
import com.eason.transfer.openapi.core.system.entity.vo.MessageModel;
import com.eason.transfer.openapi.core.system.entity.vo.UserVo;
import com.eason.transfer.openapi.core.system.utils.RandomGUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class RoleAction extends BaseAction {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ResourceRoleDao resourceRoleDao;

	/**
	 * 获取角色列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/role/getRoles")
	@ResponseBody
	public DataModel getRoles(HttpServletRequest requert,
                              HttpServletResponse response) {
		try {
            DataModel<Role> dataModel=new DataModel();
            List<Role> list=roleDao.findAll();
            dataModel.setRows(list);
			return dataModel;
		} catch (Exception e) {
			log.error("获取角色发生异常===="+e.getStackTrace());
		}
		return null;
	}


	/**
	 //	 * 角色配置资源
	 //	 */
	@RequestMapping("/admin/role/roleConfigResource")
	@ResponseBody
	public MessageModel roleConfigResouce(@RequestParam(value = "ids[]", required = true) String[] resourceIds,@RequestParam(value = "id", required = true) String roleId){
		try {
			List<RoleResource> roleResourceList = resourceRoleDao.findAllByRoleId(roleId);
			for (int i = 0; i < resourceIds.length ; i++) {
				boolean isAdd=true;
				for (int j = 0; j < roleResourceList.size(); j++) {
					if(roleResourceList.get(j).getResourceId().equals(resourceIds[i])){
						isAdd=false;
						break;
					}
				}
				if(isAdd){
					RoleResource roleResource=new RoleResource();
					roleResource.setId(RandomGUID.newGuid());
					roleResource.setResourceId(resourceIds[i]);
					roleResource.setRoleId(roleId);
					resourceRoleDao.save(roleResource);
				}
			}
			return SUCCESS;
		}catch(Exception e){
			log.error("保存角色资源发生异常====" + e.getStackTrace());
			return FAILURE;
		}
	}

	/**
	 * 新增角色
	 */
	@RequestMapping("/admin/role/addRole")
	@ResponseBody
	public MessageModel addRole(Role role){
		try{
            roleDao.save(role);
			return SUCCESS;
		}catch (Exception e) {
			log.error("新增角色发生异常===="+e.getStackTrace());
			return  null;
		}
	}

	/**
	 * 修改角色
	 */
	@RequestMapping("/admin/role/updateRole")
	@ResponseBody
	public MessageModel updateRole(Role role){
		try{
            Role newRole=new Role();
            newRole.setCode(role.getCode());
            newRole.setTitle(role.getTitle());
            roleDao.save(role);
			return SUCCESS;
		}catch (Exception e) {
			log.error("修改角色发生异常===="+e.getStackTrace());
			return null;
		}
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping("/admin/role/deleteRole")
	@ResponseBody
	public MessageModel deleteRole2(@RequestParam(value = "id", required = true) String id){
		try{
            roleDao.deleteById(id);
			return SUCCESS;
		}catch (Exception e) {
			log.error("删除角色发生异常===="+e.getStackTrace());
			return FAILURE;
		}
	}
//
//	@RequestMapping("/role/getResourceOfRole")
//	@ResponseBody
//	public List<TreeModel> getResourceOfRole(@RequestParam(value = "roleId", required = false) String roleId,@RequestParam(value = "id", required = false) String resouceId) {
//		try {
////			if(StringUtil.isEmpty(resouceId)||"root".equals(resouceId)){
////				TreeModel treeModel = new TreeModel();
////				treeModel.setId("root");
////				treeModel.setText("系统资源");
////				treeModel.setState("open");
////
////				List<TreeModel> list = new ArrayList<TreeModel>();
////				list.add(treeModel);
////				List<TreeModel> childList = roleManager.getResourceOfRole(roleId);
////				if(childList!=null&&childList.size()>0){
////					treeModel.setChildren(childList.toArray(new TreeModel[childList.size()]));
////				}
////				return list;
////			}else{
////				return roleManager.getResourceOfRole(roleId);
////			}
//
//			List<TreeModel> list = new ArrayList<TreeModel>();
//			list.add(roleManager.getResourceOfRole(roleId));
//			return list;
//		} catch (Exception e) {
//			log.error("获取角色资源发生异常====" + e.getStackTrace());
//		}
//		return null;
//	}
//
//	/**
//	 * 角色配置资源
//	 */
//	@RequestMapping("/role/roleConfigResource")
//	@ResponseBody
//	public MessageModel roleConfigResouce(@RequestParam(value = "ids[]", required = true) String[] resourceIds,@RequestParam(value = "id", required = true) String roleId){
//		try {
//			roleManager.exeAssignResouces(roleId, resourceIds);
//			return SUCCESS;
//		}catch(Exception e){
//			log.error("保存角色资源发生异常====" + e.getStackTrace());
//			return FAILURE;
//		}
//	}
//
//	/**
//	 * 获取审核人员
//	 */
//	@RequestMapping("/role/getApproveUserByRoleId")
//	@ResponseBody
//	public DataModel getApproveUserByRoleId(String roleIds){
//		DataModel model = new DataModel<UserExtForm>();
//		try {
//			 model = roleManager.getUsersByRoleIds(roleIds);
//			return model;
//		} catch (Exception e) {
//			log.error("获取用户发生异常====" + e.getStackTrace());
//		}
//		return model;
//	}

}
