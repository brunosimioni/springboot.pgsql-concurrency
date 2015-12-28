package springboot.psql.concurrent;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jersey.JerseyApiReader;
import com.wordnik.swagger.reader.ClassReaders;

@Configuration
public class SBPSQLConcurrentContainerListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

	public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {

		int port = event.getEmbeddedServletContainer().getPort();
	    
	    // Swagger.
        SwaggerConfig config = ConfigFactory.config();
        config.setBasePath("https://localhost:"+port+"/api");
        config.setApiVersion("1.0.0");
        ScannerFactory.setScanner(new DefaultJaxrsScanner());
        ClassReaders.setReader(new JerseyApiReader());
	}
}
