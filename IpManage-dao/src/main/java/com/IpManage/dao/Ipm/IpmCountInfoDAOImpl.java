package com.IpManage.dao.Ipm;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ouln on 2020年6月16日17:21:20.
 */
@Repository
public class IpmCountInfoDAOImpl implements IpmCountInfoDAO {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Map> countByProject() {
        return sqlSessionTemplate.selectList("countByProject");
    }

    @Override
    public List<Map> countByAffiliatedUnit() {
        return sqlSessionTemplate.selectList("countByAffiliatedUnit");
    }
}
