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
    
	private ScheduleTest() {}
    
    public ScheduleTest getInstance() {
    	return instance;
    }

    /**
     * ������ʱ����
     */
    public static void createSingleSchedule() {
    	
    	JPushClient jpushClient =new JPushClient(
        		Condition.MASTER_SECRET,
        		Condition.APP_KEY
        );
    	
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
            
            System.out.println("�����" + result);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * �����ճ���������
     */
    public static void createDailySchedule() {
    	
    	JPushClient jpushClient =new JPushClient(
        		Condition.MASTER_SECRET,
        		Condition.APP_KEY
        );
        
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
            
            System.out.println("�����" + result);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * �����ܳ���������
     */
    public static void createWeeklySchedule() {

    	JPushClient jpushClient =new JPushClient(
        		Condition.MASTER_SECRET,
        		Condition.APP_KEY
        );
    	
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
            System.out.println("�����" + result);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * �����³���������
     */
    public static void createMonthlySchedule() {

    	JPushClient jpushClient =new JPushClient(
        		Condition.MASTER_SECRET,
        		Condition.APP_KEY
        );
    	
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
           System.out.println("schedule result is " + result);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ��ȡ��Ч�� schedule �б�
     */
    public static void getScheduleList() {
    	
    	JPushClient jpushClient =new JPushClient(
        		Condition.MASTER_SECRET,
        		Condition.APP_KEY
        );
    	
        final int page = 1;//����ҳ�룬ÿ��ҳ��������50��Schedule

        try {
            ScheduleListResult list = jpushClient.getScheduleList(page);
            
            System.out.println("Schedule����= " + list.getTotal_count());
            int i=0;
            for(ScheduleResult s : list.getSchedules()) {
            	System.out.println((++i)+": "+s.toString());
            }
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ��ȡָ����Schedule
     */
    public static void getSchedule() {
    	
    	JPushClient jpushClient =new JPushClient(
        		Condition.MASTER_SECRET,
        		Condition.APP_KEY
        );

        try {
            ScheduleResult result = jpushClient.getSchedule(
            		Condition.SCHEDULE_ID_1
            );
            
            System.out.println("�����" + result);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * �޸�ָ����Schedule
     */
    public static void updateSchedule() {
    	
    	JPushClient jpushClient =new JPushClient(
        		Condition.MASTER_SECRET,
        		Condition.APP_KEY
        );
        
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
        	
        	System.out.println("�����" + result);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
           e.printStackTrace();
        }
    }

    /**
     * ɾ��Schedule
     */
    public static void deleteSchedule() {
    	
    	JPushClient jpushClient =new JPushClient(
        		Condition.MASTER_SECRET,
        		Condition.APP_KEY
        );
    	
        try {
            jpushClient.deleteSchedule(
            		Condition.SCHEDULE_ID_TEST
            );
        
        } catch (APIConnectionException e) {
           e.printStackTrace();
        } catch (APIRequestException e) {
           e.printStackTrace();
        }
    }
}
