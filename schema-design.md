# Smart Clinic System - Schema Design

This document defines the schema design for the Smart Clinic Management System.  
The system uses **MySQL** for structured relational data (patients, doctors, appointments, admin) and **MongoDB** for flexible document-based data (prescriptions).

---

## 🗄️ MySQL Database Design

### 1. Patients Table
```sql
CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    dob DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
س