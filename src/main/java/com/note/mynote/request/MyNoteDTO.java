package com.note.mynote.request;

import com.note.mynote.dto.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyNoteDTO {
    private Long id;
    private String title;
    private String content;
    private boolean done;
    private Date createdAt;
    private List<TagDTO> tags;
}