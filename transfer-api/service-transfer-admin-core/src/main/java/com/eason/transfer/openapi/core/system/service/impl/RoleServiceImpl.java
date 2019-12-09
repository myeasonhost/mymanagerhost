package com.eason.transfer.openapi.core.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eason.basic.dao.GenericDAO;
import com.eason.basic.utils.BeanUtils;
import com.eason.basic.utils.RandomGUID;
import com.eason.system.po.Role;
import com.eason.system.po.RoleResource;
import com.eason.system.po.User;
import com.eason.system.service.RoleService;
import com.eason.system.vo.DataModel;
import com.eason.system.vo.ResourceExtForm;
import com.eason.system.vo.RoleExtForm;
import com.eason.system.vo.TreeModel;
import com.eason.system.vo.TreeModel.AttributesModel;
import com.eason.system.vo.UserExtForm;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private GenericDAO baseDao;
	 
	
	@Override
	public void addRole(RoleExtForm roleExtForm) {
		Role role = BeanUtils.copyProperties(roleExtForm,Role.class);
		role.setId(RandomGUID.newGuid());
		baseDao.save(role);
	}

	@Override
	public void exeAssignResouces(String roleId,String[] resourceIds) {
		 String sql = "delete from RoleResource rr where rr.roleId='"+roleId+"' ";
		 baseDao.execteBulk(sql);
		 RoleResource cmsRoleResource;
		 for(String rrid:resourceIds){
		 cmsRoleResource = new RoleResource();
		 cmsRoleResource.setId(RandomGUID.newGuid());
		 cmsRoleResource.setRoleId(roleId);
		 cmsRoleResource.setResourceId(rrid);
		 baseDao.save(cmsRoleResource);
		 }
	}

	@Override
	public void deleteRole(String...roleIds) {
		 String sql = null;
		for(String id:roleIds){
			 sql = "delete from RoleResource rr where rr.roleId='"+id+"'  ";
			 baseDao.execteBulk(sql);
			 baseDao.delete(Role.class,id);
		}
	}

	@Override
	public RoleExtForm getRoleById(String roleId) {
		return BeanUtils.copyProperties(baseDao.get(Role.class,roleId),RoleExtForm.class);
	}

	@Override
	public List<RoleExtForm> getRoles(String... args) {
		String sql = " from Role r  order by r.code";
		return BeanUtils.transform(baseDao.getGenericByHql(sql), RoleExtForm.class);
	}

	@Override
	public void updateRole(RoleExtForm roleExtForm) {
		Role role = baseDao.get(Role.class,roleExtForm.getId());
		role.setCode(roleExtForm.getCode());
		role.setTitle(roleExtForm.getTitle());
		baseDao.update(role);
	}

	@Override
	public List<RoleResource> getResoucerOfRole(String roleId) {
		String sql = " from RoleResource rr where  rr.roleId='"+roleId+"'";
		List<RoleResource> list = baseDao.getGenericByHql(sql);
		return list;
	}
	
	@Override
	public TreeModel  generateResourceTree(List<ResourceExtForm> resourceExtForms){
		TreeModel root = new TreeModel();
		List<TreeModel> treeModeList = new ArrayList<TreeModel>();
		List<TreeModel> parentList = new ArrayList<TreeModel>();
		HashMap<String,ResourceExtForm> treemMap = new LinkedHashMap<String,ResourceExtForm>();
		HashMap<String, List<ResourceExtForm>> treeMapList =new LinkedHashMap<String, List<ResourceExtForm>>();
		for(ResourceExtForm form:resourceExtForms){
			String parentId = form.getParentId();
			if(!StringUtils.isEmpty(parentId)){
					if(treeMapList.containsKey(parentId)){
						treeMapList.get(parentId).add(form);
					}else{
						List<ResourceExtForm> list = new ArrayList<ResourceExtForm>();
						list.add(form);
						treeMapList.put(parentId,list);
					}
					treemMap.put(form.getId(), form);
			}else{
				TreeModel teemoModel = convertResouce2TreeNode(form);
				treeModeList.add(teemoModel);
				parentList.add(teemoModel);
			}
		}
		
		if(treeModeList.size()>0){
			root.setChildren(treeModeList.toArray(new TreeModel[treeModeList.size()]));
			root.setState("open");
			root.setId("root");
			root.setText("资源管理");
		}
		
		boolean hasChidren = true;
		List<TreeModel> nextTreeModelList = new ArrayList<TreeModel>();
		Set<String> parentIdKeySet = treeMapList.keySet();
		while(hasChidren){
			hasChidren = false;
			nextTreeModelList.clear();
			for(TreeModel parentTreeModel : parentList){
				Iterator<String> it = parentIdKeySet.iterator();
				while(it.hasNext()){
					String key = it.next();
					if(key.equals(parentTreeModel.getId())){
						List<ResourceExtForm> innerResouceList = treeMapList.get(key);
						List<TreeModel> childrenModelList = new ArrayList<TreeModel>();
						for(ResourceExtForm ref : innerResouceList){
							TreeModel tm = convertResouce2TreeNode(ref);
							tm.setState("open");
							nextTreeModelList.add(tm);
							childrenModelList.add(tm);
							hasChidren = true;
						}
						parentTreeModel.setChildren(childrenModelList.toArray(new TreeModel[childrenModelList.size()]));
						parentTreeModel.setState("open");
					}
				}
				if(!hasChidren){
					parentTreeModel.setState("closed");
				}else{
					/**
					 * 解决树显示问题
					 */
					parentTreeModel.setChecked(false);
				}
			}
			parentList = nextTreeModelList;
		}
		return root;
	}
	
	private TreeModel convertResouce2TreeNode(ResourceExtForm ref){
		TreeModel treeModel = new TreeModel();
		treeModel.setId(ref.getId());
		treeModel.setText(ref.getTitle());
		treeModel.setChecked(ref.isChecked());
		AttributesModel attributeModel = treeModel.new AttributesModel();
		attributeModel.setCode(ref.getCode());
		attributeModel.setParentId(ref.getParentId());
		attributeModel.setHref(ref.getHref());
		treeModel.setAttributes(attributeModel);
		return treeModel;
	}

	@Override
	public DataModel<UserExtForm> getUsersByRoleIds(String roleIds) {
		if (StringUtils.isEmpty(roleIds)) return new DataModel<UserExtForm>();
		DataModel<UserExtForm> model = new DataModel<UserExtForm>();
		String[] roleIdArr = roleIds.split(",");
		List<User> userlist = new ArrayList<User>();
		for (int i = 0; i < roleIdArr.length; i++) {
			String roleId = roleIdArr[i];
			Role po = baseDao.get(Role.class, roleId);
			if (null != po) {
				Set<User> userSet = po.getUsers();
				Iterator<User> it = userSet.iterator();
				while (it.hasNext()) {
					User  user = (User) it.next();
					if ("1".equals(user.getIsEnabled()) || StringUtils.isEmpty(user.getIsEnabled())) {
						userlist.add(user);
					}
				}
			}
		}
		if (userlist.size()>0) {
			model.setRows(BeanUtils.transform(userlist, UserExtForm.class));
		}
		return model;
	}
	

}
