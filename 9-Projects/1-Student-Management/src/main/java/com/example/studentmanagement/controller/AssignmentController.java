package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Assignment;
import com.example.studentmanagement.entity.AssignmentSubmission;
import com.example.studentmanagement.service.AssignmentService;
import com.example.studentmanagement.service.AssignmentSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final AssignmentSubmissionService submissionService;

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/create/{courseId}")
    public Assignment createAssignment(@PathVariable Long courseId,
                                       @RequestParam String title,
                                       @RequestParam String description,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dueDate
    ){
        return assignmentService.createAssignment(courseId,title,description,dueDate);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/submit/{assignmentId}")
    public AssignmentSubmission submitAssignment(
            @PathVariable Long assignmentId,
            @RequestParam String message,
            @RequestParam MultipartFile file,
            Principal principal
            ) throws IOException {
        return submissionService.submitAssignment(assignmentId, principal.getName(),message,file);
    }

    @PreAuthorize("hasAnyRole('STUDENT','INSTRUCTOR')")
    @GetMapping("/course/{courseId}")
    public List<Assignment> getAssignmentsByCourse(@PathVariable Long courseId) {
        return assignmentService.getAssignmentsByCourse(courseId);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/submissions/{assignmentId}")
    public List<AssignmentSubmission> getSubmissions(@PathVariable Long assignmentId) {
        return submissionService.getSubmissionsByAssignment(assignmentId);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PutMapping("/grade/{submissionId}")
    public AssignmentSubmission gradeSubmission(
        @PathVariable Long submissionId,
        @RequestParam String grade,
        @RequestParam String feedback) {
        return submissionService.gradeSubmission(submissionId,grade,feedback);

    }


}
