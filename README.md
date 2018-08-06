[![Build Status](https://travis-ci.org/PersonalHealthTrain/library.svg?branch=master)](https://travis-ci.org/PersonalHealthTrain/library)
[![codecov](https://codecov.io/gh/PersonalHealthTrain/library/branch/master/graph/badge.svg)](https://codecov.io/gh/PersonalHealthTrain/library)

# Personal Health Train - Library
Kotlin library for the PHT project, which is intended to use with the individual services.


## Documentation
The current documentation is available at:

https://personalhealthtrain.github.io/library/


## Installation

The build system used in this project is Gradle. For building the project, use
```
gradle build
```
The project can be installed to the local Maven repository by using:
```
gradle publishToMavenLocal
```

## Tests
Running the Unit tests is performed by using
```
gradle test
```
Note that running the tests requires the current user
to be able to use a Docker daemon. 

## Javadoc
The Javadoc can be accessed [here](https://personalhealthtrain.github.io/library/javadoc/library/).


## Usage
Any client that wishes to use this library needs to include it
as a dependency. 

### Gradle
If the project that depends on this library uses Gradle
as its build system, then the library can be included as follows:

```$xslt
    compile group: 'de.difuture.ekut.pht', name: 'library', version: '0.0.1-SNAPSHOT'
```
Make sure that you replace the version above with the one that you want
for your project, which is usually the latest one.
