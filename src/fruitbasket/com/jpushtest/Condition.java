package fruitbasket.com.jpushtest;

public class Condition {
	private static final Condition instance=new Condition();
	
	//推送服务主机
	public static final String PUSH_HOST="https://api.jpush.cn";
	
	//北京的推送服务主机
	///不能使用
	public static final String PUSH_HOST_BJ="https://bjapi.jiguang.cn";
	public static final String PUSH_HOST_BJ_PUSH=PUSH_HOST_BJ+"/v3/push";
	
	//JPustTest
	public static final String APP_KEY ="a99b3e53258a1b864a1ce758";
	public static final String MASTER_SECRET="67016c8107e78f12c4325eee";
	
	//应用分组
	public static final String GROUP_APP_KEY="9506b8fbaa9af774487c58b7";
	public static final String GROUP_MASTER_SECRET="88860bbef64efeba16da17c7";
	
	public static final String REGISTRATION_ID_1="190e35f7e01704b2f97";//中兴
	public static final String REGISTRATION_ID_2="120c83f7607363a95d7";//模拟器 5X
	public static final String REGISTRATION_ID_3="170976fa8ad15e6e6ad";//华为
	
	public static final String TAG_1 = "tag_1";
	public static final String TAG_2 = "tag_2";
	
	public static final String ALIAS_1="alias_1";
	public static final String ALIAS_2="alias_2";
	
	/*
	 * 用户分群功能中的群ID
	 */
	public static final String GROUP_1_ID="37cac3be70";
	
	/**
	 * Message ID
	 */
	public static final long MSG_ID=2644024052L;
	
	/**
	 * Schedule ID
	 */
	public static final String SCHEDULE_ID_1="2624aa18-897f-11e8-91d9-fa163e219ead";
	public static final String SCHEDULE_ID_2="26802450-8980-11e8-bc43-fa163e8eb280";
	public static final String SCHEDULE_ID_TEST="c9a21728-8981-11e8-a382-0021f6b55802";
	
	private Condition() {}
	
	public Condition getInstance() {
		return instance;
	}

}
