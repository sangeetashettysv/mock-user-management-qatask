# Frontend Automation Project

This is a Selenium + TestNG + Cucumber BDD automation project for the frontend application: 

The project automates **Update User** functionalities.

---

## Project Structure


- `frontend` → React + Vite frontend application
- `automation-tests-frontend` → Selenium + TestNG + Cucumber BDD tests
- `config.properties` → Contains environment URL and browser
- `features/` → Cucumber feature files
- `stepDefinitions/` → Step definitions
- `pages/` → Page Object Model classes
- `utils/` → Test context, WebDriver management, config reader

---

## Prerequisites

1. **Java 23** or later
2. **Maven 3.8+**
3. **Node.js 18+** (for frontend)
4. **Browsers:** Chrome / Firefox

---

## Setup

1. **Clone the repository:**

- git clone <your-github-repo-url>
- cd mock-user-management-qatask


2. **Install frontend dependencies and run the app:**

- npm install
- npm run dev

- After running npm run dev, the terminal will show a local URL, e.g., http://localhost:5173/.

- Copy this URL and update config.properties:

- qa.url=http://localhost:5173/
- staging.url=http://localhost:5173/
- prod.url=http://localhost:5173/

3. **Running Automation Tests Locally**

- Open a new terminal in automation-tests-frontend:
- cd automation-tests-frontend

4. **Run all tests:**

- mvn clean test

5. **Run tests with particular tag**
mvn clean test -Dcucumber.filter.tags="@req-UL-UPDATE-001"


