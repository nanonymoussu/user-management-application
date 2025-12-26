package th.co.aerothai.nanon.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import th.co.aerothai.nanon.model.User;
import th.co.aerothai.nanon.service.UserService;

// Web MVC Controller for serving static HTML pages (Thymeleaf templates).
// * src/main/resources/templates/**.html
@Controller
public class WebController {

    @Autowired
    private UserService userService;

    /**
     * Serves the Home/Landing page.
     * - Fetchs the user list to display in the main table.
     *
     * @param model - The Spring UI Model used to pass data to the Thymeleaf view.
     * @return The name of the template ("index").
     */
    @GetMapping("/")
    public String index(
            Model model,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<User> userPage = userService.findUsers(keyword, page, size, "id", "asc");

        // Fetch data for the list view with query pagination
        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("totalItems", userPage.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("activePage", "users");

        return "index"; // src/main/resources/templates/index.html
    }

    /**
     * Serves the Dashboard page.
     * - Calculates stats using Java Streams and passes to the view for Chart.js
     *
     * @param model - The Spring UI Model.
     * @return The name of the template ("dashboard").
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Dashboard stats still fetch all for accurate summary
        var users = userService.findAllUsers();

        // Statistics 1: Total Users Count
        model.addAttribute("totalUsers", users.size());

        // Statistics 2: Group Users by Role for the Chart
        // Output example: { "Admin": 2, "User": 5 }
        Map<String, Long> roleStats = users.stream()
                .collect(Collectors.groupingBy(User::getRole, Collectors.counting()));

        model.addAttribute("roleStats", roleStats);
        model.addAttribute("activePage", "dashboard");

        return "dashboard"; // src/main/resources/templates/dashboard.html
    }
}
