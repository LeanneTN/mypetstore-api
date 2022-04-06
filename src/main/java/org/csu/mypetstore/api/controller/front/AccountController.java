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
@RequestMapping("/account/")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("login")
    @ResponseBody
    public CommonResponse<AccountVO> login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session){
        System.out.println("进来了");
        CommonResponse<AccountVO> response = accountService.getAccount(username, password);
        if(response.isSuccess())
            session.setAttribute("login_account", response.getData());
        return response;
    }

    @PostMapping("get_login_account_info")
    @ResponseBody
    public CommonResponse<AccountVO> login(HttpSession session){
        AccountVO loginAccount = (AccountVO) session.getAttribute("login_account");
        if(loginAccount == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，不能获取用户信息");
        return CommonResponse.createForSuccess(loginAccount);
    }
}
