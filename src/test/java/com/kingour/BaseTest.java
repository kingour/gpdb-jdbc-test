package com.kingour;

import com.kingour.jdbc.gpdb.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.text.NumberFormat;

@SpringBootTest(classes = App.class)
@TestPropertySource(properties = {"run=false"})
@DirtiesContext
public class BaseTest extends AbstractTransactionalTestNGSpringContextTests {

    protected static Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Test(enabled = false)
    public void test() {
        logger.info("debug...");
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        logger.info(nf.format(0.456d));
    }

}
