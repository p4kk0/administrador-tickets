package mx.servis.admonticket.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.servis.admonticket.persistence.model.Story;
import mx.servis.admonticket.persistence.model.Ticket;

@Repository("storyRepository")
public interface StoryRepository extends JpaRepository<Ticket, Long>{

	void saveAndFlush(Story story);

}
