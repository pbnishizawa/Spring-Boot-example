package com.example.demo;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository repository;

    //全件取得
    @GetMapping("/showEmployeeList")
    public List<Employee> getall() {
        return repository.findAll();
    }

    
    //IDで検索
    @GetMapping("/{id}")
    public Optional<Employee> findById(@PathVariable("id") Long id) {
    return repository.findById(id);
  }

    // 1件追加用メソッド
    @PostMapping("/user/insert")
    public String insertEmployee(@RequestBody ) {
        return repository.save(user);
        
    }


    //削除
    @GetMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
    repository.deleteById(id);
  }



}
