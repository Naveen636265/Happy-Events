package com.Naveen.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Naveen.entity.AttendiesEntity;
import com.Naveen.entity.EventsEntity;
import com.Naveen.entity.Form;
import com.Naveen.entity.TaskEntity;
import com.Naveen.serviceInterface.EventServiceInterface;
import com.Naveen.serviceInterface.RegisterService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Events")
public class EventsController {

	@Autowired
	private EventServiceInterface service;
	
	@Autowired
	private RegisterService service1;

	@GetMapping("BookingPage")
	public String shakthi(@RequestParam String eventCategory, Model model, HttpSession session) {
		String page = "";

		EventsEntity enity2 = service.loadEventData(eventCategory);
		model.addAttribute("EventData", enity2);
		if (enity2 != null) {
			model.addAttribute("uname", session.getAttribute("uname"));
			model.addAttribute("umail", session.getAttribute("umail"));
			model.addAttribute("uphone", session.getAttribute("uphone"));
			page = "EventData";
		} else {
			model.addAttribute("message", eventCategory + " Under Development");
			page = "Home";
		}
		return page;
	}

	// User booking page
	@GetMapping("/booking")
	public String booking(HttpSession session, Model model, @RequestParam(required = false) String message) {
		if (message != null) {
			model.addAttribute("message", message);
		}
		model.addAttribute("uname", session.getAttribute("uname"));
		model.addAttribute("umail", session.getAttribute("umail"));
		model.addAttribute("uphone", session.getAttribute("uphone"));
		List<Form> list = service.getUserEvents((String) session.getAttribute("umail"));

		model.addAttribute("list", list);
		return "UserBookings";
	}

	// User Booking Canceling
	@GetMapping("deleteUserBooking")
	public String cancelEvent(HttpSession session, Model model, @RequestParam Integer id) {
		service.deleteUserBooking(id);
		return "redirect:/Events/booking?message= Event of id: " + id + " Cancelled successfully";

	}
	@PostMapping("adminAddEvents")
	public String adminAddEvents(@ModelAttribute EventsEntity event, Model model) {
		//boolean exist = service.checkUser(user.getEventId());
		//String page = "";
//		if (exist == false) {
		System.out.println(event.getEventAmount());
			Integer uid = service.saveEvent(event);
			System.out.println("hello");
//			String uname = user.getUserName();

           return "addEvents";
	}
	
//	@PostMapping("adminDeleteEvents")
//	public String admindeleteEvents(@ModelAttribute EventsEntity event, Model model) {
//		//boolean exist = service.checkUser(user.getEventId());
//		//String page = "";
////		if (exist == false) {
//		System.out.println(event.getEventAmount());
//			service.deleteEvent(event.getEventCategory());
//			System.out.println("hello");
////			String uname = user.getUserName();
//
//           return "deleteEvents";
//	}
	@GetMapping("/VeiwAttender")
	public String VeiwAttendies(Model model, @RequestParam(required = false) String id) {	
		TaskEntity t=service1.getTaskByBId(id);
		AttendiesEntity a1=service1.getAttendisById(t.getCateringAndHospitality());
		AttendiesEntity a2=service1.getAttendisById(t.getEntertainmentAndActivities());
		AttendiesEntity a3=service1.getAttendisById(t.getLogisticsAndOperations());
		AttendiesEntity a4=service1.getAttendisById(t.getVenueManagement());
		
		model.addAttribute("t", t);
		model.addAttribute("c", a1);
		model.addAttribute("e", a2);
		model.addAttribute("l", a3);
		model.addAttribute("v", a4);
		
		return "VeiwAttender";	
	}
	
	@GetMapping("/deleteEvents1")
	public String data1(Model model, @RequestParam(required = false) String message) {
		System.out.println("jjj");
		List<EventsEntity> list=service.data1();
		
		
		if (message != null) {
			model.addAttribute("message", message);
		}
		model.addAttribute("list", list);
		System.out.println(list);
		return "deleteEvents";

	}
	
	@GetMapping("/delete1")
	public String delete(@RequestParam Integer eventId) {
		System.out.println("hello");
		service.deleteEvent(eventId);
		return "redirect:/Events/deleteEvents1?message=deleteEvents " + eventId + " Deleted successfully";
	}
}
