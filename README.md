# Fish Farm Management System - Design Document

## 1. Overview
The Fish Farm Management System is a Spring Boot-based RESTful API designed to manage and monitor all operational components of a fish farming environment. It includes modules for transactions, fish stock, feed usage, provender, water analysis and treatment, personnel, payroll, sampling, veterinary care, probiotic application, farm equipment management, customer management, and feed composition tracking.

---

## 2. Goals
- Provide a modular, scalable backend for managing fish farm operations.
- Enable real-time tracking and updating of fish stock, feeding, health, and environmental metrics.
- Ensure transparency in payroll, veterinary care, and feed formulation.
- Offer extensibility for future integrations with frontend dashboards or IoT sensors.

---

## 3. Modules & Requirements

### 3.1 Transaction Management
**Purpose:** Record monetary or material transactions.
- Fields: id, type, amount, date, description, relatedEntityId, relatedEntityType
- Supports linking to: FishStock, Provender, Payroll, Supplier, Customer, ProbioticApplication, FarmEquipment
- Auto-updates associated entity on transaction creation
- Supports batch transaction processing

### 3.2 Fish Stock Management
**Purpose:** Track species, quantities, and locations.
- Fields: id, species, quantity, stage, pondId, dateAdded
- Supports associations: Sampling, Feed Usage, Veterinary Care, Transactions

### 3.3 Feed Usage
**Purpose:** Log daily feed given to specific fish stock.
- Fields: id, fishStockId, feedType, quantityUsed, date
- On creation, subtracts quantity from Provender

### 3.4 Provender Management
**Purpose:** Manage feed inventory.
- Fields: id, feedType, quantity, supplier, lastRestocked, pricePerKg, expiryDate, feedingNotes, manufacturer
- Associated with: FeedComposition, FeedUsage, Transactions
- On creation, supports nested feed composition

### 3.5 Feed Composition
**Purpose:** Track ingredients and nutrients in a feed type.
- Fields: id, provenderId, ingredients (list), premix, moistureContent, ashPercentage

### 3.6 Fish Stock Sampling
**Purpose:** Record weight, size, health of fish stock.
- Fields: id, fishStockId, averageWeight, averageLength, survivalRate, healthStatus, date

### 3.7 Feed Sampling
**Purpose:** Evaluate quality of feed used on a fish stock.
- Fields: id, fishStockId, samplingDate, feedType, source, moistureContent, proteinContent, remarks

### 3.8 Water Analysis
**Purpose:** Log water quality metrics per pond.
- Fields: id, pondId, ph, ammonia, oxygen, temperature, nitrite, nitrate, hardness, alkalinity, notes, date

### 3.9 Water Treatment
**Purpose:** Record treatments based on analysis.
- Fields: id, pondId, treatmentType, chemicalUsed, date, effectiveness, dosage, dosageUnit, appliedBy, probioticId

### 3.10 Pond Management
**Purpose:** Manage physical fish farming environments.
- Fields: id, name, size, location, status, fishCapacity

### 3.11 Personnel Management
**Purpose:** Track farm staff and assignments.
- Fields: id, name, role, contact, shift, salary, savings, netPerceived, status, isActive

### 3.12 Payroll Management
**Purpose:** Manage employee payment records.
- Fields: id, personnelId, baseSalary, bonus, deduction, netPay, paymentDate, paymentMethod
- Supports monthly bulk payroll generation
- Auto-transaction creation and update on save

### 3.13 Veterinary Care
**Purpose:** Record health interventions for fish stocks.
- Fields: id, fishStockId, date, diagnosis, treatment, veterinarianName, notes, dosage, animalType, speciesOrBreed, medication

### 3.14 Probiotic Application
**Purpose:** Log application of probiotics to ponds or fish stocks.
- Fields: id, pondId, fishStockId (optional), date, probioticName, purpose, method, quantity, concentration, manufactureDate, expiryDate, usageInstructions
- Supports transactions and supplier association

### 3.15 Farm Equipment Management
**Purpose:** Track equipment inventory and maintenance.
- Fields: id, name, equipmentType, manufacturer, qty, purchaseDate, cost, maintenanceSchedule, location, status
- Associations: Purchase transaction, Maintenance records

### 3.16 Customer Management
**Purpose:** Track customer details and associated transactions.
- Fields: id, name, email, phone, address
- Associations: Transactions

---

## 4. Tech Stack
- Spring Boot 3
- Java 21
- PostgreSQL / H2
- Spring Data JPA
- Lombok, MapStruct (optional)
- Springdoc OpenAPI (Swagger)
- Docker (optional for deployment)

---

## 5. API Documentation & Testing
- Swagger UI available at `/swagger-ui.html`
- Postman collection provided with full endpoint coverage

---

## 6. Future Enhancements
- JWT authentication & role-based access
- Kafka for event-driven updates (e.g., IoT sensor data)
- Frontend dashboard (React + Tremor)
- PDF/Excel export for reports

---

## 7. Contact & License
- Author: Fish Farm Dev Team
- License: MIT (open for commercial and non-commercial use)
