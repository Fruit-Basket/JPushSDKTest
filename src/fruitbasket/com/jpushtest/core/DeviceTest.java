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

	private static final DeviceTest instance=new DeviceTest();
	
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
	 * ���ָ��RegistrationId������Alias��Tag
	 */
	public static void clearDeviceTagAlias(
			final String masterSecret,
			final String appKey,
			final String RegistrationId) {
		
		JPushClient jpushClient =
				new JPushClient(masterSecret,appKey);
		
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
	public static void deleteAlias(
			final String masterSecret,
			final String appKey,
			final String alias) {
		
		JPushClient jpushClient =
				new JPushClient(masterSecret,appKey);
		
		try {
			DefaultResult result=jpushClient.deleteAlias(
					alias,
					null
		    );
			System.out.println("Got result " + result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��JPush�������д�ŵ������Ӧ���µ����б�ǩ
	 */
	public static void getTagList(
			final String masterSecret,
			final String appKey) {
		
		JPushClient jpushClient =
				new JPushClient(masterSecret,appKey);
		
		try {
			TagListResult result=jpushClient.getTagList();
			
			StringBuilder stringBuilder=new StringBuilder("���б�ǩ��");
			for(String tag:result.tags) {
				stringBuilder.append(tag+"��");
			}
			System.out.println(stringBuilder.toString());
			
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ѯ�ض��豸�Ƿ����ض���ǩ
	 */
	public static void isDeviceInTag(
			final String masterSecret,
			final String appKey,
		    final String tag,
		    final String registrationId) {
		
		JPushClient jpushClient =
				new JPushClient(masterSecret,appKey);
		
		try {
			BooleanResult result=jpushClient.isDeviceInTag(
					tag,
					registrationId
			);
			
			System.out.println(registrationId+"�Ƿ���"+Condition.TAG_1+": "+result.result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���±�ǩ��Ӧ���豸
	 */
	public static void addRemoveDevicesFromTag(
			final String masterSecret,
			final String appKey,
			final String tag,
			final String[] addRegistrationIds,
			final String[] removeRegistrationIds) {
		
		JPushClient jpushClient =
				new JPushClient(masterSecret,appKey);
		
		Set<String> addSet=new HashSet<>();
		for(String registrationId:addRegistrationIds) {
			addSet.add(registrationId);
		}
		
		Set<String> removeSet=new HashSet<>();
		for(String registrationId:removeRegistrationIds) {
			removeSet.add(registrationId);
		}
		
		try {
			
			DefaultResult result=jpushClient.addRemoveDevicesFromTag(
					tag,
					addSet,
					removeSet
			);
			if(result.isResultOK()==true) {
				System.out.println("OK");
			}
			else {
				System.out.println("ʧ��");
			}
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ����ǩ���Լ����豸�İ󶨹�ϵ
	 */
	public static void deleteTag(final String masterSecret,final String appKey) {
		
		JPushClient jpushClient =
				new JPushClient(masterSecret,appKey);
		
		try {
			
			DefaultResult result=jpushClient.deleteTag(
					Condition.TAG_1,
					"android"
			);
			
			if(result.isResultOK()==true) {
				System.out.println("OK");
			}
			else {
				System.out.println("ʧ��");
			}
			
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * ��ѯ�豸������״̬���������ʱ��
	 * 
	 * �ֶ�online��10min���Ƿ�����
	 * �ֶ�last_online_time����online==falseʱ����ʾ�������������ʱ�䣻��������ڲ�������Ϊfalse
	 */
	public static void getUserOnlineStatus(final String masterSecret,final String appKey,final String... registrationIds) {
		
		JPushClient jpushClient =
				new JPushClient(masterSecret,appKey);
		
		try {
			Map<String, OnlineStatus> result =  jpushClient.getUserOnlineStatus(
					registrationIds
			);

			for(String registration:registrationIds) {
				System.out.println(registrationId+": "+result.get(registrationIds).toString());
			}
			
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��registrationId���ֻ���
	 */
	public static void bindMobile(
			final String masterSecret,
			final String appKey,
			final String registrationId,
			final String mobileNumber) {
		
		JPushClient jpushClient =
				new JPushClient(masterSecret,appKey);
		try {
			DefaultResult result =  jpushClient.bindMobile(
					registrationId,
					mobileNumber
			);
			///���ȡ�����ֻ����룿
			//����null��""��û������
			System.out.println("���ؽ����" + result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
}


