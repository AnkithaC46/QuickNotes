package com.ankithac.NotesApp.Controller;

import com.ankithac.NotesApp.Dto.NotesDto;
import com.ankithac.NotesApp.Service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NotesController {

    private  final NotesService notesService;

    @PostMapping
    public ResponseEntity<NotesDto> addNotes(@RequestBody NotesDto notesDto){
        NotesDto notes=notesService.createNote(notesDto);
        return ResponseEntity.ok(notes);
    }
    @GetMapping
    public ResponseEntity<List<NotesDto>> getNotes(){
        List<NotesDto> notes=notesService.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotesDto> updateNotes(@PathVariable  Long id,@RequestBody NotesDto notesDto){
        NotesDto notes=notesService.updateNote(id,notesDto);
        return ResponseEntity.ok(notes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotes(@PathVariable Long id){
        notesService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<NotesDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(notesService.getNoteById(id));
    }
    @PostMapping("/{id}/share")
    public ResponseEntity<String> share(@PathVariable Long id) {
        return ResponseEntity.ok(notesService.generateShareLink(id));
    }
    @GetMapping("/shared/{token}")
    public ResponseEntity<NotesDto> getShared(@PathVariable String token) {
        NotesDto dto=notesService.getNoteByShareToken(token);
        return ResponseEntity.ok(dto);
    }


}
