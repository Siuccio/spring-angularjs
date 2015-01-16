package it.spring.web.model;

import java.util.Collection;

public class ResponseTask {

	private Collection<Task> taskConclude,taskAssignment,taskTake;
	private Long taskConcludeCount,taskAssignmentCount,taskTakeCount;
	public Collection<Task> getTaskConclude() {
		return taskConclude;
	}
	public void setTaskConclude(Collection<Task> taskConclude) {
		this.taskConclude = taskConclude;
	}
	public Collection<Task> getTaskAssignment() {
		return taskAssignment;
	}
	public void setTaskAssignment(Collection<Task> taskAssignment) {
		this.taskAssignment = taskAssignment;
	}
	public Collection<Task> getTaskTake() {
		return taskTake;
	}
	public void setTaskTake(Collection<Task> taskTake) {
		this.taskTake = taskTake;
	}
	public Long getTaskConcludeCount() {
		return taskConcludeCount;
	}
	public void setTaskConcludeCount(Long taskConcludeCount) {
		this.taskConcludeCount = taskConcludeCount;
	}
	public Long getTaskAssignmentCount() {
		return taskAssignmentCount;
	}
	public void setTaskAssignmentCount(Long taskAssignmentCount) {
		this.taskAssignmentCount = taskAssignmentCount;
	}
	public Long getTaskTakeCount() {
		return taskTakeCount;
	}
	public void setTaskTakeCount(Long taskTakeCount) {
		this.taskTakeCount = taskTakeCount;
	}

	
	
	
}
