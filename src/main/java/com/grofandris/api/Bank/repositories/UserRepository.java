package com.grofandris.api.Bank.repositories;

import com.grofandris.api.Bank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
