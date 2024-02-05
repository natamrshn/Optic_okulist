package spring.boot.optic.okulist.service.admin;

import spring.boot.optic.okulist.model.Role;

import java.util.List;

public interface AdminService {
    void grantPermissionsToAdmins();

    void grantPermissionsToSpecificAdmins(List<Long> adminIds);

    void revokePermissionsFromAdmins();

    void revokePermissionsFromSpecificAdmin(List<Long> adminIds);

    void updatePermissionsByRole(String userEmail, Role.RoleName newRoleName);
}
