package org.sample.moviedb.config;

import java.util.List;

import org.sample.moviedb.resolver.CheckUserResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final CheckUserResolver checkUserResolver;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(checkUserResolver);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:80", "https://portfolio1.store")
			.allowedMethods("GET", "POST")
			.allowCredentials(true)
			.allowedHeaders("Accept", "Authorization", "Content-Type")
			.maxAge(3600);
	}

}
