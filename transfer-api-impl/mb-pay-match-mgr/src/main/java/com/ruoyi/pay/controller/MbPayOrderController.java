package com.ruoyi.pay.controller;

import java.util.List;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.pay.domain.MbPayOrder;
import com.ruoyi.pay.service.IMbPayOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充值订单Controller
 *
 * @author doctor
 * @date 2022-09-05
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/order/recharge" )
public class MbPayOrderController extends BaseController {

    private final IMbPayOrderService iMbPayOrderService;

    /**
     * 查询充值订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:recharge:list')")
    @GetMapping("/list")
    public TableDataInfo list(MbPayOrder mbPayOrder) {
        startPage();
        List<MbPayOrder> list = iMbPayOrderService.queryList(mbPayOrder);
        return getDataTable(list);
    }

    /**
     * 导出充值订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:recharge:export')" )
    @Log(title = "充值订单" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(MbPayOrder mbPayOrder) {
        List<MbPayOrder> list = iMbPayOrderService.queryList(mbPayOrder);
        ExcelUtil<MbPayOrder> util = new ExcelUtil<MbPayOrder>(MbPayOrder.class);
        return util.exportExcel(list, "recharge" );
    }

    /**
     * 获取充值订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:recharge:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iMbPayOrderService.getById(id));
    }

    /**
     * 新增充值订单
     */
    @PreAuthorize("@ss.hasPermi('order:recharge:add')" )
    @Log(title = "充值订单" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MbPayOrder mbPayOrder) {
        return toAjax(iMbPayOrderService.save(mbPayOrder) ? 1 : 0);
    }

    /**
     * 修改充值订单
     */
    @PreAuthorize("@ss.hasPermi('order:recharge:edit')" )
    @Log(title = "充值订单" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MbPayOrder mbPayOrder) {
        return toAjax(iMbPayOrderService.updateById(mbPayOrder) ? 1 : 0);
    }

    /**
     * 删除充值订单
     */
    @PreAuthorize("@ss.hasPermi('order:recharge:remove')" )
    @Log(title = "充值订单" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iMbPayOrderService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
