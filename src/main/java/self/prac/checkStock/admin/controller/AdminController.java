package self.prac.checkStock.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import self.prac.checkStock.admin.domain.Admin;
import self.prac.checkStock.admin.repository.AdminRepository;
import self.prac.checkStock.admin.service.AdminService;
import self.prac.checkStock.global.jwt.JwtUtil;
import self.prac.checkStock.global.domain.UserDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/signUp")
    public ResponseEntity<Admin> signUp(@RequestBody Admin admin) {
        adminService.signUp(admin);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/signIn")
    public String signIn(@RequestBody Admin admin) {
        Admin signInAdmin = adminService.signIn(admin);
        UserDto adminDto = new UserDto(signInAdmin.getId(), signInAdmin.getName(), signInAdmin.getEmail(), signInAdmin.getRole());
        return jwtUtil.generateToken(adminDto);
    }

}
