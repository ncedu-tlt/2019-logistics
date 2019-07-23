package ru.ncedu.logistics.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.logistics.api.dto.OfficeDTO;
import ru.ncedu.logistics.api.entity.OfficeEntity;
import ru.ncedu.logistics.api.exception.OfferingExistsException;
import ru.ncedu.logistics.api.exception.OfferingNotFoundException;
import ru.ncedu.logistics.api.exception.OfficeNotFoundException;
import ru.ncedu.logistics.api.service.OfficeService;
import ru.ncedu.logistics.api.util.PageUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${api.prefix.v1}/offices")
@Api(tags = "OfficeController")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

    @PostMapping
    @ApiOperation("Create office")
    public OfficeDTO create(@Valid @RequestBody OfficeDTO office){
        return officeService.create(office);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get office")
    public OfficeDTO get(@PathVariable("id") String id) throws OfficeNotFoundException{
        return officeService.getById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update office")
    public OfficeDTO update(
            @PathVariable("id") String id,
            @Valid @RequestBody OfficeDTO office) throws OfficeNotFoundException {
        return officeService.update(id, office);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete office")
    public void delete(@PathVariable("id") String id){
        officeService.delete(id);
    }

    @PostMapping("/{officeId}/addOffering")
    @ApiOperation("Add offering")
    public OfficeDTO addOffering(@PathVariable("officeId") String officeId,
                                @Valid @RequestBody OfficeEntity.Offering offering) throws OfferingExistsException {
        return officeService.addOffering(officeId, offering);
    }

    @PostMapping("/{officeId}/updateOffering")
    @ApiOperation("Update offering")
    public OfficeDTO updateOffering(@PathVariable("officeId") String officeId,
                                 @Valid @RequestBody OfficeEntity.Offering offering) throws OfferingNotFoundException {
        return officeService.updateOffering(officeId, offering);
    }

    @PostMapping("/{officeId}/removeOffering")
    @ApiOperation("Remove offering")
    public OfficeDTO removeOffering(@PathVariable("officeId") String officeId,
                                    @Valid @RequestBody OfficeEntity.Offering offering){
        return officeService.removeOffering(officeId, offering);
    }

    @GetMapping
    @ApiOperation("Find offices")
    public List<OfficeDTO> find(@ApiParam("Name") @RequestParam(value = "name*", required = false) String nameRegex,
                                @ApiParam("Fields") @RequestParam(value = "fields", required = false) List<String> fields,
                                @ApiParam("Sort") @RequestParam(value = "sort", required = false) List<String> sorting,
                                @ApiParam("Range") @RequestHeader(value = "Range", required = false) String ranging,
                                HttpServletResponse response){
        Pageable pageable = PageUtils.parse(ranging, sorting);
        PageUtils.setContentRange(response, pageable, officeService.count(nameRegex));
        return officeService.find(nameRegex, pageable, fields);
    }


}
