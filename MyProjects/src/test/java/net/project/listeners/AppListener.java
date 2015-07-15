package net.project.listeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import net.project.loggers.AppLogger;
import net.project.webDriverUtils.TestBase;
import net.project.webDriverUtils.WebDriverUtilFunctions;

import org.apache.commons.io.FileDeleteStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.collections.Lists;
import org.testng.internal.Utils;
import org.testng.xml.XmlSuite;

public class AppListener  implements ISuiteListener,ITestListener,IExecutionListener,IInvokedMethodListener, WebDriverEventListener,IReporter /*, IAnnotationTransformer2*/{
public List<Long> threadRunTimes=new ArrayList<Long>();
	//////private static final Logger L = Logger.getLogger(AppListener.class);

    // ~ Instance fields ------------------------------------------------------

    private PrintWriter m_out;

    private int m_row;

    private Integer m_testIndex;

    private int m_methodIndex;

    private Scanner scanner;
    
    /*public static Map<String,Map<String,Object>> mapOfTestContextParameters= new LinkedHashMap<String, Map<String,Object>>();
    public static Map<String,Map<String,List<Map<String,String>>>> mapOfPreTestCaseSteps=new LinkedHashMap<String, Map<String,List<Map<String,String>>>>();
    public static Map<String,Map<String,List<Map<String,String>>>> mapOfTestSteps=new LinkedHashMap<String, Map<String,List<Map<String,String>>>>();
    public static Map<String,String> testNameSetting=new HashMap<String, String>();
    public static Map<String,Map<String,List<String>>> mapOfTestsAndTestCases=new LinkedHashMap<String, Map<String,List<String>>>();

	public static Map<String,Map<String,List<Map<String,String>>>>  mapOfParametersFromDataProviders=new LinkedHashMap<String,Map<String,List<Map<String,String>>>>();
    
    List<String> onetestContext=null;
    List<String> testCases=null;*/
    
	Map<String,ArrayList<String>> mapOfSuitesAndTestCases = new HashMap<String, ArrayList<String>>();
	ArrayList<String> testCaseNames=new ArrayList<String>();
	String zipFileTimeStamp=null;
	
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		

		
		
		
		
		WebDriverUtilFunctions webDriverUtilFunctions=new WebDriverUtilFunctions();

