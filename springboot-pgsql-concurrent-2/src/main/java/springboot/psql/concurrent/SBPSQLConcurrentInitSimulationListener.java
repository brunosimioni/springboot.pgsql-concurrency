package springboot.psql.concurrent;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springboot.psql.concurrent.repository.ConcurrentItem;

@Configuration
public class SBPSQLConcurrentInitSimulationListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
		
		long total = 10000;
		
		for (long i = 0; i < total; i++) 
		{
			ConcurrentItem ci = this.jdbcTemplate.queryForObject(
	    		"SELECT id, counter2 FROM concurrentitems WHERE id = ?",
	            new Object[]{1},
	            new RowMapper<ConcurrentItem>() {	
	                public ConcurrentItem mapRow(ResultSet rs, int rowNum) throws SQLException {
	                	ConcurrentItem ci = new ConcurrentItem();
	                	ci.setId(rs.getInt("id"));
	                	ci.setCounter2(rs.getLong("counter2"));
	                    return ci;
	                }
            });

			ci.setCounter2(ci.getCounter2() + 1);
			
			this.jdbcTemplate.update("UPDATE concurrentitems SET counter2 = ? WHERE id = ?", ci.getCounter2(), ci.getId());
			this.jdbcTemplate.update("INSERT INTO concurrentitems_counters (id_concurrentitem, value) VALUES (?, ?)", ci.getId(), ci.getCounter2());
			this.jdbcTemplate.update("INSERT INTO concurrentitems_children (id_concurrentitem, count) VALUES (?, ?)", ci.getId(), ci.getCounter2());
			
			if (i%10 == 0) {
				System.out.println((100 * i / total) + "%");
			}
		}
	}
}
