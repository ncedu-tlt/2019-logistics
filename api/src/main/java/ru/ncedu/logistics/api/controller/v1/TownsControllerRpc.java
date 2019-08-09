package ru.ncedu.logistics.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.logistics.api.dto.TownDTO;
import ru.ncedu.logistics.api.entity.TownEntity;
import ru.ncedu.logistics.api.service.TownService;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.prefix.v1}/towns")
@Api(tags = "TownRpcController")
@RequiredArgsConstructor
public class TownsControllerRpc {

    private final TownService townService;

    @PostMapping("/{townId}/addRoad")
    @ApiOperation("Add road")
    public TownDTO addRoad(@PathVariable("townId") String townId,
                           @Valid @RequestBody TownEntity.Road road){
        return townService.addRoad(townId, road);
    }

    @PostMapping("/{townId}/updateRoad")
    @ApiOperation("Update road")
    public TownDTO updateRoad(@PathVariable("townId") String townId,
                              @Valid @RequestBody TownEntity.Road road){
        return townService.updateRoad(townId, road);
    }

    @PostMapping("/{townId}/removeRoad")
    @ApiOperation("Remove road")
    public TownDTO removeRoad(@PathVariable("townId") String townId,
                              @Valid @RequestBody TownEntity.Road road){
        return townService.removeRoad(townId, road);
    }
}
