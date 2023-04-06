package self.prac.checkStock.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.prac.checkStock.admin.repository.AdminRepository;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
}
