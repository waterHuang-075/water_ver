//package com.example.demoMaven;
package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages= {"com.example"})
@RestController

public class DemoOpenApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoOpenApplication.class, args);
    }

    
    @GetMapping("/hello3") // 使用HTTP Method: GET，Url路徑: hello。來獲取資源
    public String helloWorld() {
        return "Hello World From Spring Boot!!";
    }

}
