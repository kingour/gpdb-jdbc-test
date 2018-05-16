package com.kingour.jdbc.gpdb.service;

import com.kingour.jdbc.gpdb.config.Config;
import com.kingour.jdbc.gpdb.dao.pgsql.mybatis.TableMapper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

@Service
@Import(Config.class)
public class PgService {

    static Logger logger = LoggerFactory.getLogger(PgService.class);

    @Autowired
    private TableMapper pgDAO;

    @Autowired
    public Config conf;

    public void runTask() {
        logger.info("begin to do test...");
        for (int i = 0; i < 10; i++) {
            logger.info("Testing no partition {}: ...", i);
            pgDAO.testNoPrt();
        }

        for (int i = 0; i < 10; i++) {
            logger.info("Testing with partition {}: ...", i);
            pgDAO.testPrt();
        }
    }
}
