package com.tenniscourts.reservations;

import com.tenniscourts.guests.Guest;
import com.tenniscourts.guests.GuestService;
import com.tenniscourts.schedules.Schedule;
import com.tenniscourts.schedules.ScheduleDTO;
import com.tenniscourts.schedules.ScheduleMapper;
import com.tenniscourts.schedules.ScheduleService;
import com.tenniscourts.tenniscourts.TennisCourt;
import com.tenniscourts.tenniscourts.TennisCourtDTO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ReservationService.class)
public class ReservationServiceTest {

    @InjectMocks
    ReservationService reservationService;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    ReservationMapper reservationMapper;

    @Mock
    GuestService guestService;

    @Mock
    ScheduleService scheduleService;

    @Autowired
    ScheduleMapper scheduleMapper;

    @Test
    public void getRefundValueFullRefund() {
        Schedule schedule = new Schedule();

        LocalDateTime startDateTime = LocalDateTime.now().plusDays(2);

        schedule.setStartDateTime(startDateTime);

        Assert.assertEquals(reservationService.getRefundValue(Reservation.builder().schedule(schedule).value(new BigDecimal(10L)).build()), new BigDecimal(10));
    }

    @Test
    public void bookReservationTest() {
        when(guestService.findById(anyLong())).thenReturn(getGuestMock());
        when(scheduleService.findEntity(anyLong())).thenReturn(Optional.of(getScheduleMock()));
        when(reservationMapper.map(any(Reservation.class))).thenReturn(getReservationDTOMock());
        CreateReservationRequestDTO requestDTO = CreateReservationRequestDTO.builder().guestId(1L).scheduleId(1L).build();
        ReservationDTO reservationDTO= reservationService.bookReservation(requestDTO);
        Assert.assertNotNull(reservationDTO);
    }

    @Test
    public void rescheduleReservationTest() {
        when(guestService.findById(anyLong())).thenReturn(getGuestMock());
        when(scheduleService.findEntity(anyLong())).thenReturn(Optional.of(getSchedule2Mock()));
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(getReservationMock()));
        ReservationDTO prev = getReservationDTOMock();
        prev.setReservationStatus(ReservationStatus.RESCHEDULED.name());
        ReservationDTO dto = getReservationDTO2Mock();
        dto.setPreviousReservation(prev);
        when(reservationMapper.map(any(Reservation.class))).thenReturn(dto);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(getReservationRescheduledMock());
        ReservationDTO reservationDTO = reservationService.rescheduleReservation(1L, 2L);
        Assert.assertEquals(Long.valueOf(2L), reservationDTO.getScheduledId());
    }

    private Guest getGuestMock() {
        Guest guest = new Guest();
        guest.setId(1L);
        guest.setName("Joe Doe");
        return guest;
    }

    private Schedule getScheduleMock() {
        Schedule schedule = new Schedule();
        schedule.setId(1l);
        schedule.setTennisCourt(getTennisCourtMock());
        schedule.setStartDateTime(LocalDateTime.of(2021, 9, 10, 10, 00,00));
        schedule.setEndDateTime(LocalDateTime.of(2021, 9, 10, 11, 00,00));
        return schedule;
    }

    private Schedule getSchedule2Mock() {
        Schedule schedule = new Schedule();
        schedule.setId(2l);
        schedule.setTennisCourt(getTennisCourtMock());
        schedule.setStartDateTime(LocalDateTime.of(2021, 9, 10, 11, 00,00));
        schedule.setEndDateTime(LocalDateTime.of(2021, 9, 10, 12, 00,00));
        return schedule;
    }

    private TennisCourt getTennisCourtMock() {
        TennisCourt tennisCourt = new TennisCourt();
        tennisCourt.setId(1L);
        tennisCourt.setName("Wimbledon - Centre Court");
        return tennisCourt;
    }

    private ScheduleDTO getScheduleDTOMock() {
        ScheduleDTO schedule = new ScheduleDTO();
        schedule.setId(1l);
        schedule.setTennisCourt(getTennisCourtDTOMock());
        schedule.setStartDateTime(LocalDateTime.of(2021, 9, 10, 10, 00,00));
        schedule.setEndDateTime(LocalDateTime.of(2021, 9, 10, 11, 00,00));
        return schedule;
    }

    private ScheduleDTO getScheduleDTO2Mock() {
        ScheduleDTO schedule = new ScheduleDTO();
        schedule.setId(2l);
        schedule.setTennisCourt(getTennisCourtDTOMock());
        schedule.setStartDateTime(LocalDateTime.of(2021, 9, 10, 11, 00,00));
        schedule.setEndDateTime(LocalDateTime.of(2021, 9, 10, 12, 00,00));
        return schedule;
    }

    private TennisCourtDTO getTennisCourtDTOMock() {
        TennisCourtDTO tennisCourt = new TennisCourtDTO();
        tennisCourt.setId(1L);
        tennisCourt.setName("Wimbledon - Centre Court");
        return tennisCourt;
    }

    private Reservation getReservationMock() {
        Reservation reservation = Reservation.builder().guest(getGuestMock()).schedule(getScheduleMock()).value(new BigDecimal(10l)).build();
        reservation.setId(1L);
        return reservation;
    }

    private Reservation getReservationRescheduledMock() {
        Reservation reservation = Reservation.builder()
                .guest(getGuestMock())
                .schedule(getScheduleMock())
                .reservationStatus(ReservationStatus.RESCHEDULED)
                .value(new BigDecimal(10L))
                .build();
        reservation.setId(1L);
        return reservation;
    }

    private ReservationDTO getReservationDTOMock() {
        return ReservationDTO.builder()
                .id(1L)
                .schedule(getScheduleDTOMock())
                .reservationStatus(ReservationStatus.READY_TO_PLAY.name())
                .value(new BigDecimal(10L))
                .scheduledId(1L)
                .guestId(1L)
                .build();
    }

    private ReservationDTO getReservationDTO2Mock() {
        return ReservationDTO.builder()
                .id(2L)
                .schedule(getScheduleDTO2Mock())
                .reservationStatus(ReservationStatus.READY_TO_PLAY.name())
                .value(new BigDecimal(10L))
                .scheduledId(2L)
                .guestId(1L)
                .build();
    }
}