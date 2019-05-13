# testAutomation
Suite can be ran by using mvn clean test on project root folder, -Dbrowser can be used to use options "edge", "chrome" or "headless"

For Edge browser, edge version must be 17.17134, this is a driver limitation

Example: `mvn clean test -Dbrowser=chrome`

Test report can be found under /target/surefire-reports/emailable-report.html

Driver executables must be set on /testAutomation/drivers/

For debugging purposes, if you want to run this suite by running testng.xml file, you must uncomment
line 10 <parameter name="browser" value="chrome"/> after debugging is done, for the maven command to pick the =Dbrowser tag
that line should be commented again
