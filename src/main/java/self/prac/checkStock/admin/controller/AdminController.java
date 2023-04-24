package self.prac.checkStock.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import self.prac.checkStock.admin.domain.Admin;
import self.prac.checkStock.admin.repository.AdminRepository;
import self.prac.checkStock.admin.service.AdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final AdminRepository adminRepository;

    @PostMapping("/signUp")
    public ResponseEntity<Admin> signUp(@RequestBody Admin admin) {
        adminService.signUp(admin);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/signIn")
    public ResponseEntity<Admin> signIn(@RequestBody Admin admin) {



        return ResponseEntity.ok(admin);
    }

}
