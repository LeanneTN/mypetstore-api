package org.csu.mypetstore.api.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import com.zhenzi.sms.ZhenziSmsClient;
import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.common.ResponseCode;
import org.csu.mypetstore.api.service.AccountService;
import org.csu.mypetstore.api.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@Controller
@RequestMapping("/api/user/")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private Producer kaptchaProducer;

    //手机验证码
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    //榛子云系统上获取
    private String appId = "111119";
    private String appSecret = "638feb42-8ea3-4218-81c4-f7a370f0fa36";
    private String templateId = "8508";

    //获取手机验证码
    @PostMapping("/phoneCode")
    @ResponseBody
    public CommonResponse<String> getCode(
            @RequestParam("phoneNumber") String phoneNumber,
            HttpSession session) {
        try {
            //用于接收发送结果反馈 形式是JSON
            JSONObject result_json = new JSONObject();
            //随机生成验证码
            String phoneCode = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);

            //发送短信
            Map<String, Object> params = new HashMap<String, Object>();//参数需要通过Map传递
            params.put("number", phoneNumber);
            params.put("templateId", templateId);
            String[] templateParams = new String[2];
            templateParams[0] = phoneCode;
            templateParams[1] = "5分钟";
            params.put("templateParams", templateParams);
            String result = client.send(params);

            result_json = JSONObject.parseObject(result);
            if (result_json.getIntValue("code") != 0) {//发送短信失败
                return CommonResponse.createForError(ResponseCode.CODE_ERROR.getCode(), "发送验证码失败！");
            }
            //将验证码存到session中,同时存入创建时间
            Map<String,Object> code_info = new HashMap<String, Object>();
            code_info.put("phoneNumber", phoneNumber);
            code_info.put("phoneCode", phoneCode);
            code_info.put("createTime", System.currentTimeMillis());
            // 将认证码存入SESSION
            session.setAttribute("code_info", code_info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.createForSuccessMessage("已成功发送验证码！");
    }

    //使用手机验证码登录
    @PostMapping("/phoneLogin")
    @ResponseBody
    public CommonResponse<AccountVO> checkPhoneLogIn(
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("phoneCode") String phoneCode,
            HttpSession session){
        //根据输入的手机号获取用户对象
        CommonResponse<AccountVO> response = accountService.getAccountByPhone(phoneNumber);
        //成功获取了手机号对应的用户
        if(response.isSuccess()){
            AccountVO accountVO = response.getData();
            //从session中得到发送的验证码
            Map<String,Object> userCode = new HashMap<String,Object>();
            userCode = (HashMap) session.getAttribute("code_info");

            if(userCode == null)
                return CommonResponse.createForError("系统异常！");

            System.out.println(userCode.toString());
            System.out.println(userCode.containsKey("phoneNumber"));

            //获取服务器发送的手机验证码
            String phoneCodeSend = (String) userCode.get("phoneCode");
            //删除验证码防止重复操作
            session.removeAttribute("code_info");

            //用户输入的验证码正确，登陆成功
            if(accountVO.getPhone() !=null && phoneCodeSend.equals(phoneCode)){
                session.setAttribute("login_account", accountVO);
                return CommonResponse.createForSuccess(accountVO);
            }

            //用户输入的验证码不正确
            else
                return CommonResponse.createForError(ResponseCode.CODE_ERROR.getCode(), "验证码错误！");
        }
        //没有该手机号对应的用户
        else{
            System.out.println("没有这个用户");
            return response;
        }
    }

    //获取、切换并显示验证码
    @RequestMapping("/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = kaptchaProducer.createText();
        session.setAttribute(KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = kaptchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    //账号和密码登录
    @PostMapping("login")
    @ResponseBody
    public CommonResponse<AccountVO> login(
            HttpSession session,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("code") String code){
        //获取session中的验证码
        String token =(String) session.getAttribute(KAPTCHA_SESSION_KEY);
        //删除验证码防止重复操作
        session.removeAttribute(KAPTCHA_SESSION_KEY);
        //MD5
        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));


        //验证码正确再进行下一步操作
        try {
            if(token.equals(code)){
                CommonResponse<AccountVO> response = accountService.getAccount(username, passwordMD5);
                if(response.isSuccess())
                    session.setAttribute("login_account", response.getData());
                return response;
            }else{
                return CommonResponse.createForError(ResponseCode.CODE_ERROR.getCode(), "验证码错误！");
            }
        } catch (Exception e) {
            return CommonResponse.createForError(ResponseCode.CODE_ERROR.getCode(), "您未请求验证码！");
        }
    }

    //注册
    @PostMapping("register")
    @ResponseBody
    public CommonResponse<AccountVO> register(
            HttpSession session,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("code") String code){
        //获取session中的验证码
        String token =(String) session.getAttribute(KAPTCHA_SESSION_KEY);
        //删除验证码防止重复操作
        session.removeAttribute(KAPTCHA_SESSION_KEY);

        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        //验证码正确再进行下一步操作
        if(token.equals(code)){
            CommonResponse<AccountVO> response = accountService.insertAccount(username, passwordMD5);
            //将账号放到session中
            session.setAttribute("login_account", response.getData());
            return response;
        }else{
            return CommonResponse.createForError(ResponseCode.CODE_ERROR.getCode(), "验证码错误！");
        }
    }

    //查看账号信息
    @PostMapping("get_login_account_info")
    @ResponseBody
    public CommonResponse<AccountVO> login(HttpSession session){
        AccountVO loginAccount = (AccountVO) session.getAttribute("login_account");
        if(loginAccount == null)
            return CommonResponse.createForError(ResponseCode.NOT_LOGIN.getCode(), "未登录！");
        return CommonResponse.createForSuccess(loginAccount);
    }

    //更行账号信息
    @PostMapping("/update_account")
    @ResponseBody
    public CommonResponse<AccountVO> updateAccount(
            HttpSession session,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address1") String address1,
            @RequestParam("address2") String address2,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("zip") String zip,
            @RequestParam("country") String country,
            @RequestParam("languagePreference") String languagePreference,
            @RequestParam("favouriteCategoryId") String favouriteCategoryId,
            @RequestParam("listOption") boolean listOption,
            @RequestParam("bannerOption") boolean bannerOption){
        AccountVO loginAccount = (AccountVO) session.getAttribute("login_account");
        if(loginAccount == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");

        loginAccount.setFirstName(firstName);
        loginAccount.setLastName(lastName);
        loginAccount.setEmail(email);
        loginAccount.setPhone(phone);
        loginAccount.setAddress1(address1);
        loginAccount.setAddress2(address2);
        loginAccount.setCity(city);
        loginAccount.setState(state);
        loginAccount.setZip(zip);
        loginAccount.setCountry(country);
        loginAccount.setLanguagePreference(languagePreference);
        loginAccount.setFavouriteCategoryId(favouriteCategoryId);
        loginAccount.setListOption(listOption);
        loginAccount.setBannerOption(bannerOption);

        CommonResponse<AccountVO> response = accountService.updateAccount(loginAccount);
        if(response.isSuccess())
            session.setAttribute("login_account", response.getData());
        return response;
    }

    //判断用户名是否存在
    @PostMapping("username")
    @ResponseBody
    public CommonResponse<AccountVO> username(@RequestParam("username") String username){
        CommonResponse<AccountVO> response = accountService.getAccount(username);
        return response;
    }

    //退出
    @PostMapping("sign_out")
    @ResponseBody
    public CommonResponse<AccountVO> signOut(HttpSession session){
        if(session.getAttribute("login_account") == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");
        session.removeAttribute("login_account");
        return CommonResponse.createForSuccessMessage("退出成功！");
    }
}
