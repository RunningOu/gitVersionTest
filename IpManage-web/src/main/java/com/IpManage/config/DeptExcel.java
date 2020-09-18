package com.IpManage.config;

import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.util.List;

/**
 * @author zmg
 * @desc 描述：
 * <p>
 * 实现方案：
 * @date 2020-07-14 16:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget("deptExcel")
public class DeptExcel implements Serializable {
    /**
     * 部门代码
     */
    @Excel(name = "部门代码", orderNum = "2", width = 20, needMerge = true)
    private String code;
    /**
     * 部门名称
     */
    @Excel(name = "部门名称", orderNum = "1", width = 20, needMerge = true)
    private String name;

    @ExcelCollection(name = "员工信息", orderNum = "3")
    private List<UserExcel> users;

}
