package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="TableGroup")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose private int id;

	@Column
	@Expose private String name;

	@Column
	@Lob
	@Expose private String description;

	@Column
	@Expose private Date dateOfCreation;

	@ManyToOne
	@Expose private User admin;

	@ManyToMany
	private List<User> members = new ArrayList<>();
	
	@OneToMany
	private List<Post> posts = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public void addMember(User u) {
		this.members.add(u);
	}
	public void removeMember(User u) {
		this.members.remove(u);
	}
	
	
	
}
