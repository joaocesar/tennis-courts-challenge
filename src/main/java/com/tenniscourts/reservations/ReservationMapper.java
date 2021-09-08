package com.tenniscourts.reservations;

import com.tenniscourts.guests.Guest;
import com.tenniscourts.schedules.Schedule;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation map(ReservationDTO source);

    @InheritInverseConfiguration
    ReservationDTO map(Reservation source);

    default Reservation map(CreateReservationRequestDTO source) {
        Reservation reservation = new Reservation();
        Guest guest = new Guest();
        guest.setId(source.getGuestId());
        Schedule schedule = new Schedule();
        schedule.setId(source.getScheduleId());
        reservation.setGuest(guest);
        reservation.setSchedule(schedule);
        return reservation;
    };
}
