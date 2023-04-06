package self.prac.checkStock.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import self.prac.checkStock.admin.repository.AdminRepository;
import self.prac.checkStock.admin.service.AdminService;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final AdminRepository adminRepository;


}
