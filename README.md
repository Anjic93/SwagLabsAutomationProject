# SwagLabs

SwagLabs is a website for testing purposes where the main goal is to add items to the cart and finish shopping

## Dependencies

- Run on Windows 11 OS
- IDE for this project is IntelliJ Idea Community Edition 2023.2.5
- Browsers needed is Chrome 

## Instalation

Open terminal in IDE and git clone the repository

```
git clone https://github.com/Anjic93/SwagLabsAutomationProject.git
```

- java version "21.0.1" 2023-10-17 LTS
- Apache Maven 4.0.0

## Framework Walkthorugh

Packages:

- Base - Contains classes used through the app and classes that could be useful for DDT
- Pages - Contains classes for each page
- Tests - Contains test classes

Files:

- pom.xml - Contains all dependencies used in the project (last updated: 10.10.2023.)
- Credentials.xlsx - Excel file used to read some data for DDT as an alternative
- .gitignore - File used to store all items that are not pushed to github

## Naming convention

- Classes are written in camel case with first character in upper case
- Methods are written in camel case with first character in lower case
- Class objects are named the same as a class name with lower case for first character
- Test methods are named as test case names

## Other

- Test methods are kept clean
- Each test should have at least 2 assertions with few test exceptions
- Priorities are set with 10 increment, if higher priority occur later in testing, priority of such tests are placed between the two priorities