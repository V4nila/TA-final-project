# Web & API Automation 



## 🚀 Key Features
* **Design Pattern:** Page Object Model (POM) for high maintainability.
* **Testing Styles:** BDD-style (Given/When/Then) API validations.
* **Synchronization:** Custom `WaitUtils` to ensure test stability.
* **Reporting:** Allure Reports with integrated `@Step` documentation.
* **Execution:** Parallel test execution via TestNG.

---

## 👥 Group Members & Contributions

The project was divided into three core technical modules. Each member was responsible for the end-to-end automation of their assigned domain, including Page Object design, test script development, and reporting integration.

| Name | Domain Responsibility & Scope |
| :--- | :--- |
| **Giorgi Vanishvili** | **API Layer:** Automated  REST API scenarios using RestAssured. Focused on JSON schema validation, Status Code verification, and Response Body assertions for the backend services. |
| **Grigol Skhulukhia** | **Product & Communication:** Automated  UI test cases covering Product Navigation, Search functionality, and the Contact Us/Subscription modules using Selenium. |
| **Giga Koshoridze** | **User Authentication:** Automated  UI test cases focusing on the complete User Lifecycle: Signup, Login/Logout flows, and the Account Deletion process. |

---

## 🛠️ Installation & Setup

### Prerequisites
* **Java 17**
* **Maven 3.x**
* **Allure Command Line Tool**

### Steps to Run
1. **Clone the repository:**
   git clone https://github.com/V4nila/TA-final-project.git
   cd TA-final-project

2. **Execute all tests (UI & API):**
   mvn clean test

3. **Generate and open the Allure Report:**
   allure serve allure-results


   
