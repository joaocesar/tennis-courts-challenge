package com.tenniscourts.tenniscourts;

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

@RestController
@AllArgsConstructor
@Api(value = "Tennis Courts", produces = APPLICATION_JSON_VALUE)
public class TennisCourtController extends BaseRestController {

    private static final String PATH = "/tennis-courts";

    private final TennisCourtService tennisCourtService;

    @ApiOperation(value = "Add Tennis Court")
    @RequestMapping(path = PATH + "/add", method = POST, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = CREATED)
    public ResponseEntity<Void> addTennisCourt(@RequestBody @Valid TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }

    @ApiOperation(value = "Get Schedule from ID")
    @RequestMapping(path = PATH + "/{tennisCourtId}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = OK)
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(@PathVariable("tennisCourtId") @NotNull Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }

    @ApiOperation(value = "Get Schedule from ID")
    @RequestMapping(path = PATH + "/{tennisCourtId}/schedules", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = OK)
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(@PathVariable("tennisCourtId") @NotNull Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
