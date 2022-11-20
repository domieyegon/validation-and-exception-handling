package com.unify.validation.web.rest;

import com.unify.validation.service.UserService;
import com.unify.validation.service.dto.UserDTO;
import com.unify.validation.web.rest.errors.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserResource {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDTO) throws URISyntaxException {
        log.info("REST request to save user: {}", userDTO);

        UserDTO result =  userService.save(userDTO);
        return ResponseEntity.created(new URI("/api/users/"+ result.getId())).body(result);
    }

    @GetMapping("/users")
    public List<UserDTO> findAll() {
        log.info("REST request to get all users");
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> findOne(@PathVariable Long id) {
        log.info("REST request to get user by id: {}", id);

        return ResponseEntity.ok().body(userService.findById(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);

        return ResponseEntity.ok().headers(new HttpHeaders()).build();
    }

}
