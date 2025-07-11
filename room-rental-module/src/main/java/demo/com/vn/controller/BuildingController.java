package demo.com.vn.controller;

import demo.com.vn.dto.BuildingDTO;
import demo.com.vn.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @GetMapping("/search")
    public ResponseEntity<Page<BuildingDTO>> findBuildings(Pageable pageable) {
        return ResponseEntity.ok(buildingService.findBuildings(pageable));
    }

    @PostMapping("/save")
    public ResponseEntity<BuildingDTO> save(BuildingDTO reqDTO) {
        return ResponseEntity.ok(buildingService.save(reqDTO));
    }
}
