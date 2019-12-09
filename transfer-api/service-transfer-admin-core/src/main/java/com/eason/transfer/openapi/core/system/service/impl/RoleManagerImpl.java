package com.eason.transfer.openapi.core.system.service.impl;

import com.eason.transfer.openapi.core.system.entity.po.RoleResource;
import com.eason.transfer.openapi.core.system.entity.vo.DataModel;
import com.eason.transfer.openapi.core.system.entity.vo.ResourceExtForm;
import com.eason.transfer.openapi.core.system.entity.vo.RoleExtForm;
import com.eason.transfer.openapi.core.system.entity.vo.TreeModel;
import com.eason.transfer.openapi.core.system.service.ResourceService;
import com.eason.transfer.openapi.core.system.service.RoleManager;
import com.eason.transfer.openapi.core.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RoleManagerImpl  implements RoleManager {
	
	@Autowired
	private RoleService roleServices;
	
	@Autowired
	private ResourceService resourceService;

	@Override
	public void addRole(RoleExtForm roleExtForm) {
		roleServices.addRole(roleExtForm);
	}

	@Override
	public void exeAssignResouces(String roleId,String[] resourceIds) {
		roleServices.exeAssignResouces(roleId,resourceIds);
	}

	@Override
	public void deleteRole(String... roleIds) {
		roleServices.deleteRole(roleIds);
	}

	@Override
	public RoleExtForm getRoleById(String roleId) {
		return roleServices.getRoleById(roleId);
	}

	@Override
	public DataModel getRoles(String... args) {
		DataModel model = new DataModel();
		model.setRows(roleServices.getRoles(args));
		return model;
	}

	@Override
	public void updateRole(RoleExtForm roleExtForm) {
		 roleServices.updateRole(roleExtForm);
	}

	@Override
	public TreeModel getResourceOfRole(String roleId) {
		
		List<ResourceExtForm> r = resourceService.getResources();
		List<RoleResource> ur = roleServices.getResoucerOfRole(roleId);
		
		for(ResourceExtForm ref:r){
			if(ur!=null && ur.size()>0){
				boolean checked = false;
				for(RoleResource uref:ur){
					if(ref.getId().equals(uref.getResourceId())){
						ref.setChecked(true);
						checked = true;
						break;
					}
				}
				if(!checked)ref.setChecked(false);
			}else{
				ref.setChecked(false);
			}
		}
		return roleServices.generateResourceTree(r);
		 
	}

	@Override
	public DataModel getUsersByRoleIds(String roleIds) {
		// TODO Auto-generated method stub
		return roleServices.getUsersByRoleIds(roleIds);
	}

}
