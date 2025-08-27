package com.example.Smart_Clinic.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "prescriptions")
public class Prescription {

    @Id
    private String id;

    @NotNull
    private Long appointmentId;

    @NotNull
    private String patientName;

    private List<Medication> medications;

    private String notes;

    // Inner class for medications
    public static class Medication {
        private String name;
        private String dosage;
        private String frequency;
        private int durationDays;

        // Constructors, getters, setters
        public Medication() {}
        public Medication(String name, String dosage, String frequency, int durationDays) {
            this.name = name;
            this.dosage = dosage;
            this.frequency = frequency;
            this.durationDays = durationDays;
        }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDosage() { return dosage; }
        public void setDosage(String dosage) { this.dosage = dosage; }
        public String getFrequency() { return frequency; }
        public void setFrequency(String frequency) { this.frequency = frequency; }
        public int getDurationDays() { return durationDays; }
        public void setDurationDays(int durationDays) { this.durationDays = durationDays; }
    }

    // Constructors
    public Prescription() {}

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public List<Medication> getMedications() { return medications; }
    public void setMedications(List<Medication> medications) { this.medications = medications; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}

