package com.MovieTicketBooking.MovieTicketBooking.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Integer> {
    Optional<UserModel> findByEmailAndPassword(String email, String eassword);

        boolean existsByEmail(String email);

    Optional<UserModel> findByEmail(String email);
}
