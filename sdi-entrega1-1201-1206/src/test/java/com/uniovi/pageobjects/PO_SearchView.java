package com.uniovi.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_SearchView extends PO_NavView{
	static public void fillForm(WebDriver driver, String text, String descriptionP, String
			price) {
		WebElement search = driver.findElement(By.name("searchText"));
		search.click();
		search.clear();
		search.sendKeys(text);
		By boton = By.className("Buscar");
		driver.findElement(boton).click();
		}	
}
