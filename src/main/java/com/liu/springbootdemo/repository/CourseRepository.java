package com.liu.springbootdemo.repository;

import com.liu.springbootdemo.entity.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<Course,String> {
    List<Course> getAllByCoursetypeEqualsOrderByCourselevelAsc(String coursetype);
}
