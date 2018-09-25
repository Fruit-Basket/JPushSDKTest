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
 * ���͹��ܲ���
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
	 * ����App֪ͨ�������豸
	 * @param masterSecret
	 * @param appKey
	 * @param alert ֪ͨ����
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
	
        //����һ������
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.all())//ָ������ƽ̨������Android��iOS
				.setAudience(Audience.all())//ָ��ȫ��ͻ���
				.setNotification(Notification.alert(alert))
				.build();
  
        try {
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * ָ������Ŀ�꣺Registration ID
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
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * ָ������Ŀ�꣺����
	 * @param masterSecret
	 * @param appKey
	 * @param alias ����
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
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * ָ������Ŀ�꣺��ǩ
	 * @param masterSecret
	 * @param appKey
	 * @param tags ��ǩ
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
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * ָ������Ŀ�꣺ͬʱʹ�ñ����ͱ�ǩ
	 * @param masterSecret
	 * @param appKey
	 * @param alias_1 ����1
	 * @param alias_2 ����2
	 * @param tags ��ǩ
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
        				.addAudienceTarget(AudienceTarget.alias(alias_1, alias_2))//ָ��Ŀ��Ϊalias_1��alias_2�Ĳ���
						.addAudienceTarget(AudienceTarget.tag(tags))//ָ��Ŀ��Ϊtags�Ĳ���
						.build())
        		.setNotification(Notification.alert("by RegistrationIds"))
        		.build();
  
        try {
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * ����ƽ̨��Android
	 * @param masterSecret Ӧ�õ�MasterSecret�������ڼ����̨�п���
	 * @param appKey Ӧ�õ�AppKey�������ڼ�������в�ѯ��
	 * @param title ֪ͨ����
	 * @param alert ֪ͨ����
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
	
        //����һ������
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.android())//ָ��Androidƽ̨
				.setAudience(Audience.all())//ָ��ȫ��ͻ���
				.setNotification(Notification.newBuilder()
	                    .setAlert(alert)
	                    //�½�Android֪ͨ
	                    .addPlatformNotification(
	                    		AndroidNotification.newBuilder()//Android 
	                            .setTitle(title)
	                            .addExtra("name", "StudyZ").build()//����֪ͨ�����Extra�ֶ�
	                    )
	                    .build())
				
				.build();
  
        try {
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * ָ���������ݣ�Android��iOS
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
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	/**
	 * �Զ�����Ϣ
	 * @param masterSecret
	 * @param appKey
	 * @param content �Զ�����Ϣ����
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
        		//�Զ�����Ϣ
				.setMessage(
						Message.newBuilder()
						.setMsgContent(content)//��Ϣ����
						.addExtra("extra", "JPush message")
						.build()
				)
        		.build();
  
        try {
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * ͬʱ����App֪ͨ���Զ�����Ϣ
	 * 
	 * ˵����
	 * Android�ͻ��˻�չʾ������
	 * 
	 * @param masterSecret
	 * @param appKey
	 * @param alert ֪ͨ����
	 * @param content �Զ�����Ϣ����
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
        		//�½�����֪ͨ
				.setNotification(Notification.newBuilder()
		                    .setAlert(alert)
		                    .build())
				
				//�Զ�����Ϣ
				.setMessage(
						Message.newBuilder()
						.setMsgContent(content)
						.build()
				)
        		.build();
  
        try {
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * �ڻ�Ϊ��������ת��ָ����Activity  
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
	
        //ָ�������Ϊ���ʹ򿪵�Activity
      	final String activityUri="com.example.jiguang.jpusttest.HuaweiActivity";
      	
        //����һ������
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.all())//ָ��ȫ��ͻ���
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
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * ��FCM��������ת��ָ����Activity  
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
	
       //ָ�����FCM֪ͨʱ�򿪵�Activity
       final String uriAction="com.example.jiguang.jpusttest.FCMActivity";
      		
        //����һ������
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.all())//ָ��ȫ��ͻ���
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
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * ʹ��JSON
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
		
		//����JSON����
        String payloadString="";///�޷�������ȷ��JSON
        
        PushPayload payload = gson.fromJson(payloadString, PushPayload.class);
  
        try {
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}

	/**
	 * ����time_to_live
	 * @param masterSecret
	 * @param appKey
	 * @param timeToLive ������Ч�ڣ�����Ϊ��λ��
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
			//�����ͷ��ͳ�ȥ
            PushResult result=jpushClient.sendPush(payload);
			jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ʹ��Cid
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
        	//��ȡCID�б�
            CIDResult cidResult=jpushClient.getCidList(
        					10, //Cid����
        					"push"//����
        			);
    	
            //����һ������
    		PushPayload payload =PushPayload.newBuilder()
    				.setPlatform(Platform.all())//ָ������ƽ̨������Android��iOS
    				.setAudience(Audience.all())//ָ��ȫ��ͻ���
    				.setNotification(Notification.alert("use cid"))
    				.setCid(cidResult.cidlist.get(0))
    				.build();
    		
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * �û���Ⱥ����
	 * @param masterSecret
	 * @param appKey
	 * @param gourpIds �û���ȺID�������ڼ��������ȡ��
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
	
        //����һ������
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.all())//ָ������ƽ̨������Android��iOS
				.setAudience(Audience.segment(groupIds))
				.setNotification(Notification.alert("by group push"))
				.build();
  
        try {
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * ���Ų���
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
        
        //��������
		SMS sms=SMS.newBuilder()
				.setTempID(152316)//����ģ��id
				.setDelayTime(3)
				//.addPara(key, value)//��ӱ������еĶ�������Ҫ�õ�����
				.build();
	
        //����һ������
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.all())//ָ������ƽ̨������Android��iOS
				.setAudience(Audience.all())//ָ��ȫ��ͻ���
				.setNotification(Notification.alert("���Ų���"))
				.setSMS(sms)
				.build();
  
        try {
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	

	/**
	 * ����APNs����
	 * @param masterSecret
	 * @param appKey
	 * @param alert ֪ͨ����
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
	
        //����һ������
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert(alert))
				
				.setOptions(Options.newBuilder()
						.setApnsProduction(false)//false:���Ի�����true����������
						.build())
				
				.build();
  
        try {
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            
            System.out.println("���ؽ����");
            System.out.println("Message ID��"+result.msg_id);
            System.out.println("�����룺"+result.getResponseCode());
            System.out.println("״̬�룺"+result.statusCode);
            System.out.println("ԭʼ���ݣ�"+result.getOriginalContent());
            
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
	}
	
}
