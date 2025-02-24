package com.Naveen.serviceInterface;

import java.util.List;

import com.Naveen.entity.AttendiesEntity;
import com.Naveen.entity.RegisterEntity;


public interface AttendiesService {
	Integer saveAttendie(AttendiesEntity entity);
	AttendiesEntity findbyEmail(String email);
	
}
