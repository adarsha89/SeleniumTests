# SeleniumTests
Description: SeleniumMaven project to Test Codepair in HackerRankForWork website

Purpose: Functional testing of Codepair. The project is aimed at covering all major functionalities that can be automated using Selenium WebDriver

Components:

1) Maven framework to manage the dependencies in terms of jar files and to package the entire application.
2) TestNG framework for test automation management (Management of creation and execution of test suites and testcases    and test execution reports )
3) Apache POI API to integrate with Microsoft excel
4) Java mail API to email the test execution reports, screenshots,etc after zipping these files.


Features: 

1) This project is a Maven Java project
2) TestNG annotations are used to create TestCases,TestSuites,Listeners,TestFactories, TestExecutionReports and         TestDataProviders .
3) Log4j framework is used for logging events during test execution
4) Spring Data Access and Spring Beans is used for connecting to database
5) PageFactory pattern is used to organize the AUT(Application Under Test) into various pages.
6) Finding broken links is also included in the tests
7) The test project can be used to run testcases in a data driven or a hybrid method.
8) The wait functions used in the testcases take into account the dynamic nature of the webpages
9) The Test Execution Report layout has been customized on the default layout of Report in TestNG
10) The project is aimed at being independent of the Test Execution environment.


Design patterns used in this automation project:

1) Page Object pattern: The entire front end of the AUT is maintained in terms of Pages , so that webelements and        their actions, corresponding to any given page are defined together. This enables code corresponding to a page to     be maitained independently from the code related to any other page
2) Factory pattern is implemented using Page Factory class of Selenium:
    The page factory uses @FindBy annotations to recognize webElements using xpath and does lazy initialization of       Page classes. 
3) Reusability of code: All high level functionalities like login signup etc are defined as functions that enable        modularity and reusability
4) Independent test cases: TestNG framework enables independent execution of testcases.
5) Abstraction Levels: The code has abstraction from a very low level like a click to a high level like signup or        starting an interview.
6) Data-driven testing: Test factories and dataproviders provided in TestNG framework has enabled testcases to be        executed in a data driven manner, ie: by getting various test inputs from independent sources external to the        testcases.
7) Hybrid testing: Java Reflection API has been used to enable hybrid testing for the testcases.
8) Default Data: Default data is used for some basic functionalities like Login.


Setup Instructions for any OS:
  1) Download latest JDK from the link : http://www.oracle.com/technetwork/java/javase/downloads/index.html and            install it on your system 
  2) Download latest version of Eclipse Junos
  3) Download and install eclipse ide software under the heading : Eclipse IDE for Java Developers  from the link:         https://eclipse.org/downloads/
  5)Clone this repository into your Github local folder
  6) In the File menu option Import your project into Eclipse using the Import option and browsing to your project
  6) Once the project is added in Eclipse , context click on the project in the Package Explorer and go to                 'Properties>Java Build Path>Libraries' 
  7) Using the Add Library option add libraries: 
      a) Maven Managed Dependencies
      b)TestNG
      c)JRE System Library: Java 7 or higher version (If not already present)
  8) Context click on the 'pom.xml' file and Run As:
      a)Maven clean
      b)Maven generate-resources
      c)Maven install
        one after the other
  9) The Sanity Test Suite file:CodePairSanitySuite.xml  is placed under: src/test/resources package under testsuites       folder. This suite contains the sanity test cases as of today
  10) To Execute the test suite, context click on the file and Run As: TestNG suite
      
