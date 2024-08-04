package com.note.mynote;

import com.note.mynote.entity.MyNoteEntity;
import com.note.mynote.request.MyNoteRequest;
import com.note.mynote.service.MyNoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
