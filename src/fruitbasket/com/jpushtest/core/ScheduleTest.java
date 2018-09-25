package fruitbasket.com.jpushtest.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.TimeUnit;
import cn.jiguang.common.Week;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.schedule.ScheduleListResult;
import cn.jpush.api.schedule.ScheduleResult;
import cn.jpush.api.schedule.model.SchedulePayload;
import cn.jpush.api.schedule.model.TriggerPayload;
import fruitbasket.com.jpushtest.Condition;

/**
 * 用户创建定时任务
 * @author jiguang
 *
 */
public class ScheduleTest {
	private static final ScheduleTest instance=new ScheduleTest();
	
    protected static final Logger LOG = LoggerFactory.getLogger(ScheduleTest.class);
    
    private static JPushClient jpushClient =
			new JPushClient(Condition.MASTER_SECRET, Condition.APP_KEY);

    /*
     * 定期任务开始时间
     */
    private static final String startTime="2018-07-17 12:00:00";
    
    /*
     * 定期任务结束时间
     */
    private static final String endTime="2018-12-30 12:00:00";
    
    /*
     * 任务执行时刻
     */
    private static final String executeTime="14:00:00";
    
	private ScheduleTest() {}
    
    public ScheduleTest getInstance() {
    	return instance;
    }

    /**
     * 创建定时任务
     */
    public static void createSingleSchedule() {
        
        final String name = "single_schedule";//任务名称
        final String time = "2018-07-18 11:30:00";//任务执行时刻
        
        //设置推送
        PushPayload push = 
        		PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert("定时任务"))
				.build();
        
        try {
            
        	ScheduleResult result = jpushClient.createSingleSchedule(
        			name, 
        			time, 
        			push
        	);
            
            LOG.info("结果：" + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * 创建日常定期任务
     */
    public static void createDailySchedule() {
        
    	final String name = "test_daily_schedule";//任务名字
    	final String start = "2018-07-17 12:00:00";//任务开始时间
        final String end = "2018-12-30 12:00:00";//任务结束时间
        final String time = "14:00:00";//任务执行时刻 
        
        PushPayload push = PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert("日常任务"))
				.build();
        try {
            ScheduleResult result = jpushClient.createDailySchedule(
            		name,
            		start,
            		end, 
            		time, 
            		push
            );
            
            LOG.info("结果：" + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * 创建周常定期任务
     */
    public static void createWeeklySchedule() {

        String name = "test_weekly_schedule";//任务名字
        String start = "2018-07-17 12:00:00";//任务开始时间
        String end = "2018-12-30 12:00:00";//任务结束时间
        String time = "14:00:00";//任务执行时刻
        Week[] days = {//任务执行的日期
        		Week.MON,//星期一
        		Week.FRI//星期五
        };
        
        PushPayload push =PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert("周常任务"))
				.build();
        
        try {
            ScheduleResult result = 
            		jpushClient.createWeeklySchedule(
            				name, 
            				start,
            				end, 
            				time,
            				days, 
            				push
            );
            LOG.info("结果：" + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * 创建月常定期任务
     */
    public static void createMonthlySchedule() {

        String name = "test_monthly_schedule";//任务名字
        String start = "2018-07-17 12:00:00";//任务开始时间
        String end = "2018-12-30 12:00:00";//任务结束时间
        String time = "14:00:00";//任务执行时刻
        String[] points = {//任务执行的日期
        		"01", //当月的1号
        		"02"//当月的2号
        };
        
        PushPayload push = PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert("月常任务"))
				.build();
        try {
            ScheduleResult result = jpushClient.createMonthlySchedule(
            		name, 
            		start, 
            		end, 
            		time, 
            		points, 
            		push
           );
            LOG.info("schedule result is " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later.", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
    
    /**
     * 获取有效的 schedule 列表
     */
    public static void getScheduleList() {
        final int page = 1;//请求页码，每个页面最多包含50个Schedule

        try {
            ScheduleListResult list = jpushClient.getScheduleList(page);
            
            LOG.info("Schedule总数= " + list.getTotal_count());
            int i=0;
            for(ScheduleResult s : list.getSchedules()) {
                LOG.info((++i)+": "+s.toString());
            }
            
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
    
    /**
     * 获取指定的Schedule
     */
    public static void getSchedule() {

        try {
            ScheduleResult result = jpushClient.getSchedule(
            		Condition.SCHEDULE_ID_1
            );
            
            LOG.info("结果：" + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
    
    /**
     * 修改指定的Schedule
     */
    public static void updateSchedule() {
        
    	final String name="test_update_schedule";//定期任务名字
        String[] points = {//任务执行的日期
        		Week.MON.name(),//星期一
        		Week.FRI.name()//星期五
        };
        
        TriggerPayload trigger = TriggerPayload.newBuilder()
                .setPeriodTime(
                		"2018-07-17 12:00:00",//定期任务开始时间
                		"2018-12-30 12:00:00",//定期任务结束时间
                		"15:00:00")//定期任务执行时刻
                
                //每隔2个星期的星期一和星期五执行一次任务
                .setTimeFrequency(//定期任务的执行频率
                		TimeUnit.WEEK,//时间单位
                		2,//2个时间单位
                		points)
                
                .buildPeriodical();
        
        PushPayload push = PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert("更新过的任务"))
				.build();
        
        SchedulePayload payload = SchedulePayload.newBuilder()
                .setName(name)
                .setEnabled(false)//Schedule的状态
                .setTrigger(trigger)
                .setPush(push)
                .build();
        
        try {
        	ScheduleResult result=jpushClient.updateSchedule(
            		Condition.SCHEDULE_ID_TEST, 
            		payload
            );
        	
        	LOG.info("结果：" + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * 删除Schedule
     */
    public static void deleteSchedule() {
        try {
            jpushClient.deleteSchedule(
            		Condition.SCHEDULE_ID_TEST
            );
        
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
}
