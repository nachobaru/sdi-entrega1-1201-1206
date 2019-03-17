package com.uniovi.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView{

	public static void fillForm(WebDriver driver, String emailParam, String passwordParam) {
		WebElement email = driver.findElement(By.name("username"));
		email.click();
		email.clear();
		email.sendKeys(emailParam);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordParam);
		By boton = By.className("btn");
		driver.findElement(boton).click();
		
	}

}
