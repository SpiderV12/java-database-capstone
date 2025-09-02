package com.example.Smart_Clinic.back_end.repository;

import com.example.Smart_Clinic.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}