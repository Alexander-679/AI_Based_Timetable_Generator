package com.timetable.controller;

import com.timetable.dto.TimetableInputDTO;
import com.timetable.model.Timetable;
import com.timetable.service.TimetableService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.timetable.util.TimetablePdfGenerator;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
@CrossOrigin(origins = "*")
public class TimetableController {

    private final TimetableService timetableService;

    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @PostMapping("/input")
    public String saveInputAndGenerate(@RequestBody TimetableInputDTO input) {

        timetableService.saveUserInput(input);
        timetableService.generateTimetable();

        return "Timetable generated successfully";
    }

    @GetMapping
    public List<Timetable> getTimetable() {
        return timetableService.getAllTimetables();
    }
    @GetMapping("/download")
public ResponseEntity<byte[]> downloadPdf() {

    List<Timetable> timetables = timetableService.getAllTimetables();
    byte[] pdf = TimetablePdfGenerator.generatePdf(timetables);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=timetable.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdf);
}
}
