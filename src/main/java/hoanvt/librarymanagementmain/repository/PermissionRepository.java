package hoanvt.librarymanagementmain.repository;

import hoanvt.librarymanagementmain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}