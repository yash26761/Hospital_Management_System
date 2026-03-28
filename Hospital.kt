class Doctor(docId: String, docName: String, docSpecialty: String, docFee: Int) {
    var id: String = docId
    var name: String = docName
    var specialty: String = docSpecialty
    var consultationFee: Int = docFee
    var activePatients: Int = 0
}

class Patient(patId: String, patName: String, patAge: Int, patIllness: String, patPhone: String) {
    var id: String = patId
    var name: String = patName
    var age: Int = patAge
    var illness: String = patIllness
    var phone: String = patPhone
    var isAdmitted: Boolean = false
}

class AdmissionRecord(pId: String, dId: String, day: Int, ward: String) {
    var patientId: String = pId
    var doctorId: String = dId
    var dayAdmitted: Int = day
    var wardType: String = ward
    var isDischarged: Boolean = false
}

class AppointmentRecord(pId: String, dId: String, day: Int) {
    var patientId: String = pId
    var doctorId: String = dId
    var dayBooked: Int = day
    var isDone: Boolean = false
}

class HospitalManagement {
    var doctors = arrayOfNulls<Doctor>(20)
    var patients = arrayOfNulls<Patient>(20)
    var admissions = arrayOfNulls<AdmissionRecord>(50)
    var appointments = arrayOfNulls<AppointmentRecord>(50)
    var currentDay: Int = 1
    var chargePerDay: Int = 1000

    fun advanceDay() {
        currentDay++
        println("\n>>> A day has passed. It is now Day $currentDay <<<")
        checkLongStay()
    }

    fun checkLongStay() {
        println("\n--- Long Stay Alert (Day $currentDay) ---")
        var found = false
        for (i in 0 until 50) {
            var a = admissions[i]
            if (a != null && !a.isDischarged) {
                var daysStayed = currentDay - a.dayAdmitted
                if (daysStayed >= 5) {
                    println("ALERT: Patient ID ${a.patientId} has been admitted for $daysStayed day(s)!")
                    found = true
                }
            }
        }
        if (!found) println("No long stay alerts.")
    }

    // ── DOCTOR OPERATIONS ────────────────────

    fun addDoctor(id: String, name: String, specialty: String, fee: Int) {
        for (i in 0 until 20) {
            if (doctors[i] != null && doctors[i]?.id == id) {
                println("Error: Doctor ID '$id' already exists.")
                return
            }
        }
        for (i in 0 until 20) {
            if (doctors[i] == null) {
                doctors[i] = Doctor(id, name, specialty, fee)
                println("SUCCESS: Dr. $name ($specialty) added. Consultation Fee: Rs.$fee")
                return
            }
        }
        println("Error: Doctor list is full!")
    }

    fun deleteDoctor(doctorId: String) {
        for (i in 0 until 20) {
            var d = doctors[i]
            if (d != null && d.id == doctorId) {
                if (d.activePatients > 0) {
                    println("Error: Cannot delete! Dr. ${d.name} has ${d.activePatients} active patient(s).")
                    return
                }
                doctors[i] = null
                println("SUCCESS: Doctor '$doctorId' deleted.")
                return
            }
        }
        println("Error: Doctor ID not found.")
    }

    fun searchDoctor(query: String) {
        println("\n--- Search Results for '$query' ---")
        var found = false
        for (i in 0 until 20) {
            var d = doctors[i]
            if (d != null) {
                if (d.name.lowercase().contains(query.lowercase()) ||
                    d.specialty.lowercase().contains(query.lowercase()) ||
                    d.id.lowercase().contains(query.lowercase())) {
                    println("ID: ${d.id} | Dr. ${d.name} | Specialty: ${d.specialty} | Fee: Rs.${d.consultationFee} | Active Patients: ${d.activePatients}")
                    found = true
                }
            }
        }
        if (!found) println("No doctors found for '$query'")
    }

    fun displayDoctors() {
        println("\n--- All Doctors ---")
        var count = 0
        for (i in 0 until 20) {
            var d = doctors[i]
            if (d != null) {
                println("ID: ${d.id} | Dr. ${d.name} | Specialty: ${d.specialty} | Fee: Rs.${d.consultationFee} | Active Patients: ${d.activePatients}")
                count++
            }
        }
        if (count == 0) println("No doctors registered.")
        else println("Total: $count doctor(s)")
    }

