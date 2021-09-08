package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@AllArgsConstructor
@Api(value = "Schedules", produces = APPLICATION_JSON_VALUE)
public class ScheduleController extends BaseRestController {

    private static final String PATH = "/schedules";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final ScheduleService scheduleService;

    @ApiOperation(value = "Add Schedule")
    @RequestMapping(path = PATH + "/add", method = POST, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = CREATED)
    public ResponseEntity<Void> addScheduleTennisCourt(@RequestBody @Valid CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }


    @ApiOperation(value = "Get Schedules from range of dates")
    @RequestMapping(path = PATH + "/findByDates", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = OK)
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(
            @ApiParam(format = DATE_FORMAT, example = "2021-09-07") @RequestParam(value = "startDate") @DateTimeFormat(pattern = DATE_FORMAT) LocalDate startDate,
            @ApiParam(format = DATE_FORMAT, example = "2021-09-08") @RequestParam(value = "endDate") @DateTimeFormat(pattern = DATE_FORMAT) LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    @ApiOperation(value = "Get Schedule from ID")
    @RequestMapping(path = PATH + "/{scheduleId}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = OK)
    public ResponseEntity<ScheduleDTO> findByScheduleId(@PathVariable("scheduleId") @NotNull Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}
