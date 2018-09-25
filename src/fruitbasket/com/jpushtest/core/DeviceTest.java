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

	private static final DeviceTest instance=new DeviceTest();
	
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
	 * 清除指定RegistrationId的所有Alias和Tag
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
	 * 获JPush服务器中存放的在这个应用下的所有标签
	 */
	public static void getTagList(
			final String masterSecret,
			final String appKey) {
		
		JPushClient jpushClient =
				new JPushClient(masterSecret,appKey);
		
		try {
			TagListResult result=jpushClient.getTagList();
			
			StringBuilder stringBuilder=new StringBuilder("所有标签：");
			for(String tag:result.tags) {
				stringBuilder.append(tag+"、");
			}
			System.out.println(stringBuilder.toString());
			
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询特定设备是否有特定标签
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
			
			System.out.println(registrationId+"是否有"+Condition.TAG_1+": "+result.result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新标签对应的设备
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
				System.out.println("失败");
			}
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除标签，以及与设备的绑定关系
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
				System.out.println("失败");
			}
			
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 查询设备的在线状态、最后在线时间
	 * 
	 * 字段online：10min内是否在线
	 * 字段last_online_time：当online==false时，表示两天内最后在线时间；如果两天内不在线则为false
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
	 * 把registrationId绑定手机号
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
			///如何取消绑定手机号码？
			//传入null和""都没有作用
			System.out.println("返回结果：" + result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
}


