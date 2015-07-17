package net.project.webDriverUtils;

import org.openqa.selenium.WebDriver;

public interface BrowserSpecificWebDriverCapabilities {

	public WebDriver getDefaultWebDriver();
	public WebDriver getRemoteWebDriver();
}
