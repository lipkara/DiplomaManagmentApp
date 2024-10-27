package com.example.dpma.controller;

import com.example.dpma.model.Student;
import com.example.dpma.model.User;
import com.example.dpma.service.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ThesisService thesisService;


    @RequestMapping("/student/dashboard")
    public String getStudentHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.err.println(currentPrincipalName);
        model.addAttribute("username", currentPrincipalName);
        model.addAttribute("role", "STUDENT");

        return "student/dashboard";
    }

    @RequestMapping("/student/editStudentInfo")
    public String updateStudent(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Student student = studentService.findStudentByUserId(user.getId());
        model.addAttribute("student", student);
        return "student/editInfo";
    }


    @RequestMapping("/student/save")
    public String studentSave(@ModelAttribute("student") Student student, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.err.println(currentPrincipalName);
        model.addAttribute("username", currentPrincipalName);
        model.addAttribute("role", "STUDENT");
        studentService.saveProfile(student);
        return "student/dashboard";
    }

    @RequestMapping("/student/showAllSubjects")
    public String studentShowAllSubjects(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Student student = studentService.findStudentByUserId(user.getId());
        model.addAttribute("subjects", studentService.listStudentSubjects());


        return "student/subjectsList";
    }

    @RequestMapping("/student/detailedSubject")
    public String detailedSubject(@RequestParam("subject_id")Integer subject_id, Model model){
        model.addAttribute("subject", subjectService.findById(subject_id));
        return "student/detailedSubject";
    }


    @RequestMapping("/student/ApplicationCreate")
    public String applyToSubject(@RequestParam("subject_id") Integer subjectId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Student student = studentService.findStudentByUserId(user.getId());
        model.addAttribute("username", currentPrincipalName);
        model.addAttribute("role", "STUDENT");
        if(applicationService.isApplicationPresent(student.getId(), subjectId)){
            model.addAttribute("SuccessMessage", "You have already applied for this subject.");
            model.addAttribute("subjects", studentService.listStudentSubjects());
        }
        else if(thesisService.isThesisPresent(student.getId())){

            model.addAttribute("SuccessMessage", "You are already assigned to a thesis subject.");
            model.addAttribute("subjects", studentService.listStudentSubjects());
        }
        else {
            model.addAttribute("applied", "You have successfully apllied to this subject");
            model.addAttribute("subjects", studentService.listStudentSubjects());
            studentService.applyToSubject(student, subjectId);
        }

        return "/student/subjectsList";
    }

}