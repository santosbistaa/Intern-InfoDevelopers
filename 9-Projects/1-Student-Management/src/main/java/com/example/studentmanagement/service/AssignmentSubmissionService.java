package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Assignment;
import com.example.studentmanagement.entity.AssignmentSubmission;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.repository.AssignmentRepository;
import com.example.studentmanagement.repository.AssignmentSubmissionRepository;
import com.example.studentmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentSubmissionService {

    private final AssignmentSubmissionRepository submissionRepo;
    private final AssignmentRepository assignmentRepo;
    private final UserRepository userRepo;


    @Value("${file.upload-dir}")
    private String uploadDir;

    public AssignmentSubmission submitAssignment(Long assignmentId, String studentEmail,
                                                 String message, MultipartFile file) throws IOException {
        Assignment assignment = assignmentRepo.findById(assignmentId).orElseThrow();
        User student = userRepo.findByEmail(studentEmail).orElseThrow();

        String filePath = null;
        if(!file.isEmpty()) {
            String directory = uploadDir + "/assignment-" + assignmentId + "/student-" + student.getId();
            Files.createDirectories(Paths.get(directory));
            filePath = directory + "/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
        }

        AssignmentSubmission submission = AssignmentSubmission.builder()
                .assignment(assignment)
                .student(student)
                .filePath(filePath)
                .submittedAt(LocalDateTime.now())
                .build();
        return submissionRepo.save(submission);
    }

    public List<AssignmentSubmission> getSubmissionsByAssignment(Long assignmentId){
        Assignment assignment = assignmentRepo.findById(assignmentId).orElseThrow();
        return submissionRepo.findByAssignment(assignment);
    }

    public AssignmentSubmission gradeSubmission(Long submissionId, String grade, String feedback) {
        AssignmentSubmission submission = submissionRepo.findById(submissionId).orElseThrow();
        submission.setGrade(grade);
        submission.setFeedback(feedback);
        return submissionRepo.save(submission);

    }
}

