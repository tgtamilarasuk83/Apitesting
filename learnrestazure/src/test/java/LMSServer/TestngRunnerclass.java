package LMSServer;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class TestngRunnerclass {
	@CucumberOptions(
	        
	        glue = {"LMSServer"},
	        plugin = {
	                "pretty",
	                "json:target/cucumber.json",
	                "html:target/cucumber-report.html"
	        }
	)
	public class Testing_LMSServer extends AbstractTestNGCucumberTests {
	}

}
