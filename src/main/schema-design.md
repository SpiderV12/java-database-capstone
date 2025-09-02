# Smart Clinic System – Database Design

## 1. MySQL Database Design

The relational database will store structured data such as patients, doctors, appointments, and admin users.

### 1.1 Table: patients
| Column Name       | Data Type       | Constraints                  |
|------------------|----------------|------------------------------|
| patient_id        | INT            | PRIMARY KEY, AUTO_INCREMENT  |
| first_name        | VARCHAR(50)    | NOT NULL                     |
| last_name         | VARCHAR(50)    | NOT NULL                     |
| email             | VARCHAR(100)   | NOT NULL, UNIQUE             |
| phone_number      | VARCHAR(20)    | NULL                         |
| date_of_birth     | DATE           | NULL                         |
| created_at        | TIMESTAMP      | DEFAULT CURRENT_TIMESTAMP    |

### 1.2 Table: doctors
| Column Name       | Data Type       | Constraints                  |
|------------------|----------------|------------------------------|
| doctor_id         | INT            | PRIMARY KEY, AUTO_INCREMENT  |
| first_name        | VARCHAR(50)    | NOT NULL                     |
| last_name         | VARCHAR(50)    | NOT NULL                     |
| specialty         | VARCHAR(100)   | NOT NULL                     |
| email             | VARCHAR(100)   | NOT NULL, UNIQUE             |
| phone_number      | VARCHAR(20)    | NULL                         |
| created_at        | TIMESTAMP      | DEFAULT CURRENT_TIMESTAMP    |

### 1.3 Table: appointments
| Column Name       | Data Type       | Constraints                      |
|------------------|----------------|----------------------------------|
| appointment_id    | INT            | PRIMARY KEY, AUTO_INCREMENT      |
| patient_id        | INT            | FOREIGN KEY REFERENCES patients(patient_id) |
| doctor_id         | INT            | FOREIGN KEY REFERENCES doctors(doctor_id) |
| appointment_date  | DATETIME       | NOT NULL                         |
| status            | VARCHAR(20)    | DEFAULT 'Scheduled'              |
| notes             | TEXT           | NULL                             |

### 1.4 Table: admin
| Column Name       | Data Type       | Constraints                  |
|------------------|----------------|------------------------------|
| admin_id          | INT            | PRIMARY KEY, AUTO_INCREMENT  |
| username          | VARCHAR(50)    | NOT NULL, UNIQUE             |
| password_hash     | VARCHAR(255)   | NOT NULL                     |
| email             | VARCHAR(100)   | NOT NULL, UNIQUE             |
| created_at        | TIMESTAMP      | DEFAULT CURRENT_TIMESTAMP    |

---

## 2. MongoDB Collection Design

The document-based database will store flexible or nested data such as prescriptions, feedback, and logs.

### 2.1 Collection: prescriptions

**Example Document:**

```json
{
  "_id": "64f2b3c7a1f3e2b4d7c6e5a1",
  "patient_id": 1,
  "doctor_id": 2,
  "date_issued": "2025-08-27T09:30:00Z",
  "medications": [
    {
      "name": "Amoxicillin",
      "dosage": "500mg",
      "frequency": "3 times a day",
      "duration_days": 7
    },
    {
      "name": "Paracetamol",
      "dosage": "500mg",
      "frequency": "as needed",
      "duration_days": 5
    }
  ],
  "notes": "Take medications with food. Follow-up in one week."
}
