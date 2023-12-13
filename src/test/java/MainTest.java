import api.auth.ReUsableMethods;
import api.auth.RequestLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

import static io.restassured.RestAssured.given;
public class MainTest {
    @Test()
    public void openAirbaTest() throws InterruptedException{
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/chromedriver");

        //System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        //WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://airba:bJR63XgzSqRYGhHV55BDMF@test.airba.dev");

        WebElement profileUser = driver.findElement(By.xpath("//div[@class='flex space-x-2']/button[1]"));
        System.out.println(profileUser.getText());
        profileUser.click();

        Thread.sleep(3000);

        driver.quit();
    }
    @Test()
    public void authTest() {
        String phoneNumber = "+77775000101";
        RestAssured.baseURI = "https://api.mp-test.airba.dev/sso/api/v1/auth/signin/fast";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(RequestLoad.authSignInFast(phoneNumber))
                .when()
                .post()
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        String responseParam = js.get("masked_phone");
        System.out.println(responseParam);
    }

}
