package com.project.devowls.job;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "my.obj")
@Getter
@Setter
public class MyObject {

    private String cronExecExpr = "5 * * * * *";

    // getter and setter
}
@Getter
@Setter
class MyClass {
  private MyObject myObj;

  public MyClass(MyObject myObj) {
    this.myObj = myObj;
  }

  @Scheduled(cron = "${my.obj.cron-exec-expr:5 * * * * *}")
  private void someTask() {
     System.out.println("my job");
  }
}
