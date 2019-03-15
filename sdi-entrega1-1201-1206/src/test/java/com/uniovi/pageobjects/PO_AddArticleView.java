package com.uniovi.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_AddArticleView  extends PO_NavView{
	static public void fillForm(WebDriver driver, String titleP, String descriptionP, String
			price) {
		WebElement title = driver.findElement(By.name("title"));
		title.click();
		title.clear();
		title.sendKeys(titleP);
		WebElement name = driver.findElement(By.name("description"));
		name.click();
		name.clear();
		name.sendKeys(descriptionP);
		WebElement lastname = driver.findElement(By.name("price"));
		lastname.click();
		lastname.clear();
		lastname.sendKeys(price);
		By boton = By.className("btn");
		driver.findElement(boton).click();
		}	
}
