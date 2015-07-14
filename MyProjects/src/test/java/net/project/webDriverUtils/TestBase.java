package net.project.webDriverUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestBase {

    private static Map<ITestResult,List<Throwable>> verificationFailuresMap = new HashMap<ITestResult,List<Throwable>>();

    public static void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
    }

    public static void assertFalse(boolean condition) {
        Assert.assertFalse(condition);
    }

    public static void assertEquals(Object actual, Object expected) {
        Assert.assertEquals(actual, expected);
    }

    public static void verifyTrue(boolean condition) {
        try {
            assertTrue(condition);
        } catch (Throwable e) {
            addVerificationFailure(e);
        }
    }

    public static void verifyFalse(boolean condition) {
        try {
            assertFalse(condition);
        } catch (Throwable e) {
            addVerificationFailure(e);
        }
    }

    public static void verifyEquals(Object actual, Object expected) {
        try {
            assertEquals(actual, expected);
        } catch (Throwable e) {
            addVerificationFailure(e);
        }
    }

    public static List<Throwable> getVerificationFailures() {
        List<Throwable> verificationFailures =  verificationFailuresMap.get(Reporter.getCurrentTestResult());
        return verificationFailures == null ? new ArrayList<Throwable>() : verificationFailures;
    }

    private static void addVerificationFailure(Throwable e) {
        List<Throwable> verificationFailures = getVerificationFailures();
        verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
        verificationFailures.add(e);
    }
}