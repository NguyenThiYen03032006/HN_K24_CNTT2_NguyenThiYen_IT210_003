package hkt03.javaweb.it210_hackathon_03.repositpry;

import hkt03.javaweb.it210_hackathon_03.model.dto.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class CourseRepository {

    private static List<Course> list = new ArrayList<>();

    static {
        list.add(new Course(1L, "Khoa hoc so 1", "Nguyen Van A", 10, null));
    }

    public List<Course> findAll() {
        return list;
    }

    public void save(Course course) {
        // ADD
        if (course.getId() == null) {
            Long newId = list.stream()
                    .mapToLong(Course::getId)
                    .max()
                    .orElse(0) + 1;

            course.setId(newId);
            list.add(course);
        }
        // UPDATE
        else {
            int index = IntStream.range(0, list.size())
                    .filter(i -> list.get(i).getId().equals(course.getId()))
                    .findFirst()
                    .orElse(-1);

            if (index != -1) {
                list.set(index, course);
            }
        }
    }

    public void delete(Long id) {
        list.removeIf(c -> c.getId().equals(id));
    }

    public Course findById(Long id) {
        return list.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}