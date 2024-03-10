package org.nabiha.mobileapi.users;

import org.nabiha.mobileapi.users.dto.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;
@RestController
public class UsersController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/users")
    public Users greeting(@RequestParam(value = "name", defaultValue = "users") String name) {
        return new Users(counter.incrementAndGet(), String.format(template, name));
    }

}
