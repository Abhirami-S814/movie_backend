package com.MovieTicketBooking.MovieTicketBooking.TicketBooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TicketBookingRepo extends JpaRepository<TicketBookingModel, Integer> {

    int countBookedSeatsByScreenId(Integer screenId);

    // Existing method without lock
    List<TicketBookingModel> findByScreenIdAndShowDateAndShowTime(Integer screenId, LocalDate showDate, LocalTime showTime);

    // New method with pessimistic lock to prevent race conditions
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM TicketBookingModel b WHERE b.screenId = :screenId AND b.showDate = :showDate AND b.showTime = :showTime")
    List<TicketBookingModel> findByScreenIdAndShowDateAndShowTimeForUpdate(
            @Param("screenId") Integer screenId,
            @Param("showDate") LocalDate showDate,
            @Param("showTime") LocalTime showTime
    );
}
