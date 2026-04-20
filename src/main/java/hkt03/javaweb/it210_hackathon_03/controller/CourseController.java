package hkt03.javaweb.it210_hackathon_03.controller;

import hkt03.javaweb.it210_hackathon_03.model.dto.Course;
import hkt03.javaweb.it210_hackathon_03.repositpry.CourseRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public String listCourses(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        List<Course> list = courseRepository.findAll();
        model.addAttribute("courses", list);
        model.addAttribute("keyword", keyword);
        return "course-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "course-form";
    }

    @PostMapping("/save")
    public String saveCourse(
            @Valid @ModelAttribute("course") Course course,
            BindingResult result,
            @RequestParam(value = "imageFile", required = false) MultipartFile file,
            HttpServletRequest request) {

        if (result.hasErrors()) {
            return "course-form";
        }

        // upload ảnh
        if (file != null && !file.isEmpty()) {
            try {
                String uploadPath = request.getServletContext().getRealPath("/uploads/");
                File dir = new File(uploadPath);
                if (!dir.exists()) dir.mkdirs();

                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + fileName));
                course.setThumbnail(fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (course.getId() != null) {
            Course oldData = courseRepository.findById(course.getId());
            if (oldData != null) {
                course.setThumbnail(oldData.getThumbnail());
            }
        }

        courseRepository.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            return "redirect:/courses";
        }
        model.addAttribute("course", course);
        return "course-form";
    }
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseRepository.delete(id);
        return "redirect:/courses";
    }
}