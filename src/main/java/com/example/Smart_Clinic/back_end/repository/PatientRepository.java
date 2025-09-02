package com.example.Smart_Clinic.back_end.repository;


import com.example.Smart_Clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