		String DATE_FORMAT = "yyyyMMddHHmmss";
	    SimpleDateFormat sdf =new SimpleDateFormat(DATE_FORMAT);
	    Calendar c1 = Calendar.getInstance();
		 zipFileTimeStamp=sdf.format(c1.getTime());
		try {
			webDriverUtilFunctions.zipFolder("target/surefire-reports", "D:/ArchivedReportsAndTestSources/"+zipFileTimeStamp+"reports.zip");
			webDriverUtilFunctions.zipFolder("target/generated-test-sources", "D:/ArchivedReportsAndTestSources/"+zipFileTimeStamp+"generated-test-sources.zip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AppLogger.logError("Not able to create zip files: "+e.getCause());
		}
		
		
		
		
		List<String> folders=new ArrayList<String>();
		folders.add("target/generated-test-sources/test-logs/");
		folders.add("target/generated-test-sources/screenshots/");
		File folderToBeCleaned=null;
				
				for(String folder : folders )
				{
					folderToBeCleaned=new File(folder);
					try {
						System.gc();
				        Thread.sleep(1000);
						FileDeleteStrategy.FORCE.delete(folderToBeCleaned);
					} catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						AppLogger.logError("Folder: "+folder+ " not cleaned successfully"+ e.getCause());
					}
					folderToBeCleaned.mkdir();
				}
		AppLogger.logDebug("Log4j appender configuration is successful !!");
		AppLogger.logInfo("----------Testing of TestSuite: "+ suite.getName()+"has started----------");
		
		
		
	}

	 
	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		AppLogger.logInfo("----------------Finishing the execution of Test Suite :  "+ this.getClass().getSimpleName()+ "--------------------");			
		//WebDriverUtilFunctions.convertHTMLToPDF("target/surefire-reports/emailable-report.html", "target/surefire-reports/emailable-report.pdf");
		//WebDriverUtilFunctions.emailFile("adarsha.shetty.1989@gmail.com", "trnkuohdtefxsdqp", "adarsha.shetty.1989@gmail.com", "adarsha.shetty.1989@gmail.com","target/surefire-reports/emailable-report.pdf","emailable-report.pdf");
		 
		
		
	}	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		AppLogger.logInfo("----------------------------------------Starting to execute TestCase: "+result.getMethod().getDescription()+"-----------------------------");
		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		AppLogger.logInfo("----------------------Successfully executed TestCase:"+ result.getTestContext().getName()+"-------------------");
		
		if (result.getMethod().isTest())
        {
			if(result.getMethod().getMethodName().equals("driverShutdown"))
			{
				return;
			}
			
			if(result.getMethod().getMethodName().equals("driverShutdown"));
			{
				Long actualStartLongTime=0l;
				actualStartLongTime=(Long)result.getAttribute("startTime");
	          if(actualStartLongTime==null)
	          {
	        	  actualStartLongTime=0l;
	          }
				Long duration=result.getEndMillis()-actualStartLongTime;
	            Long endLongTime=result.getStartMillis()+duration;
	            result.setEndMillis(endLongTime);
			}
			
        }
		}
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		AppLogger.logError("----------------------TestCase: "+result.getMethod().getMethodName()+" failed-------------------");
		AppLogger.logError("Test failed due to following reason: ");
		result.getThrowable().printStackTrace();
	
		if (result.getMethod().isTest())
        {
			if(result.getMethod().getMethodName().equals("driverShutdown"))
			{
				return;
			}
			
			if(result.getMethod().getMethodName().equals("driverShutdown"));
			{
				Long duration=0l;
				Long actualStartLongTime=(Long)result.getAttribute("startTime");
				if(actualStartLongTime!=null)
				{
					duration=result.getEndMillis()-actualStartLongTime;
				}
				
	            Long endLongTime=result.getStartMillis()+duration;
	            result.setEndMillis(endLongTime);
			}
			
        }
		}
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
		AppLogger.logError("----------------------TestCase: "+result.getTestName()+" Skipped-------------------");
		AppLogger.logError("Test was skipped due to following reason: "+ result.getThrowable().getStackTrace());
		
	}

	 
	@Override
	public void onStart(ITestContext context) {
		AppLogger.logInfo("----------------Starting testcontext:----------- "+context.getName());		
	}
	 
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		AppLogger.logInfo("----------------Finishing testcontext:----------- "+context.getName());
		ArrayList<String> listOfTestCases=null;
		if(!mapOfSuitesAndTestCases.containsKey(context.getSuite().getName()))
		{
			listOfTestCases=new ArrayList<String>();
			listOfTestCases.add(context.getName());
			mapOfSuitesAndTestCases.put(context.getSuite().getName(),listOfTestCases);
		}
		else
		{
			listOfTestCases=mapOfSuitesAndTestCases.get(context.getSuite().getName());
			listOfTestCases.add(context.getName());			
		}
		testCaseNames.add(context.getName());
		AppLogger.logInfo("----------------------------------------Finishing  invoking of all TestCases in the Test+"+ context.getName()+"-----------------------------");
		
	}


	@Override
	public void onExecutionStart() {
		// TODO Auto-generated method stub
		AppLogger.logInfo("Starting execution of TestNG");
	}

	@Override
	public void onExecutionFinish() {
		WebDriverUtilFunctions.setMapOfSuitesAndTestCases(mapOfSuitesAndTestCases);
		AppLogger.logInfo(mapOfSuitesAndTestCases.toString());		
		zipFileTimeStamp=Calendar.YEAR+"-"+Calendar.MONTH+"-"+Calendar.DAY_OF_MONTH+"-"+Calendar.HOUR_OF_DAY+"-"+Calendar.MINUTE+"-"+Calendar.SECOND;	
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
	
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		// TODO Auto-generated method stub
		
        Reporter.setCurrentTestResult(result);
        String description=null;
        
        
        if (method.isTestMethod()) {
        description=method.getTestResult().getMethod().getDescription();
        if(description==null)
        {
        	description=" : ";
        }
        description=description.split(":")[0];
        AppLogger.logInfo("method name is:"+description	 );
            List<Throwable> verificationFailures = TestBase.getVerificationFailures();

            //if there are verification failures...
            if (verificationFailures.size() > 0) {

                //set the test to failed
                result.setStatus(ITestResult.FAILURE);

                //if there is an assertion failure add it to verificationFailures
                if (result.getThrowable() != null) {
                    verificationFailures.add(result.getThrowable());
                }

                int size = verificationFailures.size();
                //if there's only one failure just set that
                if (size == 1) {
                    result.setThrowable((Throwable) verificationFailures.get(0));
                } else {
                    //create a failure message with all failures and stack traces (except last failure)
                   // we use string buffer because it will not create copy of string in Heap area
                    StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):nn");
                    for (int i = 0; i < size - 1; i++) {
                        failureMessage.append("Failure ").append(i + 1).append(" of ").append(size).append(":n");
                        Throwable t = (Throwable) verificationFailures.get(i);
                        String fullStackTrace = Utils.stackTrace(t, false)[1];
                        failureMessage.append(fullStackTrace).append("nn");
                    }

                    //final failure
                    Throwable last = (Throwable) verificationFailures.get(size - 1);
                    failureMessage.append("Failure ").append(size).append(" of ").append(size).append(":n");
                    failureMessage.append(last.toString());

                    //set merged throwable
                    Throwable merged = new Throwable(failureMessage.toString());
                    merged.setStackTrace(last.getStackTrace());

                    result.setThrowable(merged);
                }
            }
        }
    }


	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("After changing value of: ");
	}


	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("After clicking on: ");
	}


	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("After FindBy : ");
	}


	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("After navigating back : ");
	}


	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("After navigating forward: ");
	}


	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("After navigating to: ");
	}


	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("After executing script: ");
	}


	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("Before changing value of : ");
	}


	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("Before clicking on : ");
	}


	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("Before FindBy:");
	}


	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("Before navigating back : ");
	}


	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("Before navigating forward : ");
	}


	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("Before navigating to : ");
	}


	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		//AppLogger.logInfo("Before executing script : ");
	}


	@Override
	public void onException(Throwable arg0, WebDriver webDriver) {
		
		
		WebDriverUtilFunctions webDriverUtilFunctions=new WebDriverUtilFunctions();
		List<String> listOfMessage= Arrays.asList(arg0.getLocalizedMessage().split(" "));
		StringBuilder errorMessage1=new StringBuilder();
		errorMessage1.append(listOfMessage.get(0).replace("{errorMessage", "_").replaceAll("[-+.^:{,\"]","").trim());
		errorMessage1.append(listOfMessage.get(1).replace("{errorMessage", "_").replaceAll("[-+.^:{,\"]","").trim());
		errorMessage1.append(listOfMessage.get(2).replace("{errorMessage", "_").replaceAll("[-+.^:{,\"]","").trim());
		AppLogger.logInfo("Error message1 is : "+errorMessage1.toString().trim());
		if(errorMessage1.toString().trim().equals("errorMessageUnabletofind"))
		{
			errorMessage1.delete(0, errorMessage1.length()-1);
			AppLogger.logInfo("This error is: "+ arg0.getCause().toString());
			errorMessage1.append(arg0.getCause().toString().split(" ")[0].replaceAll("[-+.^:{,\"]",""));
		}
		try {
			
			webDriverUtilFunctions.getScreenShot(webDriver, Reporter.getCurrentTestResult().getTestContext().getName(), Reporter.getCurrentTestResult().getMethod().getMethodName(),errorMessage1.toString());
		} catch (Throwable e) {
			
		}
		
	}


	


	@Override
    public void generateReport(List<XmlSuite> xml, List<ISuite> suites,
            String outdir) {
        try {
            m_out = createWriter(outdir);
        } catch (IOException e) {
            AppLogger.logError("not able to generate report");
            return;
        }

        startHtml(m_out);
        generateSuiteSummaryReport(suites);
        generateMethodSummaryReport(suites);
        generateMethodDetailReport(suites);
        endHtml(m_out);
        m_out.flush();
        m_out.close();
    }

    protected PrintWriter createWriter(String outdir) throws IOException {
        new File(outdir).mkdirs();
        return new PrintWriter(new BufferedWriter(new FileWriter(new File(outdir, "customized-emailable-report"+".html"))));
    }

    /**
     * Creates a table showing the highlights of each test method with links to
     * the method details
     */
    protected void generateMethodSummaryReport(List<ISuite> suites) {
        m_methodIndex = 0;
        startResultSummaryTable("methodOverview");
        int testIndex = 1;
        for (ISuite suite : suites) {
            if (suites.size() > 1) {
                titleRow(suite.getName(), 5);
            }
            Map<String, ISuiteResult> r = suite.getResults();
            for (ISuiteResult r2 : r.values()) {
                ITestContext testContext = r2.getTestContext();
                String testName = testContext.getName();
                m_testIndex = testIndex;
                resultSummary(suite, testContext.getFailedConfigurations(),testName, "failed", " (configuration methods)");
                resultSummary(suite, testContext.getFailedTests(), testName,"failed", "");
                resultSummary(suite, testContext.getSkippedConfigurations(),testName, "skipped", " (configuration methods)");
                resultSummary(suite, testContext.getSkippedTests(), testName,"skipped", "");
                resultSummary(suite, testContext.getPassedTests(), testName,"passed", "");
                testIndex++;
            }
        }
        m_out.println("</table>");
    }

    /** Creates a section showing known results for each method */
    protected void generateMethodDetailReport(List<ISuite> suites) {
        m_methodIndex = 0;
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> r = suite.getResults();
            for (ISuiteResult r2 : r.values()) {
                ITestContext testContext = r2.getTestContext();
                if (r.values().size() > 0) {
                    m_out.println("<h1>" + testContext.getName() + "</h1>");
                }
                resultDetail(testContext.getFailedConfigurations());
                resultDetail(testContext.getFailedTests());
                resultDetail(testContext.getSkippedConfigurations());
                resultDetail(testContext.getSkippedTests());
                resultDetail(testContext.getPassedTests());
            }
        }
    }

    /**
     * @param tests
     */
    private void resultSummary(ISuite suite, IResultMap tests, String testname,
            String style, String details) {
    	String groups=null;
        if (tests.getAllResults().size() > 0) {
            StringBuffer buff = new StringBuffer();
            String lastClassName = "";
            int mq = 0;
            int cq = 0;
            for (ITestNGMethod method : getMethodSet(tests, suite)) {
                m_row += 1;
                m_methodIndex += 1;
                ITestClass testClass = method.getTestClass();
                String className = testClass.getName();
                if (mq == 0) {
                    String id = (m_testIndex == null ? null : "t"
                            + Integer.toString(m_testIndex));
                    titleRow(testname + " &#8212; " + style + details, 5, id);
                    m_testIndex = null;
                  groups= Arrays.asList(method.getGroups()).toString();
                }
                if (!className.equalsIgnoreCase(lastClassName)) {
                    if (mq > 0) {
                        cq += 1;
                        m_out.print("<tr class=\"" + style
                                + (cq % 2 == 0 ? "even" : "odd") + "\">"
                                + "<td");
                        if (mq > 1) {
                            m_out.print(" rowspan=\"" + mq + "\"");
                        }
                        m_out.println(">"
                        //+lastClassName+
                        		+suite.getName() + "</td>" + buff);
                    }
                    mq = 0;
                    buff.setLength(0);
                    lastClassName = className;
                }
                Set<ITestResult> resultSet = tests.getResults(method);
                long end = Long.MIN_VALUE;
                long start = Long.MAX_VALUE;
                for (ITestResult testResult : tests.getResults(method)) {
                    if (testResult.getEndMillis() > end) {
                        end = testResult.getEndMillis();
                    }
                    if (testResult.getStartMillis() < start) {
                        start = testResult.getStartMillis();
                        
                    }
                }
                mq += 1;
                if (mq > 1) {
                    buff.append("<tr class=\"" + style
                            + (cq % 2 == 0 ? "odd" : "even") + "\">");
                }
                String description = method.getDescription();
                if(description==null)
                {
                	description=" : ";
                }
                String testInstanceName = resultSet
                        .toArray(new ITestResult[] {})[0].getTestName();
                buff.append("<td><a href=\"#m"
                        + m_methodIndex
                        + "\">"
                        + description.split(":")[0]
                       /* + " "
                        + (description != null && description.length() > 0 ? "(\""
                                + description + "\")"
                                : "")*/
                        + "</a>"
                        + (null == testInstanceName ? "" : "<br>("
                                + testInstanceName + ")") + "</td>"
                        + "<td class=\"numi\">" + resultSet.size() + "</td>"
                        + "<td>" + start + "</td>" +"<td>" + method.getPriority()+"</td>" +"<td>" + description.split(":")[1]+"</td>" + "<td class=\"numi\">"
                        + (end - start)/1000.000 + "</td>" +"<td>" + groups + "</td>"+ "</tr>");
            }
            if (mq > 0) {
                cq += 1;
                m_out.print("<tr class=\"" + style
                        + (cq % 2 == 0 ? "even" : "odd") + "\">" + "<td");
                if (mq > 1) {
                    m_out.print(" rowspan=\"" + mq + "\"");
                }
                m_out.println(">" + lastClassName + "</td>" + buff);
            }
        }
    }

    /** Starts and defines columns result summary table */
    private void startResultSummaryTable(String style) {
        tableStart(style, "summary");
       /* m_out.println("<tr><th>Class</th>"
                + "<th>Method</th><th># of<br/>Scenarios</th><th>Start</th><th>Priority</th><th>Testcase Description</th><th>Time<br/>(ms)</th></tr>");
       */ 
        m_out.println("<tr><th>Suite</th>"
                + "<th>Method</th><th># of<br/>Scenarios</th><th>Start</th><th>Priority</th><th>Testcase Description</th><th>Time<br/>(s)</th><th>Groups</th></tr>");
       
        m_row = 0;
    }

    @SuppressWarnings("unused")
	private String qualifiedName(ITestNGMethod method) {
        StringBuilder addon = new StringBuilder();
        String[] groups = method.getGroups();
        int length = groups.length;
        if (length > 0 && !"basic".equalsIgnoreCase(groups[0])) {
            addon.append("(");
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    addon.append(", ");
                }
                addon.append(groups[i]);
            }
            addon.append(")");
        }

        return "<b>" + method.getMethodName() + "</b> " + addon;
    }

    private void resultDetail(IResultMap tests) {
        for (ITestResult result : tests.getAllResults()) {
            ITestNGMethod method = result.getMethod();
            m_methodIndex++;
            String cname = method.getTestClass().getName();
            m_out.println("<h2 id=\"m" + m_methodIndex + "\">" + cname + ":"
                    + method.getMethodName() + "</h2>");
            Set<ITestResult> resultSet = tests.getResults(method);
            generateForResult(result, method, resultSet.size());
            m_out.println("<p class=\"totop\"><a href=\"#summary\">back to summary</a></p>");

        }
    }

    /**
     * Write the first line of the stack trace
     * 
     * @param tests
     */
    private void getShortException(IResultMap tests) {

        for (ITestResult result : tests.getAllResults()) {
            m_methodIndex++;
            Throwable exception = result.getThrowable();
            List<String> msgs = Reporter.getOutput(result);
            boolean hasReporterOutput = msgs.size() > 0;
            boolean hasThrowable = exception != null;
            if (hasThrowable) {
                boolean wantsMinimalOutput = result.getStatus() == ITestResult.SUCCESS;
                if (hasReporterOutput) {
                    m_out.print("<h3>"
                            + (wantsMinimalOutput ? "Expected Exception"
                                    : "Failure") + "</h3>");
                }

                // Getting first line of the stack trace
                String str = Utils.stackTrace(exception, true)[0];
                scanner = new Scanner(str);
                String firstLine = scanner.nextLine();
                m_out.println(firstLine);
            }
        }
    }

    /**
     * Write all parameters
     * 
     * @param tests
     */
    private void getParameters(IResultMap tests) {

        for (ITestResult result : tests.getAllResults()) {
            m_methodIndex++;
            Object[] parameters = result.getParameters();
            boolean hasParameters = parameters != null && parameters.length > 0;
            if (hasParameters) {

                for (Object p : parameters) {
                    m_out.println(Utils.escapeHtml(Utils.toString(p)) + " | ");
                }
            }

        }
    }

    private void generateForResult(ITestResult ans, ITestNGMethod method,
            int resultSetSize) {
        Object[] parameters = ans.getParameters();
        boolean hasParameters = parameters != null && parameters.length > 0;
        if (hasParameters) {
            tableStart("result", null);
            m_out.print("<tr class=\"param\">");
            for (int x = 1; x <= parameters.length; x++) {
                m_out.print("<th>Param." + x + "</th>");
            }
            m_out.println("</tr>");
            m_out.print("<tr class=\"param stripe\">");
            for (Object p : parameters) {
                m_out.println("<td>" + Utils.escapeHtml(Utils.toString(p))
                        + "</td>");
            }
            m_out.println("</tr>");
        }
        List<String> msgs = Reporter.getOutput(ans);
        boolean hasReporterOutput = msgs.size() > 0;
        Throwable exception = ans.getThrowable();
        boolean hasThrowable = exception != null;
        if (hasReporterOutput || hasThrowable) {
            if (hasParameters) {
                m_out.print("<tr><td");
                if (parameters.length > 1) {
                    m_out.print(" colspan=\"" + parameters.length + "\"");
                }
                m_out.println(">");
            } else {
                m_out.println("<div>");
            }
            if (hasReporterOutput) {
                if (hasThrowable) {
                    m_out.println("<h3>Test Messages</h3>");
                }
                for (String line : msgs) {
                    m_out.println(line + "<br/>");
                }
            }
            if (hasThrowable) {
                boolean wantsMinimalOutput = ans.getStatus() == ITestResult.SUCCESS;
                if (hasReporterOutput) {
                    m_out.println("<h3>"
                            + (wantsMinimalOutput ? "Expected Exception"
                                    : "Failure") + "</h3>");
                }
                generateExceptionReport(exception, method);
            }
            if (hasParameters) {
                m_out.println("</td></tr>");
            } else {
                m_out.println("</div>");
            }
        }
        if (hasParameters) {
            m_out.println("</table>");
        }
    }

    protected void generateExceptionReport(Throwable exception,
            ITestNGMethod method) {
        m_out.print("<div class=\"stacktrace\">");
        m_out.print(Utils.stackTrace(exception, true)[0]);
        m_out.println("</div>");
    }

    /**
     * Since the methods will be sorted chronologically, we want to return the
     * ITestNGMethod from the invoked methods.
     */
    private Collection<ITestNGMethod> getMethodSet(IResultMap tests,
            ISuite suite) {
        List<IInvokedMethod> r = Lists.newArrayList();
        List<IInvokedMethod> invokedMethods = suite.getAllInvokedMethods();
        for (IInvokedMethod im : invokedMethods) {
            if (tests.getAllMethods().contains(im.getTestMethod())) {
                r.add(im);
            }
        }
        Arrays.sort(r.toArray(new IInvokedMethod[r.size()]), new TestSorter());
        List<ITestNGMethod> result = Lists.newArrayList();

        // Add all the invoked methods
        for (IInvokedMethod m : r) {
            result.add(m.getTestMethod());
        }

        // Add all the methods that weren't invoked (e.g. skipped) that we
        // haven't added yet
        for (ITestNGMethod m : tests.getAllMethods()) {
            if (!result.contains(m)) {
                result.add(m);
            }
        }
        return result;
    }

    @SuppressWarnings({ "unused", "deprecation" })
    public void generateSuiteSummaryReport(List<ISuite> suites) {
        tableStart("testOverview", null);
        m_out.print("<tr>");
        tableColumnStart("Test");
        tableColumnStart("Methods<br/>Passed");
        tableColumnStart("Scenarios<br/>Passed");
        tableColumnStart("# skipped");
        tableColumnStart("# failed");
        tableColumnStart("Error messages");
        tableColumnStart("Parameters");
        tableColumnStart("Start<br/>Time");
        tableColumnStart("End<br/>Time");
        tableColumnStart("Total<br/>Time");
        tableColumnStart("Included<br/>Groups");
        tableColumnStart("Excluded<br/>Groups");

        m_out.println("</tr>");
        NumberFormat formatter = new DecimalFormat("#,##0.0");
        int qty_tests = 0;
        int qty_pass_m = 0;
        int qty_pass_s = 0;
        int qty_skip = 0;
        int qty_fail = 0;
        long time_start = Long.MAX_VALUE;
        long time_end = Long.MIN_VALUE;
        m_testIndex = 1;
        for (ISuite suite : suites) {
            if (suites.size() > 1) {
                titleRow(suite.getName(), 8);
            }
            Map<String, ISuiteResult> tests = suite.getResults();
            for (ISuiteResult r : tests.values()) {
                qty_tests += 1;
                ITestContext overview = r.getTestContext();
                startSummaryRow(overview.getName());
                int q = getMethodSet(overview.getPassedTests(), suite).size();
                qty_pass_m += q;
                summaryCell(q, Integer.MAX_VALUE);
                q = overview.getPassedTests().size();
                qty_pass_s += q;
                summaryCell(q, Integer.MAX_VALUE);
                q = getMethodSet(overview.getSkippedTests(), suite).size();
                qty_skip += q;
                summaryCell(q, 0);
                q = getMethodSet(overview.getFailedTests(), suite).size();
                qty_fail += q;
                summaryCell(q, 0);

                // NEW
                // Insert error found
                m_out.print("<td class=\"numi" + (true ? "" : "_attn") + "\">");
                getShortException(overview.getFailedTests());
                getShortException(overview.getSkippedTests());
                m_out.println("</td>");

                // NEW
                // Add parameters for each test case (failed or passed)
                m_out.print("<td class=\"numi" + (true ? "" : "_attn") + "\">");

                // Write OS and Browser
                // m_out.println(suite.getParameter("os").substring(0, 3) +
                // " | "
                // + suite.getParameter("browser").substring(0, 3) + " | ");

                getParameters(overview.getFailedTests());
                getParameters(overview.getPassedTests());
                getParameters(overview.getSkippedTests());
                m_out.println("</td>");

                // NEW
                summaryCell(overview.getStartDate().toLocaleString(),true);
                m_out.println("</td>");
                summaryCell(overview.getEndDate().toLocaleString(),true);
                m_out.println("</td>");

                time_start = Math.min(overview.getStartDate().getTime(),time_start);
                time_end = Math.max(overview.getEndDate().getTime(), time_end);
                summaryCell(
                        formatter.format((overview.getEndDate().getTime() - overview
                                .getStartDate().getTime()) / 1000.)
                                + " seconds", true);
                summaryCell(overview.getIncludedGroups());
                summaryCell(overview.getExcludedGroups());
                m_out.println("</tr>");
                m_testIndex++;
            }
        }
        if (qty_tests > 1) {
            m_out.println("<tr class=\"total\"><td>Total</td>");
            summaryCell(qty_pass_m, Integer.MAX_VALUE);
            summaryCell(qty_pass_s, Integer.MAX_VALUE);
            summaryCell(qty_skip, 0);
            summaryCell(qty_fail, 0);
            summaryCell(" ", true);
            summaryCell(" ", true);
            summaryCell(" ", true);
            summaryCell(" ", true);
            summaryCell(
                    formatter.format(((time_end - time_start) / 1000.) / 60.)
                            + " minutes", true);
            m_out.println("<td colspan=\"3\">&nbsp;</td></tr>");
        }
        m_out.println("</table>");
    }

    private void summaryCell(String[] val) {
        StringBuffer b = new StringBuffer();
        for (String v : val) {
            b.append(v + " ");
        }
        summaryCell(b.toString(), true);
    }

    private void summaryCell(String v, boolean isgood) {
        m_out.print("<td class=\"numi" + (isgood ? "" : "_attn") + "\">" + v
                + "</td>");
    }

    private void startSummaryRow(String label) {
        m_row += 1;
        m_out.print("<tr"
                + (m_row % 2 == 0 ? " class=\"stripe\"" : "")
                + "><td style=\"text-align:left;padding-right:2em\"><a href=\"#t"
                + m_testIndex + "\">" + label + "</a>" + "</td>");
    }

    private void summaryCell(int v, int maxexpected) {
        summaryCell(String.valueOf(v), v <= maxexpected);
    }

    private void tableStart(String cssclass, String id) {
        m_out.println("<table cellspacing=\"0\" cellpadding=\"0\""
                + (cssclass != null ? " class=\"" + cssclass + "\""
                        : " style=\"padding-bottom:2em\"")
                + (id != null ? " id=\"" + id + "\"" : "") + ">");
        m_row = 0;
    }

    private void tableColumnStart(String label) {
        m_out.print("<th>" + label + "</th>");
    }

    private void titleRow(String label, int cq) {
        titleRow(label, cq, null);
    }

    private void titleRow(String label, int cq, String id) {
        m_out.print("<tr");
        if (id != null) {
            m_out.print(" id=\"" + id + "\"");
        }
        m_out.println("><th colspan=\"" + cq + "\">" + label + "</th></tr>");
        m_row = 0;
    }

    /** Starts HTML stream */
    protected void startHtml(PrintWriter out) {
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        out.println("<head>");
        out.println("<title>Customized - TestNG Report</title>");
        out.println("<style type=\"text/css\">");
        out.println("table {margin-bottom:10px;border-collapse:collapse;empty-cells:show}");
        out.println("td,th {border:1px solid #009;padding:.25em .5em}");
        out.println(".result th {vertical-align:bottom}");
        out.println(".param th {padding-left:1em;padding-right:1em}");
        out.println(".param td {padding-left:.5em;padding-right:2em}");
        out.println(".stripe td,.stripe th {background-color: #E6EBF9}");
        out.println(".numi,.numi_attn {text-align:right}");
        out.println(".total td {font-weight:bold}");
        out.println(".passedodd td {background-color: #0A0}");
        out.println(".passedeven td {background-color: #3F3}");
        out.println(".skippedodd td {background-color: #CCC}");
        out.println(".skippedodd td {background-color: #DDD}");
        out.println(".failedodd td,.numi_attn {background-color: #F33}");
        out.println(".failedeven td,.stripe .numi_attn {background-color: #D00}");
        out.println(".stacktrace {white-space:pre;font-family:monospace}");
        out.println(".totop {font-size:85%;text-align:center;border-bottom:2px solid #000}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
    }

    /** Finishes HTML stream */
    protected void endHtml(PrintWriter out) {
        out.println("</body></html>");
    }

    // ~ Inner Classes --------------------------------------------------------
    /** Arranges methods by classname and method name */
    private class TestSorter implements Comparator<IInvokedMethod> {
        // ~ Methods
        // -------------------------------------------------------------
    
        /** Arranges methods by classname and method name */
        
        public int compare(IInvokedMethod o1, IInvokedMethod o2) {
            // System.out.println("Comparing " + o1.getMethodName() + " " +
            // o1.getDate()
            // + " and " + o2.getMethodName() + " " + o2.getDate());
            return (int) (o1.getDate() - o2.getDate());
            // int r = ((T) o1).getTestClass().getName().compareTo(((T)
            // o2).getTestClass().getName());
            // if (r == 0) {
            // r = ((T) o1).getMethodName().compareTo(((T) o2).getMethodName());
            // }
            // return r;
        }
    }

/*	@Override
	public void transform(ITestAnnotation annotation, Class testClass,Constructor testConstructor, Method testMethod) {
		AppLogger.logInfo("--------In Transformation method-----");
		if(annotation.getTestName().equals("driverShutdown"))
		{
			annotation.setEnabled(true);
			return;
		}
		
		AppLogger.logInfo("Map of testcases in AppListener: "+AppListener.mapOfTestsAndTestCases.toString());
		AppLogger.logInfo("Map of Pretestcasesteps in AppListener: "+AppListener.mapOfPreTestCaseSteps.toString());
		
		String testContext=(String)AppListener.mapOfTestsAndTestCases.keySet().toArray()[0];
		AppLogger.logInfo("CurrentTestContext from AppListener is: "+testContext);
		String testCase=(String)AppListener.mapOfTestsAndTestCases.get(testContext).keySet().toArray()[0];
		AppLogger.logInfo("CurrentTestCase from AppListener is: "+testCase);
		List<String> testCaseConfigs=AppListener.mapOfTestsAndTestCases.get(testContext).remove(testCase);
		AppLogger.logInfo("Map of testcase configs is: "+testCaseConfigs.toString());
		AppLogger.logInfo("Map of testsandtestcases after current deletion: "+AppListener.mapOfTestsAndTestCases.get(testContext).toString());
		if(AppListener.mapOfTestsAndTestCases.get(testContext).isEmpty())
			AppListener.mapOfTestsAndTestCases.remove(testContext);
		Integer priorityInt=new Integer(testCaseConfigs.get(0));
		annotation.setPriority(priorityInt);
		Integer runFlag=new Integer(testCaseConfigs.get(1));
		annotation.setTestName(testCase);
		annotation.setDescription(testCaseConfigs.get(2));
		try {
			annotation.setDataProviderClass(Class.forName("net.project.dataProviders."+testCaseConfigs.get(3)));
		} catch (ClassNotFoundException e) {
			AppLogger.logInfo("Not able to set data provider class: "+"net.project.dataProviders"+testCaseConfigs.get(3));
		}
		if(testCaseConfigs.get(4)!=null || !testCaseConfigs.get(4).equals("") )
		{
			annotation.setDataProvider(testCaseConfigs.get(4));
		}
		
		annotation.setGroups(testCaseConfigs.get(5).split(","));
		if(testCaseConfigs.get(6)!=null || !testCaseConfigs.get(6).equals("") )
		{
			annotation.setSuiteName(testCaseConfigs.get(6));
		}
		
		AppLogger.logInfo("dataProviderclass is: "+annotation.getDataProviderClass().getName());
		AppLogger.logInfo("dataProvider method is: "+annotation.getDataProvider());
		AppLogger.logInfo("groups is: "+annotation.getGroups().toString());
		AppLogger.logInfo("suite name is: "+annotation.getSuiteName());
		AppLogger.logInfo("runFlag is: "+runFlag);
		AppLogger.logInfo("testcasename and isEnabled is: "+annotation.getTestName()+annotation.getEnabled());
		if(!runFlag.equals(0))
		{
			AppLogger.logInfo("Enabling testcase: " + annotation.getTestName());
			annotation.setEnabled(true);
			
		}
			
		else
			annotation.setEnabled(false);
		
		
	}


	@Override
	public void transform(IConfigurationAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		
		
		
	}


	@Override
	public void transform(IDataProviderAnnotation annotation, Method method) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void transform(IFactoryAnnotation annotation, Method method) {
		// TODO Auto-generated method stub
	
	}

*/
    public void testEnded() {
		// TODO Auto-generated method stub

	}

	public void testEnded(String arg0) {
		// TODO Auto-generated method stub
	}

	public void testStarted() {
		// TODO Auto-generated method stub
	}

	public void testStarted(String arg0) {
		// TODO Auto-generated method stub
	}


}
