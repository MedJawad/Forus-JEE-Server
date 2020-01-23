package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="TableUser")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Expose private int id;

	@Column(unique = true,length = 30)
	@Expose private String username;

	@Column
	@Expose private String email;
	
	@Column
	private String password;

	@Expose private int nbrOfPosts=0;

	@OneToMany(mappedBy = "user")
	private List<Post> posts = new ArrayList<>();

	@OneToMany(mappedBy = "admin")
	private List<Group> ownedGroups = new ArrayList<>();
	
	@ManyToMany(mappedBy = "members")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Group> groups = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNbrOfPosts() {
		return nbrOfPosts;
	}

	public void setNbrOfPosts(int nbrOfPosts) {
		this.nbrOfPosts = nbrOfPosts;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public void addPost(Post post) {
		this.posts.add(post);
	}
	public void removePost(Post post) {
		this.posts.remove(post);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Group> getOwnedGroups() {
		return ownedGroups;
	}

	public void setOwnedGroups(List<Group> ownedGroups) {
		this.ownedGroups = ownedGroups;
	}
	public void addOwnedGroup(Group group) {
		this.ownedGroups.add(group);
	}
	public void removeOwnedGroup(Group group) {
		this.ownedGroups.remove(group);
	}
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public void addGroup(Group group) {
		this.groups.add(group);
	}
	public void removeGroup(Group group) {
		this.groups.remove(group);
	}
	
	
}
