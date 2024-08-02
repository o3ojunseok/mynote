package com.note.mynote.service;

import com.note.mynote.entity.MyNoteEntity;
import com.note.mynote.repository.MyNoteRepository;
import com.note.mynote.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MyNoteService {

    private final MyNoteRepository myNoteRepository;
    private final TagRepository tagRepository;

    public MyNoteService(MyNoteRepository myNoteRepository, TagRepository tagRepository) {
        this.myNoteRepository = myNoteRepository;
        this.tagRepository = tagRepository;
    }

    public List<MyNoteEntity> findAll() {
        return myNoteRepository.findAll();
    }
}
