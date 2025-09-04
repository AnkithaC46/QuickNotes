package com.ankithac.NotesApp.Service;


import com.ankithac.NotesApp.Dto.NotesDto;
import com.ankithac.NotesApp.Entity.NotesEntity;
import com.ankithac.NotesApp.Repository.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final NotesRepository notesRepository;

    public List<NotesDto> getAllNotes() {
        List<NotesEntity> notes=notesRepository.findAll();
        return notes.stream().map(this::toDto).toList();
    }


    public NotesDto createNote(NotesDto notesDto) {
        NotesEntity entity=toEntity(notesDto);
        NotesEntity newEntity= notesRepository.save(entity);
        return toDto(newEntity);
    }

    public NotesDto updateNote(Long id, NotesDto notesDto) {
        NotesEntity entity=toEntity(notesDto);
        NotesEntity  entity1=notesRepository.findById(id).orElseThrow(
                ()->new RuntimeException("No notes with this id found")
        );
       entity1.setTitle(entity.getTitle());
       entity1.setContent(entity.getContent());
       entity1=notesRepository.save(entity1);
       return toDto(entity1);
    }

    public void deleteNote(Long id) {
        NotesEntity  entity1=notesRepository.findById(id).orElseThrow(
                ()->new RuntimeException("No notes with this id found")
        );
        notesRepository.delete(entity1);

    }
    public NotesDto getNoteById(Long id) {
        return toDto(notesRepository.findById(id).orElseThrow(()->new RuntimeException("No Notes with this id")));
    }
    public String generateShareLink(Long id) {
        NotesEntity note = toEntity(getNoteById(id));
        String token = UUID.randomUUID().toString();
        note.setShareToken(token);
        notesRepository.save(note);
        return token;
    }
    public NotesDto getNoteByShareToken(String token) {
        NotesEntity notes =notesRepository.findByShareToken(token)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        return toDto(notes);
    }


    public NotesDto toDto(NotesEntity notesEntity){
        return NotesDto.builder()
                .id(notesEntity.getId())
                .title(notesEntity.getTitle())
                .content(notesEntity.getContent())
                .createdAt(notesEntity.getCreatedAt())
                .updatedAt(notesEntity.getUpdatedAt())
                .build();
    }

    public NotesEntity toEntity(NotesDto notesDto){
        return NotesEntity.builder()
                .title(notesDto.getTitle())
                .content(notesDto.getContent())
                .build();
    }


}
