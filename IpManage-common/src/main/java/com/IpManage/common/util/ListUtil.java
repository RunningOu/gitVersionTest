package com.IpManage.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装集合
 * ouln
 * 2019-7-29 08:19:31
 */
public class ListUtil {
    /**
     * 按指定大小，分隔集合，将集合按规定个数分为n个部分
     *
     * @param list
     * @param len
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int len) {
        if(list==null||list.size()==0||len<1){
            return null;
        }
        List<List<T>> result=new ArrayList<>();
        int size=list.size();
        int count=(size+len-1)/len;
        for(int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
