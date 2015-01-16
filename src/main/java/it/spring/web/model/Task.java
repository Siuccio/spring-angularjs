package it.spring.web.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;





@Entity
public class Task{

	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE)
	 private int id;
    
    private String title = null;
    private String description = null;
    private Date assignment = null;
    private Date conclusion = null;
    private Double dayWork;

    @Enumerated(EnumType.STRING)
    private Area area;
    @Enumerated(EnumType.STRING)
    private Fase fase;
    
    @Enumerated(EnumType.STRING)
    private Critical criticality;
    
    
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private Users users = null;
    

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAssignment() {
		return assignment;
	}

	public void setAssignment(Date assignment) {
		this.assignment = assignment;
	}

	public Date getConclusion() {
		return conclusion;
	}

	public void setConclusion(Date conclusion) {
		this.conclusion = conclusion;
	}

	public Double getDayWork() {
		return dayWork;
	}

	public void setDayWork(Double dayWork) {
		this.dayWork = dayWork;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	
	
	public Critical getCriticality() {
		return criticality;
	}

	public void setCriticality(Critical criticality) {
		this.criticality = criticality;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description="
				+ description + ", assignment=" + assignment + ", conclusion="
				+ conclusion + ", dayWork=" + dayWork + ", area=" + area
				+ ", fase=" + fase + ", criticality=" + criticality
				+ ", users=" + users + "]";
	}





    
    
}
