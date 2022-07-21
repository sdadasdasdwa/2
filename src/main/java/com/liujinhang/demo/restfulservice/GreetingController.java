package com.liujinhang.demo.restfulservice;

import com.liujinhang.demo.entity.User;
import com.liujinhang.demo.logic.GreetingLogicInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GreetingController {

    @Autowired
    // @Qualifier("englishGreeting")
    public GreetingLogicInterface GreetingLogic = null;

    @Autowired
    public JpaRepository<User, Integer> japRepository = null;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        // User user = new User();
        // user.setName("liu");
        // japRepository.save(user);

        // Optional<User> userOptional = japRepository.findById(1);
        // userOptional.ifPresent(user -> System.out.println(user.getName()));

        return this.GreetingLogic.sayGreeting(name);
    }
}