    fun viewDoctorDetails(doctorId: String) {
        var found = false
        for (i in 0 until 20) {
            var d = doctors[i]
            if (d != null && d.id == doctorId) {
                println("\n--- Doctor Details ---")
                println("ID          : ${d.id}")
                println("Name        : Dr. ${d.name}")
                println("Specialty   : ${d.specialty}")
                println("Fee         : Rs.${d.consultationFee}")
                println("Active Pts  : ${d.activePatients}")
                println("Patients:")
                var patFound = false
                for (j in 0 until 50) {
                    var a = admissions[j]
                    if (a != null && a.doctorId == doctorId && !a.isDischarged) {
                        println("  - Patient ID: ${a.patientId} | Admitted on Day: ${a.dayAdmitted} | Ward: ${a.wardType}")
                        patFound = true
                    }
                }
                if (!patFound) println("  No active patients.")
                found = true
                break
            }
        }
        if (!found) println("Error: Doctor ID not found.")
    }

    // ── PATIENT OPERATIONS ───────────────────

    fun addPatient(id: String, name: String, age: Int, illness: String, phone: String) {
        for (i in 0 until 20) {
            if (patients[i] != null && patients[i]?.id == id) {
                println("Error: Patient ID '$id' already exists.")
                return
            }
        }
        for (i in 0 until 20) {
            if (patients[i] == null) {
                patients[i] = Patient(id, name, age, illness, phone)
                println("SUCCESS: Patient '$name' registered.")
                return
            }
        }
        println("Error: Patient registry is full!")
    }

    fun deletePatient(patientId: String) {
        for (i in 0 until 20) {
            var p = patients[i]
            if (p != null && p.id == patientId) {
                if (p.isAdmitted) {
                    println("Error: Cannot delete! Patient is currently admitted. Discharge first.")
                    return
                }
                patients[i] = null
                println("SUCCESS: Patient '$patientId' deleted.")
                return
            }
        }
        println("Error: Patient ID not found.")
    }

    fun searchPatient(query: String) {
        println("\n--- Search Results for '$query' ---")
        var found = false
        for (i in 0 until 20) {
            var p = patients[i]
            if (p != null) {
                if (p.name.lowercase().contains(query.lowercase()) ||
                    p.illness.lowercase().contains(query.lowercase()) ||
                    p.id.lowercase().contains(query.lowercase())) {
                    var status = if (p.isAdmitted) "Admitted" else "Not Admitted"
                    println("ID: ${p.id} | Name: ${p.name} | Age: ${p.age} | Illness: ${p.illness} | Status: $status")
                    found = true
                }
            }
        }
        if (!found) println("No patients found for '$query'")
    }

    fun displayPatients() {
        println("\n--- All Patients ---")
        var count = 0
        for (i in 0 until 20) {
            var p = patients[i]
            if (p != null) {
                var status = if (p.isAdmitted) "Admitted" else "Not Admitted"
                println("ID: ${p.id} | Name: ${p.name} | Age: ${p.age} | Illness: ${p.illness} | Phone: ${p.phone} | Status: $status")
                count++
            }
        }
        if (count == 0) println("No patients registered.")
        else println("Total: $count patient(s)")
    }

    fun viewPatientDetails(patientId: String) {
        var found = false
        for (i in 0 until 20) {
            var p = patients[i]
            if (p != null && p.id == patientId) {
                println("\n--- Patient Details ---")
                println("ID       : ${p.id}")
                println("Name     : ${p.name}")
                println("Age      : ${p.age}")
                println("Illness  : ${p.illness}")
                println("Phone    : ${p.phone}")
                println("Status   : ${if (p.isAdmitted) "Currently Admitted" else "Not Admitted"}")
                for (j in 0 until 50) {
                    var a = admissions[j]
                    if (a != null && a.patientId == patientId && !a.isDischarged) {
                        println("Ward     : ${a.wardType}")
                        println("Admitted : Day ${a.dayAdmitted}")
                        println("Days     : ${currentDay - a.dayAdmitted}")
                        println("Bill so far: Rs.${(currentDay - a.dayAdmitted) * chargePerDay}")
                    }
                }
                found = true
                break
            }
        }
        if (!found) println("Error: Patient ID not found.")
    }

    // ── ADMIT / DISCHARGE ────────────────────

    fun admitPatient(patientId: String, doctorId: String, ward: String) {
        var foundPatient: Patient? = null
        var foundDoctor: Doctor? = null

        for (i in 0 until 20) {
            if (patients[i] != null && patients[i]?.id == patientId) foundPatient = patients[i]
            if (doctors[i] != null && doctors[i]?.id == doctorId) foundDoctor = doctors[i]
        }

        if (foundPatient == null) { println("Error: Patient ID not found."); return }
        if (foundDoctor == null) { println("Error: Doctor ID not found."); return }
        if (foundPatient.isAdmitted) { println("Error: Patient is already admitted."); return }

        for (i in 0 until 50) {
            if (admissions[i] == null) {
                admissions[i] = AdmissionRecord(patientId, doctorId, currentDay, ward)
                foundPatient.isAdmitted = true
                foundDoctor.activePatients++
                println("SUCCESS: ${foundPatient.name} admitted under Dr. ${foundDoctor.name} on Day $currentDay.")
                println("Ward: $ward | Charge: Rs.$chargePerDay per day")
                return
            }
        }
        println("Error: Ward is full!")
    }

