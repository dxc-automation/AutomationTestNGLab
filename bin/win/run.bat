set ProjectPath=C:\Projects\PROJECTS\AutomationTestLab-master\target
cd %ProjectPath%
java -cp tests.jar org.testng.TestNG C:\Projects\PROJECTS\AutomationTestLab-master\src\main\resources\xml_files\TestCase_01.xml
pause