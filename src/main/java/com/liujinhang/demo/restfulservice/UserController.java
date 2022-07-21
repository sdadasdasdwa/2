package com.liujinhang.demo.restfulservice;

import com.liujinhang.demo.entity.Department;
import com.liujinhang.demo.entity.User;
import com.liujinhang.demo.logic.GreetingLogicInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    // @Qualifier("englishGreeting")
    public GreetingLogicInterface GreetingLogic = null;

    @Autowired
    public JpaRepository<User, Integer> userRepository = null;

    @Autowired
    public JpaRepository<Department, Integer> departmentRepository = null;

    @GetMapping(value = "/users/name/{name}")
    public List<User> findUserByName(@PathVariable("name") String name){

        User user = new User();
        user.setName(name);
        Example<User> example = Example.of(user);

        return userRepository.findAll(example);
    }

    /*
    * Post : body里面提交数据
    * */
    @PostMapping(value = "/users/name/{name}/department/{department_id}")
    public User createUserByName(
            @PathVariable("name") String name,
            @PathVariable("department_id") int department_id){

        Optional<Department> departmentOptional = departmentRepository.findById(department_id);

        if(departmentOptional.isPresent()){
            Department department = departmentOptional.get();
            User user = new User();
            user.setName(name);
            user.setDepartment(department);

            user = userRepository.save(user);

            return user;

        }else{
            return null;
        }
    }

    @GetMapping(value = "/users/name/prefix/{prefix}")
    public List<User> findUserByNamePrefix(@PathVariable("prefix") String prefix){

        Query query =
                entityManager.createQuery("select case when (locate(user.name, :prefix) = 0) then ('true') else ('false') end from User user");
        query.setParameter("prefix", prefix);
        List result = query.getResultList();

        return result;
    }

    /*
    * 不太推荐这么设计，用于展示事务
    * */
    @Transactional(rollbackOn = Exception.class)
    @PatchMapping(value = "/users/department/before/{before_department_id}/after/{after_department_id}")
    public List<User> updateUserDepartment(@PathVariable("before_department_id") int before,
                                     @PathVariable("after_department_id") int after){

        User user = new User();
        user.setDepartment(departmentRepository.findById(before).get());
        Example<User> example = Example.of(user);

        List<User> users = userRepository.findAll(example);

        for (User singleUser: users) {

            singleUser.setDepartment(departmentRepository.findById(after).get());
            userRepository.save(singleUser);

        }

        return users;

    }

}
