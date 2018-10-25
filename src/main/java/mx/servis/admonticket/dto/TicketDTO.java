package mx.servis.admonticket.dto;

import java.io.Serializable;
import java.util.Date;



public class TicketDTO implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	public static void main(String[] args) {
//		Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
//		TicketDTO newTicket = new TicketDTO();
//		newTicket.setAsignedUserId(1l);
//		newTicket.setName("Primer ticket");
//		newTicket.setComment("Primer comentario Ticket");
//		newTicket.setEnvironmentId(1l);
//		newTicket.setPriorityId(2l);
//		newTicket.setProjectId(2l);
//		newTicket.setReference(null);
//		newTicket.setSchemaId(3l);
//		newTicket.setStatusId(1l);
//		newTicket.setTechnologyId(1l);
//		newTicket.setTime(4l);
//		newTicket.setUnitTimeId(2l);
//		
//		System.out.println(gson.toJson(newTicket));
//		
//	}
	
	private Long id;
	private String name;
	private String comment;
	private String newComment;
	private Long reference;
	private Long time;
	private Long asignedUserId;
	private String asignedUserLogin;
	private String asignedUserImageUrl;
	private Long priorityId;
	private String priorityName;
	private Long environmentId;
	private String environmentName;
	private Long technologyId;
	private String technologyName;
	private Long statusId;
	private String  statusName;
	private Long schemaId;
	private String  schemaName;
	private Long projectId;
	private String  projectName;
	private Long unitTimeId;
	private String  unitTimeName;
//	private List<TicketAttachmentDTO> attachments;
//	private List<StoryDTO> stories;
	
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getNewComment() {
		return newComment;
	}

	public void setNewComment(String newComment) {
		this.newComment = newComment;
	}

	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getAsignedUserId() {
		return asignedUserId;
	}

	public void setAsignedUserId(Long asignedUserId) {
		this.asignedUserId = asignedUserId;
	}
	
	public String getAsignedUserImageUrl() {
		return asignedUserImageUrl;
	}

	public void setAsignedUserImageUrl(String asignedUserImageUrl) {
		this.asignedUserImageUrl = asignedUserImageUrl;
	}

	public Long getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Long priorityId) {
		this.priorityId = priorityId;
	}

	public Long getEnvironmentId() {
		return environmentId;
	}

	public void setEnvironmentId(Long environmentId) {
		this.environmentId = environmentId;
	}

	public Long getTechnologyId() {
		return technologyId;
	}

	public void setTechnologyId(Long technologyId) {
		this.technologyId = technologyId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getSchemaId() {
		return schemaId;
	}

	public void setSchemaId(Long schemaId) {
		this.schemaId = schemaId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getUnitTimeId() {
		return unitTimeId;
	}

	public void setUnitTimeId(Long unitTimeId) {
		this.unitTimeId = unitTimeId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getAsignedUserLogin() {
		return asignedUserLogin;
	}

	public void setAsignedUserLogin(String asignedUserLogin) {
		this.asignedUserLogin = asignedUserLogin;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public String getEnvironmentName() {
		return environmentName;
	}

	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}

	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUnitTimeName() {
		return unitTimeName;
	}

	public void setUnitTimeName(String unitTimeName) {
		this.unitTimeName = unitTimeName;
	}
	

//	public List<TicketAttachmentDTO> getAttachments() {
//		return attachments;
//	}
//
//	public void setAttachments(List<TicketAttachmentDTO> attachments) {
//		this.attachments = attachments;
//	}
//
//	public List<StoryDTO> getStories() {
//		return stories;
//	}
//
//	public void setStories(List<StoryDTO> stories) {
//		this.stories = stories;
//	}
	
//	private List<Attached> attached;
	
	

}
