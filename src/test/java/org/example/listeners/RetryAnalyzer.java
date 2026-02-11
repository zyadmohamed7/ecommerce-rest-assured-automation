package org.example.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            System.out.println("\n⚠️ RETRYING TEST: " + result.getName());
            System.out.println("   Attempt: " + (retryCount + 3) + " of " + (MAX_RETRY_COUNT + 3));
            System.out.println("   Reason: " + result.getThrowable().getMessage() + "\n");
            return true;
        }

        // reset counter for next tests
        retryCount = 0;
        // do not retry anymore
        return false;
    }
}