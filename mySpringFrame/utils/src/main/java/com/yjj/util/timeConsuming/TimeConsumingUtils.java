package com.yjj.util.timeConsuming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class TimeConsumingUtils {
	
	private final List<TimeProperty> timePropertyList = new ArrayList<TimeProperty>();
	private TimeProperty oldTimeProperty;
	
	static final ThreadLocal<TimeConsumingUtils> sThreadLocal = new ThreadLocal<TimeConsumingUtils>();
	
	private TimeConsumingUtils(){}
	
	public static TimeConsumingUtils getTimeConsumingUtil(){
		TimeConsumingUtils timeConsumingUtils = sThreadLocal.get();
		if(timeConsumingUtils == null){
			timeConsumingUtils = new TimeConsumingUtils();
			sThreadLocal.set(timeConsumingUtils);
		}
		return timeConsumingUtils;
	}
	
	public  void clear(){
		sThreadLocal.set(null);
	}
	
	
	public void addTimeProperty(String description){
		timePropertyList.add(new TimeProperty(description));
	}

	public List<TimeProperty> getTimePropertyList() {
		return timePropertyList;
	}

	public TimeProperty getOldTimeProperty() {
		return oldTimeProperty;
	}

	public void setOldTimeProperty(TimeProperty oldTimeProperty) {
		this.oldTimeProperty = oldTimeProperty;
	}
	
}

