package com.eason.transfer.openapi.core.system.action;

import com.eason.transfer.openapi.core.system.base.BaseAction;
import com.eason.transfer.openapi.core.system.dao.*;
import com.eason.transfer.openapi.core.system.entity.po.*;
import com.eason.transfer.openapi.core.system.entity.vo.*;
import com.eason.transfer.openapi.core.system.utils.RandomGUID;
import com.eason.transfer.openapi.core.utils.SparkLib;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 用户管理
 */
@Controller
public class UserAction extends BaseAction {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private ResourceRoleDao resourceRoleDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @RequestMapping(value = "/")
    public ModelAndView admin() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/{path1}/{path2}/{path3}.ftl")
    public ModelAndView indexPath(@PathVariable String path1, @PathVariable String path2, @PathVariable String path3) {
        return new ModelAndView(path1 + "/" + path2 + "/" + path3);
    }

    /**
     * 新增用户
     */
    @RequestMapping("/admin/user/addUser")
    @ResponseBody
    public MessageModel addUser(UserVo userVo) {
        userVo.setIsEnabled(userVo.getIsEnabled() == null ? "0" : "1");
        User userPo = new User();
        userPo.setName(userVo.getName());
        userPo.setAccount(userVo.getAccount());
        userPo.setEmail(userVo.getEmail());
        userPo.setMobile(userVo.getMobile());
        userPo.setTele(userVo.getTele());
        userPo.setIsEnabled(userVo.getIsEnabled());
        userPo.setPwd(SparkLib.encodePassword(userVo.getPwd(), 30));
        userPo.setId(RandomGUID.newGuid());
        this.userDao.save(userPo);
        return this.SUCCESS;
    }

    /**
     * 查找用户
     */
    @RequestMapping("/admin/user/getUserById")
    @ResponseBody
    public UserVo getUserById(String id) {
        UserVo userVo = new UserVo();
        User userPo = this.userDao.getOne(id);
        BeanUtils.copyProperties(userPo, userVo);
        userVo.setPwd("");
        return userVo;
    }


    /**
     * 更新用户
     */
    @RequestMapping("/admin/user/updateUser")
    @ResponseBody
    public MessageModel updateUser(UserVo userVo) {
        User userPo = this.userDao.getOne(userVo.getId());
        userVo.setIsEnabled(userVo.getIsEnabled() == null ? "0" : "1");
        userPo.setName(userVo.getName());
        userPo.setEmail(userVo.getEmail());
        userPo.setMobile(userVo.getMobile());
        userPo.setTele(userVo.getTele());
        userPo.setIsEnabled(userVo.getIsEnabled());
        userPo.setPwd(SparkLib.encodePassword(userVo.getPwd(), 30));
        this.userDao.save(userPo);
        return this.SUCCESS;
    }

    /**
     * 获取所有用户
     */
    @RequestMapping("/admin/user/getUsers")
    @ResponseBody
    public DataModel getUsers() {
        DataModel<UserVo> dataModel = new DataModel();
        List<User> list = userDao.findAll();
        List<UserVo> listVo = new ArrayList<>();
        list.forEach(user -> {
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setName(user.getName());
            userVo.setAccount(user.getAccount());
            userVo.setEmail(user.getEmail());
            userVo.setMobile(user.getMobile());
            userVo.setTele(user.getTele());
            userVo.setIsEnabled(user.getIsEnabled());
            listVo.add(userVo);
        });
        dataModel.setTotal(listVo.size());
        dataModel.setRows(listVo);
        return dataModel;
    }

    /**
     * 用户登出
     */
    @RequestMapping("/admin/user/logout")
    @ResponseBody
    public ModelAndView logout() {
        this.removeSessionAccount();
        return new ModelAndView("login");
    }

    /**
     * 用户更新密码
     */
    @RequestMapping("/admin/user/changePassword")
    @ResponseBody
    public MessageModel changePassword() {
        this.removeSessionAccount();
        return this.SUCCESS;
    }

