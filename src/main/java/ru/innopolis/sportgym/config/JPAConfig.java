package ru.innopolis.sportgym.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static ru.innopolis.sportgym.config.Constance.*;

/**
 * Конфигурация ORM
 * Created by Кирилл on 05.11.2016.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ru.innopolis.sportgym.DAO")
public class JPAConfig {

    //Менеджер транзакций
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    //Контейнер фабрики
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(getDataSource());
        lcemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lcemfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lcemfb.setPackagesToScan("ru.innopolis.sportgym.entity");
        return lcemfb;
    }

    /**
     * Создаем DataSource - MySQL используем пулл apache.commons.dbcp
     *
     * @return dataSource
     */
    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER_DB);
        dataSource.setUrl(URL_BASE);
        dataSource.setUsername(USERNAME_DB);
        dataSource.setPassword(PASSWORD_DB);
        return dataSource;
    }


    // Используем в качестве реализации JPA - Hibernate
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }

}
