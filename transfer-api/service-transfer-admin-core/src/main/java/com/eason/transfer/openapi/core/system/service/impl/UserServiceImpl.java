package com.eason.transfer.openapi.core.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.eason.transfer.openapi.core.system.entity.vo.UserExtForm;
import com.eason.transfer.openapi.core.system.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eason.basic.dao.GenericDAO;
import com.eason.basic.utils.BeanUtils;
import com.eason.basic.utils.RandomGUID;
import com.eason.basic.utils.SparkLib;
import com.eason.system.po.Resource;
import com.eason.system.po.User;
import com.eason.system.po.UserRole;
import com.eason.system.service.UserService;
import com.eason.system.vo.DataModel;
import com.eason.system.vo.ResourceExtForm;
import com.eason.system.vo.UserExtForm;
import com.eason.system.vo.UserVo;
@Service
public class UserServiceImpl implements UserService {
	//用户名和密码，最大不能超过30个长度
	private static final int MAX_PASSWORD=30;
	@Autowired
	private GenericDAO baseDao;
	
	@Override
	public boolean addUser(UserExtForm userExtForm) {
		/*
		if(this.checkUseEmailExisted(userExtForm.getId(), userExtForm.getEmail())){
			return false;
		}else if(this.checkUserAccountExisted(userExtForm.getId(), userExtForm.getAccount())){
			return false;
		}else if(this.checkUserMobileExisted(userExtForm.getId(), userExtForm.getMobile())){
			return false;
		}*/
		User cmsUser = BeanUtils.copyProperties(userExtForm,User.class);
		cmsUser.setId(RandomGUID.newGuid());
		cmsUser.setPwd(SparkLib.encodePassword(userExtForm.getPwd(), MAX_PASSWORD));
		baseDao.save(cmsUser);
		return true;
	}
	
	@Override
	public boolean checkUserAccountExisted(String id,String account){
		String sql =""; 
		boolean isexists = false;
		if(StringUtils.isNotEmpty(id)){
			sql = " from User u where u.account ='"+account+"' and u.id <> '"+id+"' ";
		}else{
			sql = " from User u where u.account ='"+account+"'";
		}
		
		int count = baseDao.getGenericCountByHql(sql);
		if(count>0) isexists = true;
		return isexists;
	}
	
	@Override
	public boolean checkUserMobileExisted(String id,String mobile){
		String sql =""; 
		boolean isexists = false;
		if(StringUtils.isNotEmpty(id)){
			sql = " from User u where u.mobile ='"+mobile+"' and u.id <> '"+id+"' ";
		}else{
			sql = " from User u where u.mobile ='"+mobile+"'";
		}
		
		int count = baseDao.getGenericCountByHql(sql);
		if(count>0) isexists = true;
		return isexists;
	}
	
	@Override
	public boolean checkUseEmailExisted(String id,String email){
		String sql =""; 
		boolean isexists = false;
		if(StringUtils.isNotEmpty(id)){
			sql = " from User u where u.email ='"+email+"' and u.id <> '"+id+"' ";
		}else{
			sql = " from User u where u.email ='"+email+"'";
		}
		
		int count = baseDao.getGenericCountByHql(sql);
		if(count>0) isexists = true;
		return isexists;
	}
	 
	@Override
	public void exeAssignRoles(String userId,String []roleIds) {
		/**
		 * 删除用户具有的角
		 */
		String sql  = "delete from UserRole r where r.userId ='"+userId+"'";
		baseDao.execteBulk(sql);
		UserRole role =null;
		for(String roleId:roleIds){
			role = new UserRole();
			role.setId(RandomGUID.newGuid());
			role.setUserId(userId);
			role.setRoleId(roleId);
			baseDao.save(role);
		}
		
	}

	@Override
	public void deleteUser(String userId) {
		//删除用户，要删除用户所具有的角色
		String sql = "delete from UserRole ur where ur.userId='"+userId+"'";
		baseDao.execteBulk(sql);
		baseDao.delete(User.class,userId);
	}

	@Override
	public List<UserRole> getRolesByUserId(String userId) {
		String sql =  "from UserRole ur where  ur.userId='"+userId+"'";
		List<UserRole> list = baseDao.getGenericByHql(sql);
		return list;
	}
	

	@Override
	public UserExtForm getUserById(String userId) {
		  User cmsUser = baseDao.get(User.class,userId);
		  if(cmsUser!=null){
			  return BeanUtils.copyProperties(cmsUser, UserExtForm.class);
		  }
		return null;
	}

	@Override
	public DataModel getUsers(String... args) {
		DataModel model = new DataModel();
		model.setRows(BeanUtils.transform(baseDao.listAll(User.class), UserExtForm.class));
		return model;
	}

	@Override
	public boolean updateUser(UserExtForm userExtForm) {
		/*
		if(this.checkUseEmailExisted(userExtForm.getId(), userExtForm.getEmail())){
			return false;
		}else if(this.checkUserAccountExisted(userExtForm.getId(), userExtForm.getAccount())){
			return false;
		}else if(this.checkUserMobileExisted(userExtForm.getId(), userExtForm.getMobile())){
			return false;
		}*/
		User cmsUser =  baseDao.get(User.class,userExtForm.getId());
		cmsUser.setEmail(userExtForm.getEmail());
		cmsUser.setName(userExtForm.getName());
		cmsUser.setIsEnabled(userExtForm.getIsEnabled());
		cmsUser.setTele(userExtForm.getTele());
		cmsUser.setMobile(userExtForm.getMobile());
		cmsUser.setPwd(SparkLib.encodePassword(userExtForm.getPwd(), MAX_PASSWORD));
		baseDao.merge(cmsUser);
		return true;
	}
	
