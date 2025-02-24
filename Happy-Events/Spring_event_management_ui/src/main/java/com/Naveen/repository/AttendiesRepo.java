package com.Naveen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Naveen.entity.AttendiesEntity;
import com.Naveen.entity.RegisterEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface AttendiesRepo extends JpaRepository<AttendiesEntity, Integer>{

	AttendiesEntity  findByaEmail(String aEmail);

}
