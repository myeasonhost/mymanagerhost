package com.ruoyi.pay.controller;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.pay.domain.OrgAccountInfo;
import com.ruoyi.pay.service.IOrgAccountInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商户信息Controller
 *
 * @author doctor
 * @date 2022-07-20
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/org/info" )
@DataSource(value = DataSourceType.SLAVE)
public class OrgAccountInfoController extends BaseController {

    private final IOrgAccountInfoService iOrgAccountInfoService;

    /**
     * 查询商户信息列表
     */
    @PreAuthorize("@ss.hasPermi('org:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrgAccountInfo orgAccountInfo) {
        startPage();
        List<OrgAccountInfo> list = iOrgAccountInfoService.queryList(orgAccountInfo);
        return getDataTable(list);
    }

    /**
     * 导出商户信息列表
     */
    @PreAuthorize("@ss.hasPermi('org:info:export')" )
    @Log(title = "商户信息" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(OrgAccountInfo orgAccountInfo) {
        List<OrgAccountInfo> list = iOrgAccountInfoService.queryList(orgAccountInfo);
        ExcelUtil<OrgAccountInfo> util = new ExcelUtil<OrgAccountInfo>(OrgAccountInfo.class);
        return util.exportExcel(list, "info" );
    }

    /**
     * 获取商户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('org:info:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iOrgAccountInfoService.getById(id));
    }

    /**
     * 新增商户信息
     */
    @PreAuthorize("@ss.hasPermi('org:info:add')" )
    @Log(title = "商户信息" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrgAccountInfo orgAccountInfo) {
        return toAjax(iOrgAccountInfoService.save(orgAccountInfo) ? 1 : 0);
    }

    /**
     * 修改商户信息
     */
    @PreAuthorize("@ss.hasPermi('org:info:edit')" )
    @Log(title = "商户信息" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrgAccountInfo orgAccountInfo) {
        return toAjax(iOrgAccountInfoService.updateById(orgAccountInfo) ? 1 : 0);
    }

    /**
     * 删除商户信息
     */
    @PreAuthorize("@ss.hasPermi('org:info:remove')" )
    @Log(title = "商户信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iOrgAccountInfoService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
