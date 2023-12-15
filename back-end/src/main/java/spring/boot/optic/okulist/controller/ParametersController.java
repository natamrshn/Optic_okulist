package spring.boot.optic.okulist.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.contactlenses.parameters.color.ColorRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.color.ColorResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.cylinder.CylinderRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.cylinder.CylinderResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.degree.DegreeRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.degree.DegreeResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.diopter.DiopterRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.diopter.DiopterResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereResponseDto;
import spring.boot.optic.okulist.service.contactlenses.params.color.ColorService;
import spring.boot.optic.okulist.service.contactlenses.params.cylinder.CylinderService;
import spring.boot.optic.okulist.service.contactlenses.params.degree.DegreeService;
import spring.boot.optic.okulist.service.contactlenses.params.diopter.DiopterService;
import spring.boot.optic.okulist.service.contactlenses.params.sphere.SphereService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/parameters")
public class ParametersController {
    private final ColorService colorService;
    private final CylinderService cylinderService;
    private final DegreeService degreeService;
    private final DiopterService diopterService;
    private final SphereService sphereService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create-color")
    public ResponseEntity<ColorResponseDto> createColor(@RequestBody
                                                        @Valid ColorRequestDto colorRequestDto) {
        ColorResponseDto createdColor = colorService.createColor(colorRequestDto);
        return new ResponseEntity<>(createdColor, HttpStatus.CREATED);
    }

    @GetMapping("/getAll-Colors")
    public ResponseEntity<List<ColorResponseDto>> getAllColors() {
        List<ColorResponseDto> allColors = colorService.getAllColors();
        return ResponseEntity.ok(allColors);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/colors/{id}")
    public ColorResponseDto getColorById(@PathVariable Long id) {
        return colorService.getColorById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/cylinders")
    public ResponseEntity<CylinderResponseDto> createCylinder(
            @RequestBody
            @Valid CylinderRequestDto cylinderRequestDto) {
        CylinderResponseDto createdCylinder = cylinderService.createCylinder(cylinderRequestDto);
        return new ResponseEntity<>(createdCylinder, HttpStatus.CREATED);
    }

    @GetMapping("/getAll-Cylinders")
    public ResponseEntity<List<CylinderResponseDto>> getAllCylinders() {
        List<CylinderResponseDto> allCylinders = cylinderService.getAllCylinders();
        return ResponseEntity.ok(allCylinders);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/cylinder/{id}")
    public CylinderResponseDto getCylinderById(@PathVariable Long id) {
        return cylinderService.getCylinderById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/degrees")
    public ResponseEntity<DegreeResponseDto> createDegree(
            @RequestBody
            @Valid DegreeRequestDto degreeRequestDto) {
        DegreeResponseDto createdDegree = degreeService.createDegree(degreeRequestDto);
        return new ResponseEntity<>(createdDegree, HttpStatus.CREATED);
    }

    @GetMapping("/getAll-Degrees")
    public ResponseEntity<List<DegreeResponseDto>> getAllDegrees() {
        List<DegreeResponseDto> allDegrees = degreeService.getAllDegrees();
        return ResponseEntity.ok(allDegrees);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/degrees/{id}")
    public DegreeResponseDto getDegreeById(@PathVariable Long id) {
        return degreeService.getDegreeById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/diopters")
    public ResponseEntity<DiopterResponseDto> createDiopter(
            @RequestBody
            @Valid DiopterRequestDto diopterRequestDto) {
        DiopterResponseDto createdDiopter = diopterService.createDiopter(diopterRequestDto);
        return new ResponseEntity<>(createdDiopter, HttpStatus.CREATED);
    }

    @GetMapping("/getAll-Diopters")
    public ResponseEntity<List<DiopterResponseDto>> getAllDiopters() {
        List<DiopterResponseDto> allDiopters = diopterService.getAllDiopters();
        return ResponseEntity.ok(allDiopters);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/diopters/{id}")
    public DiopterResponseDto getDiopterById(@PathVariable Long id) {
        return diopterService.getDiopterById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/spheres")
    public ResponseEntity<SphereResponseDto> createSphere(
            @RequestBody
            @Valid SphereRequestDto sphereRequestDto) {
        SphereResponseDto createdSphere = sphereService.createSphere(sphereRequestDto);
        return new ResponseEntity<>(createdSphere, HttpStatus.CREATED);
    }

    @GetMapping("/getAll-Spheres")
    public ResponseEntity<List<SphereResponseDto>> getAllSpheres() {
        List<SphereResponseDto> allSpheres = sphereService.getAllSpheres();
        return ResponseEntity.ok(allSpheres);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/spheres/{id}")
    public SphereResponseDto getSphereById(@PathVariable Long id) {
        return sphereService.getSphereById(id);
    }
}
