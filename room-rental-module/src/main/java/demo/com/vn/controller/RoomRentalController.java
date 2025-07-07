package demo.com.vn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room-rental")
@RequiredArgsConstructor
public class RoomRentalController {

    @GetMapping("/get-all")
    public ResponseEntity<String> getAllBuildingInfo() {
        return ResponseEntity.ok("OK");
    }
}
