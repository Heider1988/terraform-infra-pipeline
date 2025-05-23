package controller;

import dto.response.TimeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class TesteController {

    @GetMapping("/time")
    public ResponseEntity<TimeResponse> gettime() {

        LocalDateTime brasLocalDateTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

        String formatedTime = brasLocalDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        return ResponseEntity.ok(new TimeResponse(formatedTime));
    }

}
