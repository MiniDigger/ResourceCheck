[![Build Status](https://travis-ci.com/Dyescape/ResourceCheck.svg?branch=master)](https://travis-ci.com/Dyescape/ResourceCheck)
[![Code Coverage](https://codecov.io/gh/Dyescape/ResourceCheck/coverage.svg)](https://codecov.io/gh/Dyescape/ResourceCheck)

# ResourceCheck
The ResourceCheck project is a project initiative by Dyescape to support the SpigotMC project in making the forums a safer place. This application is built in Java using the VertX microservice framework. It features a set of API endpoints in which one can upload a plugin Jar to, supposingly a Minecraft (Spigot) plugin. Once the Jar is uploaded, the Jar will be decompiled and a series of checks will be executed upon it. Once all checks are completed, a JSON response is returned containing the results of the scan. These results can be mapped and displayed above every resource uploaded to the forums. This should help keep the forums more safe as the average malicious plugin will trigger a bunch of warnings which are visible to the user.

# Usage
The ResourceCheck can be used in many different ways. You can run the applications directly through bare bone Java or through a Docker container. Below you will first find the steps on how to build the runnable Jar and the required Docker containers if desired.

## Building
First, build the runnable Jar through Maven:
```
mvn clean package
```
To then build a Docker using this freshly built Jar, use the below command.
Note: This will build the Jar with the `latest` tag as version.
```
docker build -t dyescape/resource-check:latest ./
```

## Running
To run the ResourceCheck directly through Java, use the following:
```
java -jar ./target/ResourceCheck.jar
```
To run the application through Docker compose, use the following:
```
docker-compose up
```

# Contributing
The Resource Check is responsible for handling certain safety checks on uploaded plugins to the forums. It is only natural for this project to have strict guidelines regarding contribution. Bugs can lead to false negative reputation of a developer as it may display false warnings above a fully safe resource. Following our guidelines will greatly help you to keep the high quality standards of this project as high as possible.

## Code principles
This project tends to follow the SOLID principles. These principles help us improve code quality significantly. It will help us keep our code maintainable and testable. The SOLID principle is a collection of 5 underlaying principles. Please refer to online documentation to what these invididual principles mean.
The underlaying principles are:
* Single responsibility principle
* Open/closed principle
* Liskov substitution principle
* Interface segregation principle
* Dependency inversion principle

## Code coverage
To prevent bugs as best as we can, we must make sure the develop and master branch of this project will have precisely 100% code coverage. We will not comply with anything lower than 100%. When committing your work to GitHub, always make sure to provide corresponding test cases that test the code that you just committed. If you ever come to the point where you cannot test a piece of code, you must elaborate whether your code complies with the above code principles. If you follow the principles above correctly, all is testable.
