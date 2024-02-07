package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(String.format("%s/product/create", baseUrl));
        String pageTitle = driver.getTitle();

        // Verify
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(String.format("%s/product/create", baseUrl));
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));

        String nameKey = "Detergen Arum Manis";
        String quantityKey = "25";
        nameInput.sendKeys(nameKey);
        quantityInput.sendKeys(quantityKey);

        WebElement submitInput = driver.findElement(By.tagName("button"));
        submitInput.click();

        driver.get(String.format("%s/product/list", baseUrl));
        List<WebElement> product = driver.findElements(By.tagName("td"));
        String productName = product.get(0).getText();
        String productQuantity = product.get(1).getText();

        // Verify
        assertEquals(nameKey, productName);
        assertEquals(quantityKey, productQuantity);
    }
}
