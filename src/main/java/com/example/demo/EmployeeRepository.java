package com.example.demo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//extendsしているJpaRepositorが＠Repositoryという意味のため省略可能
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  
}
