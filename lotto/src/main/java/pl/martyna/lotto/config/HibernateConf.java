package pl.martyna.lotto.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Contains basic Hibernate configuration
 * @author Martyna Drabinska
 * @version 0.1
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class HibernateConf {

    /**
     * Returns session factory
     * @return session factory
     * @throws NamingException thrown if cannot read required parameters from context
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws NamingException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("pl.martyna.lotto" );
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    /**
     * Returns data source
     * @return data source
     * @throws NamingException thrown if cannot read required parameters from context
     */
    @Bean
    public DataSource dataSource() throws NamingException {

        Context env = (Context)new InitialContext().lookup("java:comp/env");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName((String)env.lookup("driver"));
        dataSource.setUrl((String)env.lookup("database-url") );
        dataSource.setUsername((String)env.lookup("user"));
        dataSource.setPassword((String)env.lookup("password"));

        return  dataSource;
    }

    /**
     * Returns transaction manager
     * @return transaction manager
     * @throws NamingException thrown if cannot read required parameters from context
     */
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() throws NamingException{
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    /**
     * Returns hibernate properties
     * @return hibernate properties
     */
    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        return hibernateProperties;
    }
}