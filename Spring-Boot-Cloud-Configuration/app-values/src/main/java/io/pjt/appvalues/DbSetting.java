package io.pjt.appvalues;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("pj")
public class DbSetting {
private Map<String, String> dbConn;
private String appEnv;
private String teamHead;

public Map<String, String> getDbConn() {
	return dbConn;
}

public void setDbConn(Map<String, String> dbConn) {
	this.dbConn = dbConn;
}

public String getAppEnv() {
	return appEnv;
}

public void setAppEnv(String appEnv) {
	this.appEnv = appEnv;
}

public String getTeamHead() {
	return teamHead;
}

public void setTeamHead(String teamHead) {
	this.teamHead = teamHead;
}

public DbSetting() {
	
}
}
