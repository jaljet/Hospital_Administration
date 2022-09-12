package com.bushuev.med.hospital_admin.repos;


import com.bushuev.med.hospital_admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByUsername(String username);
}