    fun dischargePatient(patientId: String, doctorId: String) {
        var foundRecord: AdmissionRecord? = null

        for (i in 0 until 50) {
            var a = admissions[i]
            if (a != null && a.patientId == patientId && a.doctorId == doctorId && !a.isDischarged) {
                foundRecord = a
                a.isDischarged = true
                break
            }
        }

        if (foundRecord == null) { println("Error: No active admission found."); return }

        var daysStayed = currentDay - foundRecord.dayAdmitted
        if (daysStayed == 0) daysStayed = 1
        var roomBill = daysStayed * chargePerDay

        var doctorFee = 0
        for (i in 0 until 20) {
            if (doctors[i] != null && doctors[i]?.id == doctorId) {
                doctorFee = doctors[i]?.consultationFee ?: 0
                doctors[i]?.activePatients = (doctors[i]?.activePatients ?: 1) - 1
            }
            if (patients[i] != null && patients[i]?.id == patientId) {
                patients[i]?.isAdmitted = false
            }
        }

        var totalBill = roomBill + doctorFee
        println("\n========= DISCHARGE SUMMARY =========")
        println("Patient ID     : $patientId")
        println("Doctor ID      : $doctorId")
        println("Days Stayed    : $daysStayed")
        println("Room Charges   : Rs.$roomBill")
        println("Doctor Fee     : Rs.$doctorFee")
        println("-------------------------------------")
        println("TOTAL BILL     : Rs.$totalBill")
        println("=====================================")
        println("SUCCESS: Patient discharged on Day $currentDay.")
    }

    // ── APPOINTMENTS ─────────────────────────

    fun bookAppointment(patientId: String, doctorId: String) {
        var foundPatient: Patient? = null
        var foundDoctor: Doctor? = null

        for (i in 0 until 20) {
            if (patients[i] != null && patients[i]?.id == patientId) foundPatient = patients[i]
            if (doctors[i] != null && doctors[i]?.id == doctorId) foundDoctor = doctors[i]
        }

        if (foundPatient == null) { println("Error: Patient ID not found."); return }
        if (foundDoctor == null) { println("Error: Doctor ID not found."); return }

        for (i in 0 until 50) {
            if (appointments[i] == null) {
                appointments[i] = AppointmentRecord(patientId, doctorId, currentDay)
                println("SUCCESS: Appointment booked for ${foundPatient.name} with Dr. ${foundDoctor.name} on Day $currentDay.")
                println("Consultation Fee: Rs.${foundDoctor.consultationFee}")
                return
            }
        }
        println("Error: Appointment system is full.")
    }

    fun displayAppointments() {
        println("\n--- All Appointments ---")
        var count = 0
        for (i in 0 until 50) {
            var a = appointments[i]
            if (a != null) {
                var status = if (a.isDone) "Done" else "Pending"
                println("Patient ID: ${a.patientId} | Doctor ID: ${a.doctorId} | Day: ${a.dayBooked} | Status: $status")
                count++
            }
        }
        if (count == 0) println("No appointments found.")
        else println("Total: $count appointment(s)")
    }

    // ── STATISTICS ───────────────────────────

    fun displayStatistics() {
        var totalDoctors = 0
        var totalPatients = 0
        var admittedPatients = 0
        var totalAdmissions = 0
        var activeAdmissions = 0
        var totalAppointments = 0

        for (i in 0 until 20) {
            if (doctors[i] != null) totalDoctors++
            if (patients[i] != null) {
                totalPatients++
                if (patients[i]?.isAdmitted == true) admittedPatients++
            }
        }
        for (i in 0 until 50) {
            if (admissions[i] != null) {
                totalAdmissions++
                if (admissions[i]?.isDischarged == false) activeAdmissions++
            }
            if (appointments[i] != null) totalAppointments++
        }

        println("\n--- Hospital Statistics ---")
        println("Current Day        : $currentDay")
        println("Total Doctors      : $totalDoctors")
        println("Total Patients     : $totalPatients")
        println("Admitted Patients  : $admittedPatients")
        println("Active Admissions  : $activeAdmissions")
        println("Total Admissions   : $totalAdmissions")
        println("Total Appointments : $totalAppointments")
        println("Charge Per Day     : Rs.$chargePerDay")
    }
}

// ──────────────────────────────────────────
//  MAIN
// ──────────────────────────────────────────

