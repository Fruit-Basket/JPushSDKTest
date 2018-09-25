package fruitbasket.com.jpushtest.core;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.TimeUnit;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.report.MessageStatus;
import cn.jpush.api.report.MessagesResult;
import cn.jpush.api.report.MessagesResult.Message;
import cn.jpush.api.report.ReceivedsResult;
import cn.jpush.api.report.UsersResult;
import cn.jpush.api.report.model.CheckMessagePayload;
import fruitbasket.com.jpushtest.Condition;

/**
 * ��ȡ������Ϣ��ͳ����Ϣ
 * 
 * @author FruitBasket
 *
 */
public class ReportsTest {
	
	private static final ReportsTest instance=new ReportsTest();
	protected static final Logger LOG = LoggerFactory.getLogger(ReportsTest.class);
	
	private static JPushClient jpushClient;
	static {
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName(Condition.PUSH_HOST);
		jpushClient = new JPushClient(Condition.MASTER_SECRET, Condition.APP_KEY, null, config);
	}
	
	private ReportsTest() {	
	}
	
	public static ReportsTest getInstance() {
		return instance;
	}
	
	/**
	 * ��Ϣ�ʹ�ͳ��
	 * ��ѯһ����Ϣ���ʹ�ͳ������
	 */
	public static void getReportReceiveds(
			final String masterSecret,
			final String appKey,
			final String messageId) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://report.jpush.cn");
        JPushClient jpushClient = new JPushClient(
        		masterSecret, 
        		appKey, 
        		null,
        		config);
        
        try {
            ReceivedsResult result = jpushClient.getReportReceiveds(
            		messageId
            );
            List<ReceivedsResult.Received> list= result.received_list;
            if(list.isEmpty()==false) {
            	for(ReceivedsResult.Received received:list) {
                	LOG.info("Message ID="+received.msg_id);
                	
                	/**
                	 * 1.����App֪ͨʱ�������˴�App֪ͨ���ʹ���
                	 * 2.�����Զ�����Ϣʱ�������˴��Զ�����Ϣ���ʹ���
                	 * 3.ͬʱ����App֪ͨ���Զ�����Ϣʱ������ֵ��1��ͬ����2�п��ܲ�ͬ����Ϊ�ͻ��˿��ܼ����˳�������ͬʱJPush����û��������
                	 */
                	LOG.info("Android�ʹ���="+received.android_received);
                	
                	LOG.info("App֪ͨ���͵�APNs�ɹ���="+received.ios_apns_sent);
                	LOG.info("֪ͨ��APNs�ʹﵽiOS�豸��="+received.ios_apns_received);
                	LOG.info("iOS�Զ�����Ϣ�ʹ��豸��="+received.ios_msg_received);
                }
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
     * �ʹ�״̬��ѯ
     * ��ѯһ����Ϣ�ڵ�����һ���豸�ϵ��ʹ�״̬
     */
    public static void getMessageStatus(
    		final String masterSecret,
    		final String appKey,
    		final long messageId,
    		final String... registrationIds
    		) {
    	
    	ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://report.jpush.cn");
        JPushClient jpushClient = new JPushClient(
        		masterSecret, 
        		appKey, 
        		null,
        		config);

        CheckMessagePayload payload = 
        		CheckMessagePayload.newBuilder()
                .setMsgId(messageId)
                .addRegistrationIds(registrationIds)
                //.setDate("2018-07-12")//��ѡ����������ָ������
                .build();
        
        try {
        
        	Map<String, MessageStatus> map =
            		jpushClient.getMessageStatus(payload);
            System.out.println("map.size()=="+map.size());
            
            for (Map.Entry<String, MessageStatus> entry : map.entrySet()) {
                System.out.println("RegistrationId: " + entry.getKey() + " status: " + entry.getValue().getStatus());
            }
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
           e.printStackTrace();
        }
    }
    
    /**
     * ��ά����Ϣͳ��
     */
    public static void getReportMessages() {
        try {
            MessagesResult result = jpushClient.getReportMessages(
            	String.valueOf(Condition.MSG_ID)
            );
            List<MessagesResult.Message> messageList=result.messages;
            
            for(Message message:messageList) {
            
            	LOG.info("Android����Ŀ����="+message.android.target);
            	LOG.info("����������="+message.android.online_push);
            	LOG.info("�����ʹ���="+message.android.received);
            	LOG.info("�û����������="+message.android.click);//����ͳ�ƻ����ӳ�
            	LOG.info("�Զ�����Ϣ�����   ="+message.android.msg_click);///���ʹ�����ͳ����
         
            	LOG.info("iOS APNs֪ͨ����Ŀ����="+message.ios.apns_target);
            	LOG.info("APNs֪ͨ���ͳɹ���="+message.ios.apns_sent);
            	LOG.info("APNs֪ͨ�ʹ��� ="+message.ios.apns_received);
            	LOG.info("�û����������="+message.ios.click);
            	LOG.info("�Զ�����ϢĿ����="+message.ios.target);
            	LOG.info("�Զ�����Ϣ�ʹ���="+message.ios.received);
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
	 * ��ѯ�û�ͳ������
	 * �ṩ��2������ĳʱ��ε��û����ͳ�����ݣ������û��������û�����Ծ�û�
	 */
    public static void getReportUsers() {
    
        try {
            UsersResult result = jpushClient.getReportUsers(
            		TimeUnit.DAY,//ʱ�䵥λ
            		"2018-07-10",//��ʼʱ��
            		3//����ʱ��
            );
            LOG.debug("���ؽ��=" + result);
            
            List<UsersResult.User> lists=result.items;
            int i=0;
            if(lists.isEmpty()==false) {
            	for(UsersResult.User user:lists) {
                	LOG.debug("ʱ��"+(++i));
                	if(user.android!=null) {
                		LOG.debug("Android�����û�="+user.android.add);
                    	LOG.debug("Android�����û�="+user.android.online);
                    	LOG.debug("Android��Ծ�û�="+user.android.active);///Ϊ��û��ʵ��ͳ�Ʒ���APIҲ�������ͳ���
                	}
                	
                	if(user.ios!=null) {
                		LOG.debug("iOS�����û�="+user.ios.add);
                    	LOG.debug("iOS�����û�="+user.ios.online);
                    	LOG.debug("iOS��Ծ�û�="+user.ios.active);
                	}
                }
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
}
