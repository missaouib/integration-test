package com.anderscore.testcontainers.frontend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.anderscore.testcontainers.resolver.ServletContainerContextParameterResolver;
import com.anderscore.testcontainers.resolver.WebDriverParameterResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.anderscore.testcontainers.extension.WebDriverContainerExtension;
import com.anderscore.testcontainers.util.ServletContainerContext;
import com.anderscore.testcontainers.extension.DbContainerExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

//tag::WekanSchedulerTest[]
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(DbContainerExtension.class)
@ExtendWith(WebDriverContainerExtension.class)
@ExtendWith(ServletContainerContextParameterResolver.class)
@ExtendWith(WebDriverParameterResolver.class)
public class WekanSchedulerTest {

  @Test
  public void createScheduler(ServletContainerContext context, RemoteWebDriver webDriver) {
      webDriver.get(context.getHttpUrl());
      
      // SchedulerOverviewPage
      assertEquals("Scheduler", webDriver.findElement(By.tagName("h1")).getText());
      webDriver.findElement(By.id("new")).click();
      
      // SchedulerCreationPage
      assertEquals("Scheduler anlegen", webDriver.findElement(By.tagName("h1")).getText());
      WebElement nameInput = webDriver.findElement(By.id("name"));
      nameInput.sendKeys("TestScheduler");
      nameInput.submit();
      
      // SchedulerOverviewPage
      assertEquals("Scheduler", webDriver.findElement(By.tagName("h1")).getText());
      assertEquals("1", webDriver.findElement(By.cssSelector("td:nth-child(1) span")).getText());
      assertEquals("TestScheduler", webDriver.findElement(By.cssSelector("td:nth-child(2) span")).getText());
  }
}
//tag::WekanSchedulerTest[]