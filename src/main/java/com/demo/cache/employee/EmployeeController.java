package com.demo.cache.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    @GetMapping("/employee/{id}")
    @Cacheable(value = "emp_cache", key = "#id")
    public Employee getEmployeeById(@PathVariable(name = "id") Long id) {
        log.info("Employee {} Fetching results from database", id);
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/cache/evict")
    @CacheEvict(value = "emp_cache", allEntries = true)
    public void invalidateCache() {
        log.info("emp_cache evicted");
    }
}
