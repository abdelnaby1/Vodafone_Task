# Vodafone Task

Vodafone Task Which contains 3 Parts

## Installation

clone the Project using
```bash
git clone https://github.com/abdelnaby1/Vodafone_Task.git
```
###### Dependencies installation
after cloning the project you need to install all the dependencies\
if you are using `IntelliJ` go to the maven icon and click the refresh button
###### Other installation
you need to install allure on your device to see allure report
## Task Structure
### Part 1
will be found inside the Part1_Test_Plan_with_Bug_Report folder in one pdf file which contains the Yallkora test plan and bug report for the six bug
##
### Part 2
will be found inside the src folder and to run it using the maven build tool\
use the following command
```bash
mvn clean test -Pcart
```
and to see you allure report run the following command
```bash
allure serve "allure-results-path"
```
-Pcart is a profile id that is mapped to run the cart.xml file
##
### Part 3
will be found inside the Part3_API_Test_Cases folder which contains all test scenarios and test cases for APIs\
and\
to run the automation script for these apis test cases use the following command
```bash
mvn clean test -Papi
```
-Papi is a profile id that is mapped to run the api.xml file

##
## Thank you waiting for your feedback