package ma.optimum.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.optimum.ppmtool.exceptions.ProjectIdException;
import ma.optimum.ppmtool.models.Project;
import ma.optimum.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdate(Project project) {

		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);

		} catch (Exception e) {
			throw new ProjectIdException(
					"Project with ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}

	}

	public Project findProjectByIdentifier(String projectId) {

		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

		if (project == null) {
			throw new ProjectIdException("Project with ID '" + projectId.toUpperCase() + "' doesn't exist");
		}

		return project;
	}
	
	
	public Iterable<Project> findAllProjects(){
		
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Cannot delete Project with ID '" + projectId.toUpperCase() + "', because it doesn't exist.");
		}
		
		projectRepository.delete(project);
		
	}

}
