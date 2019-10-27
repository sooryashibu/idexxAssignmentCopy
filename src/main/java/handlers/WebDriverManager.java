package handlers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import enums.BrowserType;
import enums.EnvironmentType;
import utils.FileReaderManager;

public class WebDriverManager {
	private WebDriver driver;
	private static BrowserType browserType;
	private static EnvironmentType environmentType;

	public WebDriverManager() {

		browserType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}

	public WebDriver getDriver() {
		if (driver == null)
			return createDriver();
		return driver;
	}

	private WebDriver createDriver() {

		switch (environmentType) {
		case LOCAL:
			driver = createLocalDriver();
			break;

		case SAUCELABS:
			MutableCapabilities capabilities = FileReaderManager.getInstance().getConfigReader().getCapabilities();
			createSauceDriver(capabilities);
			break;
		}

		return driver;
	}

	private void createSauceDriver(MutableCapabilities capabilities) {
		ThreadLocal<String> sessionId = new ThreadLocal<>();

		Map<String, String> creds = FileReaderManager.getInstance().getConfigReader().getSaucelabCreds();
		String username = creds.get("SAUCE_USERNAME");
		String accesskey = creds.get("SAUCE_ACCESS_KEY");

		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("username", username);
		sauceOptions.setCapability("accessKey", accesskey);
		sauceOptions.setCapability("seleniumVersion", "3.11.0");
		sauceOptions.setCapability("name", "idexx assignment");

		capabilities.setCapability("sauce:options", sauceOptions);

		String SAUCE_REMOTE_URL = "http://" + username + ":" + accesskey + "@ondemand.saucelabs.com:80/wd/hub";
		try {
			driver = new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), capabilities);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		sessionId.set(((RemoteWebDriver) driver).getSessionId().toString());

		// set current sessionId
		String id = ((RemoteWebDriver) driver).getSessionId().toString();
		sessionId.set(id);
	}

	private WebDriver createLocalDriver() {
		System.setProperty(FileReaderManager.getInstance().getConfigReader().getDriverProperty(),
				FileReaderManager.getInstance().getConfigReader().getDriverPath());

		switch (browserType) {

		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case CHROME:
			driver = new ChromeDriver();
			break;
		default:
			break;
		}

		if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())
			driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(),
				TimeUnit.SECONDS);
		return driver;
	}
}