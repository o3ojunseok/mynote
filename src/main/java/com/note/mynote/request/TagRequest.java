package com.note.mynote.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TagRequest {
    Long id;
    String tagName;

    boolean deleted;
}