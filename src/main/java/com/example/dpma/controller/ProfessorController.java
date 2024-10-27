package com.example.dpma.controller;

import com.example.dpma.model.*;
import com.example.dpma.service.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    ThesisService thesisService;

    @Autowired
    StudentService studentService;
    @Autowired
    ApplicationService applicationService;

    @RequestMapping("/professor/dashboard")
    public String getProfessorHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.err.println(currentPrincipalName);
        model.addAttribute("username", currentPrincipalName);
        model.addAttribute("role", "PROFESSOR");

        return "professor/dashboard";
    }


    @RequestMapping("/professor/editProfessorInfo")
    public String updateStudent(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Professor professor = professorService.findProfessorByUserId(user.getId());
        model.addAttribute("professor", professor);
        return "professor/editInfo";
    }

    @RequestMapping("/professor/save")
    public String studentSave(@ModelAttribute("professor") Professor professor, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.err.println(currentPrincipalName);
        model.addAttribute("username", currentPrincipalName);
        model.addAttribute("role", "PROFESSOR");
        professorService.saveProfile(professor);
        return "professor/dashboard";
    }


    @RequestMapping("/professor/showSubjectForm")
    public String showSubjectForm(Model model) {

        Subject subject = new Subject();

        model.addAttribute("subject", subject);
        return "/professor/subjectForm";

    }


    //Add information for completed
    @RequestMapping("/professor/addSubject")
    public String addSubject(@ModelAttribute("subject") Subject subject, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Professor professor = professorService.findProfessorByUserId(user.getId());
        subject.setProfessor(professor);
        subjectService.saveSubject(subject);
        professorService.saveSubject(professor, subject);
        model.addAttribute("successMessage", "Subject registered successfully!");
        model.addAttribute("username", currentPrincipalName);
        model.addAttribute("role", "PROFESSOR");


        return "/professor/dashboard";

    }

    @RequestMapping("/professor/subjectsList")
    public String listProfessorSubjects(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Professor professor = professorService.findProfessorByUserId(user.getId());
        model.addAttribute("subjects", professorService.listProfessorSubjects(professor));

        return "professor/subjectsList";
    }

    @RequestMapping("/professor/viewApplications")
    public String listApplication(@RequestParam("subject_id") Integer subjectId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Professor professor = professorService.findProfessorByUserId(user.getId());
        model.addAttribute("applications", professorService.listApplications(subjectId, professor));
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("subject", subjectService.findById(subjectId));
        return "/professor/applicationsList";

    }

    @RequestMapping("/professor/deleteSubject")
    public String deleteSubject(@RequestParam("subject_id") Integer subjectId, Model model) {
        System.out.println(subjectId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Professor professor = professorService.findProfessorByUserId(user.getId());


        subjectService.deleteById(subjectId);

        model.addAttribute("subjects", professorService.listProfessorSubjects(professor));

        return "professor/subjectsList";
    }

    @Transactional
    @RequestMapping("/professor/assignSubject")
    public String assignSubject(@RequestParam("subjectId") Integer subjectId, @RequestParam("strategy") String strategy, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Professor professor = professorService.findProfessorByUserId(user.getId());

        Student student = professorService.assignSubject(professor, subjectId, strategy);
        if (student == null){
            model.addAttribute("SuccessMessage", "There are no students that meet the threshold criteria(Average grade>=5 and Number of remaining courses <=5)");
            return "/professor/subjectsList";

        }


        model.addAttribute("SuccessMessage", ("The student " +  student.getFull_name() + " has been assigned for the " + subjectService.findById(subjectId).getSubject_name() + " thesis position."));

        return "/professor/subjectsList";
    }

    @Transactional
    @RequestMapping("/professor/assignParticular")
    public String assignSubjectToParticular(@RequestParam("subject_id") Integer subjectId, @RequestParam("student_id")Integer studentId, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Professor professor = professorService.findProfessorByUserId(user.getId());

        professorService.assignSubjectToParticular(professor, subjectId, studentId);
        System.out.println("print edw" + studentService.findStudentById(studentId).getFull_name());
        model.addAttribute("SuccessMessage", ("The student " +  studentService.findStudentById(studentId).getFull_name() + " has been assigned for the " + subjectService.findById(subjectId).getSubject_name() + " thesis position."));

        return "/professor/subjectsList";
    }

    @RequestMapping("/professor/thesisList")

    public String thesisList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Professor professor = professorService.findProfessorByUserId(user.getId());

        List<Thesis> theses = professor.getTheses();
        model.addAttribute("theses", theses);
        return "/professor/thesisList";
    }

    @RequestMapping("/professor/setGrades")
    public String setGrades(@RequestParam("thesis_id")Integer thesisId, Model model){
        Thesis thesis = thesisService.findById(thesisId);
        model.addAttribute("thesis", thesis);
        return "/professor/setGrades";

    }

    @RequestMapping("/professor/saveGrades")
    public String saveGrades(@ModelAttribute("thesis") Thesis thesis, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();
        User user = userService.loadUserByName(currentPrincipalName);
        Professor professor = professorService.findProfessorByUserId(user.getId());


        thesisService.setThesisGrade(thesis);
        List<Thesis> theses = professor.getTheses();
        model.addAttribute("theses", theses);
        return "professor/thesisList";
    }
}







