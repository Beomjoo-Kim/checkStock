package self.prac.checkStock.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.admin.domain.Admin;
import self.prac.checkStock.admin.repository.AdminRepository;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;
import self.prac.checkStock.global.security.Role;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    @Transactional
    public void signUp(Admin admin) {
        List<Admin> searchedAdminList = adminRepository.findByEmail(admin.getEmail());
        if (searchedAdminList.size() != 0) {
            throw new CustomRuntimeException(CustomErrorCodes.ALREADY_SIGNED);
        }
        admin.setRole(Role.ADMIN.getValue());
        adminRepository.save(admin);
    }

    public Admin signIn(Admin admin) {
        return isPasswordCorrect(admin);
    }
    @Transactional
    public Admin isPasswordCorrect(Admin admin) {
        Admin searchedAdmin = findAdminByEmail(admin.getEmail());
        if (!searchedAdmin.getPassword().equals(admin.getPassword())) {
            throw new CustomRuntimeException(CustomErrorCodes.INCORRECT_PASSWORD);
        }
        return searchedAdmin;
    }

    public Admin findAdminByEmail(String email) {
        List<Admin> adminList = adminRepository.findByEmail(email);
        if (adminList.size() < 1) {
            throw new CustomRuntimeException(CustomErrorCodes.NOT_SIGNED);
        } else if (adminList.size() > 1) {
            throw new CustomRuntimeException(CustomErrorCodes.OVER_SIGNED);
        }
        return adminList.get(0);
    }
}
