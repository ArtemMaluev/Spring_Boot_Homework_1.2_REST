package maluevartem.springboot_rest.controller;

import maluevartem.springboot_rest.model.Authorities;
import maluevartem.springboot_rest.model.User;
import maluevartem.springboot_rest.service.AuthorizationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AuthorizationController {

    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @PostMapping("/authorize")
    public List<Authorities> getAuthorities(@Valid User user) {
        System.out.format("[User: %s, password: %s] ", user.getName(), user.getPassword());
        return service.getAuthorities(user);
    }
}
