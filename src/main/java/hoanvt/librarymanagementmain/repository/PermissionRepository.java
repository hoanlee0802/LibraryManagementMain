package hoanvt.librarymanagementmain.repository;

import hoanvt.librarymanagementmain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
//    Optional<Permission> findByFunctionCode(String functionCode);
}