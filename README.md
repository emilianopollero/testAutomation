# testAutomation
Suite can be ran by using mvn clean test on project root folder, -Dbrowser can be used to use options "chrome" or "headless"

Example: `mvn clean test -Dbrowser=chrome`

Test report can be found under /target/surefire-reports/emailable-report.html

Chrome driver executable must be set on /testAutomation/drivers/chromedriver.exe

For debugging purposes, if you want to run this suite by running testng.xml file, you must uncomment
line 10 <parameter name="browser" value="chrome"/> after debugging is done, for the maven command to pick the =Dbrowser tag
that line should be commented again
