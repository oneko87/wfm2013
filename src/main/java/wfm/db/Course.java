package wfm.db;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
@Named
@RequestScoped
public class Course implements Serializable {

	private static final long serialVersionUID = -2556217790431280277L;

	@Id
	@GeneratedValue
	private int course_nr;

	private String name; // course name
	private String desc; // course description
	private String trainer; // who is responsible for the course
	private int maxMembers; // how many
	private String memberType; // bronze, silver or gold course
	private int rewardPoints; //accounts for the number of reward points user receive when participating
	private boolean indoor; // true if indoor course, false if outdoor
	private Date date;
	private String location;
	
	@OneToMany(mappedBy = "pk.course",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<USER_COURSE> userCourse = new HashSet<USER_COURSE>();
	
	/*@ManyToMany(fetch = FetchType.EAGER, mappedBy="courses")
	private Set<ACT_ID_USER> users = new HashSet<ACT_ID_USER>();
	
	
	public Set<ACT_ID_USER> getUsers() {
		return users;
	}
	public void setUsers(Set<ACT_ID_USER> users) {
		this.users = users;
	}*/
	
	public int getRewardPoints() {
		return this.rewardPoints;
	}
	
	public Set<USER_COURSE> getUserCourse() {
		return userCourse;
	}

	public void setUserCourse(Set<USER_COURSE> userCourse) {
		this.userCourse = userCourse;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
	public int getCourse_nr() {
		return course_nr;
	}
	public void setCourse_nr(int course_nr) {
		this.course_nr = course_nr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public int getMaxMembers() {
		return maxMembers;
	}
	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public boolean isIndoor() {
		return indoor;
	}
	public void setIndoor(boolean indoor) {
		this.indoor = indoor;
	}
	
	@Override
	public String toString() {
		return "Course [course_nr=" + course_nr + ", name=" + name + ", desc="
				+ desc + ", trainer=" + trainer + ", maxMembers=" + maxMembers
				+ ", memberType=" + memberType + ", indoor=" + indoor + "]";
	}
	public Date getDate() {
		
		
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	


}
