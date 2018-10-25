package mx.servis.admonticket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.servis.admonticket.respository.StoryRepository;
import mx.servis.admonticket.service.StoryService;

@Service
public class StoryServiceImpl implements StoryService{
	
	@Autowired
	private StoryRepository storyRepository;
}