    /**
     * 用户登陆
     */
    @RequestMapping(value = "/admin/user/login")
    @ResponseBody
    public MessageModel userLogin(String account, String pwd) {
        if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(pwd)) {
            User user = userDao.findByAccount(account);
            if (user == null) {
                this.FAILURE.setMessage("帐号不存在");
            } else if (!SparkLib.decodePassword(user.getPwd()).equals(pwd)) {
                this.FAILURE.setMessage("帐号和密码错误");
            } else if ("0".equals(user.getIsEnabled())) {
                this.FAILURE.setMessage("您的帐号已被禁用");
            } else {
                this.setSessionAccount(user);
                this.SUCCESS.setMessage("登录成功！");
                return this.SUCCESS;
            }
        } else {
            this.FAILURE.setMessage("帐号和密码不能为空");
        }
        return this.FAILURE;
    }

    /**
     * 获取菜单资源
     */
    @RequestMapping("/admin/user/getResourcesOfUser")
    @ResponseBody
    public MenuModel getResourcesOfUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        MenuModel treeModel1 = new MenuModel();
        treeModel1.setId("1");
        treeModel1.setText("ROOT");
        List<UserRole> userRoleList = userRoleDao.findAllByUserId(user.getId());
        List<MenuModel> subList1 = new ArrayList<>();
        for (int i = 0; i < userRoleList.size(); i++) {
            List<RoleResource> roleResourceList = resourceRoleDao.findAllByRoleId(userRoleList.get(i).getRoleId());
            for (int j = 0; j < roleResourceList.size(); j++) {
                List<MenuModel> subList2 = new ArrayList<>();
                Resource resource = resourceDao.findAllById(roleResourceList.get(j).getResourceId());
                if (resource!=null && StringUtils.isBlank(resource.getParentId()) ) {
                    MenuModel treeModel2 = new MenuModel();
                    treeModel2.setId(resource.getId());
                    treeModel2.setText(resource.getTitle());
                    List<Resource> subResource2 = resourceDao.findAllByParentId(resource.getId());
                    subResource2.forEach(subResource -> {
                        subList2.add(new MenuModel(subResource.getId(), subResource.getTitle(), subResource.getHref()));
                    });
                    treeModel2.setChildren(subList2);
                    subList1.add(treeModel2);
                }
            }
        }
        treeModel1.setChildren(subList1);
        return treeModel1;
    }

    /**
     * 获取资源列表
     */
    @RequestMapping("/admin/resource/getResources")
    @ResponseBody
    public List<TreeModel> getResources(String id) {
        return resourceConventerToTreeModel(resourceDao.findAllByParentId(id == null ? "" : id));
    }

    /**
     * 保存或者更新资源
     */
    @RequestMapping("/admin/resource/saveResource")
    @ResponseBody
    public List<TreeModel> saveResource(ResourceVo vo) {
        Resource po;
        if (StringUtils.isEmpty(vo.getId())) {
            po = new Resource();
            BeanUtils.copyProperties(vo, po);
            po.setId(RandomGUID.newGuid());
            resourceDao.save(po);
        } else {
            po = resourceDao.getOne(vo.getId());
            BeanUtils.copyProperties(vo, po);
            resourceDao.save(po);
        }
        List<Resource> list = new ArrayList<>();
        list.add(po);
        return resourceConventerToTreeModel(list);
    }

    /**
     * 删除资源
     */
    @RequestMapping("/admin/resource/deleteResource")
    @ResponseBody
    public MessageModel deleteResource(String id) {
        resourceDao.deleteById(id);
        resourceDao.deleteAllByParentId(id);
        return new MessageModel(true, "删除成功");
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
    private String isHasNodes(String id) {
        if (resourceDao.findAllByParentId(id).size() < 1) {
            return "open";
        }
        return "closed";
    }


    /**
     * 获取所有角色
     */
    @RequestMapping("/admin/role/getRoles")
    @ResponseBody
    public DataModel getRoles() {
        DataModel<RoleVo> dataModel = new DataModel();
        List<Role> list = roleDao.findAll();
        List<RoleVo> listVo = new ArrayList<>();
        list.forEach(role -> {
            RoleVo roleVo = new RoleVo();
            roleVo.setId(role.getId());
            roleVo.setCode(role.getCode());
            roleVo.setTitle(role.getTitle());
            listVo.add(roleVo);
        });
        dataModel.setTotal(listVo.size());
        dataModel.setRows(listVo);
        return dataModel;
    }

    /**
     * 获取所有角色下的所有资源
     */
    @RequestMapping("/admin/role/getResourceOfRole")
    @ResponseBody
    public List<TreeModel> getResourceOfRole(String roleId, String id) {
        List<TreeModel> list = new ArrayList<>();

        List<Resource> resourceList = resourceDao.findAllByParentId(id == null ? "" : id);
        List<RoleResource> roleResourceList = resourceRoleDao.findAllByRoleId(roleId);

        List<ResourceRoleVo> resourceRoleVos = new ArrayList<>();

        resourceList.forEach(resource -> {
            ResourceRoleVo resourceRoleVo = new ResourceRoleVo();
            BeanUtils.copyProperties(resource, resourceRoleVo);

            if (roleResourceList != null & roleResourceList.size() > 0) {
                boolean checked = false;
                for (RoleResource uref : roleResourceList) {
                    if (resource.getId().equals(uref.getResourceId())) {
                        resourceRoleVo.setChecked(true);
                        checked = true;
                        break;
                    }
                }
                if (!checked) resourceRoleVo.setChecked(false);
            } else {
                resourceRoleVo.setChecked(false);
            }
            resourceRoleVos.add(resourceRoleVo);
        });
        return resourceConventerToTreeModel2(resourceRoleVos);
    }

    /**
     * 将资源转换为对应的TreeModel
     */
    private List<TreeModel> resourceConventerToTreeModel2(List<ResourceRoleVo> resourceList) {
        List<TreeModel> treeModelList = new ArrayList<>();
        ResourceRoleVo resource;

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
            treeModel.setChecked(resource.isChecked());
            treeModelList.add(treeModel);
        }
        return treeModelList;
    }


    /**
     * 获取所有角色及该用户所具有角色
     *
     * @param userId
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/admin/user/getRolesOfUser/{userId}")
    @ResponseBody
    public DataModel getRolesOfUser(@PathVariable String userId) {
        DataModel<RoleVo> dataModel = new DataModel();
        List<Role> list = roleDao.findAll();
        List<UserRole> userRoleList = userRoleDao.findAllByUserId(userId);
        List<RoleVo> listVo = new ArrayList<>();
        for (Role r : list) {
            RoleVo roleVo = new RoleVo();
            for (UserRole ur : userRoleList) {
                if (r.getId().equals(ur.getRoleId())) {
                    roleVo.setCk(true);
                    break;
                }
            }
            roleVo.setId(r.getId());
            roleVo.setCode(r.getCode());
            roleVo.setTitle(r.getTitle());
            listVo.add(roleVo);
        }
        dataModel.setTotal(listVo.size());
        dataModel.setRows(listVo);
        return dataModel;
    }

    /**
     * 用户指定角色
     */
    @RequestMapping("/admin/user/assignRoles")
    @ResponseBody
    public MessageModel assignRoles(
            @RequestParam(value = "ids[]", required = true)
                    String[] ids, @RequestParam(value = "id", required = false)
                    String userId) {
        try {
            List<UserRole> userRoleList = userRoleDao.findAllByUserId(userId);
            for (int i = 0; i < ids.length; i++) {
                boolean isAdd = true;
                for (int j = 0; j < userRoleList.size(); j++) {
                    if (userRoleList.get(j).getRoleId().equals(ids[i])) {
                        isAdd = false;
                        break;
                    }
                }
                if (isAdd) {
                    UserRole userRole = new UserRole();
                    userRole.setId(RandomGUID.newGuid());
                    userRole.setRoleId(ids[i]);
                    userRole.setUserId(userId);
                    userRoleDao.save(userRole);
                }
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error("停用户发生异常====" + e.getStackTrace());
            return FAILURE;
        }
    }


}
