package com.uniovi.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.pageobjects.PO_HomeView;
import com.uniovi.pageobjects.PO_LoginView;
import com.uniovi.pageobjects.PO_Properties;
import com.uniovi.pageobjects.PO_RegisterView;
import com.uniovi.pageobjects.PO_View;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyWallapopTests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
		// automáticas)):
		static String PathFirefox64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
		static String Geckdriver022 = "C:\\Users\\nacar\\Desktop\\SDI\\Practica 5\\PL-SDI-Sesión5-material\\geckodriver022win64.exe";
		static WebDriver driver = getDriver(PathFirefox64, Geckdriver022);
		static String URL = "http://localhost:8090";
		public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
			System.setProperty("webdriver.firefox.bin", PathFirefox);
			System.setProperty("webdriver.gecko.driver", Geckdriver);
			WebDriver driver = new FirefoxDriver();
			return driver;
		}
		
		// Antes de cada prueba se navega al URL home de la aplicaciónn
		@Before
		public void setUp() {
			driver.navigate().to(URL);
		}

		// Después de cada prueba se borran las cookies del navegador
		@After
		public void tearDown() {
			driver.manage().deleteAllCookies();
		}// Antes de la primera prueba
		@BeforeClass
		static public void begin() {
		}

		// Al finalizar la última prueba
		@AfterClass
		static public void end() {
			// Cerramos el navegador al finalizar las pruebas
			driver.quit();
		}
//		@Test
//		public void testPunto1() {
//			// Vamos al formulario de registro
//			PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
//			// Rellenamos el formulario.
//			PO_RegisterView.fillForm(driver, "user1@email.com", "Josefo", "Perez", "77777", "77777");
//			PO_View.getP();
//			// COmprobamos el error de email repetido.
//			PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
//			// Rellenamos el formulario.
//			PO_RegisterView.fillForm(driver, "user2@email.com", "Jose", "Perez", "77777", "77777777");
//			//Comprobamos error contraseña no coincide.
//			PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
//			//SUCCESFUL :)
//			PO_RegisterView.fillForm(driver, "user2@email.com", "Jose", "Perez", "77777", "77777");	
//		}
		
		@Test
		public void testPunto2() {
			// Vamos al formulario de registro
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			//CASO 1: datos válidos como admin
			PO_LoginView.fillForm(driver, "admin@email.com", "admin");
			PO_View.getP();
			//CASO 2: datos válidos como usuario estandar
			PO_LoginView.fillForm(driver, "user1@email.com", "11111");
			//CASO 3: Inicio de sesión con datos inválidos (usuario estándar, campo email y contraseña vacíos) 
			PO_LoginView.fillForm(driver, "", "");	
			PO_LoginView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
			//CASO 4:Inicio de sesión con datos válidos (usuario estándar, email existente, pero contraseña
			//incorrecta).
			PO_LoginView.fillForm(driver, "user1@email.com", "12122");
			//CASO 5:] Inicio de sesión con datos inválidos (usuario estándar, email no existente en la aplicación).
			PO_LoginView.fillForm(driver, "user2@email.com", "12122");
			PO_LoginView.checkKey(driver, "Error.login.userDoesNotExist", PO_Properties.getSPANISH());
			


		}
		
}
