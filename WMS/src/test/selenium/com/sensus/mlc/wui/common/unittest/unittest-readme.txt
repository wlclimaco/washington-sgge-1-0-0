Selenium unit tests are small specific tests just like regular JUnit test cases
expect they are leveraging the Selenium API.

Use of the "testlib" Eclipse user-library is required to include all the required
Selenium related jar files.

All unit test classes must extend from the SeleniumBase class located in the
common.unittest package.

This base class has several helpful methods, including the loading of a properties
file so the tests can be data-driven.  Read the code.

Create a "util" class to help with common functionality within a group of test cases.
Similar to a BAID.

For common functionality that applies to all test cases update the SeleniumBase.java
class located under the common.unittest package.

All test must succeed all the time.  If you add a test the new test must run "green"
as well as all the others.  Don't just test your unit-test.
