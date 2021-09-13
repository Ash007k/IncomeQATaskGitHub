package Runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:reports/cucumber-html-report.html", "json:reports/cucumber.json"}
        ,features = {"src/test/resources/features/"}
        ,glue ={"stepDefinitions"}
)
public class CucumberRunnerTest {
}