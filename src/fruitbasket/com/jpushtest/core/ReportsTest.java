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
 * 获取推送消息的统计消息
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
	 * 消息送达统计
	 * 查询一条消息的送达统计数据
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
                	 * 1.推送App通知时，返回了此App通知的送达数
                	 * 2.推送自定义消息时，返回了此自定义消息的送达数
                	 * 3.同时推送App通知和自定义消息时，返回值跟1相同，跟2有可能不同，因为客户端肯能集成了厂商推送同时JPush进程没有在运行
                	 */
                	LOG.info("Android送达数="+received.android_received);
                	
                	LOG.info("App通知推送到APNs成功数="+received.ios_apns_sent);
                	LOG.info("通知由APNs送达到iOS设备数="+received.ios_apns_received);
                	LOG.info("iOS自定义消息送达设备数="+received.ios_msg_received);
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
     * 送达状态查询
     * 查询一条消息在单个或一组设备上的送达状态
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
                //.setDate("2018-07-12")//可选参数，用于指定日期
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
     * 多维度消息统计
     */
    public static void getReportMessages() {
        try {
            MessagesResult result = jpushClient.getReportMessages(
            	String.valueOf(Condition.MSG_ID)
            );
            List<MessagesResult.Message> messageList=result.messages;
            
            for(Message message:messageList) {
            
            	LOG.info("Android推送目标数="+message.android.target);
            	LOG.info("在线推送数="+message.android.online_push);
            	LOG.info("推送送达数="+message.android.received);
            	LOG.info("用户点击推送数="+message.android.click);//这项统计会有延迟
            	LOG.info("自定义消息点击数   ="+message.android.msg_click);///如何使用这个统计项
         
            	LOG.info("iOS APNs通知推送目标数="+message.ios.apns_target);
            	LOG.info("APNs通知推送成功数="+message.ios.apns_sent);
            	LOG.info("APNs通知送达数 ="+message.ios.apns_received);
            	LOG.info("用户点击推送数="+message.ios.click);
            	LOG.info("自定义消息目标数="+message.ios.target);
            	LOG.info("自定义消息送达数="+message.ios.received);
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
	 * 查询用户统计数据
	 * 提供近2个月内某时间段的用户相关统计数据：新增用户、在线用户、活跃用户
	 */
    public static void getReportUsers() {
    
        try {
            UsersResult result = jpushClient.getReportUsers(
            		TimeUnit.DAY,//时间单位
            		"2018-07-10",//起始时间
            		3//持续时长
            );
            LOG.debug("返回结果=" + result);
            
            List<UsersResult.User> lists=result.items;
            int i=0;
            if(lists.isEmpty()==false) {
            	for(UsersResult.User user:lists) {
                	LOG.debug("时间"+(++i));
                	if(user.android!=null) {
                		LOG.debug("Android新增用户="+user.android.add);
                    	LOG.debug("Android在线用户="+user.android.online);
                    	LOG.debug("Android活跃用户="+user.android.active);///为何没有实现统计分析API也会有这个统计项？
                	}
                	
                	if(user.ios!=null) {
                		LOG.debug("iOS新增用户="+user.ios.add);
                    	LOG.debug("iOS在线用户="+user.ios.online);
                    	LOG.debug("iOS活跃用户="+user.ios.active);
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
