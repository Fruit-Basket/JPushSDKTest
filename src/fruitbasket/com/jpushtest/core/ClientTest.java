package fruitbasket.com.jpushtest.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import fruitbasket.com.jpushtest.Condition;

public class ClientTest {
	private static final ClientTest instance=new ClientTest();
	
	protected static final Logger LOG = LoggerFactory.getLogger(ClientTest.class);

	private static final String appKey =Condition.APP_KEY;
	private static final String masterSecret =Condition.MASTER_SECRET;

	private ClientTest() {
		
	}
	
	public ClientTest getInstance() {
		return instance;
	}
	
	/**
	 * 
	 */
	public static void testDefaultClient() {
		JPushClient client = new JPushClient(masterSecret, appKey);
		//JPushClient client1 = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
	}

	/**
	 * 
	 */
	public static void testCustomClient() {
		ClientConfig config = ClientConfig.getInstance();
		config.setMaxRetryTimes(5);
		config.setConnectionTimeout(10 * 1000);	// 10 seconds
		
		//配置的SSLVersion表示指定至少支持的协议版本，也可能支持其他多个协议版本，最终支持的协议版本列表取决于JRE和运行环境
		config.setSSLVersion("TLSv1.1");// JPush server supports SSLv3, TLSv1, TLSv1.1, TLSv1.2

		JPushClient jPushClient = new JPushClient(masterSecret, appKey, null, config);
	}

	/**
	 * 
	 */
	public static void testCustomPushClient() {
		ClientConfig config = ClientConfig.getInstance();

		config.setApnsProduction(false); 	// development env
		config.setTimeToLive(60 * 60 * 24); // one day

		//config.setGlobalPushSetting(false, 60 * 60 * 24); // development env, one day

		JPushClient jPushClient = new JPushClient(masterSecret, appKey, null, config); 	// JPush client

		//PushClient pushClient = new PushClient(masterSecret, appKey, null, config); 	// push client only

	}
	
}


