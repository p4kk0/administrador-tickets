package mx.servis.admonticket.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mx.servis.admonticket.persistence.AbstractAuditingEntity;

@Entity
@Table(name = "story_attachment")
public class StoryAttachment extends AbstractAuditingEntity{
	
	@Id
	@GeneratedValue
	@Column(name="id_story_attachment", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 200)
	@Column(length = 200, unique = true, nullable = false)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 500)
	@Column(length = 500, unique = true, nullable = false)
	private String path;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_story", referencedColumnName="id_story", nullable = false)
	private Story story;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}
	
	

}
