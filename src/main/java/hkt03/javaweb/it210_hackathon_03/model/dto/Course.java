package hkt03.javaweb.it210_hackathon_03.model.dto;

import jakarta.validation.constraints.*;

public class Course {

    private Long id;

    @NotBlank(message = "Không được để trống")
    @Size(min = 5, max = 100, message = "Tên khóa học từ 5 đến 100 ký tự")
    private String courseName;

    @NotBlank(message = "Không được để trống")
    private String instructor;

    @NotNull(message = "Không được để trống")
    @Min(value = 1, message = "Thời lượng phải > 0")
    @Max(value = 500, message = "Thời lượng <= 500")
    private Integer duration;

    private String thumbnail;

    public Course() {
    }

    public Course(Long id, String courseName, String instructor, Integer duration, String thumbnail) {
        this.id = id;
        this.courseName = courseName;
        this.instructor = instructor;
        this.duration = duration;
        this.thumbnail = thumbnail;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}