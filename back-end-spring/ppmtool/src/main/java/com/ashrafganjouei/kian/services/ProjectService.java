 package com.ashrafganjouei.kian.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashrafganjouei.kian.domain.Project;
import com.ashrafganjouei.kian.exceptions.ProjectIdException;
import com.ashrafganjouei.kian.repositories.ProjectRepository;

//This is the service (business logic)
@Service
public class ProjectService {
	
	//The @Autowired allows us to inject the ProjectRepository dependency without XML configuration so that we can perform CRUD  (Create, Read, Update, and Delete) on Project Entity
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}
		catch (Exception e) {
			throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists. This ID needs to be unique.");
		}
		
		
	}
	
	public Project findProjectByIdentifier(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project==null) {
			throw new ProjectIdException("Project ID '"+projectId.toUpperCase()+"' does not exist.");
		}
		
		return project;
	}
	public Iterable<Project> findAllProject(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project==null) {
			throw new ProjectIdException("Project ID '"+projectId.toUpperCase()+"' does not exist.");
		}
		projectRepository.delete(project);
	}
}
