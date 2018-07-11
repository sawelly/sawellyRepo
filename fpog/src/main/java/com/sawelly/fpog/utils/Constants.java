/**
 *  @Project       : oel_DemonstrationTraining

 *  @Program Name  : cn.com.teacher.cistus.dt.common.Consts.java
 *  @Class Name    : Consts
 *  @Description   : 类描述
 *  @Author        : leonlau
 *  @Creation Date : 2013-6-22 上午11:05:26 
 *  @ModificationHistory  
 */

package com.sawelly.utils;

import java.util.List;

public class Constants {
	// private static Map<String, List<DictVo>> CACHE_DICT = new HashMap<String,
	// List<DictVo>>();
	// 毫秒数

	private static int CACHE_MAX_SIZE = 5000;
	// private static int CACHE_MAX_TIME_OUT = 30 * 60 * 1000; //30分钟
	private static String CACHE_MAX_TIME_OUT = "CACHE_MAX_TIME_OUT";

	public static String UTF_8 = "UTF-8";

	public static int NO = 1; // 消极

	public static int YES = 0; // 积极

	/**
	 * 首页模板
	 */
	public static String TPLDIR_INDEX = "index";
	/**
	 * 栏目页模板
	 */
	public static String TPLDIR_CHANNEL = "channel";
	/**
	 * 内容页模板
	 */
	public static String TPLDIR_CONTENT = "content";
	/**
	 * 单页模板
	 */
	public static String TPLDIR_ALONE = "alone";
	/**
	 * 专题模板
	 */
	public static String TPLDIR_TOPIC = "topic";
	/**
	 * 个人工作室模板
	 */
	public static String TPLDIR_MEMBER = "member";
	/**
	 * 专用模板
	 */
	public static String TPLDIR_SPECIAL = "special";
	/**
	 * 公用模板
	 */
	public static String TPLDIR_COMMON = "common";

	/**
	 * 标签模板
	 */
	public static String TPLDIR_TAG = "tag";
	/**
	 * 评论模板
	 */
	public static String TPLDIR_COMMENT = "comment";
	/**
	 * 留言模板
	 */
	public static String TPLDIR_GUESTBOOK = "guestbook";
	/**
	 * 站内信模板
	 */
	public static String TPLDIR_MESSAGE = "message";
	/**
	 * 列表样式模板
	 */
	public static String TPLDIR_STYLE_LIST = "style_list";
	/**
	 * 列表样式模板
	 */
	public static String TPLDIR_STYLE_PAGE = "style_page";

	/**
	 * 模板后缀
	 */
	public static String TPL_SUFFIX = ".html";

	/**
	 * 资源路径
	 */
	public static String RES_PATH = "/dt_res/";
	/**
	 * 模板路径
	 */
	public static String TPL_BASE = "/tpl/";
	/**
	 * 默认模版
	 */
	public static String TPL_DEFAULT = "www";
	/**
	 * 默认主题模版
	 */
	public static String TPL_THEME_DEFAULT = "default";
	/**
	 * 全文检索索引路径
	 */
	public static String LUCENE_PATH = "/WEB-INF/lucene";
	/**
	 * 列表样式模板路径
	 */
	public static String TPL_STYLE_LIST = "/WEB-INF/tpl/sys_defined/style_list/style_";
	/**
	 * 内容分页模板路径
	 */
	public static String TPL_STYLE_PAGE_CONTENT = "/WEB-INF/tpl/sys_defined/style_page/content_";
	/**
	 * 列表分页模板路径
	 */
	public static String TPL_STYLE_PAGE_CHANNEL = "/WEB-INF/tpl/sys_defined/style_page/channel_";
	/**
	 * 数据上传模板路径
	 */
	public static String DOWNLOAD_SRC = "/WEB-INF/download/template/";

	/**
	 * 模板资源路径
	 */
	public static String RES_TPL = "res";

	/**
	 * 模板资源表达式
	 */
	public static String RES_EXP = "${res}";
	/**
	 * 站点
	 */
	public static String SITE = "_current_site";
	public static String USER_MODEL = "_userModel";

	/**
	 * 用户KEY
	 */
	public static final String USER_KEY = "_user_key";
	/**
	 * 站点KEY
	 */
	public static final String SITE_KEY = "_site_key";
	/**
	 * 项目KEY session
	 */
	public static final String PROJECT_KEY = "_project_key";
	/**
	 * 项目cookie
	 */
	public static final String PROJECT_COOKIE_KEY = "_project_cookie_id";

	/**
	 * 单分隔符
	 */
	public static String DIR_SEPARATOR = "/";
	/**
	 * 双分隔符
	 */
	public static String DIR_DOUBLE_SEPARATOR = "//";
	/**
	 * 站点模版
	 */
	public static String TPL_FlAG = "tpl:";

	/**
	 * 空
	 */
	public static String BLANK = "";

	public static String TOKEN_NAME = "_TOKEN_NAME_";
	// 项目一级域名
	public static String PROJECT_ROOT_DOMAIN = "PROJECT_ROOT_DOMAIN";
	// 项目一级域名 缺省
	public static String PROJECT_DEFAULT_ROOT_DOMAIN = ".teacher.com.cn";
	// 验证码cookie key
	public static String COOKIE_VALIDATE_CODE = "cookie_validate_code";
}
