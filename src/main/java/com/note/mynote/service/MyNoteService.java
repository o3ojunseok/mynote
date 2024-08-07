package com.note.mynote.service;

import com.note.exception.MyNoteNotFoundException;
import com.note.mynote.dto.MyNoteDTO;
import com.note.mynote.dto.TagDTO;
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
import java.util.Optional;

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

    public MyNoteDTO findById(Long id) {
        MyNoteEntity entity = myNoteRepository.findById(id).orElseThrow(() -> new MyNoteNotFoundException(id));
        MyNoteDTO myNoteDTO;
        TagDTO tagDTO;
        List<TagDTO> tagList = new ArrayList<>();

        myNoteDTO = new MyNoteDTO(entity.getId(), entity.getTitle(), entity.getContent(), entity.isDone(), entity.getCreatedAt());

        List<TagEntity> tags = entity.getTag();

        if (tags != null) {
            for (TagEntity tag : tags) {
                tagDTO = new TagDTO(tag.getId(), tag.getMyTagName(), tag.isDeleted());
                tagList.add(tagDTO);
            }
            myNoteDTO.setTags(tagList);
        }

        return myNoteDTO;
    }

    public void update(Long id, MyNoteRequest myNoteRequest) {
        MyNoteEntity entity = myNoteRepository.findById(id).orElseThrow(() -> new MyNoteNotFoundException(id));
        entity.setTitle(myNoteRequest.getTitle());
        entity.setContent(myNoteRequest.getContent());
        entity.setDone(myNoteRequest.isDone());

        for (TagRequest tagRequest : myNoteRequest.getTag()) {
            Optional<TagEntity> tagEntityOptional = entity.getTag().stream()
                    .filter(t -> t.getId().equals(tagRequest.getId()))
                    .findFirst();

            if (tagEntityOptional.isPresent()) {
                TagEntity tag = tagEntityOptional.get();
                tag.setMyTagName(tagRequest.getTagName());
                tag.setDeleted(tagRequest.isDeleted());
            } else {
                TagEntity tag = new TagEntity();
                tag.setMyTagName(tagRequest.getTagName());
                tag.setMyNote(entity);
                entity.getTag().add(tag);
            }
        }
        myNoteRepository.save(entity);
    }

    public void delete(Long id) {
        myNoteRepository.deleteById(id);
    }

    public List<TagEntity> findMyTag() {
        return tagRepository.findAll();
    }
}
