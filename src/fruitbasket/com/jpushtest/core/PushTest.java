package fruitbasket.com.jpushtest.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.CIDResult;
import cn.jpush.api.push.GroupPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.SMS;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.InterfaceAdapter;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;
import fruitbasket.com.jpushtest.Condition;

/**
 * 推送功能测试
 * @author jiguang
 *
 */
public class PushTest {
	
	private static final PushTest instance=new PushTest();
	
	protected static final Logger LOG = LoggerFactory.getLogger(PushTest.class);
		
    public static final String TITLE = "Title: from JpushSDKTest";//推送消息的标题
	public static final String ALERT = "Content: Hello world!";//推送消息的内容
	
	public static final String MSG_CONTENT = "Content: I am Study Z!";//自定义消息内容
	
	private static JPushClient jpushClient;
	private static CIDResult cidResult;
	
	static {
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName(Condition.PUSH_HOST);
		jpushClient = new JPushClient(Condition.MASTER_SECRET, Condition.APP_KEY, null, config);
		
		try {
			//获取CID列表
			cidResult=jpushClient.getCidList(
					10, //Cid数量
					"push"//类型
			);
			
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	
	private PushTest() {}
	
	public PushTest getInstance() {
		return instance;
	}
	
	/**
	 * 推送App通知到所有设备
	 */
	public static void sendPushToAll(
			String masterSecret,
			String appKey,
			String alert//通知内容
			) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,//应用的MasterSecret，可以在极光后台中看到
				appKey,//应用的AppKey，可以在极光后台中看到
				null, 
				config
		);
		
        //构建一个推送
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert(alert))
				.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);

            LOG.info("返回结果：");
            LOG.info("Message ID："+result.msg_id);
            LOG.info("返回码："+result.getResponseCode());
            LOG.info("状态码："+result.statusCode);
            LOG.info("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            LOG.error("APIConnectionException");
        } catch (APIRequestException e) {
            LOG.error("APIRequestException");
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
	}
	
	/**
	 * 推送指定消息
	 */
	public static void sendPush() {
		
		//使用不同的PushPayload，以实现不同的推送
        PushPayload payload =
        		buildPushObject_16();
  
        try {
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();

            LOG.info("返回结果：");
            LOG.info("Message ID："+result.msg_id);
            LOG.info("返回码："+result.getResponseCode());
            LOG.info("状态码："+result.statusCode);
            LOG.info("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }
	
	/**
	 * 面向所有平台、所有设备，内容为ALERT
	 * @return 
	 */
	private static PushPayload buildPushObject_0() {
        return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert(ALERT))
				.build();
    }
	
	/**
	 * 目标：Registration ID
	 * @return
	 */
	private static PushPayload buildPushObject_1() {
		String[] regIds={
				Condition.REGISTRATION_ID_1,
				Condition.REGISTRATION_ID_2
		};
		
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(
						Audience.registrationId(
								regIds
								//JPushCondition.REGISTRATION_ID_1,//指定registration id
								//JPushCondition.REGISTRATION_ID_2
								//可以在此处传入更多的RegistrationID
								)
				)
				.setNotification(Notification.alert(ALERT))
				.build();
	}
	
	/**
	 * 目标：别名
	 * @return
	 */
	private static PushPayload buildPushObject_2() {
		
		String[] alias= {
				Condition.ALIAS_1,
				};
		
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))//别名
                .setNotification(Notification.alert(ALERT))
                .build();
    }
	
	/**
	 * 目标：标签
	 * @return
	 */
	private static PushPayload buildPushObject_3() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())//面向Android平台
                .setAudience(
                		Audience.tag(
                				Condition.TAG_1,
                				Condition.TAG_2)
                		)//标签
                .setNotification(Notification.alert(ALERT))
                .build();
    }
	
	/**
	 *  Android
	 * 指定推送样式编号、增加Extra
	 * @return
	 */
	private static PushPayload buildPushObject_4() {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				
				.setNotification(Notification.newBuilder()
		                    .setAlert(ALERT)
		                    .addPlatformNotification(
		                    		AndroidNotification.newBuilder()//Android 
		                            .setTitle(TITLE)//
		                            
		                            .setBuilderId(3)//指定样式编号
		                            .addExtra("name", "StudyZ").build()//推送通知中添加Extra字段
		                    )
		                    .build())
		
				.build();
	}
	
	/**
	 * 自定义消息
	 * @return
	 */
	private static PushPayload buildPushObject_5() {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				//自定义消息
				.setMessage(
						Message.newBuilder()
						.setMsgContent(MSG_CONTENT)//消息内容
						.addExtra("extra", "JPush message")
						.build()
				)
				.build();
	}
	
	/**
	 * 目标：标签、别名
	 * 自定义消息
	 * @return
	 */
	private static PushPayload buildPushObject_6() {
		return PushPayload.newBuilder()
					.setPlatform(Platform.android_ios())//面向iOS平台
					
					.setAudience(
						Audience.newBuilder()
							.addAudienceTarget(AudienceTarget.tag(Condition.TAG_1, Condition.TAG_2))//指定目标TAG为TAG_1和TAG_2的并集
							.addAudienceTarget(AudienceTarget.alias(Condition.ALIAS_1, Condition.ALIAS_2))//指定目标ALIAS为ALIAS_1和ALIAS_2的并集
							.build()
						)
					
					//自定义消息
					.setMessage(
						Message.newBuilder()
							.setMsgContent(MSG_CONTENT)
							.addExtra("extra", "JPush message")
							.build()
						)
					.build();
	}
	
	/**
	 * JSON
	 * @return
	 */
	private static PushPayload buildPushObject_7() {
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(PlatformNotification.class, new InterfaceAdapter<PlatformNotification>())
                .create();
		
		//根据JSON构建
        String payloadString="";///无法构建正确的Json
        
        PushPayload payload = gson.fromJson(payloadString, PushPayload.class);
        return payload;
		
	}
	
	/**
	 * 同时推送App通知、自定义消息
	 * 
	 * 说明：
	 * Android客户端会展示此推送
	 * @return
	 */
	private static PushPayload buildPushObject_8() {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				
				//新建推送通知
				.setNotification(Notification.newBuilder()
		                    .setAlert(ALERT)
		                    .addPlatformNotification(
		                    		AndroidNotification.newBuilder()
		                            .setTitle(TITLE)
		                            .build())
		                    .build())
				
				//自定义消息
				.setMessage(
						Message.newBuilder()
						.setMsgContent(MSG_CONTENT)
						.build()
				)
				.build();
	}
	
	/**
	 * 
	 * @return
	 */
	private static PushPayload buildPushObject_9() {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				
				.setNotification(Notification.newBuilder()//新建推送通知
		                    .setAlert(ALERT)
		                    .addPlatformNotification(
		                    		AndroidNotification.newBuilder()
		                            .setTitle(TITLE)
		                            .build())
		                    .build())
				
				.setOptions(Options.newBuilder()
						.setTimeToLive(120)
						.build())
				.build();
	}
	
	/**
	 * 给Android和iOS同时发送消息
	 */
	public static PushPayload buildPushObject_11() {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				
				//新建推送通知
				.setNotification(Notification.newBuilder()
		                    .setAlert(ALERT)
		                    
		                    .addPlatformNotification(
		                    		AndroidNotification.newBuilder()
		                            .setTitle(TITLE)
		                            .addExtra("name", "StudyZ")//推送通知中添加Extra字段
		                            .build()
		                            )
		                    
		                    .addPlatformNotification(
		                    		IosNotification.newBuilder()
		                    		.setAlert(ALERT)
		                    		.build())
		                    .build())

				.build();
	}
	
	/**
	 * 使用CID
	 * @return
	 */
	public static PushPayload buildPushObject_12() {
		
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert(ALERT))
				.setCid(cidResult.cidlist.get(0))
				.build();
	}
	
	/**
	 * 使用用户分群推送
	 * @return
	 */
	public static PushPayload buildPushObject_13() {
		
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(
						Audience.segment(Condition.GROUP_1_ID)
						)
				.setNotification(Notification.alert(ALERT))
				.build();
	}
	
	/**
	 * 在华为推送中跳转到指定的Activity                 
 	 * @return
	 */
	private static PushPayload buildPushObject_14() {
		//指定点击华为推送打开的Activity
		final String activityUri="com.example.jiguang.jpusttest.HuaweiActivity";
		
		Notification notification=Notification
				.newBuilder()
				.setAlert(ALERT)
                .addPlatformNotification(
                		AndroidNotification.newBuilder()
                        .setTitle(TITLE)
                        .setUriActivity(activityUri)
                        .build())
                .build();   
		
		return PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.all())
				.setNotification(notification)
				.build();
	}
	
	/**
	 * 在FCM中跳转到指定的Activity 
	 */
	private static PushPayload buildPushObject_15() {
		//指定点击FCM通知时打开的Activity
		final String uriAction="com.example.jiguang.jpusttest.FCMActivity";
		
		Notification notification=Notification
				.newBuilder()
				.setAlert(ALERT)
                .addPlatformNotification(
                		AndroidNotification.newBuilder()
                        .setTitle(TITLE)
                        .setUriAction(uriAction)
                        .build())
                .build();   
		
		return PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.all())
				.setNotification(notification)
				.build();
		
	}
	
	/**
	 * 推送到APNs生产环境
	 * @return
	 */
	private static PushPayload buildPushObject_16() {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert(ALERT))
				.setOptions(Options.newBuilder()
						.setApnsProduction(false)
						.build())
				.build();
	}
	
	/**
	 * 使用短信补充
	 */
	public static void testSendSms() {
		final long  tempID=152316;//短信模板id
		
		try {
			
			SMS sms=SMS.newBuilder()
					.setTempID(tempID)
					.setDelayTime(3)
					//.addPara(key, value)//添加变量，有的短信中需要用到变量
					.build();
			
			PushResult result = jpushClient.sendAndroidNotificationWithRegistrationID(
					"短信通知对应的推送的标题",
					"短信通知对应的推送的内容", 
					sms,
					null, 
					Condition.REGISTRATION_ID_2);
			
			LOG.info("调用结果：" + result);
		
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
	 * 使用应用分组推送推送
	 */
	public static void groupPush(
			String groupMastersecret,
			String groupAppKey,
			String alert//通知内容
			) {
		
		GroupPushClient groupPushClient=new GroupPushClient(
				groupMastersecret,
				groupAppKey
				);
		
		PushPayload pushPayload=
				PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert(alert))
				.build();
		
		try {
			groupPushClient.sendGroupPush(pushPayload);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置APNs环境
	 * @param masterSecret
	 * @param appKey
	 */
	public static void test1(final String masterSecret,final String appKey) {
		ClientConfig config = ClientConfig.getInstance();
	    config.setPushHostName("https://api.jpush.cn");
		JPushClient jpushClient = new JPushClient(masterSecret,appKey, null, config);
		
		PushPayload load=PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert(ALERT))//通知内容字符串
				.setOptions(Options.newBuilder()
						.setApnsProduction(false)//false:测试环境；true：开发环境
						.build())
				.build();
		
		try {
			jpushClient.sendPush(load);
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 利用别名指定推送目标
	 * @param masterSecret
	 * @param appKey
	 * @param alert 通知内容
	 * @param alias 别名，可以传入单个别名或别名数组
	 */
	public static void sendToAlias(final String masterSecret,final String appKey,final String alert,final String ... alias) {
		ClientConfig config = ClientConfig.getInstance();
	    config.setPushHostName("https://api.jpush.cn");
		JPushClient jpushClient = new JPushClient(Condition.MASTER_SECRET, Condition.APP_KEY, null, config);
	
        PushPayload payload=PushPayload.newBuilder()
        	.setPlatform(Platform.all())
            .setAudience(Audience.alias(alias))//可以传入单个别名或别名数组
            .setNotification(Notification.alert(alert))
            .build();
        
        try {
			jpushClient.sendPush(payload);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置time_to_live
	 */
	public static void sendPush0(
			final String masterSecret,
			final String appKey,
			final String title,//通知标题
			final String alert//通知内容
			) {
		
		ClientConfig config = ClientConfig.getInstance();
	    config.setPushHostName("https://api.jpush.cn");
		JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey, 
				null, 
				config);
		
		PushPayload payload=PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.newBuilder()//新建推送通知
                    .setAlert(alert)
                    .addPlatformNotification(
                    		AndroidNotification.newBuilder()
                            .setTitle(title)
                            .build())
                    .build())
				.setOptions(Options.newBuilder()
						.setTimeToLive(120)//设置离线消息时间
						.build())
				.build();
		
		try {
			jpushClient.sendPush(payload);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
}
