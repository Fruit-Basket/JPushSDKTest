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
 * �û�������ʱ����
 * @author jiguang
 *
 */
public class ScheduleTest {
	private static final ScheduleTest instance=new ScheduleTest();
	
    protected static final Logger LOG = LoggerFactory.getLogger(ScheduleTest.class);
    
    private static JPushClient jpushClient =
			new JPushClient(Condition.MASTER_SECRET, Condition.APP_KEY);

    /*
     * ��������ʼʱ��
     */
    private static final String startTime="2018-07-17 12:00:00";
    
    /*
     * �����������ʱ��
     */
    private static final String endTime="2018-12-30 12:00:00";
    
    /*
     * ����ִ��ʱ��
     */
    private static final String executeTime="14:00:00";
    
	private ScheduleTest() {}
    
    public ScheduleTest getInstance() {
    	return instance;
    }

    /**
     * ������ʱ����
     */
    public static void createSingleSchedule() {
        
        final String name = "single_schedule";//��������
        final String time = "2018-07-18 11:30:00";//����ִ��ʱ��
        
        //��������
        PushPayload push = 
        		PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert("��ʱ����"))
				.build();
        
        try {
            
        	ScheduleResult result = jpushClient.createSingleSchedule(
        			name, 
        			time, 
        			push
        	);
            
            LOG.info("�����" + result);
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
     * �����ճ���������
     */
    public static void createDailySchedule() {
        
    	final String name = "test_daily_schedule";//��������
    	final String start = "2018-07-17 12:00:00";//����ʼʱ��
        final String end = "2018-12-30 12:00:00";//�������ʱ��
        final String time = "14:00:00";//����ִ��ʱ�� 
        
        PushPayload push = PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert("�ճ�����"))
				.build();
        try {
            ScheduleResult result = jpushClient.createDailySchedule(
            		name,
            		start,
            		end, 
            		time, 
            		push
            );
            
            LOG.info("�����" + result);
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
     * �����ܳ���������
     */
    public static void createWeeklySchedule() {

        String name = "test_weekly_schedule";//��������
        String start = "2018-07-17 12:00:00";//����ʼʱ��
        String end = "2018-12-30 12:00:00";//�������ʱ��
        String time = "14:00:00";//����ִ��ʱ��
        Week[] days = {//����ִ�е�����
        		Week.MON,//����һ
        		Week.FRI//������
        };
        
        PushPayload push =PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert("�ܳ�����"))
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
            LOG.info("�����" + result);
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
     * �����³���������
     */
    public static void createMonthlySchedule() {

        String name = "test_monthly_schedule";//��������
        String start = "2018-07-17 12:00:00";//����ʼʱ��
        String end = "2018-12-30 12:00:00";//�������ʱ��
        String time = "14:00:00";//����ִ��ʱ��
        String[] points = {//����ִ�е�����
        		"01", //���µ�1��
        		"02"//���µ�2��
        };
        
        PushPayload push = PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert("�³�����"))
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
     * ��ȡ��Ч�� schedule �б�
     */
    public static void getScheduleList() {
        final int page = 1;//����ҳ�룬ÿ��ҳ��������50��Schedule

        try {
            ScheduleListResult list = jpushClient.getScheduleList(page);
            
            LOG.info("Schedule����= " + list.getTotal_count());
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
     * ��ȡָ����Schedule
     */
    public static void getSchedule() {

        try {
            ScheduleResult result = jpushClient.getSchedule(
            		Condition.SCHEDULE_ID_1
            );
            
            LOG.info("�����" + result);
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
     * �޸�ָ����Schedule
     */
    public static void updateSchedule() {
        
    	final String name="test_update_schedule";//������������
        String[] points = {//����ִ�е�����
        		Week.MON.name(),//����һ
        		Week.FRI.name()//������
        };
        
        TriggerPayload trigger = TriggerPayload.newBuilder()
                .setPeriodTime(
                		"2018-07-17 12:00:00",//��������ʼʱ��
                		"2018-12-30 12:00:00",//�����������ʱ��
                		"15:00:00")//��������ִ��ʱ��
                
                //ÿ��2�����ڵ�����һ��������ִ��һ������
                .setTimeFrequency(//���������ִ��Ƶ��
                		TimeUnit.WEEK,//ʱ�䵥λ
                		2,//2��ʱ�䵥λ
                		points)
                
                .buildPeriodical();
        
        PushPayload push = PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert("���¹�������"))
				.build();
        
        SchedulePayload payload = SchedulePayload.newBuilder()
                .setName(name)
                .setEnabled(false)//Schedule��״̬
                .setTrigger(trigger)
                .setPush(push)
                .build();
        
        try {
        	ScheduleResult result=jpushClient.updateSchedule(
            		Condition.SCHEDULE_ID_TEST, 
            		payload
            );
        	
        	LOG.info("�����" + result);
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
     * ɾ��Schedule
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
