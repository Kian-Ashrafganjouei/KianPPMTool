package com.ashrafganjouei.kian.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashrafganjouei.kian.domain.Project;

@Repository
//Interface extends CRUD  (Create, Read, Update, and Delete). Only accepts type of project and Long (64-bit signed integer.)
public interface ProjectRepository extends CrudRepository<Project, Long> {

	Project findByProjectIdentifier(String projectId);
	Iterable<Project> findAll();
}
