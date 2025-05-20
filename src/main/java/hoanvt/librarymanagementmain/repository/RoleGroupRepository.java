package hoanvt.librarymanagementmain.repository;

import hoanvt.librarymanagementmain.entity.RoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Long> {
    Optional<RoleGroup> findByRoleGroupCode(String code);
}