package com.example.demoMaven.controller;

import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class DemoController {
  private static final Logger LOG = Logger.getLogger(DemoController.class.getName());
        
    @GetMapping("/hello") // 使用HTTP Method: GET，Url路徑: hello。來獲取資源
    public String helloWorld() {
        
       LOG.info("SUB hello GO");

        return "Hello World From Spring Boot  SUB demo!!";
    }

    @GetMapping("/go/{msg}")
    public @ResponseBody String go(@PathVariable String msg)

    {

        System.out.printf("msg " + msg);
        String rtMsg = msg + "_OK";
        return rtMsg;
    }
    

}
