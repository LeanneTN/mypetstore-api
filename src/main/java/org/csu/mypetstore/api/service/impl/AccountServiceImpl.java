package org.csu.mypetstore.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.Account;
import org.csu.mypetstore.api.entity.BannerData;
import org.csu.mypetstore.api.entity.Profile;
import org.csu.mypetstore.api.entity.SignOn;
import org.csu.mypetstore.api.persistence.AccountMapper;
import org.csu.mypetstore.api.persistence.BannerDataMapper;
import org.csu.mypetstore.api.persistence.ProfileMapper;
import org.csu.mypetstore.api.persistence.SignOnMapper;
import org.csu.mypetstore.api.service.AccountService;
import org.csu.mypetstore.api.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BannerDataMapper bannerDataMapper;
    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private SignOnMapper signOnMapper;


    @Override
    public CommonResponse<AccountVO> getAccount(String username, String password) {
        QueryWrapper<SignOn> queryWrapper = new QueryWrapper<SignOn>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password",password);

        SignOn signOn = signOnMapper.selectOne(queryWrapper);

        if(signOn == null)
            return CommonResponse.createForError("用户名或密码不正确");
        return getAccount(username);
    }

    @Override
    public CommonResponse<AccountVO> getAccount(String username) {
        Account account = accountMapper.selectById(username);
        Profile profile = profileMapper.selectById(username);
        BannerData bannerData = bannerDataMapper.selectById(profile.getFavouriteCategoryId());

        if(account == null)
            return CommonResponse.createForError("获取用户信息失败");

        AccountVO accountVO = entityToVO(account,profile,bannerData);
        return CommonResponse.createForSuccess(accountVO);
    }

    private AccountVO entityToVO(Account account, Profile profile, BannerData bannerData){
        AccountVO accountVO = new AccountVO();
        //将三个对象合并成一个
        accountVO.setUsername(account.getUsername());
        accountVO.setEmail(account.getEmail());
        accountVO.setFirstName(account.getFirstName());
        accountVO.setLastName(account.getLastName());
        accountVO.setStatus(account.getStatus());
        accountVO.setAddress1(account.getAddress1());
        accountVO.setAddress2(account.getAddress2());
        accountVO.setCity(account.getCity());
        accountVO.setState(account.getState());
        accountVO.setZip(account.getZip());
        accountVO.setCountry(account.getCountry());
        accountVO.setPhone(account.getPhone());
        accountVO.setFavouriteCategoryId(profile.getFavouriteCategoryId());
        accountVO.setLanguagePreference(profile.getLanguagePreference());
        accountVO.setListOption(profile.isListOption());
        accountVO.setBannerOption(profile.isBannerOption());
        accountVO.setBannerName(bannerData.getBannerName());

        return accountVO;
    }

}
