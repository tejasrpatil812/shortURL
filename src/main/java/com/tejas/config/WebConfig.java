package com.tejas.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan("com.tejas")
@EnableWebMvc
@EnableAsync
@EnableSwagger2
@EnableJpaRepositories(basePackages= {"com.tejas"})
public class WebConfig extends WebMvcConfigurerAdapter{
	
	//DATABASAE MANAGER
	@Bean
	public EntityManagerFactory entityManagerFactory(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(createJPAAdapter());
		factory.setDataSource(dataSource);
		factory.setPersistenceProviderClass(HibernatePersistence.class);
		factory.setPackagesToScan("com.tejas");
		factory.setJpaProperties(createJPAProperties());
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	//SWAGGER FOR API DOCUMENTATION
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
	
	//SWAGGER 
	@Override 
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
	
	@Bean
	  public JpaTransactionManager transactionManager() throws Exception {
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(entityManagerFactory());

	      return transactionManager;
	  }

	   private Properties createJPAProperties() {
	      Properties properties = new Properties();
	      return properties;
	  }

	   private HibernateJpaVendorAdapter createJPAAdapter() {
	       HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      vendorAdapter
	              .setShowSql(true);
	      vendorAdapter.setGenerateDdl(true);
	      vendorAdapter.setDatabase(Database.MYSQL);

	      return vendorAdapter;
	  }
}
