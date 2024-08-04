package com.note.mynote.service;

import com.note.mynote.entity.MyNoteEntity;
import com.note.mynote.entity.TagEntity;
import com.note.mynote.repository.MyNoteRepository;
import com.note.mynote.repository.TagRepository;
import com.note.mynote.request.MyNoteRequest;
import com.note.mynote.request.TagRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    private MyNoteEntity toEntity(MyNoteRequest request) {
        MyNoteEntity entity = new MyNoteEntity();
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setCreatedAt(new Date());
        entity.setDone(request.isDone());

        return entity;
    }

    public MyNoteRequest save(MyNoteRequest noteRequest) {
        MyNoteEntity entity = toEntity(noteRequest);
        List<TagEntity> tagList = new ArrayList<>();
        if (!noteRequest.getTag().isEmpty()) {

            for (TagRequest t : noteRequest.getTag()) {
                TagEntity tag = new TagEntity();
                tag.setMyTagName(t.getTagName());
                tag.setMyNote(entity);
                tagList.add(tag);
            }
            entity.setTag(tagList);
        }
        ModelMapper modelMapper = new ModelMapper();
        MyNoteEntity save = myNoteRepository.save(entity);
        return modelMapper.map(save, MyNoteRequest.class);
    }
}
