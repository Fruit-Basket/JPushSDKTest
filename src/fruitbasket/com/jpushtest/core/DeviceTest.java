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
 * 用于在服务器端查询、设置、更新、删除设备的 tag和alias
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
	 * 查询设备的Tag和Alias
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
			System.out.println("别名="+result.alias);
			System.out.println("标签="+result.tags.toString());
			
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新指定RegistrationID的Alias和Tag
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
	 * 清除指定RegistrationID的所有Alias和Tag
	 */
	public static void clearDeviceTagAlias() {
		try {
			DefaultResult result =  jpushClient.updateDeviceTagAlias(
					registrationId,
					true,//清除所有别名
					true//清除所有标签
			);
			
			System.out.println("Got result " + result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询别名对应的设备
	 * 注意，暂不能查询标签对应的设备
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
			System.out.println("Android设备：");
			for(String registrationID:result1.registration_ids) {
				System.out.println(i+": "+registrationID);
				i++;
			}
			
			AliasDeviceListResult result2=jpushClient.getAliasDeviceList(
					alias,
					"ios"
			);
			i=1;
			System.out.println("iOS设备：");
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
	 * 删除别名，以及该别名与设备的对应关系
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
	 * 获JPush服务器中存放的在这个应用下的所有标签
	 */
	public static void getTagList() {
		try {
			TagListResult result=jpushClient.getTagList();
			
			StringBuilder stringBuilder=new StringBuilder("所有标签：");
			for(String tag:result.tags) {
				stringBuilder.append(tag+"、");
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
	 * 查询特定设备是否有特定标签
	 */
	public static void isDeviceInTag() {
		
		try {
			BooleanResult result=jpushClient.isDeviceInTag(
					Condition.TAG_1,
					registrationId
			);
			
			LOG.info(registrationId+"是否有"+Condition.TAG_1+": "+result.result);
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新标签对应的设备
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
				LOG.info("失败");
			}
		} catch (APIConnectionException | APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除标签，以及与设备的绑定关系
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
				LOG.info("失败");
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
	 * 查询设备的在线状态、最后在线时间
	 * 
	 * 字段online：10min内是否在线
	 * 字段last_online_time：当online==false时，表示两天内最后在线时间；如果两天内不在线则为false
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
	 * 把registrationId绑定手机号
	 */
	public static void bindMobile() {
		try {
			DefaultResult result =  jpushClient.bindMobile(
					Condition.TAG_1,
					"13728860097"
					///如何取消绑定手机号码？
					//传入null和""都没有作用
			);
			LOG.info("返回结果：" + result);
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


