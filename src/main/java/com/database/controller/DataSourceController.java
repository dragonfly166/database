package com.database.controller;

import com.database.config.PrimaryDataSourceConfig;
import com.database.mapper.primary.PrimaryMapper;
import com.database.mapper.secondary.SecondaryMapper;
import com.database.service.DataSourceService;
import com.database.util.DataSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunlongfei
 */
@Slf4j
@RestController
@RequestMapping("/datasource")
public class DataSourceController {

    @Autowired
    private PrimaryMapper primaryMapper;

    @Autowired
    private SecondaryMapper secondaryMapper;

    @Autowired
    private DataSourceService dataSourceService;

    @GetMapping("/query")
    public String query() {
        String msg = primaryMapper.query(1);
        log.info(msg);
        msg = secondaryMapper.query(1);
        log.info(msg);
        return "success";
    }

    @PostMapping("/tran")
    public String transaction() {
        try {
            dataSourceService.transaction();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }

        try {
            dataSourceService.transaction2();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }

        return "success";
    }

    @GetMapping("/dynamic/query")
    public String dynamicQuery() throws Exception {
        String msg = primaryMapper.query(1);
        log.info(msg);
        DataSourceUtil.setDataSource("secondary");
        msg = primaryMapper.query(1);
        log.info(msg);

        msg = dataSourceService.query(1);
        log.info(msg);
        msg = dataSourceService.query2(1);
        log.info(msg);

        return "success";
    }
}
