package edu.uclm.es.ds2021;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.carrefour.es/supermercado/el-mercado/cat20002/c");

		WebElement botonCookies = driver.findElement(By.xpath("/html/body/div[3]/div/footer/div[1]/div/button"));
		botonCookies.click();

		WebElement divPaginas = driver.findElement(By.className("pagination__main"));
		String texto = divPaginas.getText().trim();
		int posTercerEspacio = texto.lastIndexOf(' ');
		texto = texto.substring(posTercerEspacio).trim();

		int paginas = Integer.parseInt(texto);

		WebElement siguiente;
		List<WebElement> flechitas;

		String home = System.getProperty("user.home");
		System.out.println(home);
		try (FileOutputStream fos = new FileOutputStream(home + "/productos.json")) {
			fos.write("[".getBytes());
			procesarPagina(driver, fos);
			siguiente = driver.findElement(By.className("pagination__row"));
			siguiente = siguiente.findElement(By.tagName("a"));
			siguiente.click();

			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {

			}

			for (int i = 1; i < paginas; i++) {
				System.out.println("** PAGINA" + (i + 1) + "");
				Thread.sleep(5000);
				procesarPagina(driver, fos);
				Thread.sleep(2000);
				flechitas = driver.findElements(By.className("router-link-active"));
				siguiente = flechitas.get(1);
//				siguiente = driver.findElement(By.className("icon-right-arrow-thin"));
				siguiente.click();
				System.out.println("\n\n\n");
			}
			fos.write("]".getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			fos.write("]".getBytes());
		}

	}

	private static void procesarPagina(WebDriver driver, FileOutputStream fos) throws IOException {
		String precio;
		WebElement spanPrecio;
		List<WebElement> divProductos = driver.findElements(By.className("product-card__parent"));
		for (WebElement divProducto : divProductos) {
			List<WebElement> spanesPrecio = divProducto.findElements(By.className("product-card__price"));
			if (spanesPrecio.size() > 0) {
				spanPrecio = divProducto.findElement(By.className("product-card__price"));
			} else {
				spanPrecio = divProducto.findElement(By.className("product-card__price--current"));
			}
			precio = spanPrecio.getText();
			WebElement h2 = divProducto.findElement(By.tagName("h2"));
			String nombre = h2.getText();
			System.out.println(nombre + "..." + precio);

			JSONObject jso = new JSONObject();
			jso.put("nombre", nombre);
			jso.put("precio", precio);

			fos.write(jso.toString().getBytes());
		}
	}
}