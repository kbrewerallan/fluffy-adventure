# Open JDK

Download open JDK - https://access.redhat.com/documentation/pt-br/openjdk/11/html-single/installing_and_using_openjdk_11_for_windows/index.


## Installation

Setup java following the guide - https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html

# Maven

Download and installation instructions can be found here - https://maven.apache.org/install.html

# Setup and Run

To ensure tests work please update teh Constants.java file references for PASSWORD and EMAIL to be your normal login values.

To run the tests use any chosen IDE ([Eclipse](https://www.eclipse.org/downloads/) / [VScode](https://code.visualstudio.com/) with [Java](https://code.visualstudio.com/docs/languages/java) plugins will work).

All test can be ran at once using the following command.

```shell
mvn clean test
```  

Or ran individually using the IDE test runner.
