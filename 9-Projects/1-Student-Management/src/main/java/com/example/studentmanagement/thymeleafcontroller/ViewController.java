package com.example.studentmanagement.thymeleafcontroller;

import com.example.studentmanagement.entity.Assignment;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Material;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.repository.UserRepository;
import com.example.studentmanagement.service.AssignmentService;
import com.example.studentmanagement.service.CourseService;
import com.example.studentmanagement.service.EnrollmentService;
import com.example.studentmanagement.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final UserRepository userRepository;
    private final CourseService courseService;
    private final MaterialService materialService;
    private final AssignmentService assignmentService;
    private final EnrollmentService enrollmentService;

    // this is the home redirect to dashboard
    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    // this is LoginPage
    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    // this is the registration page
    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }

    // role specific dashboard for Instructor and Student
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails user, Model model){
        if (user==null) return "redirect:/login";

        boolean isInstructor=user.getAuthorities().stream()
                .anyMatch(auth->auth.getAuthority().equals("ROLE_INSTRUCTOR"));

        boolean isStudent=user.getAuthorities().stream()
                .anyMatch(auth->auth.getAuthority().equals("ROLE_STUDENT"));

        model.addAttribute("username",user.getUsername());

        if(isInstructor) return "dashboard/instructor";

//        if (isStudent) return "dashboard/student";

        if (isStudent) {
            // Get the User entity by email (assuming username = email)
            User student = userRepository.findByEmail(user.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Fetch enrolled courses and add to model
            List<Course> enrolledCourses = enrollmentService.getEnrolledCourses(student.getEmail());
            model.addAttribute("enrolledCourses", enrolledCourses);

            return "dashboard/student";
        }

        return "redirect:/login";
    }

    // view all Courses
    @GetMapping("/courses")
    public String viewCourses(Model model){
        model.addAttribute("courses",courseService.getAllCourses());
        return "course/courses";
    }

    // upload materials page for instructor only
    @GetMapping("/materials/upload")
    public String uploadMaterialPage() {
        return "material/upload";
    }

    // view materials
    @GetMapping("/materials/view/course/{courseId}")
    public String viewCourseMaterial(@PathVariable Long courseId, Model model){
        List<Material> materials = materialService.getMaterialByCourse(courseId);
        model.addAttribute("materials",materials);
        model.addAttribute("courseId",courseId);
        return "material/view-material";
    }


    // view assignments for a course
    @GetMapping("/courses/{id}/assignments")
    public String viewAssignmentsByCourse(@PathVariable Long id, Model model) {
        List<Assignment> assignments = assignmentService.getAssignmentsByCourse(id);
        model.addAttribute("assignments",assignments);

        return "assignment/list";
    }

    // submit assignment by student
    @GetMapping("/assignments/submit")
    public String submitAssignmentForm() {
        return "assignment/submit";
    }

    // create assignment page for instructor
    @GetMapping("/assignments/create")
    public String createAssignment(Model model){
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("assignment",new Assignment());
        model.addAttribute("courses",courses);
        return "assignment/create";
    }

    // view all submissions for instructor
    @GetMapping("/assignments/submissions/all")
    public String viewAllAssignments(){
        return "assignment/submissions";
    }
}
