package th.co.aerothai.nanon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import th.co.aerothai.nanon.dto.UserDTO;
import th.co.aerothai.nanon.model.User;
import th.co.aerothai.nanon.service.UserService;

// REST Controller for managing Users via JSON APIs.
// * Handles HTTP requests relative to the "/api/users" endpoint.
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * GET /api/users?page=1&size=5&sort=name&keyword=nanon
     * - Retrieves the list of all users.
     *
     * @param keyword - Username or Name to search for.
     * @param page    - Page Number
     * @param size    - Page Size
     * @param sort    - Sort Direction (Ascending or Descending)
     * @return 200 OK with a list of User data.
     */
    @GetMapping
    public Page<User> getUsers(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        return userService.findUsers(keyword, page, size, sort, "asc");
    }

    /**
     * GET /api/users/{id}
     * - Retrieves a single user by ID.
     *
     * @param id - The user ID from the URL path.
     * @return 200 OK with User data if found, 404 Not Found if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/users
     * - Creates a new user.
     *
     * @param user - The User data to be created.
     * @return 200 OK with the created User data.
     */
    @PostMapping
    public User createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    /**
     * PUT /api/users/{id}
     * - Updates an existing user.
     *
     * @param id          - The ID of the user to update.
     * @param userDetails - The updated user data from the request body.
     * @return 200 OK with updated User if found, 404 Not Found if not.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    /**
     * DELETE /api/users/{id}
     * - Deletes a user by ID.
     *
     * @param id - The ID of the user to delete.
     * @return 200 OK if successful, 404 Not Found if not.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
