package com.techprime.model;

public class DashboardData {
	private Integer totalProjects;
	private Integer closedProjects;
	private Integer runningProjects;
	private Integer closureDelay;
	private Integer cancelledProjects;

	
	public DashboardData(Integer totalProjects, Integer closedProjects, Integer runningProjects, Integer closureDelay,
			Integer cancelledProjects) {
		super();
		this.totalProjects = totalProjects;
		this.closedProjects = closedProjects;
		this.runningProjects = runningProjects;
		this.closureDelay = closureDelay;
		this.cancelledProjects = cancelledProjects;
	}

	public DashboardData() {
		
	}

	public Integer getTotalProjects() {
		return totalProjects;
	}
	public void setTotalProjects(Integer totalProjects) {
		this.totalProjects = totalProjects;
	}
	public Integer getClosedProjects() {
		return closedProjects;
	}
	public void setClosedProjects(Integer closedProjects) {
		this.closedProjects = closedProjects;
	}
	public Integer getRunningProjects() {
		return runningProjects;
	}
	public void setRunningProjects(Integer runningProjects) {
		this.runningProjects = runningProjects;
	}
	public Integer getClosureDelay() {
		return closureDelay;
	}
	public void setClosureDelay(Integer closureDelay) {
		this.closureDelay = closureDelay;
	}
	public Integer getCancelledProjects() {
		return cancelledProjects;
	}
	public void setCancelledProjects(Integer cancelledProjects) {
		this.cancelledProjects = cancelledProjects;
	}

	
}
