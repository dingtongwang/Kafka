package com.csu.service;

import com.csu.exception.CourseNotFoundException;
import com.csu.model.Course;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CourseService {

    private static final Map<String, Course> COURSE_MAP = new ConcurrentHashMap<>();

    static {
        Course course1 = new Course("Wizardry");
        Course course2 = new Course("Wizardry");
        Course course3 = new Course("Wizardry");
        COURSE_MAP.put(course1.getCode(), course1);
        COURSE_MAP.put(course2.getCode(), course2);
        COURSE_MAP.put(course3.getCode(), course3);
    }

    public Collection<Course> getCourses() {
        return COURSE_MAP.values();
    }

    public Course getCourse(String code) {
        return Optional.ofNullable(COURSE_MAP.get(code)).orElseThrow(() -> new CourseNotFoundException(code));
    }
}
