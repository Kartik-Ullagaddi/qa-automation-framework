# Selenium QE Automation Framework

## Overview
This project is a scalable test automation framework built using Java, Selenium WebDriver, and TestNG.
It follows the Page Object Model (POM) design pattern and supports data-driven testing using JSON to ensure maintainability and reusability.

## Key Features
- Page Object Model (POM) for better code organization
- Data-driven testing using JSON
- Modular and maintainable framework design
- Reusable utility components (configuration handling, JSON parsing)
- TestNG integration for execution and assertions
- Coverage of both positive and negative scenarios

## Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven
- JSON

## Project Structure
src
├── main
│   ├── java
│   │   ├── base        // WebDriver setup
│   │   ├── pages       // Page Object classes
│   │   ├── utils       // Utilities (Config reader, JSON parser)
│   │
│   └── resources
│       └── config.properties   // Configuration file
│
├── test
│   ├── java
│   │   └── tests       // Test classes
│   │
│   └── resources
│       ├── testdata
│       │   └── users.json      // Test data
│       └── testng.xml          // Test suite configuration

## Test Coverage
- Valid Login Test
- Locked User Login Test

## Execution

### Using TestNG
Run testng.xml from the IDE

### Using Maven
mvn clean test

## Key Learnings
- Applied automation framework design principles (POM, modular design)
- Implemented data-driven testing using JSON
- Improved test maintainability and reusability
- Gained hands-on experience with Selenium WebDriver and TestNG

## Author
Kartik Ullagaddi
https://github.com/kartik-ullagaddi
