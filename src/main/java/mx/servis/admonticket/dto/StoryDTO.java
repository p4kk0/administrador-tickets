package mx.servis.admonticket.dto;

import java.util.List;

public class StoryDTO {

	private Long id;
	private String comment;
	private Long ticketId;
	private Long asignedUserId;
	private Long statusId;
	private List<StoryAttachmentDTO> attachments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getAsignedUserId() {
		return asignedUserId;
	}

	public void setAsignedUserId(Long asignedUserId) {
		this.asignedUserId = asignedUserId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public List<StoryAttachmentDTO> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<StoryAttachmentDTO> attachments) {
		this.attachments = attachments;
	}

}
