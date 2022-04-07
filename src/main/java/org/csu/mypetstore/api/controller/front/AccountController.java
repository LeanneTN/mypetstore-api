package org.csu.mypetstore.api.controller.front;

import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.common.ResponseCode;
import org.csu.mypetstore.api.service.AccountService;
import org.csu.mypetstore.api.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class AccountController {
    @Autowired
    private AccountService accountService;

    //登录
    @PostMapping("login")
    @ResponseBody
    public CommonResponse<AccountVO> login(
            HttpSession session,
            @RequestParam("username") String username,
            @RequestParam("password") String password){
        CommonResponse<AccountVO> response = accountService.getAccount(username, password);
        if(response.isSuccess())
            session.setAttribute("login_account", response.getData());
        return response;
    }

    //注册
    @PostMapping("register")
    @ResponseBody
    public CommonResponse<AccountVO> register(
            HttpSession session,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("code") String code){
        CommonResponse<AccountVO> response = accountService.insertAccount(username, password);
        return response;
    }

    //查看账号信息
    @PostMapping("get_login_account_info")
    @ResponseBody
    public CommonResponse<AccountVO> login(HttpSession session){
        AccountVO loginAccount = (AccountVO) session.getAttribute("login_account");
        if(loginAccount == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");
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

        System.out.println(loginAccount);

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
