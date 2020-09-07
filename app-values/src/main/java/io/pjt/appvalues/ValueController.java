package io.pjt.appvalues;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValueController {

	@Value("${my.greetings: this is my default value}")
	private String greetingMessage;
	
	@Value("${my.list.value}")
	private List<String> listValues;
	
	@Value("This is my Static Value")
	private String staticVal;
	
	public String getStaticVal() {
		return staticVal;
	}

	public void setStaticVal(String staticVal) {
		this.staticVal = staticVal;
	}

	public ValueController() {
		
	}

	public String getGreetingMessage() {
		return greetingMessage;
	}

	public void setGreetingMessage(String greetingMessage) {
		this.greetingMessage = greetingMessage;
	}

	public List<String> getListValues() {
		return listValues;
	}

	public void setListValues(List<String> listValues) {
		this.listValues = listValues;
	}
	@Autowired
	private DbSetting dbSetting;
	
	@GetMapping("/greeting")
	public String greetingMethod() {
		return greetingMessage;
	}
	@GetMapping("/list")
	public List<String> listMethod() {
		return listValues;
	}
	
	@GetMapping("/pj")
	public String pjVals() {
		return dbSetting.getAppEnv() + " : " + dbSetting.getTeamHead();
	}
	@GetMapping("/dbParam")
	public Map<String,String> dbParams() {
		return dbSetting.getDbConn();
	}
}
