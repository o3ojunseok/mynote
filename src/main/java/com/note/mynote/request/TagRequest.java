package com.note.mynote.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

//@Setter
@Getter
@Builder
@AllArgsConstructor
public class TagRequest {
    Long id;
    String tagName;
    boolean deleted;
}
