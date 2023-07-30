package main;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EntityScan(basePackages ={"location_vector_mgmt"})
@EnableJpaRepositories(basePackages = {"location_vector_mgmt"})
@ComponentScan({"location_vector_mgmt"})
public class Locations_Main extends SpringBootServletInitializer  
{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Locations_Main.class);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Locations_Main.class, args);
	}
	
	 @Bean(name = "asyncExecutor")
	  public Executor taskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(50);
	    executor.setMaxPoolSize(100);
	    executor.setQueueCapacity(500);
	    executor.setThreadNamePrefix("prodservmods");
	    executor.initialize();
	    return executor;
	  }
}