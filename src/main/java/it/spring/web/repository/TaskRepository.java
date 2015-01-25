package it.spring.web.repository;

import java.util.Collection;

import it.spring.web.model.Fase;
import it.spring.web.model.Task;
import it.spring.web.model.Users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    
	 public final static String FIND_NOT_ASSIGMENT = "SELECT t FROM Task t WHERE t.fase = :fase";
	 
	 
	
	//public Collection<Task> findByFase(Fase fase);
	
		@Query("SELECT t FROM Task t WHERE t.id = :id ")
		public Task findWithId(@Param("id") Integer id);
	 
	@Query("SELECT count(t) FROM Task t WHERE t.fase = :fase and users=:user")
	public Long countByFase(@Param("fase") Fase fase,@Param("user") Users user);
	 
	 @Query("SELECT t FROM Task t WHERE t.fase = :fase and users=:user")
	public Collection<Task> findTaskUserWithFase(@Param("fase") Fase fase,@Param("user") Users user); 
	 
	 @Query("SELECT t FROM Task t WHERE t.fase = :fase and users=:user")
		public Page<Task> findTaskUserWithFasePage(@Param("fase") Fase fase,@Param("user") Users user,Pageable pageable); 
	 
	 public Page<Task> findByFase(Fase fase,Pageable pageable);
	 
}