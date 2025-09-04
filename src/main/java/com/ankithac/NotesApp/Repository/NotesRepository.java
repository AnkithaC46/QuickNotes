package com.ankithac.NotesApp.Repository;

import com.ankithac.NotesApp.Entity.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotesRepository extends JpaRepository<NotesEntity,Long> {


    Optional<NotesEntity> findByShareToken(String shareToken);

}
