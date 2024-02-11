package spring.boot.optic.okulist.service.admin.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.model.RegisteredUser;
import spring.boot.optic.okulist.model.Role;
import spring.boot.optic.okulist.repository.RoleRepository;
import spring.boot.optic.okulist.repository.UserRepository;
import spring.boot.optic.okulist.service.admin.AdminService;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void grantPermissionsToAdmins() {
        Role adminRole = roleRepository.findRoleByName(Role.RoleName.ADMIN)
                .orElseThrow(() -> new EntityNotFoundException("Role 'ADMIN' not found"));
        List<RegisteredUser> adminUsers = userRepository
                .findUsersByRolesContainingAndIsDeletedFalse(adminRole);
        for (RegisteredUser adminUser : adminUsers) {
            adminUser.setCreatePermission(true);
            adminUser.setUpdatePermission(true);
            adminUser.setDeletePermission(true);
        }
        userRepository.saveAll(adminUsers);
    }

    @Override
    public void revokePermissionsFromAdmins() {
        Role adminRole = roleRepository.findRoleByName(Role.RoleName.USER)
                .orElseThrow(() -> new EntityNotFoundException("Role 'ADMIN' not found"));
        List<RegisteredUser> adminUsers = userRepository
                .findUsersByRolesContainingAndIsDeletedFalse(adminRole);
        for (RegisteredUser adminUser : adminUsers) {
            adminUser.setCreatePermission(false);
            adminUser.setUpdatePermission(false);
            adminUser.setDeletePermission(false);
        }
        userRepository.saveAll(adminUsers);
    }

    @Override
    public void grantPermissionsToSpecificAdmins(List<Long> adminIds) {
        List<RegisteredUser> specificAdmins = userRepository.findAllById(adminIds);
        for (RegisteredUser specificAdmin : specificAdmins) {
            specificAdmin.setCreatePermission(true);
            specificAdmin.setUpdatePermission(true);
            specificAdmin.setDeletePermission(true);
        }
        userRepository.saveAll(specificAdmins);
    }

    @Override
    public void revokePermissionsFromSpecificAdmin(List<Long> adminIds) {
        List<RegisteredUser> specificAdmins = userRepository.findAllById(adminIds);
        for (RegisteredUser specificAdmin : specificAdmins) {
            specificAdmin.setCreatePermission(false);
            specificAdmin.setUpdatePermission(false);
            specificAdmin.setDeletePermission(false);
        }
        userRepository.saveAll(specificAdmins);
    }

    @Override
    public void updatePermissionsByRole(String userEmail, Role.RoleName newRoleName) {
        RegisteredUser user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + userEmail));

        // Update other fields if that's necessary
        if (newRoleName == Role.RoleName.MAIN_ADMIN || newRoleName == Role.RoleName.ADMIN) {
            user.setAdmin(true);
            user.setCreatePermission(true);
            user.setUpdatePermission(true);
            user.setDeletePermission(true);
        } else {
            // revoke permits if the role doesnt match admin/ main admin
            user.setAdmin(false);
            user.setCreatePermission(false);
            user.setUpdatePermission(false);
            user.setDeletePermission(false);
        }

        userRepository.save(user);
    }
}
