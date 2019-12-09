package com.eason.transfer.openapi.core.system.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eason.basic.dao.GenericDAO;
import com.eason.basic.utils.BeanUtils;
import com.eason.basic.utils.RandomGUID;
import com.eason.system.po.Resource;
import com.eason.system.service.ResourceService;
import com.eason.system.vo.ResourceExtForm;

@Service
public class ResourceServiceImpl  implements ResourceService{
	
	@Autowired
	private GenericDAO baseDao;

	@Override
	public boolean checkResouceCode(String id,String code){
		boolean isExist = false;
		String sql = " select count(*) from Resouce r where r.code= '"+code+"' ";
		if(!StringUtils.isEmpty(id)){
			sql += " r.id != '"+id+"' ";
		}
		int i = baseDao.getGenericCountByHql(sql);
		if(i>0) isExist = true;
		return isExist;
	}

	@Override
	public ResourceExtForm addResource(ResourceExtForm resourceExtForm) {
		Resource cmsResource = BeanUtils.copyProperties(resourceExtForm,Resource.class);
		cmsResource.setId(RandomGUID.newGuid());
		baseDao.save(cmsResource);
		
//		if(!StringUtil.isEmpty(cmsResource.getParentId())){
//			Resource parentResouce = baseDao.get(Resource.class, cmsResource.getParentId());
//		}
		
		
		resourceExtForm.setId(cmsResource.getId());
		return resourceExtForm;
	}
	
	/**
	 *获取一级子节点
	 * @param parentCode
	 * @return
	 */
	@Override
	public List<ResourceExtForm> getResourcesByParentId(String parentId){
		StringBuffer sb = new StringBuffer();
		sb.append(" from Resource r  where  1=1 ");
		if(parentId!=null&&!parentId.trim().equals("")){
			sb.append(" and r.parentId = '").append(parentId).append("' ");
		}else{
			sb.append(" and ( r.parentId ='' or r.parentId is null) ");
		} 
		sb.append("order by r.code ");
		List<Resource> list = baseDao.getGenericByHql(sb.toString());
		return BeanUtils.transform(list, ResourceExtForm.class);
	}
	
	@Override
	public void deleteResource(String... resourceIds){ 
		String hsql =null;
		for(String id:resourceIds){
			Resource cmsResource = baseDao.get(Resource.class,id);
			hsql = "delete from t_sys_resource where id = '"+cmsResource.getId() +"'";
			baseDao.execteNativeBulk(hsql);
			hsql = "delete from t_sys_resource where parentId = '"+cmsResource.getId() +"'";
			baseDao.execteNativeBulk(hsql);
			hsql = "delete from t_sys_roleresource  where resourceId = '"+cmsResource.getId() +"'";
			baseDao.execteNativeBulk(hsql);
		}
	}

	@Override
	public ResourceExtForm getResourceById(String resourceId) {
		Resource cmsResource = baseDao.get(Resource.class,resourceId);
		if(cmsResource!=null){
			return BeanUtils.copyProperties(cmsResource,ResourceExtForm.class);
		}
		return null;
	}

	@Override
	public List<ResourceExtForm> getResources(String... args) {
		String sql  = "from Resource r order by r.code";
		List<Resource> list = baseDao.getGenericByHql(sql);
		return BeanUtils.transform(list, ResourceExtForm.class);
	}

	@Override
	public ResourceExtForm updateResource(ResourceExtForm resourceExtForm) {
		Resource cmsResource = BeanUtils.copyProperties(resourceExtForm,Resource.class);
		baseDao.merge(cmsResource);
		return resourceExtForm;
		
	}

}
