package wfm.task;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.activiti.cdi.BusinessProcess;

import wfm.bean.User;
import wfm.db.Course;


@Stateful
@Named
@ConversationScoped
public class addCourseTask {

	@Inject
	private BusinessProcess businessProcess;

	@Inject
	private Course course;
	
	@Inject
	private User user;

	@PersistenceContext
	private EntityManager entityManager;

	public void addCourse(String taskId) {
		businessProcess.startTask(taskId);
		
		course.setMaxMembers(10);
		course.setIndoor(true);
		course.setTrainer(user.getUsername());

		System.out.println("Course: " + course.toString());
		// add to database:
		//entityManager.persist(course);
		//entityManager.flush();

		businessProcess.setVariable("approvalAction", "logout");
		businessProcess.completeTask();
	}

}
