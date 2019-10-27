

**To run the tests:**
If you are using Eclipse: `Right click on the project and select Run As > maven Install`
If you are in terminal use the command `mvn install`

**How to configure the project to run in various environments**
First of all to run in saucelabs, setup the saucelabs credentials in `configs/Configuation.properties` file under the `CREDENTIALS` section.

1) Set the environment property to `local` for running locally or `saucelabs`for running in saucelabs
2) Provide the configurations (in `configs/Configuation.properties` file) for the browser and OS you want to run the suite in.(You can just uncomment the provided configurations)

 **To view the advanced cucumber reports:**
open `target/cucumber-report-html/cucumber-html-reports/feature-overview.html` in your browser

 **To view other reports:**
open `target/cucumber-report-html/cucumber-reports/cucumber-pretty/index.html` in your browser