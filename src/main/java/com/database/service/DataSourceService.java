package com.database.service;

import com.database.mapper.primary.PrimaryMapper;
import com.database.mapper.secondary.SecondaryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sunlongfei
 */
@Slf4j
@Service
public class DataSourceService {

    @Autowired
    private PrimaryMapper primaryMapper;

    @Autowired
    private SecondaryMapper secondaryMapper;

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void transaction() throws Exception {
        primaryMapper.insertItem("tran");
        log.info("inserted in transaction");
        throw new Exception("transaction exception");
    }

    @Transactional(value = "transactionManager2", rollbackFor = Exception.class)
    public void transaction2() throws Exception {
        secondaryMapper.insertItem("tran2");
        log.info("inserted in transaction2");
        throw new Exception("transaction2 exception");
    }
}
