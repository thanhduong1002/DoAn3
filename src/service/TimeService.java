package service;

import java.util.List;

import dao.TimeDAO;
import entity.Time;

public class TimeService {
	
	private static TimeService instance;
	private TimeService() {
	}
	public static TimeService getInstance() {
		if(instance == null) {
			instance = new TimeService();
		}
		return instance;
	}
	
	public List<Time> getAllTime(){
		return TimeDAO.getInstance().getAllTime();
	}
	public Time updateTime(Time time) {
		return TimeDAO.getInstance().updateTime(time);
	}
	public Time getTimeById(int idTime) {
		List<Time> list = getAllTime();
		for(Time item : list) {
			if(item.getIdTime() == idTime) {
				return item;
			}
		}
		return null;
	}
}
