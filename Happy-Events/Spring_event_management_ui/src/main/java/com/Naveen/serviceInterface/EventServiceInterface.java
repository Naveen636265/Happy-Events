package com.Naveen.serviceInterface;

import java.util.List;

import com.Naveen.entity.EventsEntity;
import com.Naveen.entity.Form;

public interface EventServiceInterface {

	// public String loadEvent(String eventCategory);
	public EventsEntity loadEventData(String eventCategory);

	public List<Form> getUserEvents(String email);

	public void deleteUserBooking(Integer id);
	
	Integer saveEvent(EventsEntity entity);
	
	public void deleteEvent(String eventCategory);
	
	public List<EventsEntity> data1();
	void deleteEvent(int eventId);

}
