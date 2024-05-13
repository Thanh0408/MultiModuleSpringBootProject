package demo.com.vn.repository;

import demo.com.vn.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, String> {
}
