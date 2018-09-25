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
	
	private PushTest() {}
	
	public PushTest getInstance() {
		return instance;
	}
	
	/**
	 * 推送App通知到所有设备
	 * @param masterSecret
	 * @param appKey
	 * @param alert 通知内容
	 */
	public static void sendPush0(String masterSecret,String appKey,String alert) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
	
        //构建一个推送
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.all())//指定所有平台，包括Android和iOS
				.setAudience(Audience.all())//指定全体客户端
				.setNotification(Notification.alert(alert))
				.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 指定推送目标：Registration ID
	 * @param masterSecret
	 * @param appKey
	 * @param RegistrationIds
	 */
	public static void sendPush1(String masterSecret,String appKey,String... RegistrationIds) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
		
        PushPayload payload=PushPayload.newBuilder()
        		.setPlatform(Platform.all())
        		.setAudience(Audience.registrationId(RegistrationIds))
        		.setNotification(Notification.alert("by RegistrationIds"))
        		.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 指定推送目标：别名
	 * @param masterSecret
	 * @param appKey
	 * @param alias 别名
	 */
	public static void sendPush2(String masterSecret,String appKey,String... alias) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
		
        PushPayload payload=PushPayload.newBuilder()
        		.setPlatform(Platform.all())
        		.setAudience(Audience.alias(alias))
        		.setNotification(Notification.alert("by alias"))
        		.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 指定推送目标：标签
	 * @param masterSecret
	 * @param appKey
	 * @param tags 标签
	 */
	public static void sendPush3(String masterSecret,String appKey,String... tags) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
		
        PushPayload payload=PushPayload.newBuilder()
        		.setPlatform(Platform.all())
        		.setAudience(Audience.tag(tags))
        		.setNotification(Notification.alert("by Tags"))
        		.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 指定推送目标：同时使用别名和标签
	 * @param masterSecret
	 * @param appKey
	 * @param alias_1 别名1
	 * @param alias_2 别名2
	 * @param tags 标签
	 */
	public static void sendPush4(String masterSecret,String appKey,String alias_1,String alias_2,String... tags) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
		
        PushPayload payload=PushPayload.newBuilder()
        		.setPlatform(Platform.all())
        		.setAudience(
        				Audience.newBuilder()
        				.addAudienceTarget(AudienceTarget.alias(alias_1, alias_2))//指定目标为alias_1和alias_2的并集
						.addAudienceTarget(AudienceTarget.tag(tags))//指定目标为tags的并集
						.build())
        		.setNotification(Notification.alert("by RegistrationIds"))
        		.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 推送平台：Android
	 * @param masterSecret 应用的MasterSecret，可以在极光后台中看到
	 * @param appKey 应用的AppKey，可以在极光控制中查询到
	 * @param title 通知标题
	 * @param alert 通知内容
	 */
	public static void sendPush5(String masterSecret,String appKey,String title,String alert) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
	
        //构建一个推送
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.android())//指定Android平台
				.setAudience(Audience.all())//指定全体客户端
				.setNotification(Notification.newBuilder()
	                    .setAlert(alert)
	                    //新建Android通知
	                    .addPlatformNotification(
	                    		AndroidNotification.newBuilder()//Android 
	                            .setTitle(title)
	                            .addExtra("name", "StudyZ").build()//推送通知中添加Extra字段
	                    )
	                    .build())
				
				.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 指定推送内容：Android与iOS
	 * @param masterSecret
	 * @param appKey
	 * @param title
	 * @param alert
	 */
	public static void sendPush6(String masterSecret,String appKey,String title,String alert) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
		
        PushPayload payload=PushPayload.newBuilder()
        		.setPlatform(Platform.all())
        		.setAudience(Audience.all())
        		.setNotification(Notification.newBuilder()
	                    .setAlert(alert)
	                    
	                    .addPlatformNotification(
	                    		AndroidNotification.newBuilder()
	                            .setTitle(title)
	                            .build()
	                            )
	                    
	                    .addPlatformNotification(
	                    		IosNotification.newBuilder()
	                    		.setAlert(alert)
	                    		.build())
	                    .build())
        		.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	/**
	 * 自定义消息
	 * @param masterSecret
	 * @param appKey
	 * @param content 自定义消息内容
	 */
	public static void sendPush7(String masterSecret,String appKey,String content) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
		
        PushPayload payload=PushPayload.newBuilder()
        		.setPlatform(Platform.all())
        		.setAudience(Audience.all())
        		//自定义消息
				.setMessage(
						Message.newBuilder()
						.setMsgContent(content)//消息内容
						.addExtra("extra", "JPush message")
						.build()
				)
        		.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 同时推送App通知、自定义消息
	 * 
	 * 说明：
	 * Android客户端会展示此推送
	 * 
	 * @param masterSecret
	 * @param appKey
	 * @param alert 通知内容
	 * @param content 自定义消息内容
	 */
	public static void sendPush8(String masterSecret,String appKey,String alert,String content) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
		
        PushPayload payload=PushPayload.newBuilder()
        		.setPlatform(Platform.all())
        		.setAudience(Audience.all())
        		//新建推送通知
				.setNotification(Notification.newBuilder()
		                    .setAlert(alert)
		                    .build())
				
				//自定义消息
				.setMessage(
						Message.newBuilder()
						.setMsgContent(content)
						.build()
				)
        		.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 在华为推送中跳转到指定的Activity  
	 * @param masterSecret 
	 * @param appKey
	 * @param title
	 * @param alert
	 */
	public static void sendPush9(String masterSecret,String appKey,String title,String alert) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
	
        //指定点击华为推送打开的Activity
      	final String activityUri="com.example.jiguang.jpusttest.HuaweiActivity";
      	
        //构建一个推送
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.all())//指定全体客户端
				.setNotification(Notification.newBuilder()
						.setAlert(alert)
		                .addPlatformNotification(
		                		AndroidNotification.newBuilder()
		                        .setTitle(title)
		                        .setUriActivity(activityUri)
		                        .build())
		                .build())
				
				.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 在FCM推送中跳转到指定的Activity  
	 * @param masterSecret 
	 * @param appKey
	 * @param title
	 * @param alert
	 */
	public static void sendPush10(String masterSecret,String appKey,String title,String alert) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
	
       //指定点击FCM通知时打开的Activity
       final String uriAction="com.example.jiguang.jpusttest.FCMActivity";
      		
        //构建一个推送
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.all())//指定全体客户端
				.setNotification(Notification
						.newBuilder()
						.setAlert(alert)
		                .addPlatformNotification(
		                		AndroidNotification.newBuilder()
		                        .setTitle(title)
		                        .setUriAction(uriAction)
		                        .build())
		                .build())
				
				.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 使用JSON
	 * @param masterSecret 
	 * @param appKey
	 */
	public static void sendPush11(String masterSecret,String appKey) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
	
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PlatformNotification.class, new InterfaceAdapter<PlatformNotification>())
                .create();
		
		//根据JSON构建
        String payloadString="";///无法构建正确的JSON
        
        PushPayload payload = gson.fromJson(payloadString, PushPayload.class);
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}

	/**
	 * 设置time_to_live
	 * @param masterSecret
	 * @param appKey
	 * @param timeToLive 推送有效期，以秒为单位·
	 */
	public static void sendPush12(final String masterSecret,final String appKey,final int timeToLive) {
		
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
				.setNotification(Notification.newBuilder()
                    .setAlert("set time_to_live")
                    .build())
				.setOptions(Options.newBuilder()
						.setTimeToLive(timeToLive)
						.build())
				.build();
		
		try {
			//将推送发送出去
            PushResult result=jpushClient.sendPush(payload);
			jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用Cid
	 * @param masterSecret
	 * @param appKey
	 */
	public static void sendPush13(String masterSecret,String appKey) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
        
        try {
        	//获取CID列表
            CIDResult cidResult=jpushClient.getCidList(
        					10, //Cid数量
        					"push"//类型
        			);
    	
            //构建一个推送
    		PushPayload payload =PushPayload.newBuilder()
    				.setPlatform(Platform.all())//指定所有平台，包括Android和iOS
    				.setAudience(Audience.all())//指定全体客户端
    				.setNotification(Notification.alert("use cid"))
    				.setCid(cidResult.cidlist.get(0))
    				.build();
    		
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 用户分群推送
	 * @param masterSecret
	 * @param appKey
	 * @param gourpIds 用户分群ID，可以在极光控制中取得
	 */
	public static void sendPush14(String masterSecret,String appKey,String... groupIds) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
	
        //构建一个推送
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.all())//指定所有平台，包括Android和iOS
				.setAudience(Audience.segment(groupIds))
				.setNotification(Notification.alert("by group push"))
				.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 短信补充
	 * @param masterSecret
	 * @param appKey
	 */
	public static void sendPush15(String masterSecret,String appKey) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
        
        //构建短信
		SMS sms=SMS.newBuilder()
				.setTempID(152316)//短信模板id
				.setDelayTime(3)
				//.addPara(key, value)//添加变量，有的短信中需要用到变量
				.build();
	
        //构建一个推送
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.all())//指定所有平台，包括Android和iOS
				.setAudience(Audience.all())//指定全体客户端
				.setNotification(Notification.alert("短信补充"))
				.setSMS(sms)
				.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
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
	 * @param alert 通知内容
	 */
	public static void sendPush16(String masterSecret,String appKey,String alert) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,
				appKey,
				null, 
				config
		);
	
        //构建一个推送
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert(alert))
				
				.setOptions(Options.newBuilder()
						.setApnsProduction(false)//false:测试环境；true：开发环境
						.build())
				
				.build();
  
        try {
        	//将推送发送出去
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("返回结果：");
            System.out.println("Message ID："+result.msg_id);
            System.out.println("返回码："+result.getResponseCode());
            System.out.println("状态码："+result.statusCode);
            System.out.println("原始内容："+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
}
