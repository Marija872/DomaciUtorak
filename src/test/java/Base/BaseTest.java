package Base;

import Pages.HomepagePage;
import Pages.SecureLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;

    public ExcelReader excelReader;

    public HomepagePage homepagePage;

    public SecureLoginPage secureLoginPage;

    @BeforeClass
    public void setUp() throws IOException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        excelReader = new ExcelReader("C:\\Users\\marij\\OneDrive\\Desktop\\DOmaciutorak\\TestData.xlsx");
        homepagePage = new HomepagePage(driver);
        secureLoginPage = new SecureLoginPage(driver);


    }

    @AfterClass
    public void tearDown () {
        driver.quit();
    }

}
