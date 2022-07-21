package com.liujinhang.demo.repository.jpa;

import com.liujinhang.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(
        path = "departments",
        collectionResourceRel = "departments",
        itemResourceRel = "department")
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @RestResource(rel="byName", path="byName")
    List<Department> findUserByName(@Param("name")String name);

}
