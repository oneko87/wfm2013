package wfm.task;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.activiti.cdi.BusinessProcess;
import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wfm.bean.User;
import wfm.db.ACT_ID_USER;
import wfm.db.Course;
import wfm.db.USER_COURSE;
import wfm.db.USER_COURSE_ID;

@Stateful
@Named
@ConversationScoped
public class deleteCourseTask {

	private static final Logger log = LoggerFactory.getLogger(LoginTask.class);

	@Inject
	private BusinessProcess businessProcess;

	@Inject
	private Course course;

	@Inject
	private User user;
	
	@Inject
	RuntimeService runtimeService;

	@PersistenceContext
	private EntityManager entityManager;

	private String courseName;

	public void deleteCourse(String taskId, int id) {

		businessProcess.startTask(taskId);

		// delete from database:

		try{
			log.info("Searching course with Id: "+id);
			course = entityManager.find(Course.class, id);
			log.info("Course found: " + course.toString());
			courseName = course.getName();	
			
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("deletedCourseName", courseName);
			
			for(USER_COURSE uc : course.getUserCourse()) {
				String executionId = runtimeService.createExecutionQuery().processInstanceId(uc.getProcessinstanceID()).orderByProcessInstanceId().desc().list().get(0).getId();
				log.info("notifying process " + uc.getProcessinstanceID() + "  with executionId-> " + executionId);
				
				runtimeService.messageEventReceived("courseCancelled", executionId, variables);
			}
		
			
			log.info("remove course_nr = "+course.getCourse_nr());
			Query q = entityManager
					.createNativeQuery("DELETE  FROM USER_COURSE uc WHERE uc.COURSE_NR ="
							+ course.getCourse_nr());
			q.executeUpdate();


			entityManager.remove(course);		  

			log.info("Course deleted successfully...");

		}catch(Exception e){
			log.error(e.getMessage());
		}
		
		businessProcess.completeTask();
		

		//variables for messages
		businessProcess.setVariable("courseAction", "deleted");
		businessProcess.setVariable("courseFromAction", courseName);
	}

	public void cancel(String taskId) {			    
	//	businessProcess.startTask(taskId);
		businessProcess.setVariable("routeAction", "cancel");
		businessProcess.completeTask();
		businessProcess.setVariable("courseAction", "cancelled");
		
	}


}
