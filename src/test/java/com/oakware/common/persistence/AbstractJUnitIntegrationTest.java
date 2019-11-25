package com.oakware.common.persistence;

import com.oakware.common.DbUnitTestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@ContextConfiguration(classes = DbUnitTestContext.class)
@Rollback
@Transactional
public abstract class AbstractJUnitIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    protected JdbcTemplate jdbcTemplate() {
        return jdbcTemplate;
    }

    protected void execute(String sql) {
        jdbcTemplate().update(sql);
    }

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }
}
