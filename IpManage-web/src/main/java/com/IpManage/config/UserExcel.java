package com.IpManage.config;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zmg
 * @desc 描述：
 * <p>
 * 实现方案：
 * @date 2020-07-14 16:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget("userExcel")
public class UserExcel {
    /**
     * 名称
     */
    @Excel(name = "姓名",orderNum = "1")
    private String name;
    /**
     * 年龄
     */
    @Excel(name = "年龄",orderNum = "2")
    private Integer age;

    /**
     * 性别
     */
    @Excel(name = "性别",orderNum = "2",replace = {"男_M", "女_F","未知_X"})
    private String sex;


    /**
     * 手机
     */
    @Excel(name = "手机",orderNum = "3",width = 20)
    private String mobilePhone;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱",orderNum = "4",width = 20)
    private String email;

    /**
     * 昵称
     */
    @Excel(name = "昵称",orderNum = "5")
    private String nickName;

    @Excel(name = "生日", exportFormat = "yyyy-MM-dd", orderNum = "6")
    private Date birth;


}
