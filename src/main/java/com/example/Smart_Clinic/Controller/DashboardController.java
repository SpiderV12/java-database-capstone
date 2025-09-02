package com.example.Smart_Clinic.Controller;

import com.example.Smart_Clinic.back_end.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class DashboardController {

    @Autowired
    private AuthService authService; // خدمة للتحقق من JWT

    @GetMapping("/adminDashboard/{token}")
    public String adminDashboard(@PathVariable String token, Model model) {
        if(authService.isTokenValid(token) && authService.getRoleFromToken(token).equals("ADMIN")) {
            model.addAttribute("token", token);
            return "admin/adminDashboard"; // Thymeleaf template
        }
        return "redirect:/login"; // إعادة التوجيه إذا لم يكن التوكن صالحاً
    }

    @GetMapping("/doctorDashboard/{token}")
    public String doctorDashboard(@PathVariable String token, Model model) {
        if(authService.isTokenValid(token) && authService.getRoleFromToken(token).equals("DOCTOR")) {
            model.addAttribute("token", token);
            return "doctor/doctorDashboard"; // Thymeleaf template
        }
        return "redirect:/login"; // إعادة التوجيه إذا لم يكن التوكن صالحاً
    }
}
