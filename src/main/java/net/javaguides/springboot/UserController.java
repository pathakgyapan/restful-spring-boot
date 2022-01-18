
package net.javaguides.springboot;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.fetchUsers(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<User> postUser(@RequestBody User user) {
        return new ResponseEntity<>(
            userService.saveUser(user),
            HttpStatus.CREATED
        );
    }

    /* get single user object by id */
    @GetMapping("{id}")
    public ResponseEntity<User> getById(
        @PathVariable(value = "id", required = true) int id
    ) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<User> getByEmail(
        @PathVariable(value = "email", required = true) String email
    ) {
        return new ResponseEntity<>(
            userService.getByEmail(email),
            HttpStatus.OK
        );
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Page<User>> getByName(
        @PathVariable(value = "name", required = true) String name,
        @PageableDefault(
            page = 0,
            size = 10,
            sort = "id",
            direction = Sort.Direction.DESC
        ) Pageable pageable
    ) {
        return new ResponseEntity<>(
            userService.getByName(name, pageable),
            HttpStatus.OK
        );
    }

    /* update user object by id */
    @PutMapping("{id}")
    public ResponseEntity<User> putDemo(
        @PathVariable(value = "id") int id,
        @RequestBody User user
    ) {
        return new ResponseEntity<>(
            userService.updateUser(id, user),
            HttpStatus.OK
        );
    }

    /* delete user object by id */
    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteDemo(@PathVariable(value = "id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
