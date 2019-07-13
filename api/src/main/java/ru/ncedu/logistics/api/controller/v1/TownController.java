package ru.ncedu.logistics.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.logistics.api.dto.TownDTO;
import ru.ncedu.logistics.api.exception.TownNotFoundException;
import ru.ncedu.logistics.api.service.TownService;
import ru.ncedu.logistics.api.util.PageUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${api.prefix.v1}/town")
@Api(tags = "TownController")
@RequiredArgsConstructor
public class TownController {

    private final TownService townService;

    @PostMapping
    @ApiOperation("Create town")
    public TownDTO create(@Valid @RequestBody TownDTO townDTO) {
        return townService.create(townDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update town")
    public TownDTO update(@PathVariable("id") String id, @Valid @RequestBody TownDTO townDTO) throws TownNotFoundException {
        return townService.update(id, townDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete town")
    public void delete(@PathVariable("id") String id) {
        townService.delete(id);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get town")
    public TownDTO get(@PathVariable("id") String id) throws TownNotFoundException {
        return townService.getById(id);
    }

    @GetMapping
    @ApiOperation("Find towns")
    public List<TownDTO> find(@ApiParam("Name") @RequestParam(value = "name*", required = false) String nameRegex,
                                      @ApiParam("Fields") @RequestParam(value = "fields", required = false) List<String> fields,
                                      @ApiParam("Sort") @RequestParam(value = "sort", required = false) List<String> sorting,
                                      @ApiParam("Range") @RequestHeader(value = "Range", required = false) String ranging,
                                      HttpServletResponse response) {
        Pageable pageable = PageUtils.parse(ranging, sorting);
        PageUtils.setContentRange(response, pageable, townService.count(nameRegex));
        return townService.find(nameRegex, pageable, fields);
    }

}
