package com.note.mynote;

import com.note.mynote.entity.MyNoteEntity;
import com.note.mynote.service.MyNoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
}
