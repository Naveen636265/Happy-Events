package com.Naveen.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Naveen.entity.AttendiesEntity;
import com.Naveen.entity.RegisterEntity;
import com.Naveen.repository.AttendiesRepo;
import com.Naveen.serviceInterface.AttendiesService;

@Service
public class AttendiesServiceImpl implements AttendiesService{
	@Autowired
	private AttendiesRepo repo;
	
	
	@Override
	public Integer saveAttendie(AttendiesEntity entity) {
		// TODO Auto-generated method stub
		return repo.save(entity).getAId();
	}


	@Override
	public AttendiesEntity findbyEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
