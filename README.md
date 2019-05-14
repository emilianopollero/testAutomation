# testAutomation
Suite can be ran by using mvn clean test on project root folder, -Dbrowser can be used to use options "edge" (Only on Windows 10 PC), "chrome" or "headless"

Java version 1.8 is required to run this suite

For Edge browser, edge version must be 17.17134, this is a driver limitation
For Linux/Mac, Chrome version 74.0.3729.6 is needed

Example: `mvn clean test -Dbrowser=headless`

Test report can be found under /target/extent-report/testReport.html
Screenshots on test report only for UI tests failures.
Addtional surfire test report can be found under target/surfire-reports/emailable-report.html

For debugging purposes, if you want to run this suite by running testng.xml file from within the IDE, you must uncomment
line 10 <parameter name="browser" value="chrome"/> after debugging is done, for the maven command to pick the =Dbrowser tag
that line should be commented again
