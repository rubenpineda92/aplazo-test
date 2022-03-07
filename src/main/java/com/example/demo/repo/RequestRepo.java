package com.example.demo.repo;

import com.example.demo.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<Request, Long> {

}
