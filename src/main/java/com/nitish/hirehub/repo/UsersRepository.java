package com.nitish.hirehub.repo;

import com.nitish.hirehub.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

}
