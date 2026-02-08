# 🚀 E-Commerce API Automation Framework

A robust REST API automation testing framework built with **Rest Assured**, **TestNG**, and **Allure Reports** for comprehensive E-Commerce API testing.


## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Running Tests](#-running-tests)
- [Generating Reports](#-generating-reports)
- [Test Scenarios](#-test-scenarios)
- [Key Components](#-key-components)
- [Contributing](#-contributing)


---

## ✨ Features

- ✅ **Complete API Test Coverage** - Login, Items Management, Order Flow
- ✅ **Data-Driven Testing** - TestNG DataProvider with JSON data files
- ✅ **Dynamic Test Data** - JavaFaker integration for realistic test data
- ✅ **Beautiful Reports** - Allure reporting with detailed test insights
- ✅ **Request/Response Logging** - Comprehensive API call tracking
- ✅ **POJO Pattern** - Clean object mapping with Jackson
- ✅ **Configurable Environments** - Easy switching between environments
- ✅ **Reusable Components** - Modular endpoint classes
- ✅ **CI/CD Ready** - Maven-based execution

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 8+ | Programming Language |
| Maven | 3.6+ | Build & Dependency Management |
| Rest Assured | 5.5.6 | API Testing Framework |
| TestNG | 7.11.0 | Test Execution Framework |
| Jackson | 2.17.0 | JSON Serialization/Deserialization |
| Allure | 2.24.0 | Test Reporting |
| JavaFaker | 1.0.2 | Test Data Generation |
| Hamcrest | 3.0 | Assertion Library |
| Log4j2 | 2.23.1 | Logging Framework |

---

## 📁 Project Structure

```
ProjectApi/
├── src/test/
│   ├── java/org/example/
│   │   ├── apis/                    # API Endpoint Classes
│   │   │   ├── BaseApis.java        # Base RequestSpecification
│   │   │   ├── LoginEndpoint.java   # Login API
│   │   │   ├── ItemsEndpoint.java   # Items CRUD APIs
│   │   │   └── OrdersEndpoint.java  # Orders & Checkout APIs
│   │   ├── pojos/                   # Plain Old Java Objects
│   │   │   ├── UserCredentialsPojo.java
│   │   │   ├── CreateItemPojo.java
│   │   │   ├── CreateOrderPojo.java
│   │   │   ├── AdminToken.java
│   │   │   └── CustomerToken.java
│   │   ├── providers/               # TestNG DataProviders
│   │   │   └── UserDataProvider.java
│   │   ├── testcases/               # Test Classes
│   │   │   ├── FlowTest.java        # Main E2E Flow Tests
│   │   │   ├── NegativeTests.java   # Negative Test Cases
│   │   │   └── LoginAnotherWay.java # Alternative Login Tests
│   │   ├── utils/                   # Utility Classes
│   │   │   ├── ConfigReader.java    # Configuration Reader
│   │   │   └── JsonUtils.java       # JSON File Handler
│   │   ├── datagenerators/          # Test Data Generators
│   │   │   ├── ItemDataGenerator.java
│   │   │   ├── OrderDataGenerator.java
│   │   │   └── UserDataGenerator.java
│   │   └── listeners/               # TestNG Listeners
│   │       ├── TestListener.java    # Test Execution Listener
│   │       └── RetryAnalyzer.java   # Retry Failed Tests
│   └── resources/
│       ├── Data/
│       │   └── users.json           # Test User Credentials
│       ├── config.properties        # Environment Configuration
│       └── testng.xml              # TestNG Suite Configuration
├── target/
│   ├── allure-results/             # Raw Allure Test Results
│   └── surefire-reports/           # TestNG Reports
├── pom.xml                         # Maven Dependencies
└── README.md                       # Project Documentation
```

---

## 📦 Prerequisites

Before running this project, ensure you have:

- **Java JDK 8+** installed ([Download](https://www.oracle.com/java/technologies/javase-downloads.html))
- **Maven 3.6+** installed ([Download](https://maven.apache.org/download.cgi))
- **Allure CLI** (Optional, for command-line report generation) ([Install Guide](https://docs.qameta.io/allure/#_installing_a_commandline))
- **Git** installed ([Download](https://git-scm.com/downloads))
- IDE: **IntelliJ IDEA** or **Eclipse** (Recommended)

### Verify Installation

```bash
java -version
mvn -version
allure --version  # Optional
```

---

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/ProjectApi.git
cd ProjectApi
```

### 2. Install Dependencies

```bash
mvn clean install
```

This will download all required dependencies defined in `pom.xml`.

### 3. Import into IDE

**IntelliJ IDEA:**
1. Open IntelliJ → **File** → **Open**
2. Select the `ProjectApi` folder
3. Wait for Maven to import dependencies

**Eclipse:**
1. **File** → **Import** → **Existing Maven Projects**
2. Browse to `ProjectApi` folder
3. Click **Finish**

---

## ⚙️ Configuration

### 1. Update Base URL

Edit `src/test/resources/config.properties`:

```properties
base.url= http://localhost:3000
```

### 2. Update Test Data

Edit `src/test/resources/Data/users.json`:

```json
[
  {
    "username": "admin@example.com",
    "password": "adminPassword123"
  },
  {
    "username": "customer@example.com",
    "password": "customerPassword123"
  }
]
```

### 3. Configure TestNG Suite

Edit `src/test/resources/testng.xml` to customize test execution:

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="API Test Suite">
    <test name="E-Commerce API Tests">
        <classes>
            <class name="org.example.testcases.FlowTest"/>
        </classes>
    </test>
</suite>
```

---

## 🧪 Running Tests

### Method 1: Maven Command Line

```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=FlowTest

# Run with specific TestNG suite
mvn test -DsuiteXmlFile=testng.xml
```

### Method 2: IntelliJ IDEA

1. Right-click on `testng.xml`
2. Select **Run 'testng.xml'**

Or:

1. Right-click on any test class (e.g., `FlowTest.java`)
2. Select **Run 'FlowTest'**

### Method 3: Run Individual Tests

1. Open test class in editor
2. Click the green play button next to `@Test` method
3. Select **Run**

---

## 📊 Generating Reports

### Allure Reports (Recommended)

#### Option 1: Auto-Open in Browser

```bash
# Run tests and generate report
mvn clean test

# Generate and open report
mvn allure:serve
```

This will automatically:
- Generate the Allure report
- Start a local web server
- Open the report in your default browser

#### Option 2: Generate Static HTML Report

```bash
# Run tests
mvn clean test

# Generate static report
mvn allure:report

# Open manually
open target/site/allure-maven-plugin/index.html
```

#### Option 3: Using Allure CLI

```bash
# Run tests
mvn clean test

# Generate and serve report
allure serve target/allure-results
```

### Sample Allure Report Features

- 📈 **Overview Dashboard** - Pass/Fail statistics, trends
- 🔍 **Test Details** - Step-by-step execution with screenshots
- 🌐 **Request/Response** - Complete API call details
- 📋 **Categorization** - Tests grouped by Epic, Feature, Story
- ⏱️ **Timeline** - Test execution timeline
- 📊 **Graphs** - Duration, severity, and trend graphs

---

## 🧩 Test Scenarios

### Complete E2E Flow Test (`FlowTest.java`)

This test executes a complete order flow:

1. **Login** - Generate tokens for Admin and Customer users
2. **Create Item** - Admin creates a new item
3. **Get Item** - Retrieve item details by ID
4. **Update Item** - Update existing item information
5. **Create Order** - Customer creates an order with items
6. **Get Order** - Retrieve order details
7. **Checkout** - Customer checks out the order
8. **Get Paid Orders** - Admin retrieves all paid orders
9. **Delete Order** - Admin deletes the order
10. **Delete Item** - Admin deletes the item

### Test Execution Flow

```
┌─────────────────┐
│  Setup Tokens   │ @BeforeClass
└────────┬────────┘
         │
    ┌────▼────────────────────────────┐
    │  Priority 1: Create Item        │
    └────┬────────────────────────────┘
         │
    ┌────▼────────────────────────────┐
    │  Priority 2: Get Item           │
    └────┬────────────────────────────┘
         │
    ┌────▼────────────────────────────┐
    │  Priority 3: Update Item        │
    └────┬────────────────────────────┘
         │
    ┌────▼────────────────────────────┐
    │  Priority 4: Create Order       │
    └────┬────────────────────────────┘
         │
    ┌────▼────────────────────────────┐
    │  Priority 5: Get Order          │
    └────┬────────────────────────────┘
         │
    ┌────▼────────────────────────────┐
    │  Priority 6: Checkout Order     │
    └────┬────────────────────────────┘
         │
    ┌────▼────────────────────────────┐
    │  Priority 7: Get Paid Orders    │
    └────┬────────────────────────────┘
         │
    ┌────▼────────────────────────────┐
    │  Priority 8: Delete Order       │
    └────┬────────────────────────────┘
         │
    ┌────▼────────────────────────────┐
    │  Priority 9: Delete Item        │
    └─────────────────────────────────┘
```

---

## 🔑 Key Components

### 1. BaseApis.java

Centralized RequestSpecification with Allure integration:

```java
public static RequestSpecification getRequestSpecification() {
    return new RequestSpecBuilder()
            .setBaseUri(ConfigReader.getBaseUrl())
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())  // Captures all API calls
            .build();
}
```

### 2. DataProvider Pattern

Dynamic user iteration from JSON:

```java
@DataProvider(name = "users")
public static Object[][] getUsers() throws IOException {
    List<UserCredentialsPojo> users = JsonUtils.readJsonFile("users.json", 
            new TypeReference<List<UserCredentialsPojo>>() {});
    Object[][] data = new Object[users.size()][1];
    for (int i = 0; i < users.size(); i++) {
        data[i][0] = users.get(i);
    }
    return data;
}
```

### 3. POJO Pattern

Clean object mapping:

```java
public class CreateItemPojo {
    private String name;
    private double price;
    private int stock;
    
    // Constructor, Getters, Setters
}
```

### 4. Endpoint Classes

Reusable API methods:

```java
public class ItemsEndpoint {
    public Response createitems(CreateItemPojo item) {
        return given()
                .spec(BaseApis.getRequestSpecification())
                .body(item)
                .when()
                .post("/items");
    }
}
```

### 5. Data Generators

Dynamic test data with JavaFaker:

```java
public static CreateItemPojo generateRandomItem() {
    return new CreateItemPojo(
            faker.commerce().productName(),
            Double.parseDouble(faker.commerce().price(10.0, 1000.0)),
            faker.number().numberBetween(1, 500)
    );
}
```

---

## 📝 Sample Test Case

```java
@Test(priority = 1)
@Epic("E-Commerce API Testing")
@Feature("Item Management")
@Story("Create Items")
@Severity(SeverityLevel.CRITICAL)
@Description("Test creating a new item as Admin user")
public void testCreateitems() throws InterruptedException {
    ItemsEndpoint itemsEndpoint = new ItemsEndpoint(adminTokenData.getToken());
    CreateItemPojo newItem = ItemDataGenerator.generateRandomItem();
    
    ItemIdPojo responsePost = itemsEndpoint.createitems(newItem)
            .then()
            .assertThat()
            .statusCode(201)
            .extract()
            .as(ItemIdPojo.class);
    
    createdId = responsePost.getId();
    System.out.println("Created item ID: " + createdId);
}
```


## 📈 Best Practices

✅ **Follow Page Object Model** - Separate endpoint logic  
✅ **Use POJO Pattern** - Clean object mapping  
✅ **Data-Driven Testing** - External test data files  
✅ **Meaningful Test Names** - Descriptive method names  
✅ **Proper Assertions** - Validate status codes and response bodies  
✅ **Logging** - Use Log4j2 for debugging  
✅ **Allure Annotations** - Enhance report readability  
✅ **Configuration Management** - Externalize configurations

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork** the repository
2. Create a **feature branch** (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. Open a **Pull Request**

---

## 👨‍💻 Author

**Zyad mohamed**
- GitHub: https://github.com/zyadmohamed7
- LinkedIn: https://www.linkedin.com/in/zyad-mohamed-182b7a363/
- Email: zyadmohamed3711@gmail.com





