package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_SHORT_TIME_OUT =5;
	public static final int DEFAULT_LONG_TIME_OUT = 20;
	
	public static final String LOGIN_PAGE_TITLE ="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTON_VALUE = "route=account/login";
	
	public static final String ACCOUNT_PAGE_TITLE ="My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTON_VALUE = "route=account/account";	
	
	public static final int ACCOUNT_PAGE_HEADER_COUNT =4;
	
	public static final List<String> EXP_ACCOUNTS_PAGE_HEADER_LIST = Arrays.asList("My Account","My Orders",
																				"My Affiliate Account","Newsletter");
	public static final String USER_RE_SUCCESS_MSG = "Your Account Has Been Created";
	
	
	//**************SHEET NAME***********************
	public static final String REGISTER_SHEET_NAME = "register";
	
}
