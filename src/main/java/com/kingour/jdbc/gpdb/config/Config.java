package com.kingour.jdbc.gpdb.config;

import com.kingour.jdbc.gpdb.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.conf")
public class Config {

    static Logger logger = LoggerFactory.getLogger(Config.class);

    private int version;

    public Config() {
        super();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return JsonUtil.toPrettyString(this);
    }
}
