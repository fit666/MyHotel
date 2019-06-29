package com.hero.hotel.controller;

import java.util.List;

import javax.annotation.Resource;

import com.hero.hotel.pojo.House;
import com.hero.hotel.pojo.HouseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hero.hotel.pojo.House;
import com.hero.hotel.service.HouseService;
import com.hero.hotel.service.HouseTypeService;

@Controller
@RequestMapping("/room")
public class HouseController {

	@Resource
	private HouseService houseService;
	@Autowired
	private HouseTypeService houseTypeService;
	

	// 查询所有房间状态

	@GetMapping("/findRoomAll")
	// @ResponseBody
	public ModelAndView findRoomAllStatus() {

		List<House> houses = houseService.findRoomAllStatus();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rooms", houses);
		modelAndView.setViewName("backstage-html/roomStatus.html");
		System.out.println(houses);
		return modelAndView;

	}

	@GetMapping("/findOkRooms")
	public ModelAndView findOkRooms() {
		List<House> houses = houseService.findOkRooms();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rooms", houses);
		modelAndView.setViewName("backstage-html/roomStatus.html");
		System.out.println(houses);
		return modelAndView;
	}

//	@GetMapping("/findNoRooms")
//	public ModelAndView findNoRooms() {
//		List<House> houses = houseService.findNoRooms();
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("rooms", houses);
//		modelAndView.setViewName("backstage-html/roomStatus.html");
//		System.out.println(houses);
//		return modelAndView;
//	}

	@GetMapping("/findZangRooms")
	public ModelAndView findZangRooms() {
		List<House> houses = houseService.findZangRooms();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rooms", houses);
		modelAndView.setViewName("backstage-html/roomStatus.html");
		System.out.println(houses);
		return modelAndView;
	}
	@GetMapping("/findAepairRooms")
	public ModelAndView findAepairRooms() {
		List<House> houses = houseService.findRepairRooms();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rooms", houses);
		modelAndView.setViewName("backstage-html/roomStatus.html");
		System.out.println(houses);
		return modelAndView;
	}

	/*
	 * 下架房间
	 */
	@RequestMapping("/room-stop")
	@ResponseBody
	public String roomStop(Integer id){
		String result=houseService.stopRoom(id);
		return result;
	}
	
	/*
	 * 上架房间
	 */
	@RequestMapping("/room-start")
	@ResponseBody
	public String roomStart(Integer id){
		String result=houseService.startRoom(id);
		return result;
	}
	/*
	 * 增加房间
	 */
	@RequestMapping("/addRoom")
	public ModelAndView addRoom(Integer typeid,Integer number){
		ModelAndView mav=new ModelAndView();
		String result=houseService.addRoom(typeid,number);
		mav.addObject("result",result);
		//查询所有的房间
		List<HouseType> allHouses=houseTypeService.findAllHouses();
		mav.addObject("allHouses", allHouses);
		mav.setViewName("/backstage-html/house-list2.html");
		return mav;
	}

	@GetMapping("/addRepair")
	@ResponseBody
	public ModelAndView addRepairRoom(Integer houseid) {
		Boolean flag = houseService.addRepairRoom(houseid);
		List<House> houses = houseService.findRoomAllStatus();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rooms", houses);
		modelAndView.setViewName("backstage-html/roomStatus.html");
		System.out.println(houses);
		return modelAndView;
	}
	
	
	
	@GetMapping("/updataroom")
	@ResponseBody
	public ModelAndView updataRoom(Integer id) {
		Boolean flag = houseService.updataRoom(id);
		List<House> houses = houseService.findRoomAllStatus();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rooms", houses);
		modelAndView.setViewName("backstage-html/roomStatus.html");
		System.out.println(houses);
		return modelAndView;
	}
	
	
	// @GetMapping("/findRoomTypeNum")
	// @ResponseBody
	// public List<Map> findRoomTypeNum(String time1, String time2) {
	//
	// System.out.println(time1);
	// System.out.println(time2);
	//
	// List<Map> maps = new ArrayList<Map>();
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("a", time1);
	// map.put("b", time2);
	// maps.add(map);
	//
	//
	// List<Map> list = houseService.findRoomTypeNum(time1,time2);
	//
	// return maps;
	// }
	
	
	

}
