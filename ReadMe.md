# Sauce Demo App
This is an sample project to demonstrate how to work with Selenium with Cucumber in Java.

## Main dependencies
- Java 11
- Selenium 4.5.3
- Cucumber 7.9.0
- Junit 5.9.1
- log4j 2.19.0
- webdrivermanager 5.3.1

# `FEATURES`

### 1) Page Object Model design pattern usage

----

### 2) Data Table Usage

----

### 3) Regular expressions (Regex) and Cucumber Expressions Examples

----

### 4) `Clear Cache` in Chrome & `Shadow Root Element` Usage

----

### 5) Random Test Data Creation

----

### 5) Pojo Class sample

----

### 5) Enum Class sample

----
### 6) Supported Reports

Framework provides 3 different reports:
* [Spark(Extent) Reports](test-output/Spark/ExtentSpark.html)
* [Pdf](test-output/Pdf/ExtentPdf.pdf)
* [Cucumber JVM Report](target/cucumber-html-reports/overview-features.html)  (use `mvn verify` to generate JVM report)

----

### 6) Logging (Log4j SLF4J Binding)
[Logs](logs/automation.log) are created under logs package by using
* apache.logging.log4j
* log4j-slf4j-impl

----


# `RUNNING TESTS`

[comment]: <> (### Run a specific Scenario  &#40;Specify a particular scenario by *line*&#41;)

[comment]: <> (mvn test -Dcucumber.options="classpath:Features/SauceLabs.feature:53")

[comment]: <> (mvn test -Dcucumber.options="/src/test/resources/Features/SauceLabs.feature:53")

[comment]: <> (==> This works because Maven puts `./src/test/resources` on your `classpath`.)


### Running only the scenarios that failed in the previous run

mvn test -Dcucumber.features="@target/rerun.txt"
