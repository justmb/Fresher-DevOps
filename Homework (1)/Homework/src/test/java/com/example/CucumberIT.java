package com.example;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(plugin = {"pretty", "json:target/cucumber.json"}, features = "classpath:features")
@RunWith(Cucumber.class)

public class CucumberIT {
}