fun main() {
    var hospital = HospitalManagement()
    var running = true

    // Sample data
    hospital.addDoctor("D001", "Arjun Sharma",  "Cardiology",    800)
    hospital.addDoctor("D002", "Priya Mehta",   "Neurology",     900)
    hospital.addDoctor("D003", "Ravi Kumar",    "Orthopedics",   700)
    hospital.addDoctor("D004", "Sneha Patil",   "Pediatrics",    600)
    hospital.addDoctor("D005", "Vikram Singh",  "General",       500)

    hospital.addPatient("P001", "Rahul Gupta",   35, "Chest Pain",   "555-1001")
    hospital.addPatient("P002", "Anita Desai",   28, "Migraine",     "555-1002")
    hospital.addPatient("P003", "Suresh Nair",   45, "Knee Pain",    "555-1003")
    hospital.addPatient("P004", "Meena Joshi",   12, "Fever",        "555-1004")
    hospital.addPatient("P005", "Kiran Shah",    52, "Hypertension", "555-1005")

    println("=== Hospital Management System ===")

    while (running) {
        println("\n--- Menu (Today is Day " + hospital.currentDay + ") ---")
        println("1.  Display All Doctors")
        println("2.  Display All Patients")
        println("3.  Search Doctor")
        println("4.  Search Patient")
        println("5.  Add Doctor")
        println("6.  Add Patient")
        println("7.  View Doctor Details")
        println("8.  View Patient Details")
        println("9.  Delete Doctor")
        println("10. Delete Patient")
        println("11. Admit Patient")
        println("12. Discharge Patient")
        println("13. Book Appointment")
        println("14. Display Appointments")
        println("15. Check Long Stay Alerts")
        println("16. Hospital Statistics")
        println("17. Advance to Next Day")
        println("0.  Exit")
        print("Enter choice: ")

        var choice = readln()

        if (choice == "1") hospital.displayDoctors()
        else if (choice == "2") hospital.displayPatients()
        else if (choice == "3") {
            print("Search doctor (name / specialty / ID): ")
            var query = readln()
            hospital.searchDoctor(query)
        }
        else if (choice == "4") {
            print("Search patient (name / illness / ID): ")
            var query = readln()
            hospital.searchPatient(query)
        }
        else if (choice == "5") {
            print("Enter Doctor ID (e.g. D006): ")
            var id = readln()
            print("Enter Name: ")
            var name = readln()
            print("Enter Specialty: ")
            var specialty = readln()
            print("Enter Consultation Fee: ")
            var fee = readln().toIntOrNull() ?: 0
            hospital.addDoctor(id, name, specialty, fee)
        }
        else if (choice == "6") {
            print("Enter Patient ID (e.g. P006): ")
            var id = readln()
            print("Enter Name: ")
            var name = readln()
            print("Enter Age: ")
            var age = readln().toIntOrNull() ?: 0
            print("Enter Illness: ")
            var illness = readln()
            print("Enter Phone: ")
            var phone = readln()
            hospital.addPatient(id, name, age, illness, phone)
        }
        else if (choice == "7") {
            print("Enter Doctor ID: ")
            var id = readln()
            hospital.viewDoctorDetails(id)
        }
        else if (choice == "8") {
            print("Enter Patient ID: ")
            var id = readln()
            hospital.viewPatientDetails(id)
        }
        else if (choice == "9") {
            hospital.displayDoctors()
            print("Enter Doctor ID to delete: ")
            var id = readln()
            hospital.deleteDoctor(id)
        }
        else if (choice == "10") {
            hospital.displayPatients()
            print("Enter Patient ID to delete: ")
            var id = readln()
            hospital.deletePatient(id)
        }
        else if (choice == "11") {
            hospital.displayPatients()
            hospital.displayDoctors()
            print("Enter Patient ID to admit: ")
            var patientId = readln()
            print("Enter Doctor ID: ")
            var doctorId = readln()
            println("Ward types: General / Private / ICU")
            print("Enter Ward Type: ")
            var ward = readln()
            hospital.admitPatient(patientId, doctorId, ward)
        }
        else if (choice == "12") {
            hospital.displayPatients()
            print("Enter Patient ID to discharge: ")
            var patientId = readln()
            print("Enter Doctor ID: ")
            var doctorId = readln()
            hospital.dischargePatient(patientId, doctorId)
        }
        else if (choice == "13") {
            hospital.displayPatients()
            hospital.displayDoctors()
            print("Enter Patient ID: ")
            var patientId = readln()
            print("Enter Doctor ID: ")
            var doctorId = readln()
            hospital.bookAppointment(patientId, doctorId)
        }
        else if (choice == "14") hospital.displayAppointments()
        else if (choice == "15") hospital.checkLongStay()
        else if (choice == "16") hospital.displayStatistics()
        else if (choice == "17") hospital.advanceDay()
        else if (choice == "0") {
            running = false
            println("Goodbye!")
        }
        else println("Invalid choice. Please try again.")
    }
}
