package com.lx.test.dao.impl;

import com.lx.test.entity.Table1;
import com.lx.test.dao.mapper.Table1Mapper;
import com.lx.test.dao.Table1DAO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 测试表1 DAO
 *
 * @author lx
 */
@Service
public class Table1DaoImpl extends ServiceImpl<Table1Mapper, Table1> implements Table1DAO {
}
