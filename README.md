# Data Center Management System

A Java Spring Boot application for managing data centers, rack rows, racks, equipment, ports, network connections, and dedicated server rental services.

# 1. صورت مسئله | Challenge Description

### فارسی

هدف این پروژه طراحی و پیاده‌سازی یک سیستم مدیریت دیتاسنتر است.

سیستم باید امکان مدیریت دیتاسنترها، موقعیت جغرافیایی، رک‌ها، تجهیزات سخت‌افزاری و اتصالات شبکه را فراهم کند.

ساختار مکانی شامل چهار سطح کشور، استان، شهر و محله است.

هر دیتاسنتر شامل چند RackRow و هر RackRow شامل چند Rack است. هر Rack دارای ۴۲ واحد (Unit) برای نصب تجهیزات است.

تجهیزات شامل انواع مختلفی مانند Server، Switch و PatchPanel هستند. هر تجهیز می‌تواند چند پورت داشته باشد و پورت‌ها باید قابلیت اتصال به تجهیزات دیگر را داشته باشند. اتصال سرورها به سوئیچ‌ها از طریق PatchPanel انجام می‌شود.

همچنین سیستم باید قابلیت مدیریت کاربران با سطوح دسترسی مختلف و ثبت فعالیت‌های انجام‌شده را داشته باشد.

در بخش دوم، سیستم امکان ثبت‌نام مشتریان، اجاره سرور اختصاصی، صدور و پرداخت فاکتور، تمدید سرویس و پشتیبانی از طریق تیکت را فراهم می‌کند.

### English

The goal of this project is to design and implement a Data Center Management System.

The system manages data centers, geographical locations, rack rows, racks, hardware equipment, ports, and network connections.

The location hierarchy consists of four levels: Country, Province, City, and Neighborhood.

Each data center contains multiple rack rows, and each rack row contains multiple racks. Each rack provides 42 units for installing equipment.

The system supports equipment such as Servers, Switches, and Patch Panels. Equipment ports can be connected to other equipment ports, with Patch Panels acting as intermediaries between servers and switches.

The system also includes user management, role-based access control, and activity logging.

In addition, the application provides dedicated server rental services, customer registration, invoice generation and payment, subscription renewal, and customer support ticket management.

# 2. پیاده‌سازی‌ها | Implemented Features

### Challenge 1: Data Center Management

- Management of Data Centers and geographical locations
- Four-level location hierarchy
- Management of RackRows and Racks
- Equipment management (Server, Switch, PatchPanel)
- Rack unit capacity and equipment placement validation
- Port management
- Network connections between equipment ports
- One-to-one port connection validation
- PatchPanel-based connections between servers and switches
- Automatic initialization of sample database data

### Challenge 2: Server Rental System

- Customer registration (Individual and Legal Entity)
- Dedicated server rental
- Subscription management
- Invoice generation for new rentals
- Invoice generation for subscription renewals
- Invoice payment
- Subscription renewal after extending the expiration date
- Support ticket creation by customers
- Support ticket replies by system users

### User Management and Security

- User management (Create, Read, Update, Delete)
- Role-based access control
- ADMIN, SUPPORT, and USER roles
- Admin user management and role modification
- Support users can view tickets and reply to them
- API access restriction using Spring Security

### Activity Logging

- Automatic activity logging using Spring AOP
- Recording the username, action, and timestamp
- Logging user management operations
- Activity log retrieval

# 3. Initial Database Data

The application initializes the database with the following sample data:

| Item | Quantity |
|---|---:|
| Data Centers | 5 |
| RackRows | 25 |
| Racks | 250 |
| Equipment | 3,500 |
| Ports | 30,000 |
| Connections | 6,000 |

Each rack contains:

- 1 Switch with 24 ports
- 1 Patch Panel with 24 front ports and 24 back ports
- 5 Servers (1U)
- 5 Servers (2U)
- 2 Servers (4U)

Each server has 4 ports, and its first port is connected to the Switch through the Patch Panel.

# 4. Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- Spring Security
- Spring AOP
- Swagger / OpenAPI

# 5. Project Structure

```text
com.zorofchi.datacenter
│
├── config
├── controller
├── entity
├── repository
└── service
```

# 6. Database Configuration

The application uses PostgreSQL.

Create a database named:

```text
datacenter_management
```

Configure the database connection in `application.yml` according to your local PostgreSQL settings.

# 7. Running the Application

1. Make sure Java 17 and PostgreSQL are installed.
2. Create the `datacenter_management` database.
3. Configure the database connection.
4. Run the application using Maven or IntelliJ IDEA.

# 8. API Documentation

After starting the application, Swagger UI is available at:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Swagger provides an interactive interface for testing the REST API endpoints.

---

**Project:** Data Center Management System

**Package:** `com.zorofchi.datacenter`

**Purpose:** Java Programming Challenge