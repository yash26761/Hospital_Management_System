# Kotlin Hospital Management System

🚀 [Click here to run the application live in your browser!](https://replit.com/@new26yash/HospitalManagementSystem)

A beginner-friendly, console-based Hospital Management application written in Kotlin. This project demonstrates core object-oriented programming concepts, allowing users to easily manage doctors, patients, admissions, appointments and billing.

## Features
- **Add Doctors:** Register new doctors with name, specialty and consultation fee.
- **Add Patients:** Register new patients with name, age, illness and phone number.
- **Search:** Quickly search doctors and patients by name, specialty, illness or ID.
- **Admit Patient:** Admit patients under a doctor with ward type (General / Private / ICU).
- **Discharge Patient:** Discharge patients with a detailed bill showing room charges and doctor fee.
- **Book Appointments:** Book consultation appointments for patients with doctors.
- **Long Stay Alerts:** Automatically alerts if a patient has been admitted for 5 or more days.
- **Hospital Statistics:** View total doctors, patients, admissions and appointments.
- **Day System:** Advance days to simulate time and track long stay patients.

## Prerequisites
To run this project on your local machine, you will need to have the following installed:
- Java Development Kit (JDK)
- Kotlin Compiler

## How to Run Locally

**Clone the repository:** Download or clone this repository to your local machine using the green "Code" button above, or run this command in your terminal:
```
git clone https://github.com/yash26761/HospitalManagementSystem.git
```

**Navigate to the folder:** Open your Command Prompt and navigate to the project directory:
```
cd path/to/your/folder
```

**Compile the Kotlin file:**
```
kotlinc Hospital.kt
```

**Run the application:**
```
kotlin HospitalKt
```

## Sample Data (Pre-loaded)

| Doctor ID | Name | Specialty | Fee |
|-----------|------|-----------|-----|
| D001 | Dr. Arjun Sharma | Cardiology | Rs.800 |
| D002 | Dr. Priya Mehta | Neurology | Rs.900 |
| D003 | Dr. Ravi Kumar | Orthopedics | Rs.700 |
| D004 | Dr. Sneha Patil | Pediatrics | Rs.600 |
| D005 | Dr. Vikram Singh | General | Rs.500 |

| Patient ID | Name | Age | Illness |
|------------|------|-----|---------|
| P001 | Rahul Gupta | 35 | Chest Pain |
| P002 | Anita Desai | 28 | Migraine |
| P003 | Suresh Nair | 45 | Knee Pain |
| P004 | Meena Joshi | 12 | Fever |
| P005 | Kiran Shah | 52 | Hypertension |

## 🏗️ Classes Used

| Class | Purpose |
|-------|---------|
| `Doctor` | Stores doctor details and consultation fee |
| `Patient` | Stores patient details and admission status |
| `AdmissionRecord` | Tracks patient admission and ward type |
| `AppointmentRecord` | Tracks doctor appointments |
| `HospitalManagement` | All hospital operations |

## 👨‍💻 Built With
- Kotlin
- No external libraries
- No Java imports

Developed by Yash
