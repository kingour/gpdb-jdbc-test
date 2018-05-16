package com.kingour.jdbc.gpdb;

import com.kingour.jdbc.gpdb.config.Config;
import com.kingour.jdbc.gpdb.service.PgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Import({ Config.class })
public class App implements CommandLineRunner {

    static Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    private PgService taskService;
    
    @Autowired
    private Environment env;
    
    @Autowired
    private Config conf;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        boolean runIt = env.getProperty("run", Boolean.class, true);
        logger.info(conf.toString());
        if (runIt) {
            logger.info("Begin to run task.");
            taskService.runTask();
        } else {
            logger.info("This is testng mode.");
        }
    }

}
