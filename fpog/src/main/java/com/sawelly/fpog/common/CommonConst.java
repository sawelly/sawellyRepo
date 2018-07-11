package com.sawelly.fpog.common;

public class CommonConst {


//	public static final String DOMAIN = "http://localhost/fpog/";
	public static final String DOMAIN = "https://www.sawelly.com/";

//	public static final String uploadRootDir = "f://upload//img//";
	public static final String uploadRootDir = "/data/upload/img/";

	public static final String defaultImg = "default_wangyan.png";

	public static final class AuditStatus {
		public static final Integer UNAUDIT = 0; // 未审核
		public static final Integer AUDIT_PASS = 1; // 通过
		public static final Integer AUDIT_FAILURE = 2; // 失败
	}

	//附件类型1.图片；2.附件
	public static final Integer ATTACHMENT_TYPE_IMG = 1;
	public static final Integer ATTACHMENT_TYPE_FILE = 2;


}