	@Override
	public void updateUserState(String state,String ...ids){
		for(String id:ids ){
			User cmsUser =  baseDao.get(User.class,id);
			cmsUser.setIsEnabled(state);
			baseDao.merge(cmsUser);
		}
		
	}
	
	@Override
	public List<ResourceExtForm> getUserResource(String userId){
		/**
		 * 获取用户资源
		 */
		StringBuffer sb = new StringBuffer("select distinct rs from UserRole ur,Role r,Resource rs,RoleResource rr ");
					 sb.append(" where r.id=ur.roleId and rr.roleId=r.id and rr.resourceId=rs.id ");
					 sb.append(" and ur.userId = '").append(userId).append("' order by rs.code ");
					 //.append("' group by rs.id order by rs.code ");
		List<Resource> resouceList = this.baseDao.getGenericByHql(sb.toString());
		List<ResourceExtForm> resultList = new ArrayList<ResourceExtForm>();
		for (Resource rs : resouceList) {
			ResourceExtForm rf = new ResourceExtForm();
			rf.setId(rs.getId());
			rf.setCode(rs.getCode());
			rf.setHref(rs.getHref());
			rf.setParentId(rs.getParentId());
			rf.setTitle(rs.getTitle());
			resultList.add(rf);
		}
//		return ListUtils.transform(resouceList,ResourceExtForm.class);
		return resultList;
	}

	@Override
	public String getLoginInfo(String loginName, String password) {
		String name = loginName.trim();
		String hsql ="from User u where (u.mobile='"+name+"' or u.account ='"+name+"' or u.email='"+name+"' ) and  isEnabled='1' ";
		List<User> list = this.baseDao.getGenericByHql(hsql);
		if(list!=null&&list.size()==1){
			User cmsUser = list.get(0);
			if(SparkLib.decodePassword(cmsUser.getPwd()).equals(password)){
				return cmsUser.getId();
			}
		}
		return null;
	}
	
	public UserVo getUserInfoByLoginNameAndPassword(String loginName, String password) {
		String name = loginName.trim();
		String hsql ="from User u where (u.mobile='"+name+"' or u.account ='"+name+"' or u.email='"+name+"' )";
		List<User> list = this.baseDao.getGenericByHql(hsql);
		if(list!=null&&list.size()==1){
			User cmsUser = list.get(0);
			if(SparkLib.decodePassword(cmsUser.getPwd()).equals(password)){
				return BeanUtils.copyProperties(cmsUser, UserVo.class);
			}
		}
		return null;
	}
	public User getUserPoByLoginNameAndPassword(String loginName, String password) {
		String name = loginName.trim();
		String hsql ="from User u where (u.mobile='"+name+"' or u.account ='"+name+"' or u.email='"+name+"' )";
		List<User> list = this.baseDao.getGenericByHql(hsql);
		if(list!=null&&list.size()==1){
			User cmsUser = list.get(0);
			return cmsUser;
		}
		return null;
	}
	
	@Override
	public boolean exeResetPassWord(String userid,String password, String newspassword){
		boolean ret = true;
		User cmsUser = baseDao.get(User.class,userid);
		if(SparkLib.decodePassword(cmsUser.getPwd()).equals(password)){
			cmsUser.setPwd(SparkLib.encodePassword(newspassword,MAX_PASSWORD));
			this.baseDao.update(cmsUser);
		}else{
			ret  = false;
		}
		return ret;
	}
	

	@Override
	public List<User> queryListByAccounts(String accounts) {
		 final String hql=" FROM User AS U WHERE U.account IN (:alist)";
		 String [] queryParams=accounts.split(",");
		 Query query = baseDao.getEntityManager().createQuery(hql);
		 query.setParameter("alist",queryParams);  
		 List<User> lsuser=query.getResultList();
		 return lsuser;
	}

	@Override
	public String queryStrMobiles(String accounts) {
		List<User> lsuser=this.queryListByAccounts(accounts);
		StringBuilder sb = new StringBuilder("");
		for(User user:lsuser){
			sb.append(user.getMobile());
			sb.append(";");
		}
		return sb.substring(0, sb.length()-1);
	}
	
	
	@Override
	public String queryStrEmails(String accounts) {
		List<User> lsuser=this.queryListByAccounts(accounts);
		StringBuilder sb = new StringBuilder("");
		for(User user:lsuser){
			sb.append(user.getEmail());
			sb.append(";");
		}
		return sb.substring(0, sb.length()-1);
	}

	@Override
	public String[] queryStrEmailAndMobiles(String accounts) {
		String [] strEmailAndMobiles = new  String[2];
		List<User> lsuser=this.queryListByAccounts(accounts);
		StringBuilder sbEmails = new StringBuilder("");
		StringBuilder sbMobiles = new StringBuilder("");
		for(User user:lsuser){
			sbMobiles.append(user.getMobile());
			sbMobiles.append(";");
			sbEmails.append(user.getEmail());
			sbEmails.append(";");
			
		}
		strEmailAndMobiles[0]=sbEmails.substring(0, sbEmails.length()-1);
		strEmailAndMobiles[1]=sbMobiles.substring(0, sbMobiles.length()-1);
		return strEmailAndMobiles;
	}
	

}
