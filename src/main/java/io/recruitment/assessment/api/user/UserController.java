package io.recruitment.assessment.api.user;

import io.recruitment.assessment.api.user.exception.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerNewCustomer(@RequestBody User user) throws EmailExistsException {
        Long id = service.registerCustomer(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).body(service.save(user));
    }

    @PostMapping(value = "/admin/register")
    public ResponseEntity<User> registerNewAdmin(@RequestBody User user) throws EmailExistsException {
        Long id = service.registerAdmin(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).body(service.save(user));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, User user) {
        user.setId(id);
        return ResponseEntity.ok(service.save(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
