package spring.boot.optic.okulist.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import spring.boot.optic.okulist.dto.contactlenses.parameters.material.MaterialRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.material.MaterialResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.sphere.SphereResponseDto;
import spring.boot.optic.okulist.service.contactlenses.params.color.ColorService;
import spring.boot.optic.okulist.service.contactlenses.params.cylinder.CylinderService;
import spring.boot.optic.okulist.service.contactlenses.params.degree.DegreeService;
import spring.boot.optic.okulist.service.contactlenses.params.diopter.DiopterService;
import spring.boot.optic.okulist.service.contactlenses.params.material.MaterialService;
import spring.boot.optic.okulist.service.contactlenses.params.sphere.SphereService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/parameters")
public class ParametersController {
    private final ColorService colorService;
    private final MaterialService materialService;
    private final CylinderService cylinderService;
    private final DegreeService degreeService;
    private final DiopterService diopterService;
    private final SphereService sphereService;


    /*
    Це я протестував воно працює

    сервіс для створення парметрів які ми потім будемо використовувати
     для створення Manufacturer
    і для створення конфігурацій.
    Конфігурації нам потрібні для того щоб в адміністратора була заготовка.Наприклад в
     нього є новий товар
    і параметри цього товару такі самі як параметри якогось іншого товарі.
    І при створенні нового товару
     адміністратор може просто обрати вже існуючу конфігурацію.
     */

    @PostMapping("/colors")
    public ResponseEntity<ColorResponseDto> createColor(@RequestBody
                                                        @Valid ColorRequestDto colorRequestDto) {
        ColorResponseDto createdColor = colorService.createColor(colorRequestDto);
        return new ResponseEntity<>(createdColor, HttpStatus.CREATED);
    }

    @GetMapping("/colors/{id}")
    public ColorResponseDto getColorById(@PathVariable Long id) {
        return colorService.getColorById(id);
    }

    @PostMapping("/materials")
    public ResponseEntity<MaterialResponseDto> createMaterial(
            @RequestBody
            @Valid MaterialRequestDto materialRequestDto) {
        MaterialResponseDto createdMaterial = materialService.createMaterial(materialRequestDto);
        return new ResponseEntity<>(createdMaterial, HttpStatus.CREATED);
    }

    @GetMapping("/materials/{id}")
    public MaterialResponseDto getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id);
    }

    @PostMapping("/cylinders")
    public ResponseEntity<CylinderResponseDto> createCylinder(
            @RequestBody
            @Valid CylinderRequestDto cylinderRequestDto) {
        CylinderResponseDto createdCylinder = cylinderService.createCylinder(cylinderRequestDto);
        return new ResponseEntity<>(createdCylinder, HttpStatus.CREATED);
    }

    @GetMapping("/cylinder/{id}")
    public CylinderResponseDto getCylinderById(@PathVariable Long id) {
        return cylinderService.getCylinderById(id);
    }

    @PostMapping("/degrees")
    public ResponseEntity<DegreeResponseDto> createDegree(
            @RequestBody
            @Valid DegreeRequestDto degreeRequestDto) {
        DegreeResponseDto createdDegree = degreeService.createDegree(degreeRequestDto);
        return new ResponseEntity<>(createdDegree, HttpStatus.CREATED);
    }

    @GetMapping("/degrees/{id}")
    public DegreeResponseDto getDegreeById(@PathVariable Long id) {
        return degreeService.getDegreeById(id);
    }

    @PostMapping("/diopters")
    public ResponseEntity<DiopterResponseDto> createDiopter(
            @RequestBody
            @Valid DiopterRequestDto diopterRequestDto) {
        DiopterResponseDto createdDiopter = diopterService.createDiopter(diopterRequestDto);
        return new ResponseEntity<>(createdDiopter, HttpStatus.CREATED);
    }

    @GetMapping("/diopters/{id}")
    public DiopterResponseDto getDiopterById(@PathVariable Long id) {
        return diopterService.getDiopterById(id);
    }

    @PostMapping("/spheres")
    public ResponseEntity<SphereResponseDto> createSphere(
            @RequestBody
            @Valid SphereRequestDto sphereRequestDto) {
        SphereResponseDto createdSphere = sphereService.createSphere(sphereRequestDto);
        return new ResponseEntity<>(createdSphere, HttpStatus.CREATED);
    }

    @GetMapping("/spheres/{id}")
    public SphereResponseDto getSphereById(@PathVariable Long id) {
        return sphereService.getSphereById(id);
    }
}
