package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.admin.AdminIdsRequestDto;
import spring.boot.optic.okulist.service.admin.AdminService;

@RestController
@RequestMapping("/admin/permissions")
@RequiredArgsConstructor
@Tag(name = "Admin Permissions", description = "Manage permissions for the ADMIN role")
public class AdminController {
    private final AdminService adminService;

    @Operation(
            summary = "Grant Permissions to All Admins",
            description = "Grant the create, update, and delete permissions to all administrators."
    )
    @PostMapping("/grant/all")
    @PreAuthorize("hasRole('MAIN_ADMIN')")
    public ResponseEntity<String> grantPermissionsToAllAdmins() {
        adminService.grantPermissionsToAdmins();
        return ResponseEntity.ok("Permissions granted to all ADMINs");
    }

    @PostMapping("/revoke-all")
    @PreAuthorize("hasRole('MAIN_ADMIN')")
    public ResponseEntity<String> revokeAllPermissionsFromAdmins() {
        adminService.revokePermissionsFromAdmins();
        return ResponseEntity.ok("Create, update, and delete permissions revoked from all admins");
    }

    @Operation(
            summary = "Grant Permissions to Specific Admins",
            description = "Grant the create, update, and delete "
                    + "permissions to specific administrators."
    )
    @PostMapping("/grant/specific")
    @PreAuthorize("hasRole('MAIN_ADMIN')")
    public ResponseEntity<String> grantPermissionsToSpecificAdmins(
            @RequestBody AdminIdsRequestDto adminIdsRequest) {
        List<Long> adminIds = adminIdsRequest.getAdminIds();
        adminService.grantPermissionsToSpecificAdmins(adminIds);
        return ResponseEntity.ok("Permissions granted to specific ADMINs");
    }

    @Operation(
            summary = "Revoke Permissions from Specific Admin",
            description = "Revoke the create, update, and delete"
                    + " permissions from a specific administrator."
    )
    @PostMapping("/revoke/specific")
    @PreAuthorize("hasRole('MAIN_ADMIN')")
    public ResponseEntity<String> revokePermissionsFromSpecificAdmin(
            @RequestBody AdminIdsRequestDto adminIdsRequest) {
        List<Long> adminIds = adminIdsRequest.getAdminIds();
        adminService.revokePermissionsFromSpecificAdmin(adminIds);
        return ResponseEntity.ok("Permissions revoked from specific ADMINs");
    }
}
