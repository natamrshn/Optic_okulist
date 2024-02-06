package spring.boot.optic.okulist.dto.admin;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import spring.boot.optic.okulist.model.Role;

@Data
@Getter
@Setter
public class UserRoleChangeRequestDto {
    private String userEmail;
    private Role.RoleName newRoleName;
}
