package com.api.terraform.pipeline.terraforminfrapipeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.api.terraform.pipeline.terraforminfrapipeline", "controller", "dto"})
public class TerraformInfraPipelineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TerraformInfraPipelineApplication.class, args);
    }

}
