package springboot.psql.concurrent;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
    @PropertySource("classpath:default.properties")
})
public class SBPSQLConcurrentSpringConfig implements InitializingBean {

	@Autowired
	private Environment env;

	@Override
	public void afterPropertiesSet() throws Exception {
	}
}