package com.note.mynote;

import com.note.mynote.dto.MyNoteDTO;
import com.note.mynote.entity.MyNoteEntity;
import com.note.mynote.request.MyNoteRequest;
import com.note.mynote.service.MyNoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class MyNoteController {
    private MyNoteService myNoteService;

    public MyNoteController(MyNoteService myNoteService) {
        this.myNoteService = myNoteService;
    }

    @GetMapping("/list")
    List<MyNoteEntity> findAll() {
        log.info("get all");
        return myNoteService.findAll();
    }

    @PostMapping("/mynote")
    ResponseEntity<MyNoteRequest> addNoteAndTag(@RequestBody MyNoteRequest noteRequest) {
        MyNoteRequest save = myNoteService.save(noteRequest);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/mynote/{id}")
    MyNoteDTO getNote(@PathVariable Long id) {
        return myNoteService.findById(id);
    }

    @PutMapping("/mynote/{id}")
    ResponseEntity<Void> updateNote(@PathVariable Long id, @RequestBody MyNoteRequest noteRequest) {
        myNoteService.update(id, noteRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/mynote/{id}")
    public String deleteNote(@PathVariable Long id) {
        myNoteService.delete(id);
        return "delete successfully";
    }
}
