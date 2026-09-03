package com.rookies6.myspringboot4project.controller;

import com.rookies6.myspringboot4project.entity.User;
import com.rookies6.myspringboot4project.exception.BusinessException;
import com.rookies6.myspringboot4project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/users") // @PostMapping -> @RequestMapping 으로 변경
@RequiredArgsConstructor
public class UserRestController {

    private final UserRepository userRepository; // Lombok @RequiredArgsConstructor 사용 시 final 권장 (@Autowired 불필요)

    @PostMapping
    public User createUser(@RequestBody User userDetail) {
        return userRepository.save(userDetail);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);//Optional<User>
        //orElseThrow(Supplier) Supplier의 추상메서드 () -> T
        User existUser = optionalUser.orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        return existUser;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}