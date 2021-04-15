package org.example.mybatis;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * @author Jdx
 * @version 1.0
 * @description 分页不生效
 * @date 2021/4/15 14:51
 */
//@Configuration
public class PageSqlFactoryCon {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultEnumTypeHandler(EnumOrdinalTypeHandler.class);
        sqlSessionFactoryBean.setConfiguration(configuration);
        //关键代码 设置 MyBatis-Plus 分页插件
        sqlSessionFactoryBean.setPlugins(new PaginationInterceptor());

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSource dataSource() {
        //设置数据库信息
        return new PooledDataSource();
    }
}
