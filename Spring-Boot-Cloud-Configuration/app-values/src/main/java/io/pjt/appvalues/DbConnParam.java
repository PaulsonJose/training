package io.pjt.appvalues;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("db")
public class DbConnParam {

	private Map<String,String> conn;

	public Map<String, String> getConn() {
		return conn;
	}

	public void setConn(Map<String, String> conn) {
		this.conn = conn;
	}

}
