# Authentication Flow Automation Framework

## Project Overview

This project is a Selenium WebDriver + Java automation framework developed for end-to-end testing of the Tiara Hub Authentication and Profile Management workflows.

The framework automates critical user journeys including:

* Login & Logout
* Forgot Password
* Role-Based Access Control
* Profile Switching
* Session Management
* Dashboard Validation

The framework follows the Page Object Model (POM) design pattern and is integrated with TestNG, Maven, Extent Reports, and Screenshot Capture utilities.

---

## Tech Stack

| Technology              | Purpose                  |
| ----------------------- | ------------------------ |
| Java                    | Programming Language     |
| Selenium WebDriver      | Browser Automation       |
| TestNG                  | Test Execution Framework |
| Maven                   | Dependency Management    |
| Page Object Model (POM) | Framework Design Pattern |
| Extent Reports          | Reporting                |
| Git & GitHub            | Version Control          |
| ChromeDriver            | Browser Driver           |

---

## Framework Features

### Page Object Model (POM)

The framework follows POM architecture to improve:

* Reusability
* Maintainability
* Scalability
* Readability

### Reporting

* Extent Reports integration
* Pass/Fail status tracking
* Screenshots attached for failures
* Execution summary

### Screenshot Capture

Automatic screenshots captured during failures using ScreenshotUtil.

### Configuration Management

Application URLs and test credentials are managed through external configuration files.

### TestNG Integration

Supports:

* Test grouping
* Suite execution
* Parallel execution (future enhancement)
* XML-driven execution

---

## Test Environment

Environment: Staging

Application Under Test:

https://staging-hub.tiara.jewelry

---

## Profiles Used For Testing

### Profile 1

**QA**

---

### Profile 2

**QA2**

Used for Profile Switching and Dashboard Validation scenarios.

---

## Important Testing Notes

* Sections 1 and 2 can be tested using any valid user account.
* Section 3 requires role-specific credentials.
* Section 4 requires an Admin account with access to both profiles.
* Passwords, accounts, and production data must not be modified.
* Staging credentials are shared among multiple users.

---

# Automated Test Scenarios

## Section 1 – Login & Logout

| TC ID | Scenario                                |
| ----- | --------------------------------------- |
| TC01  | Successful login with valid credentials |
| TC02  | Login with wrong password               |
| TC03  | Login with invalid email format         |
| TC04  | Submit login form with empty fields     |
| TC05  | Logout from application                 |
| TC06  | Access Hub URL after logout             |
| TC07  | Refresh page while logged in            |

### Expected Coverage

* Authentication validation
* Session management
* URL access protection
* Logout verification

---

## Section 2 – Forgot Password

| TC ID | Scenario                                   |
| ----- | ------------------------------------------ |
| TC08  | Open Forgot Password page                  |
| TC09  | Submit forgot password with empty email    |
| TC10  | Submit forgot password with valid email    |
| TC11  | New Password and Confirm Password mismatch |
| TC12  | Weak password validation                   |

### Expected Coverage

* Form validation
* Password rules validation
* Error handling
* User feedback verification

---

## Section 3 – Role-Based Access

### Roles Tested

* Admin
* Owner
* Store Manager

### Test Cases

| TC ID | Scenario                                   |
| ----- | ------------------------------------------ |
| TC13  | Login as Admin                             |
| TC14  | Login as Owner                             |
| TC15  | Login as Store Manager                     |
| TC16  | Verify Admin sees User Management          |
| TC17  | Verify Owner cannot see User Management    |
| TC18  | Verify Store Manager restricted URL access |
| TC19  | Switch roles using Logout/Login            |

### Expected Coverage

* Role-based permissions
* Navigation visibility
* Access restrictions
* Authorization validation

---

## Section 4 – Profile Switching

### Profiles Tested

* QA
* QA2

### Test Cases

| TC ID | Scenario                                           |
| ----- | -------------------------------------------------- |
| TC20  | Verify Profile Switcher is visible                 |
| TC21  | Verify current profile name is displayed           |
| TC22  | Switch from QA to QA2                              |
| TC23  | Verify profile name updates after switch           |
| TC24  | Verify dashboard data changes after profile switch |
| TC25  | Switch back from QA2 to QA                         |
| TC26  | Verify profile remains active after page refresh   |

### Expected Coverage

* Multi-profile support
* Dashboard data isolation
* Profile persistence
* UI validation
* Session continuity

---

## Framework Structure

src/test/java

├── base

│ └── BaseTest.java

├── pages

│ ├── LoginPage.java

│ ├── ForgotPasswordPage.java

│ ├── ProfilePage.java

│ └── RoleBasedPage.java

├── tests

│ ├── LoginLogoutTests.java

│ ├── ForgotPasswordTests.java

│ ├── ProfileSwitchTests.java

│ └── RoleBasedTests.java

├── utilities

│ ├── ConfigReader.java

│ ├── ExtentManager.java

│ ├── ExtentReportManager.java

│ └── ScreenshotUtil.java

---

## Running the Tests

### Execute Complete Regression Suite

Run:

testng.xml

### Maven Execution

mvn clean test

### TestNG Execution

Right Click → testng.xml → Run As → TestNG Suite

---

## Reporting

After execution:

Reports are generated under:

/Reports

Screenshots are generated under:

/Screenshots

Extent Report includes:

* Test Name
* Execution Status
* Failure Reason
* Screenshots
* Execution Summary

---

## Key Achievements

* Built scalable Selenium automation framework
* Implemented Page Object Model
* Automated 26 end-to-end authentication scenarios
* Added reusable utilities
* Integrated Extent Reporting
* Added Screenshot Capture
* Configured TestNG Suite Execution
* GitHub Version Controlled Project

---

## Author

Kunal Bhavasar

QA Automation Engineer

Tech Stack:
Java | Selenium | TestNG | Maven | Git | Extent Reports
