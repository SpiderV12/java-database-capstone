package com.example.Smart_Clinic.back_end.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public boolean validateToken(String token) {
        // تحقق من JWT هنا
        return token != null && !token.isEmpty();
    }

    public String getRoleFromToken(String token) {
        // استخرج الدور من التوكن
        return "DOCTOR"; // مثال ثابت
    }
}