package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import enums.BrowserType;
import enums.EnvironmentType;

public class ConfigFileReader {
	private Properties properties;
	private final String propertyFilePath = "configs\\Configuation.properties";

	public ConfigFileReader() {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public Map<String,String> getSaucelabCreds(){
		HashMap<String, String> creds =  new HashMap<String,String>();
		creds.put("SAUCE_USERNAME",  properties.getProperty("SAUCE_USERNAME"));
		creds.put("SAUCE_ACCESS_KEY",  properties.getProperty("SAUCE_ACCESS_KEY"));
		return creds;
	}
	
	public MutableCapabilities getCapabilities() {
		String browserName = properties.getProperty("browser") == null ? "chrome" : properties.getProperty("browser");
		String browserVersion = properties.getProperty("browserVersion") == null ? "73.0"
				: properties.getProperty("browserVersion");
		String platformName = properties.getProperty("platformName") == null ? "Windows 8.1"
				: properties.getProperty("platformName");

		// Set up the ChromeOptions object, which will store the capabilities
		MutableCapabilities capabilities = new MutableCapabilities();

		if (browserName.equals("chrome")) {
			ChromeOptions caps = new ChromeOptions();
			caps.setExperimentalOption("w3c", true);
			capabilities = caps;
		} else if (browserName.equals("firefox")) {
			capabilities = new FirefoxOptions();
		}

		capabilities.setCapability("browserVersion", browserVersion);
		capabilities.setCapability("platformName", platformName);
		return capabilities;
	}

	public String getDriverPath() {
		String driverPath = System.getProperty("user.dir") + properties.getProperty("driverPath");
		return driverPath;
	}

	public String getDriverProperty() {
		String driverProperty = properties.getProperty("driverProperty");
		return driverProperty;
	}

	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if (implicitlyWait != null) {
			try {
				return Long.parseLong(implicitlyWait);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;
	}

	public String getFrontendUrl() {
		String url = properties.getProperty("frontEndUrl");
		if (url != null)
			return url;
		else
			throw new RuntimeException(
					"frontEnd Url not specified in the Configuration.properties file for the Key:url");
	}

	public BrowserType getBrowser() {
		String browserName = properties.getProperty("browser");
		if (browserName == null || browserName.equals("chrome"))
			return BrowserType.CHROME;
		else if (browserName.equalsIgnoreCase("firefox"))
			return BrowserType.FIREFOX;
		else if (browserName.equals("iexplorer"))
			return BrowserType.INTERNETEXPLORER;
		else
			throw new RuntimeException(
					"Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}

	public EnvironmentType getEnvironment() {
		String environmentName = properties.getProperty("environment");
		if (environmentName == null || environmentName.equalsIgnoreCase("local"))
			return EnvironmentType.LOCAL;
		else if (environmentName.equals("saucelabs"))
			return EnvironmentType.SAUCELABS;
		else
			throw new RuntimeException(
					"Environment Type Key value in Configuration.properties is not matched : " + environmentName);
	}

	public Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if (windowSize != null)
			return Boolean.valueOf(windowSize);
		return true;
	}
}