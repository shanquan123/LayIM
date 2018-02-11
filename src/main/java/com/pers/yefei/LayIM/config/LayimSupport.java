package com.pers.yefei.LayIM.config;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.pers.yefei.LayIM.utils.SpringContextHolder;

public class LayimSupport extends ApplicationObjectSupport implements ApplicationListener{

    private static final Logger LOGGER = LoggerFactory.getLogger(LayimSupport.class);

    private Map<String, String> configMap;

    void loadConfig() {
        DataSource ds = SpringContextHolder.getBean("dataSource");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        final Map<String, String> configs = new ConcurrentHashMap<>();

        jdbcTemplate.query("select * from tbl_project_config where isValid = 1", new RowMapper<Integer>(){

            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                String code = rs.getString("code");
                String value = rs.getString("value");
                configs.put(code, value);
                return 1;
            }
        });

        configMap = configs;
    }

    public String getResourceHost() {
        return configMap.get("upload_resource_host");
    }

    public Map<String, String> getConfigMap() {
        return configMap;
    }

    public void setConfigMap(Map<String, String> configMap) {
        this.configMap = configMap;
    }



    class CheckDataVersion implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5 * 60 * 1000);
                    LOGGER.info(new Date().toString() + "时间加载配置文件开始");
                    loadConfig();
                    LOGGER.info(new Date().toString() + "时间加载配置文件完毕");

                } catch (Exception e) {
                    LOGGER.error(e.toString(), e);

                }
            }
        }
    }

    public String getValue(String key) {
        return configMap.get(key);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent
                && ((ContextRefreshedEvent) event).getApplicationContext().getParent() == null
                ) {
            loadConfig();
            Thread t = new Thread(new CheckDataVersion());
            t.setDaemon(true);
            t.start();
        }

    }
}
