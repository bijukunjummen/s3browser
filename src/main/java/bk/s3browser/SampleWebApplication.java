package bk.s3browser;


import bk.s3browser.domain.AWSCredentialType;
import bk.s3browser.domain.AmazonUser;
import bk.s3browser.domain.S3SessionScopedClient;
import bk.s3browser.service.UserDetailsService;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

@EnableAutoConfiguration
@ComponentScan
public class SampleWebApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		new SpringApplicationBuilder(SampleWebApplication.class).application()
				.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.sources(SampleWebApplication.class);
		return application;
	}

	@Bean
	@Scope(value= WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public S3SessionScopedClient s3SessionScopedClient(UserDetailsService userDetailsService) {
//		AmazonS3Client s3Client = new AmazonS3Client(new DefaultAWSCredentialsProviderChain());
		Authentication authentication = userDetailsService.getUser();
		AmazonUser amazonUser = (AmazonUser)authentication.getPrincipal();
		AmazonS3Client s3Client = null;
		if (amazonUser.getCredentialType().equals(AWSCredentialType.USER_SPECIFIED)) {
			s3Client = new AmazonS3Client(new BasicAWSCredentials(amazonUser.getAccessKey(),
					amazonUser.getSecretKey().toString()));
		} else {
			s3Client = new AmazonS3Client(new DefaultAWSCredentialsProviderChain());
		}
		S3SessionScopedClient s3SessionScopedClient = new S3SessionScopedClient();
		s3SessionScopedClient.setS3Client(s3Client);
		return s3SessionScopedClient;
	}
}
