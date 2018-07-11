package com.sawelly.utils;

public class Consts {
	/** 保存在session中的用户信息(演示) */
	public static final String SESSION_USER_ID = "userID";
	// public static final String NEW_BOOK_NAME_SESSION_KEY = "newBookName";
	/** 保存在cookie中的用户登录名称,登录时由表单读取(演示) */
	public static final String COOKIE_USER_NAME = "fast.username";
	/** 保存在缓存中的用户信息(演示) */
	public static final String USER_CACHE_KEY = "redis_user_key";

	/** 最近浏览cookie id **/
	public static final String VIEWED_COOKIE = "haoteacher_soeasy_rsId";

	/** 系统消息提示，用户页面跳转或刷新后提示 ，显示完成删除此cookie **/
	public static final String HC_SYS_TITLE = "hc_sys_title";

	/** cas 登录cookie S_CAS_TGC **/
	public static final String S_CAS_TGC = "s_cas_tgc";

	public static final String CARTITEM_COOKIE = "cartitem_cookie";

	/**
	 * 校验码
	 */
	public static final String COOKIE_VALIDATE_CODE = "cookie_validate_code";
	/** 默认超级管理员 */
	public static final Long DEFAULT_ADMIN_ROLE_ID = 1l;
	/** 默认机构管理员 */
	public static final Long DEFAULT_ORG_ROLE_ID = 2l;
	/** 默认店铺-官方店铺 */
	public static final Long DEFAULT_soeasy_ID = 1l;

	// 资源上传的临时文件夹
	public static final String RESOURCE_PATH = "book_static/upload/resource/courseware";

	// 删除标志 01已删除 02未删除
	public static final String DELETE_FLAG_Y = "01";
	public static final String DELETE_FLAG_N = "02";
	// SCORM学习相关属性
	public static final String NEXT_FLAG_Y = "02";
	public static final String NEXT_FLAG_N = "01";

	public static final String PREVIOUS_FLAG_Y = "02";
	public static final String PREVIOUS_FLAG_N = "01";

	public static final String ABANDON_FLAG_Y = "02";
	public static final String ABANDON_FLAG_N = "01";

	public static final String EXIT_FLAG_Y = "02";
	public static final String EXIT_FLAG_N = "01";

	public static final String IDS_SPLITOR = ",";

	// 订单处理信息

	public static final String SUBMIT_INFO = "您提交了订单，请马上付款";

	public static final String PAY_SUCCESS_INFO = "您已经付款，请等待商家确认，并发货";

	public static final String SEND_PRODUCT_INFO = "商家发出产品，请注意查收";

	// 学习码注册状态
	public static final Integer REG_STATUS_YES = 1;// 已注册
	public static final Integer REG_STATUS_NO = 2;// 未注册

	public static final String SECTION_CODE = "SECTION_CODE";// 学段
	public static final String SUBJECT_CODE = "SUBJECT_CODE";// 学科

	public static final Integer JOINGROUP_STATUS_SUCCESS = 0;// 通过
	public static final Integer JOINGROUP_STATUS_AUDIT = 1;// 审核中

	public static final Integer GROUP_MEMBER_TYPE_LEADER = 1;// 组员
	public static final Integer GROUP_MEMBER_TYPE_TEAM = 0;// 组长

	// 微课发布状态
	public static final Integer COURSE_PUB_STATUS = 1;// 已发布
	public static final Integer COURSE_UN_PUB_STATUS = 0;// 未发布
	
	// 删除状态
	public static final Integer DEL_STATUS = 1;// 删除状态
	public static final Integer NORAML_STATUS = 0;// 未删除
	
	
	public static final Integer USER_ROLE_TYPE_STUDENT = 10;// 学生用户类型
	public static final Integer USER_ROLE_TYPE_TEACHER = 1;// 教师用户类型
	public static final Integer USER_ROLE_TYPE_SINGLE = 8;//平台通用账号-角色
	
	public static final String IGNORE_PROJECT_ID="_IGNORE_PROJECT_ID";

	// 选课发布状态
	public static final Integer XK_COURSE_PUBLIC = 1;// 已发布
	public static final Integer XK_COURSE_HIDDEN = 0;// 未发布
	// 项目级别
	public static final Integer PROJECT_LEVEL_SCHOOL = 1;// 校级
	public static final Integer PROJECT_LEVEL_HEIGHT = 2;// 校级以上
	// 活动类别
	public static final Integer ACT_TYPE_GROUP = 1;// 组活动
	public static final Integer ACT_TYPE_FREE = 2;// 自由活动
	
	// 私信标题
	public static final String MSG_TITLE = "老师发送的消息";
	
	
	
	

}
