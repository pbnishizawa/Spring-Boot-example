package com.example.demo;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository repository;

    //全件取得
    @GetMapping
    public List<Employee> getall() {
        return repository.findAll();
    }

    
    //IDで検索
    @GetMapping("/{id}")
    public Optional<Employee> findById(@PathVariable("id") Long id) {
    return repository.findById(id);
  }

    // 1件新規作成
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee postEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    //更新
    @PutMapping("/{id}")
    public Employee modifyEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
      return repository.save(employee);
    }






    //削除
  //   @GetMapping("/delete/{id}")
  //   public void deleteEmployee(@PathVariable("id") Long id) {
  //   repository.deleteById(id);
  // }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
    repository.deleteById(id);
    }

}
