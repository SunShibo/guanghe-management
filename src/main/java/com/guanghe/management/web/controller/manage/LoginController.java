package com.guanghe.management.web.controller.manage;

import com.guanghe.management.common.constants.SysConstants;
import com.guanghe.management.entity.UserDO;
import com.guanghe.management.entity.bo.MessageRecordBO;
import com.guanghe.management.entity.bo.UserBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.service.LoginService;
import com.guanghe.management.service.MessageRecordService;
import com.guanghe.management.util.DateUtils;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.MD5Util;
import com.guanghe.management.util.RandomUtils;
import com.guanghe.management.util.message.SendMessageUtil;
import com.guanghe.management.util.redisUtils.RedissonHandler;
import com.guanghe.management.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * @author Shibo Sun
 * 登录controller
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseCotroller {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);

	@Resource(name = "loginService")
	LoginService loginService ;

	@Resource(name = "messageRecordService")
	MessageRecordService messageRecordService;

	/**
	 * 登录
	 * @param response
	 * @param mobile
	 * @param password
	 */
    @RequestMapping( value = "/signIn" )
    public void signIn(HttpServletResponse response, String mobile, String password){

		/* 1. 验证参数是否完整 */
		if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常！")) ;
			super.safeJsonPrint(response, result);
			return ;
		}
		UserBO userInfo = loginService.queryUserInfoByMobile(mobile);
		if(userInfo == null){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004" , "用户不存在！")) ;
			super.safeJsonPrint(response, result);
			return ;
		}
		// 判断密码是否正确
		if(!MD5Util.digest(password).equals(userInfo.getPassword())){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "密码输入不正确！")) ;
			super.safeJsonPrint(response, result);
			return ;
		}

		// 登陆客户信息放入Redis缓存
		String uuid = UUID.randomUUID().toString();
		super.putLoginUser(uuid, userInfo);
		super.setCookie(response, SysConstants.CURRENT_LOGIN_CLIENT_ID, uuid, SysConstants.SEVEN_DAY_TIME);


		String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userInfo)) ;
		super.safeJsonPrint(response, result);
	}

	/**
	 * 检查登录状态（长登录）
	 * @param response
	 */
	@RequestMapping( value = "/queryLoginStatus")
	public void queryLoginStatus (HttpServletResponse response, HttpServletRequest request ){

		/* 1. 找到对应的账户记录 */
		UserBO userBO = super.getLoginUser(request) ;

		/* 2. 验证账户状态 */
		if (userBO == null ) {
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010007" , "用户未登录！")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		if (StringUtils.isBlank(userBO.getStatus()) || userBO.getStatus().equals(UserDO.STATUS_FREEZE)) {
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010005" , "该账户已被冻结!")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		if (userBO.getStatus().equals(UserDO.STATUS_INACTIVE) || StringUtils.isBlank(userBO.getStatus())) {
			JSONObject json = new JSONObject() ;
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010006" , "该账户没有被激活!", json.toString())) ;
			json.put("userId" , userBO.getId()) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		/* 3. 返回用户信息 */
		String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userBO) , DateUtils.DATE_PATTERN) ;
		super.safeJsonPrint(response , result);
	}

	/**
	 * 注册用户
	 * @param response,mobile,password,confirmPassword,authCode
	 */
	@RequestMapping( value = "/register")
	public void register (HttpServletResponse response, String mobile, String password, String confirmPassword,
								  String authCode){

		/* 校验参数 */
		if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(confirmPassword)
				|| StringUtils.isEmpty(authCode)){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		if(!password.equals(confirmPassword)){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "密码和确认密码不一致")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		/* 判断手机号码是否注册过 */
		int count = loginService.selectCountByMobileCode(mobile);
		if(count > 0 ){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "手机号码已注册")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		/* 判断验证码输入是否正确 */
		String mobileAuthCode = RedissonHandler.getInstance().get(mobile + "_register");
		if(StringUtils.isEmpty(mobileAuthCode) || !mobileAuthCode.equals(authCode)){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "验证码错误")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		/* 注册用户 */
		UserDO userInfo = new UserDO();
		userInfo.setPhoneNumber(mobile);
		userInfo.setPassword(MD5Util.digest(password));
		loginService.register(userInfo);
		String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
		super.safeJsonPrint(response, result);
	}


	/**
	 * 根据手机号重置密码
	 */
	@RequestMapping(value = "/password")
	public void updatePassword(HttpServletResponse response,String mobile,Integer type, String newPassword, String confirmPassword,
								 String authCode){
		//参数校验
		if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(confirmPassword)
				|| type == null	|| StringUtils.isEmpty(authCode)){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		//判断新密码和确认密码是否一致
		if(!newPassword.equals(confirmPassword)){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "两次密码输入不正确")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		//查找用户
		UserBO userInfo = loginService.queryUserInfoByMobile(mobile);
		if(userInfo == null){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004" , "用户不存在")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		//获取缓存中验证码
		String mobileAuthCode = RedissonHandler.getInstance().get(mobile + "_reset");
		if(type == 2){
			mobileAuthCode = RedissonHandler.getInstance().get(mobile + "_update");
		}

		// 判断验证码输入是否正确
		if(StringUtils.isEmpty(mobileAuthCode) || !mobileAuthCode.equals(authCode)){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "验证码有误")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		// 修改客户密码
		userInfo.setPhoneNumber(mobile);
		userInfo.setPassword(MD5Util.digest(newPassword));
		loginService.updatePasswordByMobileCode(userInfo);
		String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
		super.safeJsonPrint(response, result);
	}


	/**
	 * 修改用户信息
	 * param userDO
	 */
	@RequestMapping("/update")
	public void updateUserInfo(HttpServletRequest request, HttpServletResponse response,UserDO userDO){
		UserBO userInfo = getLoginUser(request);
		if(userInfo == null){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004" , "用户没有登录")) ;
			super.safeJsonPrint(response, result);
			return ;
		}

		userDO.setId(userInfo.getId());
		int count = loginService.updateUserInfo(userDO);
		String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userDO)) ;
		super.safeJsonPrint(response, result);
	}

	/**
	 * 修改用户信息
	 * @param mobile  手机号码
	 * @param type    发送类型  0：注册  1：登陆页面忘记密码  2：个人信息重置密码
	 */
	@RequestMapping("/sendCode")
	public void sendMessageCode(HttpServletResponse response,String mobile, Integer type){
		if(StringUtils.isEmpty(mobile) || type == null){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数不正确")) ;
			super.safeJsonPrint(response, result);
			return ;
		}
		// 查询当前手机号码是否存在
		UserBO userInfo = loginService.queryUserInfoByMobile(mobile);
		if(type == 0){   // 如果是注册，账号不存在
			if(userInfo != null){
				String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "该用户已注册")) ;
				super.safeJsonPrint(response, result);
				return ;
			}
		}else if(type == 1 || type == 2){  // 如果重置密码，账号存在
			if(userInfo == null){
				String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004" , "没有找到该用户")) ;
				super.safeJsonPrint(response, result);
				return ;
			}
		}
		String number = RandomUtils.getRandomNumber(6);
		SendMessageUtil.sendSignInCodeMessage(mobile, number);
		MessageRecordBO message = new MessageRecordBO();
		String messageType = "";
		if(type == 0){     // 0：注册发送验证码
			messageType = "register";
			message.setType("注册验证码");
		}else if(type == 1){   // 1：登陆页面忘记密码
			messageType = "reset";
			message.setType("重置密码验证码");
		}else if(type == 2){    //  2：个人信息重置密码
			messageType = "update";
			message.setType("重置密码验证码");
		}
		message.setContent("您好，您的验证码为" + number);
		message.setMobile(mobile);
		message.setSendTime(new Date());
//		message.setStatus(0);
		// 添加发送短信记录信息
		Integer messageId = messageRecordService.addMessageRecord(message);

		message.setMessageId(messageId);

		// 保存验证码信息到Redis
		RedissonHandler.getInstance().set(mobile + "_" + messageType, number, null);

		String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(message)) ;
		super.safeJsonPrint(response, result);
	}

	/**
	 * 根据手机号查找用户信息
	 * param mobile
	 */
	@RequestMapping("/detail")
	public void getUserInfoBy(HttpServletResponse response,String mobile){
		if(StringUtils.isEmpty(mobile)){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数不正确")) ;
			super.safeJsonPrint(response, result);
			return ;
		}

		UserBO userInfo = loginService.queryUserInfoByMobile(mobile);
		if (userInfo == null){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004" , "没有找到该用户")) ;
			super.safeJsonPrint(response, result);
			return ;
		}

		String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userInfo)) ;
		super.safeJsonPrint(response, result);
	}
}
