package com.eason.transfer.openapi.core.system.action;

import com.eason.transfer.openapi.core.system.base.BaseAction;
import com.eason.transfer.openapi.core.system.dao.ResourceDao;
import com.eason.transfer.openapi.core.system.dao.UserDao;
import com.eason.transfer.openapi.core.system.entity.po.Resource;
import com.eason.transfer.openapi.core.system.entity.po.User;
import com.eason.transfer.openapi.core.system.entity.vo.*;
import com.eason.transfer.openapi.core.system.utils.RandomGUID;
import com.eason.transfer.openapi.core.utils.SparkLib;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 */
@Controller
public class UserAction extends BaseAction {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ResourceDao resourceDao;

	@RequestMapping(value = "/")
	public ModelAndView admin() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	@RequestMapping(value = "/{path1}/{path2}/{path3}")
	public ModelAndView indexPath(@PathVariable String path1, @PathVariable String path2, @PathVariable String path3) {
		return new ModelAndView(path1+"/"+path2+"/"+path3);
	}

	/**
	 * 用户登陆
	 */
	@RequestMapping(value = "/admin/user/login")
	@ResponseBody
	public MessageModel userLogin(String account,String pwd) {
		if(!StringUtils.isEmpty(account) && !StringUtils.isEmpty(pwd)){
			User user = userDao.findByAccount(account);
			if(user==null){
				this.FAILURE.setMessage("帐号不存在");
			} else if(!SparkLib.decodePassword(user.getPwd()).equals(pwd)){
				this.FAILURE.setMessage("帐号和密码错误");
			}else if("0".equals(user.getIsEnabled())){
				this.FAILURE.setMessage("您的帐号已被禁用");
			}else {
				this.setSessionAccount(user);
				this.SUCCESS.setMessage("登录成功！");
				return this.SUCCESS;
			}
		}else{
			this.FAILURE.setMessage("帐号和密码不能为空");
		}
		return this.FAILURE;
	}

	/**
	 * 获取菜单资源
	 */
	@RequestMapping("/admin/user/getResourcesOfUser")
	@ResponseBody
	public MenuModel getResourcesOfUser() {
		MenuModel treeModel1=new MenuModel();
		treeModel1.setId("1");
		treeModel1.setText("ROOT");
		List<MenuModel> subList1=new ArrayList<>();

		Resource resource=resourceDao.getOne("t0000000000000000000000000001000");

		MenuModel treeModel2=new MenuModel();
		treeModel2.setId(resource.getId());
		treeModel2.setText(resource.getTitle());

		List<MenuModel> subList2=new ArrayList<>();
		List<Resource> subResource2=resourceDao.findAllByParentId(resource.getId());
		subResource2.forEach(subResource -> {
			subList2.add(new MenuModel(subResource.getId(),subResource.getTitle(),subResource.getHref()));
		});
		treeModel2.setChildren(subList2);
		subList1.add(treeModel2);
		treeModel1.setChildren(subList1);
		return treeModel1;
	}

	/**
	 * 获取资源列表
	 */
	@RequestMapping("/admin/resource/getResources")
	@ResponseBody
	public List<TreeModel> getResources(String id) {
		return resourceConventerToTreeModel(resourceDao.findAllByParentId(id==null?"":id));
	}

	/**
	 * 保存或者更新资源
	 */
	@RequestMapping("/admin/resource/saveResource")
	@ResponseBody
	public List<TreeModel> saveResource(ResourceVo vo) {
		Resource po;
		if (StringUtils.isEmpty(vo.getId())){
			po=new Resource();
			BeanUtils.copyProperties(vo,po);
			po.setId(RandomGUID.newGuid());
			resourceDao.save(po);
		}else{
			po=resourceDao.getOne(vo.getId());
			BeanUtils.copyProperties(vo,po);
			resourceDao.save(po);
		}
		List<Resource> list=new ArrayList<>();
		list.add(po);
		return resourceConventerToTreeModel(list);
	}


	/**
	 * 将资源转换为对应的TreeModel
	 */
	private List<TreeModel> resourceConventerToTreeModel(List<Resource> resourceList) {
		List<TreeModel> treeModelList = new ArrayList<>();
		Resource resource;

		TreeModel treeModel;
		AttributesModel attributeModel;
		for (int i = 0; i < resourceList.size(); i++) {
			resource = resourceList.get(i);

			treeModel = new TreeModel();
			treeModel.setId(resource.getId());
			treeModel.setText(resource.getTitle());
			treeModel.setState(isHasNodes(resource.getId()));

			attributeModel = new AttributesModel();
			attributeModel.setCode(resource.getCode());
			attributeModel.setParentId(resource.getParentId());
			attributeModel.setHref(resource.getHref());

			treeModel.setAttributes(attributeModel);
			treeModelList.add(treeModel);
		}
		return treeModelList;
	}

	/**
	 * 判断节点是否有子节点
	 */
	private String isHasNodes(String id){
		if(resourceDao.findAllByParentId(id).size() < 1){
			return "open";
		}
		return "closed";
	}


}
