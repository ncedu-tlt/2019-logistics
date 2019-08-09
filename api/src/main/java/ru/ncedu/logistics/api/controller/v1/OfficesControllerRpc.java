package ru.ncedu.logistics.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.logistics.api.dto.OfficeDTO;
import ru.ncedu.logistics.api.entity.OfficeEntity;
import ru.ncedu.logistics.api.exception.OfferingExistsException;
import ru.ncedu.logistics.api.exception.OfferingNotFoundException;
import ru.ncedu.logistics.api.service.OfficeService;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.prefix.v1}/offices")
@Api(tags = "OfficeRpcController")
@RequiredArgsConstructor
public class OfficesControllerRpc {

    private final OfficeService officeService;

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
}
