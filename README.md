# Employee Manager – Spring Boot REST API

**Employee Manager** is a RESTful API built with **Java** and **Spring Boot**, designed for efficient management of employees, groups, and ratings. The application provides endpoints to create, delete, and retrieve data, as well as export employee information to **CSV** and calculate **group occupancy rates**.

It follows a **clean architecture**, separating concerns across controller, service, and repository layers. Robust **exception handling** ensures meaningful and standardized HTTP responses.

---

## ✨ Key Features

- Add, delete, and list employees and groups
- Assign and manage employee ratings
- Export employee data as CSV
- Calculate group occupancy dynamically
- Clean code structure with service-layer abstraction
- Robust global exception handling
- Built with **Spring Boot**, **Spring Web**, and **JUnit 5**
- Includes unit and integration tests for all endpoints

---

## 🚀 Getting Started

1. **Clone the repository**:

```bash
git clone https://github.com/sqrasdf/employee_manager.git
cd employee_manager
```

2.  **Build the project**:

```bash
./mvnw clean install
```

3. **Run the application**:

```bash
./mvnw spring-boot:run
```

4. **Run tests**:

```bash
./mvnw test
```
