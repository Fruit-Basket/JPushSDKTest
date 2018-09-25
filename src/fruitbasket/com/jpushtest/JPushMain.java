package fruitbasket.com.jpushtest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import fruitbasket.com.jpushtest.core.DeviceTest;
import fruitbasket.com.jpushtest.core.PushTest;
import fruitbasket.com.jpushtest.core.ReportsTest;
import fruitbasket.com.jpushtest.core.ScheduleTest;

public class JPushMain {
	
	private final static String ALERT="notification content";
	
	public static void main(String[] args) {
		System.out.println("JPush Main()");
		
		//System.out.println(System.currentTimeMillis());
		
		//PushTest.sendPushToAll(JPushCondition.MASTER_SECRET,JPushCondition.APP_KEY,ALERT+" "+getTime());
		//PushTest.groupPush(JPushCondition.GROUP_MASTER_SECRET,JPushCondition.GROUP_APP_KEY,"Group Push "+getTime());
		//PushTest.sendToAlias(JPushCondition.MASTER_SECRET,JPushCondition.APP_KEY,PushTest.ALERT,JPushCondition.ALIAS_1);
		//PushTest.sendPush0(JPushCondition.MASTER_SECRET,JPushCondition.APP_KEY,"title","alert");
		
		
		//ReportsTest.getReportReceiveds(Condition.MASTER_SECRET,Condition.APP_KEY,String.valueOf(Condition.MSG_ID));
		//ReportsTest.getMessageStatus(Condition.MASTER_SECRET,Condition.APP_KEY,Condition.MSG_ID,Condition.REGISTRATION_ID_1,Condition.REGISTRATION_ID_2);
		//ReportsTest.testGetMessages();
		//ReportsTest.testGetUsers();
		
		//DeviceTest.getDeviceTagAlias(Condition.MASTER_SECRET,Condition.APP_KEY,Condition.REGISTRATION_ID_1);
		//DeviceTest.updateDeviceTagAlias();
		//DeviceTest.testClear();
		//DeviceTest.getAliasDeviceList(Condition.MASTER_SECRET,Condition.APP_KEY,Condition.ALIAS_1);
		//DeviceTest.deleteAlias();
		//DeviceTest.getTags(); 
		//DeviceTest.isDeviceInTag();
		//DeviceTest.addRemoveDevicesFromTag();
		//DeviceTest.deleteTag();
		//DeviceTest.getUserOnlineStatus();
		//DeviceTest.bindMobile();
		
		//ScheduleTest.createSingleSchedule();
		//ScheduleTest.createDailySchedule();
		//ScheduleTest.createWeeklySchedule();
		//ScheduleTest.createMonthlySchedule();
		//ScheduleTest.getScheduleList();
		//ScheduleTest.getSchedule();
		//ScheduleTest.updateSchedule();
		
		//ClientTest.testDefaultClient();
		//ClientTest.testCustomClient();
		//ClientTest.testCustomPushClient();
	}
	
	/**
	 * 
	 * @return
	 */
	private static String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        return (format.format(new Date()));
	}
}
