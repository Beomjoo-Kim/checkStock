package self.prac.checkStock.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.prac.checkStock.admin.domain.Admin;
import self.prac.checkStock.admin.repository.AdminRepository;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomErrorResponse;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;
import self.prac.checkStock.global.security.Role;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public void signUp(Admin admin) {
        List<Admin> adminList = findAdminByEmail(admin.getEmail());
        if (adminList.size() > 0) {
            throw new CustomRuntimeException(CustomErrorCodes.OVER_SIGNED);
        }
        admin.setRole(Role.ADMIN.getValue());
        adminRepository.save(admin);
    }

    private List<Admin> findAdminByEmail(String email) {
        List<Admin> adminList = adminRepository.findByEmail(email);
        return adminList;
    }
}
