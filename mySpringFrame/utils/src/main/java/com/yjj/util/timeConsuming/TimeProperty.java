package com.yjj.util.timeConsuming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeProperty {
private static final Logger logger = LoggerFactory.getLogger(TimeConsumingUtils.class);
	
	private long currentTime = 0;
	private String description;
	
	public TimeProperty(String description){
		this.currentTime = System.currentTimeMillis();
		this.description = description;
	}
	
	public void getTimeConsuming(TimeConsumingUtils timeConsumingUtil){
		TimeProperty oldTimeProperty = timeConsumingUtil.getOldTimeProperty();
		if(oldTimeProperty != null){
			long consumingTime = this.getCurrentTime() - oldTimeProperty.getCurrentTime();
			logger.debug(this.getDescription()+consumingTime+" ms");
		}
		timeConsumingUtil.setOldTimeProperty(this);
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
