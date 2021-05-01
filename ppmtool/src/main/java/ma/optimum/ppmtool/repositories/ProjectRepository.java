package ma.optimum.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.optimum.ppmtool.models.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	Project findByProjectIdentifier(String projectIdentifier);

	@Override
	Iterable<Project> findAll();
}
