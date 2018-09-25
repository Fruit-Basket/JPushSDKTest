package fruitbasket.com.jpushtest.core;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.BooleanResult;
import cn.jiguang.common.resp.DefaultResult;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.AliasDeviceListResult;
import cn.jpush.api.device.OnlineStatus;
import cn.jpush.api.device.TagAliasResult;
import cn.jpush.api.device.TagListResult;
import fruitbasket.com.jpushtest.Condition;

/**
 * Device API 
 * �����ڷ������˲�ѯ�����á����¡�ɾ���豸�� tag��alias
 * @author jiguang
 *
 */
public class DeviceTest {
	
	protected static final Logger LOG = LoggerFactory.getLogger(DeviceTest.class);

	private static final DeviceTest instance=new DeviceTest();
	
	private static JPushClient jpushClient =
			new JPushClient(Condition.MASTER_SECRET, Condition.APP_KEY);
	
	private static final String registrationId=Condition.REGISTRATION_ID_3;
    
	private DeviceTest() {}
	
	public DeviceTest getInstance() {
		return instance;
	}
	
	/**
	 * ��ѯ�豸��Tag��Alias
	 */
	public static void getDeviceTagAlias(
			final String masterSecret,
			final String appKey,
			final String registrationID) {
		
		 JPushClient jpushClient =
					new JPushClient(masterSecret,appKey);
		try {
			TagAliasResult result = jpushClient.getDeviceTagAlias(
					registrationID
			);
			System.out.println("RegistrationID="
					+registrationID
			);
			System.out.println("����="+result.alias);
			System.out.println("��ǩ="+result.tags.toString());
			
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����ָ��RegistrationID��Alias��Tag
	 * @param masterSecret
	 * @param appKey
	 * @param registrationID
	 * @param alias
	 * @param tagsToAdd
	 * @param tagsToRemove
	 */
	public static void updateDeviceTagAlias(
			final String masterSecret,
			final String appKey,
			final String registrationID,
			final String alias,
			final Set<String> tagsToAdd,
			final Set<String> tagsToRemove) {
		
		JPushClient jpushClient =
				new JPushClient(masterSecret,appKey);
		
		try {
			
			DefaultResult result =  jpushClient.updateDeviceTagAlias(
					registrationID,
					alias,
					tagsToAdd, 
					tagsToRemove
			);
			
			System.out.println("Got result " + result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ָ��RegistrationID������Alias��Tag
	 */
	public static void clearDeviceTagAlias() {
		try {
			DefaultResult result =  jpushClient.updateDeviceTagAlias(
					registrationId,
					true,//������б���
					true//������б�ǩ
			);
			
			System.out.println("Got result " + result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ѯ������Ӧ���豸
	 * ע�⣬�ݲ��ܲ�ѯ��ǩ��Ӧ���豸
	 */
	public static void getAliasDeviceList(
			final String masterSecret,
			final String appKey,
			final String alias) {
		JPushClient jpushClient =
				new JPushClient(masterSecret,appKey);
		try {
			AliasDeviceListResult result1=jpushClient.getAliasDeviceList(
					alias,
					"android"
			);
			int i=1;
			System.out.println("Android�豸��");
			for(String registrationID:result1.registration_ids) {
				System.out.println(i+": "+registrationID);
				i++;
			}
			
			AliasDeviceListResult result2=jpushClient.getAliasDeviceList(
					alias,
					"ios"
			);
			i=1;
			System.out.println("iOS�豸��");
			for(String registrationID:result2.registration_ids) {
				System.out.println(i+": "+registrationID);
				i++;
			}
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ���������Լ��ñ������豸�Ķ�Ӧ��ϵ
	 */
	public static void deleteAlias() {
		try {
			DefaultResult result=jpushClient.deleteAlias(
					Condition.ALIAS_1,
					null
		    );
			LOG.info("Got result " + result);
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
	 * ��JPush�������д�ŵ������Ӧ���µ����б�ǩ
	 */
	public static void getTagList() {
		try {
			TagListResult result=jpushClient.getTagList();
			
			StringBuilder stringBuilder=new StringBuilder("���б�ǩ��");
			for(String tag:result.tags) {
				stringBuilder.append(tag+"��");
			}
			LOG.info(stringBuilder.toString());
			
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
	 * ��ѯ�ض��豸�Ƿ����ض���ǩ
	 */
	public static void isDeviceInTag() {
		
		try {
			BooleanResult result=jpushClient.isDeviceInTag(
					Condition.TAG_1,
					registrationId
			);
			
			LOG.info(registrationId+"�Ƿ���"+Condition.TAG_1+": "+result.result);
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���±�ǩ��Ӧ���豸
	 */
	public static void addRemoveDevicesFromTag() {
		
		Set<String> addSet=new HashSet<>();
		
		Set<String> removeSet=new HashSet<>();
		removeSet.add(Condition.REGISTRATION_ID_1);
		removeSet.add(Condition.REGISTRATION_ID_2);
		removeSet.add(Condition.REGISTRATION_ID_3);
		
		try {
			
			DefaultResult result=jpushClient.addRemoveDevicesFromTag(
					Condition.TAG_1,
					addSet,
					removeSet
			);
			if(result.isResultOK()==true) {
				LOG.info("OK");
			}
			else {
				LOG.info("ʧ��");
			}
		} catch (APIConnectionException | APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ����ǩ���Լ����豸�İ󶨹�ϵ
	 */
	public static void deleteTag() {
		try {
			
			DefaultResult result=jpushClient.deleteTag(
					Condition.TAG_1,
					"android"
			);
			
			if(result.isResultOK()==true) {
				LOG.info("OK");
			}
			else {
				LOG.info("ʧ��");
			}
			
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * ��ѯ�豸������״̬���������ʱ��
	 * 
	 * �ֶ�online��10min���Ƿ�����
	 * �ֶ�last_online_time����online==falseʱ����ʾ�������������ʱ�䣻��������ڲ�������Ϊfalse
	 */
	public static void getUserOnlineStatus() {
		try {
			Map<String, OnlineStatus> result =  jpushClient.getUserOnlineStatus(
					Condition.REGISTRATION_ID_1,
					Condition.REGISTRATION_ID_2,
					Condition.REGISTRATION_ID_3
			);

			LOG.info(Condition.REGISTRATION_ID_1+": "+result.get(Condition.REGISTRATION_ID_1).toString());
			LOG.info(Condition.REGISTRATION_ID_2+": "+result.get(Condition.REGISTRATION_ID_2).toString());
			LOG.info(Condition.REGISTRATION_ID_3+": "+result.get(Condition.REGISTRATION_ID_3).toString());
			
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
	 * ��registrationId���ֻ���
	 */
	public static void bindMobile() {
		try {
			DefaultResult result =  jpushClient.bindMobile(
					Condition.TAG_1,
					"13728860097"
					///���ȡ�����ֻ����룿
					//����null��""��û������
			);
			LOG.info("���ؽ����" + result);
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


