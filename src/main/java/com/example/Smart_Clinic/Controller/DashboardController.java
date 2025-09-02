package com.example.Smart_Clinic.Controller;

import com.example.Smart_Clinic.back_end.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


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
    @GetMapping("/PacntrDashboard/{token}")
    public String PacintDashboard(@PathVariable String token, Model model) {
        if(authService.isTokenValid(token) && authService.getRoleFromToken(token).equals("PACHI")) {
            model.addAttribute("token", token);
            return "pacint/PacntrDashboard"; // Thymeleaf template
        }
        return "redirect:/login"; // إعادة التوجيه إذا لم يكن التوكن صالحاً
    }
    @GetMapping("/patient/search")
    public String searchDoctor(@RequestParam String query, Model model) {
        // هنا تقدر لاحقاً تجيب بيانات من قاعدة بيانات
        // الآن مجرد مثال ثابت
        model.addAttribute("query", query);
        return "pacint/patientSearch";
    }
}
