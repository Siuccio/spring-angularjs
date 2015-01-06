package it.spring.web.repository;

import java.util.Collection;

import it.spring.web.model.Fase;
import it.spring.web.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    
	 public final static String FIND_NOT_ASSIGMENT = "SELECT t " + 
             "FROM Task t " +
             "WHERE t.fase = :fase";
	 @Query(FIND_NOT_ASSIGMENT)
	public Collection<Task> findNotAssigment(@Param("fase") Fase fase);
}