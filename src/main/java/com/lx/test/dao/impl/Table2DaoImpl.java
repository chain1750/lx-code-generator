package com.lx.test.dao.impl;

import com.lx.test.entity.Table2;
import com.lx.test.dao.mapper.Table2Mapper;
import com.lx.test.dao.Table2DAO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 测试表2 DAO
 *
 * @author lx
 */
@Service
public class Table2DaoImpl extends ServiceImpl<Table2Mapper, Table2> implements Table2DAO {
}
