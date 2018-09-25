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
	
	protected static final Logger LOG = LoggerFactory.getLogger(PushTest.class);
		
    public static final String TITLE = "Title: from JpushSDKTest";//������Ϣ�ı���
	public static final String ALERT = "Content: Hello world!";//������Ϣ������
	
	public static final String MSG_CONTENT = "Content: I am Study Z!";//�Զ�����Ϣ����
	
	private static JPushClient jpushClient;
	private static CIDResult cidResult;
	
	static {
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName(Condition.PUSH_HOST);
		jpushClient = new JPushClient(Condition.MASTER_SECRET, Condition.APP_KEY, null, config);
		
		try {
			//��ȡCID�б�
			cidResult=jpushClient.getCidList(
					10, //Cid����
					"push"//����
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
	 * ����App֪ͨ�������豸
	 */
	public static void sendPushToAll(
			String masterSecret,
			String appKey,
			String alert//֪ͨ����
			) {
		
		ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        
        JPushClient jpushClient = new JPushClient(
				masterSecret,//Ӧ�õ�MasterSecret�������ڼ����̨�п���
				appKey,//Ӧ�õ�AppKey�������ڼ����̨�п���
				null, 
				config
		);
		
        //����һ������
		PushPayload payload =PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(Notification.alert(alert))
				.build();
  
        try {
        	//�����ͷ��ͳ�ȥ
            PushResult result = jpushClient.sendPush(payload);

            LOG.info("���ؽ����");
            LOG.info("Message ID��"+result.msg_id);
            LOG.info("�����룺"+result.getResponseCode());
            LOG.info("״̬�룺"+result.statusCode);
            LOG.info("ԭʼ���ݣ�"+result.getOriginalContent());
            
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
	 * ����ָ����Ϣ
	 */
	public static void sendPush() {
		
		//ʹ�ò�ͬ��PushPayload����ʵ�ֲ�ͬ������
        PushPayload payload =
        		buildPushObject_16();
  
        try {
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();

            LOG.info("���ؽ����");
            LOG.info("Message ID��"+result.msg_id);
            LOG.info("�����룺"+result.getResponseCode());
            LOG.info("״̬�룺"+result.statusCode);
            LOG.info("ԭʼ���ݣ�"+result.getOriginalContent());
            
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
	 * ��������ƽ̨�������豸������ΪALERT
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
	 * Ŀ�꣺Registration ID
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
								//JPushCondition.REGISTRATION_ID_1,//ָ��registration id
								//JPushCondition.REGISTRATION_ID_2
								//�����ڴ˴���������RegistrationID
								)
				)
				.setNotification(Notification.alert(ALERT))
				.build();
	}
	
	/**
	 * Ŀ�꣺����
	 * @return
	 */
	private static PushPayload buildPushObject_2() {
		
		String[] alias= {
				Condition.ALIAS_1,
				};
		
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))//����
                .setNotification(Notification.alert(ALERT))
                .build();
    }
	
	/**
	 * Ŀ�꣺��ǩ
	 * @return
	 */
	private static PushPayload buildPushObject_3() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())//����Androidƽ̨
                .setAudience(
                		Audience.tag(
                				Condition.TAG_1,
                				Condition.TAG_2)
                		)//��ǩ
                .setNotification(Notification.alert(ALERT))
                .build();
    }
	
	/**
	 *  Android
	 * ָ��������ʽ��š�����Extra
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
		                            
		                            .setBuilderId(3)//ָ����ʽ���
		                            .addExtra("name", "StudyZ").build()//����֪ͨ�����Extra�ֶ�
		                    )
		                    .build())
		
				.build();
	}
	
	/**
	 * �Զ�����Ϣ
	 * @return
	 */
	private static PushPayload buildPushObject_5() {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				//�Զ�����Ϣ
				.setMessage(
						Message.newBuilder()
						.setMsgContent(MSG_CONTENT)//��Ϣ����
						.addExtra("extra", "JPush message")
						.build()
				)
				.build();
	}
	
	/**
	 * Ŀ�꣺��ǩ������
	 * �Զ�����Ϣ
	 * @return
	 */
	private static PushPayload buildPushObject_6() {
		return PushPayload.newBuilder()
					.setPlatform(Platform.android_ios())//����iOSƽ̨
					
					.setAudience(
						Audience.newBuilder()
							.addAudienceTarget(AudienceTarget.tag(Condition.TAG_1, Condition.TAG_2))//ָ��Ŀ��TAGΪTAG_1��TAG_2�Ĳ���
							.addAudienceTarget(AudienceTarget.alias(Condition.ALIAS_1, Condition.ALIAS_2))//ָ��Ŀ��ALIASΪALIAS_1��ALIAS_2�Ĳ���
							.build()
						)
					
					//�Զ�����Ϣ
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
		
		//����JSON����
        String payloadString="";///�޷�������ȷ��Json
        
        PushPayload payload = gson.fromJson(payloadString, PushPayload.class);
        return payload;
		
	}
	
	/**
	 * ͬʱ����App֪ͨ���Զ�����Ϣ
	 * 
	 * ˵����
	 * Android�ͻ��˻�չʾ������
	 * @return
	 */
	private static PushPayload buildPushObject_8() {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				
				//�½�����֪ͨ
				.setNotification(Notification.newBuilder()
		                    .setAlert(ALERT)
		                    .addPlatformNotification(
		                    		AndroidNotification.newBuilder()
		                            .setTitle(TITLE)
		                            .build())
		                    .build())
				
				//�Զ�����Ϣ
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
				
				.setNotification(Notification.newBuilder()//�½�����֪ͨ
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
	 * ��Android��iOSͬʱ������Ϣ
	 */
	public static PushPayload buildPushObject_11() {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				
				//�½�����֪ͨ
				.setNotification(Notification.newBuilder()
		                    .setAlert(ALERT)
		                    
		                    .addPlatformNotification(
		                    		AndroidNotification.newBuilder()
		                            .setTitle(TITLE)
		                            .addExtra("name", "StudyZ")//����֪ͨ�����Extra�ֶ�
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
	 * ʹ��CID
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
	 * ʹ���û���Ⱥ����
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
	 * �ڻ�Ϊ��������ת��ָ����Activity                 
 	 * @return
	 */
	private static PushPayload buildPushObject_14() {
		//ָ�������Ϊ���ʹ򿪵�Activity
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
	 * ��FCM����ת��ָ����Activity 
	 */
	private static PushPayload buildPushObject_15() {
		//ָ�����FCM֪ͨʱ�򿪵�Activity
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
	 * ���͵�APNs��������
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
	 * ʹ�ö��Ų���
	 */
	public static void testSendSms() {
		final long  tempID=152316;//����ģ��id
		
		try {
			
			SMS sms=SMS.newBuilder()
					.setTempID(tempID)
					.setDelayTime(3)
					//.addPara(key, value)//��ӱ������еĶ�������Ҫ�õ�����
					.build();
			
			PushResult result = jpushClient.sendAndroidNotificationWithRegistrationID(
					"����֪ͨ��Ӧ�����͵ı���",
					"����֪ͨ��Ӧ�����͵�����", 
					sms,
					null, 
					Condition.REGISTRATION_ID_2);
			
			LOG.info("���ý����" + result);
		
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
	 * ʹ��Ӧ�÷�����������
	 */
	public static void groupPush(
			String groupMastersecret,
			String groupAppKey,
			String alert//֪ͨ����
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
	 * ����APNs����
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
				.setNotification(Notification.alert(ALERT))//֪ͨ�����ַ���
				.setOptions(Options.newBuilder()
						.setApnsProduction(false)//false:���Ի�����true����������
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
	 * ���ñ���ָ������Ŀ��
	 * @param masterSecret
	 * @param appKey
	 * @param alert ֪ͨ����
	 * @param alias ���������Դ��뵥���������������
	 */
	public static void sendToAlias(final String masterSecret,final String appKey,final String alert,final String ... alias) {
		ClientConfig config = ClientConfig.getInstance();
	    config.setPushHostName("https://api.jpush.cn");
		JPushClient jpushClient = new JPushClient(Condition.MASTER_SECRET, Condition.APP_KEY, null, config);
	
        PushPayload payload=PushPayload.newBuilder()
        	.setPlatform(Platform.all())
            .setAudience(Audience.alias(alias))//���Դ��뵥���������������
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
	 * ����time_to_live
	 */
	public static void sendPush0(
			final String masterSecret,
			final String appKey,
			final String title,//֪ͨ����
			final String alert//֪ͨ����
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
				.setNotification(Notification.newBuilder()//�½�����֪ͨ
                    .setAlert(alert)
                    .addPlatformNotification(
                    		AndroidNotification.newBuilder()
                            .setTitle(title)
                            .build())
                    .build())
				.setOptions(Options.newBuilder()
						.setTimeToLive(120)//����������Ϣʱ��
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
