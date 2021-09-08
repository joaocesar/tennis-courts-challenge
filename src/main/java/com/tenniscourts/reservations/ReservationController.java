package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@AllArgsConstructor
@Api(value = "Reservations", produces = APPLICATION_JSON_VALUE)
public class ReservationController extends BaseRestController {

    private static final String PATH = "/reservations";

    private final ReservationService reservationService;

    @ApiOperation(value = "Book Reservation")
    @RequestMapping(path = PATH + "/book", method = POST, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = CREATED)
    public ResponseEntity<Void> bookReservation(@RequestBody @Valid CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }

    @ApiOperation(value = "Get Reservation")
    @RequestMapping(path = PATH + "/{reservationId}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = OK)
    public ResponseEntity<ReservationDTO> findReservation(@PathVariable("reservationId") @NotNull Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }

    @ApiOperation(value = "Cancel Reservation")
    @RequestMapping(path = PATH + "/cancel/{reservationId}", method = PUT, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = OK)
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable("reservationId") @NotNull Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @ApiOperation(value = "Reschedule Reservation")
    @RequestMapping(path = PATH + "/cancel/{reservationId}/schedule/{scheduleId}", method = PUT, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = OK)
    public ResponseEntity<ReservationDTO> rescheduleReservation(
            @PathVariable("reservationId") @NotNull Long reservationId,
            @PathVariable("scheduleId") @NotNull Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
