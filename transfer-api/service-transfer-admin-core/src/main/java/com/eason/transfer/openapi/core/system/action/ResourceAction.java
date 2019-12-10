//package com.eason.transfer.openapi.core.system.action;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.eason.cms.web.BaseAction;
//import com.eason.ifpr.utils.StringUtil;
//import com.eason.system.service.ResourceManage;
//import com.eason.system.vo.MessageModel;
//import com.eason.system.vo.ResourceExtForm;
//import com.eason.system.vo.TreeModel;
//import com.eason.system.vo.TreeModel.AttributesModel;
//
//@Controller
//public class ResourceAction extends BaseAction {
//
//	@Autowired
//	private ResourceManage resourceManager;
//
//	/**
//	 * 获取资源列表
//	 */
//	@RequestMapping("/resource/getResources")
//	@ResponseBody
//	public List<TreeModel> getResources(@RequestParam(value = "id", required = false) String id) {
//		if(id==null||"root".equals(id)){
//			TreeModel treeModel = new TreeModel();
//			treeModel.setId("root");
//			treeModel.setText("系统资源");
//			treeModel.setState("open");
//
//			List<TreeModel> list = new ArrayList<TreeModel>();
//			list.add(treeModel);
//			List<ResourceExtForm> resourceList = resourceManager.getResourcesByParentId(null);
//			resourceConventerToTreeModel(resourceList);
//			treeModel.setChildren(resourceConventerToTreeModel(resourceList).toArray(new TreeModel[resourceList.size()]));
//			return list;
//		}else{
//			List<ResourceExtForm> resourceList = resourceManager.getResourcesByParentId(id);
//			return resourceConventerToTreeModel(resourceList);
//		}
//	}
//
//	/**
//	 * 新增或更新资源
//	 */
//	@RequestMapping("/resource/saveResource")
//	@ResponseBody
//	public List<TreeModel> addResource(ResourceExtForm resource) {
//		ResourceExtForm ref = null;
//		if(StringUtil.isEmpty(resource.getId())){
//			ref = resourceManager.addResource(resource);
//		}else{
//			ref = resourceManager.updateResource(resource);
//		}
//		List<ResourceExtForm> list = new ArrayList<ResourceExtForm>();
//		list.add(ref);
//		return resourceConventerToTreeModel(list);
//	}
//
//
//	@RequestMapping("/resource/checkResourceCode")
//	@ResponseBody
//	public MessageModel checkResourceCode(@RequestParam(value = "id", required = false)String id,
//			@RequestParam(value = "code", required = true)String code) {
//			boolean r = resourceManager.checkResouceCode(id, code);
//			if(r){
//				return new MessageModel(false,"资源编号已经存在");
//			}else{
//				return new MessageModel(true,"资源编号已经不存在");
//			}
//	}
//
//	/**
//	 * 删除资源
//	 *
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/resource/deleteResource")
//	@ResponseBody
//	public MessageModel deleteResource2(@RequestParam(value = "id", required = true)
//			String id) {
//		try {
//			resourceManager.deleteResource(id);
//			return SUCCESS;
//		} catch (Exception e) {
//			return FAILURE;
//		}
//	}
//
//	/**
//	 * 将资源转换为对应的TreeModel
//	 */
//	private List<TreeModel> resourceConventerToTreeModel(List<ResourceExtForm> resourceList) {
//		List<TreeModel> treeModelList = new ArrayList<TreeModel>();
//		ResourceExtForm resource;
//
//		TreeModel treeModel;
//		AttributesModel attributeModel;
//		for (int i = 0; i < resourceList.size(); i++) {
//			resource = resourceList.get(i);
//
//			treeModel = new TreeModel();
//			treeModel.setId(resource.getId());
//			treeModel.setText(resource.getTitle());
//			treeModel.setState(isHasNodes(resource.getId()));
//
//			attributeModel = treeModel.new AttributesModel();
//			attributeModel.setCode(resource.getCode());
//			attributeModel.setParentId(resource.getParentId());
//			attributeModel.setHref(resource.getHref());
//
//			treeModel.setAttributes(attributeModel);
//			treeModelList.add(treeModel);
//		}
//		return treeModelList;
//	}
//
//	/**
//	 * 判断节点是否有子节点
//	 */
//	private String isHasNodes(String id){
//		if(resourceManager.getResourcesByParentId(id).size() < 1){
//			return "open";
//		}
//		return "closed";
//	}
//}
