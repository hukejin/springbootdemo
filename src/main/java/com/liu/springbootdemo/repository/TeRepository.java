package com.liu.springbootdemo.repository;

import com.liu.springbootdemo.entity.Te;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface TeRepository extends PagingAndSortingRepository<Te,String>{
    List<Te> getAllByName(String name);

    @Query("SELECT DISTINCT a FROM Te a where a.name like %?1%")
    List<Te> getDistinctByNameIsLike(String name);
}
