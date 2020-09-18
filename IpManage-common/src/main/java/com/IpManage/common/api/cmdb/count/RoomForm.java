package com.IpManage.common.api.cmdb.count;

import lombok.Data;

import java.util.Map;

/**
 * @author yl
 * @Date 2020/4/17 0017 14:57
 */
@Data
public class RoomForm {

    private String roomName;

    private Integer cabinetNum;

    private Map<String,Integer> classList;
}
