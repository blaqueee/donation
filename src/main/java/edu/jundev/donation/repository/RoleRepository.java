package edu.jundev.donation.repository;

import edu.jundev.donation.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role,Long>{
    Optional<Role> findRoleByName(String name);
}
