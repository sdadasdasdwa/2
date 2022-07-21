package com.liujinhang.demo.repository.jpa;

import com.liujinhang.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RepositoryRestResource(
        path = "users",
        collectionResourceRel = "users",
        itemResourceRel = "user")
public interface UserRepository extends JpaRepository<User, Integer> {

    @RestResource(rel="byName", path="byName")
    public List<User> findUserByName(@Param("name")String name);

    @RestResource(rel="byKeyword", path="byKeyword")
    @Query(value = "select id, name, department_id from user where name like %:keyword%", nativeQuery = true)
    public List<User> findUserByKeyword(@Param("keyword")String keyword);

    @RestResource(rel="byKeywordUsingJPQL", path="byKeywordUsingJPQL")
    @Query(value = "select user from User user where user.name like %:keyword%")
    public List<User> findUserByKeywordUsingJPQL(@Param("keyword")String keyword);

    @RestResource(rel="byPrefix", path="byPrefix")
    @Query(value =
            "select case when (locate(user.name, :prefix) = 0) then ('true') else ('false') end from User user")
    public String[] findUserByNameWithPrefix(@Param("prefix")String prefix);

}
