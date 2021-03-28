package wg.app.resourceserver.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import wg.app.resourceserver.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/status")
    public String status() {
        return "Working...";
    }

    //@Secured("ROLE_developer")
    @PreAuthorize("hasRole('developer')") // "hasAuthority('ROLE_developer') or #id == #jwt.subject"
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "Deleted user with id " + id + " and JWT subject " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return new UserRest("John", "Doe", "");
    }
}
