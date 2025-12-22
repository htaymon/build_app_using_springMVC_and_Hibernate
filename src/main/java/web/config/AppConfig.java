package web.config;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

   @Autowired
   private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("db.driver"));
        ds.setUrl(env.getProperty("db.url"));
        ds.setUsername(env.getProperty("db.username"));
        ds.setPassword(env.getProperty("db.password"));
        return ds;
    }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
       LocalContainerEntityManagerFactoryBean emf =
               new LocalContainerEntityManagerFactoryBean();

       emf.setDataSource(dataSource());
       emf.setPackagesToScan("web.model");

       HibernateJpaVendorAdapter vendorAdapter =
               new HibernateJpaVendorAdapter();
       emf.setJpaVendorAdapter(vendorAdapter);

       Properties props = new Properties();
       props.put("hibernate.hbm2ddl.auto",
               env.getProperty("hibernate.hbm2ddl.auto"));
       props.put("hibernate.show_sql",
               env.getProperty("hibernate.show_sql"));
       props.put("hibernate.dialect",
               "org.hibernate.dialect.MySQL8Dialect");

       emf.setJpaProperties(props);
       return emf;
   }

    @Bean
   public PlatformTransactionManager transactionManager() {
       JpaTransactionManager tm = new JpaTransactionManager();
       tm.setEntityManagerFactory(entityManagerFactory().getObject());
       return tm;
   }
}
