package com.example;

import com.example.ServiceTest.HomeworkApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = {HomeworkApplication.class, CucumberIT.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//cần tìm hiểu thêm
//class này để config stepdefs
public class SpringIntegrationTest {
}