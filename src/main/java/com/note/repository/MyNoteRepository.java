package com.note.repository;
import com.note.mynote.entity.MyNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyNoteRepository extends JpaRepository<MyNoteEntity, Long> {
}