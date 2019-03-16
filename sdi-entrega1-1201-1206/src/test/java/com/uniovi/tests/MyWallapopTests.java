package com.uniovi.tests;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Article;
import com.uniovi.entities.User;
import com.uniovi.pageobjects.PO_AddArticleView;
import com.uniovi.pageobjects.PO_HomeView;
import com.uniovi.pageobjects.PO_LoginView;
import com.uniovi.pageobjects.PO_NavView;
import com.uniovi.pageobjects.PO_Properties;
import com.uniovi.pageobjects.PO_RegisterView;
import com.uniovi.pageobjects.PO_View;
import com.uniovi.repositories.UserRepository;
import com.uniovi.services.UserService;
import com.uniovi.tests.utils.SeleniumUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyWallapopTests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver022 = "C:\\Users\\nacar\\Desktop\\SDI\\Practica 5\\PL-SDI-Sesión5-material\\geckodriver022win64.exe";
	static WebDriver driver = getDriver(PathFirefox64, Geckdriver022);
	static String URL = "http://localhost:8090";
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		initdb();
		driver.navigate().to(URL);
	}

	private void initdb() {
		userRepository.deleteAll();

		User user1 = new User("user1@email.com", "NAcho", "Baru");
		user1.setPassword("11111");
		user1.setRole("ROLE_STANDARD");

		User user2 = new User("user2@email.com", "Fer", "Ruiz");
		user2.setPassword("11111");
		user2.setRole("ROLE_STANDARD");

		User user3 = new User("user3@email.com", "Ale", "Church");
		user3.setPassword("11111");
		user3.setRole("ROLE_STANDARD");

		User user4 = new User("admin@email.com", "admin", "admin");
		user4.setPassword("admin");
		user4.setRole("ROLE_ADMIN");

		User user5 = new User("user4@email.com", "Miguel", "Puerta");
		user5.setPassword("admin");
		user5.setRole("ROLE_STANDARD");

		Article art11 = new Article("palo", "rama", 10);
		Article art12 = new Article("hoja", "verde lol", 45);
		Article art13 = new Article("sfg", "vsf", 10);

		Article art21 = new Article("dfv", "vdv lol", 78);
		Article art22 = new Article("dvf", "fvdfvfv lol", 56);
		Article art23 = new Article("fvddfvd", "dfv lol", 111);

		Article art31 = new Article("wefwrerf", "werfwef lol", 98);
		Article art32 = new Article("wefwefwef", "wefwf lol", 89);
		Article art33 = new Article("hwefwefweoja", "wefwef lol", 96);

		Article art41 = new Article("howefwefja", "efewf lol", 698);
		Article art42 = new Article("bdfb", "verqwdqwdde lol", 123);
		Article art43 = new Article("hwefwefoja", "vewefwefrde lol", 7);

		Article art51 = new Article("hoeefja", "verefwefde lol", 5);
		Article art52 = new Article("hoefwefja", "verefwefde lol", 63);
		Article art53 = new Article("hfewefoja", "verfwefwefbede lol", 71);

		art11.setOwner(user1);
		art12.setOwner(user1);
		art13.setOwner(user1);

		user1.addArticle(art11);
		user1.addArticle(art12);
		user1.addArticle(art13);

		art21.setOwner(user2);
		art22.setOwner(user2);
		art23.setOwner(user2);

		user2.addArticle(art21);
		user2.addArticle(art22);
		user2.addArticle(art23);

		art31.setOwner(user3);
		art32.setOwner(user3);
		art33.setOwner(user3);

		user3.addArticle(art31);
		user3.addArticle(art32);
		user3.addArticle(art33);

		art41.setOwner(user4);
		art42.setOwner(user4);
		art43.setOwner(user4);

		user4.addArticle(art41);
		user4.addArticle(art42);
		user4.addArticle(art43);

		art51.setOwner(user5);
		art52.setOwner(user5);
		art53.setOwner(user5);

		user5.addArticle(art51);
		user5.addArticle(art52);
		user5.addArticle(art53);

		userService.addUser(user1);
		userService.addUser(user2);
		userService.addUser(user3);
		userService.addUser(user4);
		userService.addUser(user5);
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

	@Test
	public void testPunto1() {

		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// CASO 4:COmprobamos el error de email repetido.
		PO_RegisterView.fillForm(driver, "user1@email.com", "Josefo", "Perez", "77777", "77777");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "user2@email.com", "Jose", "Perez", "77777", "77777777");
		// CASO 3: Comprobamos error contraseña no coincide.
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
		// CASO 2: todo vacio
		PO_RegisterView.fillForm(driver, "", "", "", "77777", "77777777");
		// CASO 1: relleno válido.
		PO_RegisterView.fillForm(driver, "user2@email.com", "Jose", "Perez", "77777", "77777");
		SeleniumUtils.textoPresentePagina(driver, "MyWallapop");
	}

	@Test
	public void testPunto2() {
		// Vamos al formulario de login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// CASO 5: datos válidos como admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.getP();
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		// CASO 6: datos válidos como usuario estandar
		PO_LoginView.fillForm(driver, "user1@email.com", "11111");
		PO_View.getP();
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		// CASO 7:Inicio de sesión con datos válidos (usuario estándar, email existente,
		// pero contraseña
		// incorrecta).
		PO_LoginView.fillForm(driver, "user1@email.com", "12122");
		// CASO 8:Inicio de sesión con datos inválidos (usuario estándar, email no
		// existente en la aplicación).
		PO_LoginView.fillForm(driver, "user2@email.com", "12122");
		// CASO 9: Inicio de sesión con datos inválidos (usuario estándar, campo email y
		// contraseña vacíos)
		PO_LoginView.fillForm(driver, "", "");
		PO_LoginView.checkElement(driver, "text", "Identificate");
		SeleniumUtils.textoPresentePagina(driver, "Error en el login");
	}

	@Test
	public void testPunto3() {
		// CASO 10: login correcto
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// CASO 11: logout y comprobar que no hay Desconectar
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_LoginView.checkElement(driver, "text", "Identificate");
		SeleniumUtils.textoNoPresentePagina(driver, "Desconectar");
	}

	@Test
	public void testPunto4() {
		// CASO 12: mostrar usuarios y comprobar que estén todos.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos.get(0).click();

		// <WebElement> numeroUsuarios = SeleniumUtils.EsperaCargaPagina(driver, "free",
		// "//tbody/tr",
		// PO_View.getTimeout());
		// assertTrue(numeroUsuarios.size() == 2);
	}

	@Test
	public void testPunto5() {
		// CASO 13: , borrar el primer usuario de la lista, comprobar que la lista se
		// actualiza
		// y dicho usuario desaparece.

		// CASO 14: Ir a la lista de usuarios, borrar el último usuario de la lista,
		// comprobar que la lista se actualiza
		// y dicho usuario desaparece.

		// CASO 15: Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista
		// se actualiza y dichos
		// usuarios desaparecen.
	}

	@Test
	public void t1estPunto8() {
		// CASO 18: Mostrar el listado de ofertas para dicho usuario y comprobar que se
		// muestran todas los que
		// existen para este usuario.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@email.com", "11111");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'article-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/article/add')]");
		elementos.get(0).click();
		PO_AddArticleView.fillForm(driver, "Prueba eliminar", "suena super guay.", "150.2");
		SeleniumUtils.textoPresentePagina(driver, "Prueba eliminar");
		driver.findElement(By.name("eliminar")).click();
	}

	@Test
	public void testPunto6() {
		// CASO 16: rellenarla con datos válidos y pulsar el botón Submit.
		// Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@email.com", "11111");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'article-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/article/add')]");
		elementos.get(0).click();
		PO_AddArticleView.fillForm(driver, "Altavoz JBL", "suena super guay.", "150.2");
		PO_HomeView.checkElement(driver, "text", "Altavoz JBL");

		// CASO 17: Ir al formulario de alta de oferta, rellenarla con datos inválidos
		// (campo título vacío) y pulsar
		// el botón Submit. Comprobar que se muestra el mensaje de campo obligatorio.
		List<WebElement> elementos2 = PO_View.checkElement(driver, "free", "//li[contains(@id,'article-menu')]/a");
		elementos2.get(0).click();
		elementos2 = PO_View.checkElement(driver, "free", "//a[contains(@href, '/article/add')]");
		elementos2.get(0).click();
		PO_AddArticleView.fillForm(driver, "", "vaia ta vacio tt", "150.2");
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}

	@Test
	public void testPunto7() {
		// CASO 18: Mostrar el listado de ofertas para dicho usuario y comprobar que se
		// muestran todas los que
		// existen para este usuario.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@email.com", "11111");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'article-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/article/add')]");
		elementos.get(0).click();
		PO_AddArticleView.fillForm(driver, "Altavoz JBL", "suena super guay.", "150.2");

		List<WebElement> elementos2 = PO_View.checkElement(driver, "free", "//li[contains(@id,'article-menu')]/a");
		elementos2.get(0).click();
		elementos2 = PO_View.checkElement(driver, "free", "//a[contains(@href, '/article/add')]");
		elementos2.get(0).click();
		PO_AddArticleView.fillForm(driver, "Patinete", "Funciona y tal.", "150.2");

		SeleniumUtils.textoPresentePagina(driver, "Altavoz JBL");
		SeleniumUtils.textoPresentePagina(driver, "Patinete");
	}

	@Test
	public void testPuntoInternacionalizacion() {
		// CASO 27: Visualizar al menos cuatro páginas en Español/Inglés/Español
		// (comprobando que algunas
		// de las etiquetas cambian al idioma correspondiente). Página
		// principal/Opciones Principales de
		// Usuario/Listado de Usuarios de Admin/Vista de alta de Oferta.

		// pagina principal
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@email.com", "11111");
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());

		// Opciones de usuario
		PO_HomeView.changeIdiom(driver, "btnEnglish");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'article-menu')]/a");
		elementos.get(0).click();
		SeleniumUtils.textoPresentePagina(driver, "Register Product");

		// Lista de usuarios
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		List<WebElement> elementos2 = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos2.get(0).click();
		elementos2 = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos2.get(0).click();
		PO_NavView.changeIdiom(driver, "btnEnglish");
		SeleniumUtils.textoPresentePagina(driver, "Users registred in the system:");

		// vista agregar articulo
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@email.com", "11111");
		List<WebElement> elementos3 = PO_View.checkElement(driver, "free", "//li[contains(@id,'article-menu')]/a");
		elementos3.get(0).click();
		elementos3 = PO_View.checkElement(driver, "free", "//a[contains(@href, '/article/add')]");
		elementos3.get(0).click();
		PO_AddArticleView.fillForm(driver, "Prueba internacional", "suena super guay.", "150.2");
		PO_NavView.changeIdiom(driver, "btnEnglish");
		SeleniumUtils.textoPresentePagina(driver, "Description");
	}

	@Test
	public void testSeguridad() {
		// CASO 28: Intentar acceder sin estar autenticado a la opción de listado de
		// usuarios del administrador. Se
		// deberá volver al formulario de login
		driver.navigate().to("http://localhost:8090/user/list");
		SeleniumUtils.textoPresentePagina(driver, "Identificate");

		// CASO 29: Intentar acceder sin estar autenticado a la opción de listado de
		// ofertas propias de un usuario
		// estándar. Se deberá volver al formulario de login.
		driver.navigate().to("http://localhost:8090/article/add");
		SeleniumUtils.textoPresentePagina(driver, "Identificate");

		// CASO 30: Estando autenticado como usuario estándar intentar acceder a la
		// opción de listado de
		// usuarios del administrador. Se deberá indicar un mensaje de acción prohibida.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@email.com", "11111");
		driver.navigate().to("http://localhost:8090/user/list");
		SeleniumUtils.textoPresentePagina(driver, "Forbidden");
	}

}
