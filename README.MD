**Purpose:**
-
The purpose of this repo is to have a basic demo template for Appium, Junit5, Gradle and AllureReports.

**Requirements:**
-
Checkout the repository using the following command: `git clone -b <branchName> https://github.com/akhil7000/AppiumDemo.git`

The recommended IDE is Intellij with the Lombok plugin installed. In order to install it follow the instructions from the official guide: https://www.jetbrains.com/help/idea/managing-plugins.html and https://projectlombok.org/setup/intellij .
After installing the Lombok plugin, it needs to be enabled as an annotation processor. The easiest way is by clicking the intellij notification that will pop up after restarting the IDE following the Lombok plugin installation. This can also be done in the Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable Annotation Processing

All tests are running using the Junit 5 framework, specifically the runner. This can be verified by ensuring that all the imports are using `import org.junit.jupiter.api.Test;`. 

**Usage:**
-
It has a set of tests inside the test package.

The POM for mobile which has objects for both android and ios is inside main package.

To run all the tests using the gradle arguments -> `--tests "<class-name>" -Dos=<android-or-ios>`. If you specify `os=android`, then the tests would run for android.

You can use any of the gradle tasks to run the tests (in build.gradle) : `demoTest, demoTestRoyal, demoTestParallel` as per the execution requirements. 

Sample gradle cmd for `DemoTest` class using gradle task `demoTest` : `gradle clean demoTest --tests "DemoTest" -Dos=android --info`