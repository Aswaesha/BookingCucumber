package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class BookingSearchHotelSteps {
    String cityName;
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("Keyword for search is {string}")
    public void KeywordForSearchIs(String cityName) {
        this.cityName = cityName;
    }

    @When("User does search")
    public void UserDoesSearch() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.booking.com/searchresults.en-gb.html");
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(cityName);
        driver.findElement(By.xpath("//h2")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("Hotel {string} is on the first page")
    public void HotelIsOnTheFirstPage(String arg0) {
        final List<WebElement> listHotels = driver.findElements(By.xpath("//div[@data-testid='title']"));
        assertEquals(listHotels.get(0).getText(), arg0, "Название отеля не совпадает");
    }

    @And("Rating should be {string}")
    public void ratingShouldBe(String arg0) {
        final List<WebElement> ratingHotel = driver.findElements(By.xpath("//a[@target='_blank']//span//div[@data-testid='review-score']/div[contains(text(),*)]"));
        assertEquals(ratingHotel.get(0).getText(), arg0, "Рейтинг отеля не совпадает");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
