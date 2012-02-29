package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import play.db.jpa.Model;

@SuppressWarnings("serial")
@Entity
public class Project extends Model {

	@Column(nullable = false)
	public String moduleName;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	public User owner;
	
	@Enumerated(EnumType.STRING)
	public ProjectStatus status;
	
	public String license;
	public String role;
	public String description;
	public String motivation;
	public String url;
	
	@OrderBy("date")
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
	public List<Comment> comments = new ArrayList<Comment>();
	
	public boolean canBeAccepted(){
		return status != ProjectStatus.CONFIRMED;
	}
	public boolean canBeRejected(){
		return status != ProjectStatus.REJECTED;
	}
	
	@Transient
	public Module getModule(){
		return Module.findByName(moduleName);
	}
}
