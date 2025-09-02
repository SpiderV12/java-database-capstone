package com.example.Smart_Clinic.back_end.service;


import org.springframework.stereotype.Service;

@Service

public class AuthService {

    // تحقق من صحة التوكن
    public boolean isTokenValid(String token) {
        // مثال: تحقق مبسط، يمكن ربطه بمكتبة JWT حقيقية
        return token != null && !token.isEmpty();
    }

    // استخرج الدور من التوكن
    public String getRoleFromToken(String token) {
        // مثال ثابت: يمكن ربطه بالـ JWT parsing
        if (token.equals("admin-token")) return "ADMIN";
        if (token.equals("doctor-token")) return "DOCTOR";
        return "UNKNOWN";
    }
}