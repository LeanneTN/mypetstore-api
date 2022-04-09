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

    //根据用户名和密码获取账号，用在登录
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

    //根据用户名获取账号，用在用户查看用户信息，以及注册时判断用户名是否已经存在
    @Override
    public CommonResponse<AccountVO> getAccount(String username) {
        Account account = accountMapper.selectById(username);
        if(account == null)
            return CommonResponse.createForError("用户名不存在");

        Profile profile = profileMapper.selectById(username);
        BannerData bannerData = bannerDataMapper.selectById(profile.getFavouriteCategoryId());

        AccountVO accountVO = entityToVO(account,profile,bannerData);
        return CommonResponse.createForSuccess(accountVO);
    }

    //更新账号信息
    @Override
    public CommonResponse<AccountVO> updateAccount(AccountVO accountVO) {
        //首先将AccountVO转成Account、Profile、BannerData
        Account account = new Account();
        Profile profile = new Profile();

        account.setUsername(accountVO.getUsername());
        account.setEmail(accountVO.getEmail());
        account.setFirstName(accountVO.getFirstName());
        account.setLastName(accountVO.getLastName());
        account.setStatus(accountVO.getStatus());
        account.setAddress1(accountVO.getAddress1());
        account.setAddress2(accountVO.getAddress2());
        account.setCity(accountVO.getCity());
        account.setState(accountVO.getState());
        account.setZip(accountVO.getZip());
        account.setCountry(accountVO.getCountry());
        account.setPhone(accountVO.getPhone());

        profile.setUsername(accountVO.getUsername());
        profile.setFavouriteCategoryId(accountVO.getFavouriteCategoryId());
        profile.setBannerOption(accountVO.isBannerOption());
        profile.setLanguagePreference(accountVO.getLanguagePreference());
        profile.setListOption(accountVO.isListOption());

        accountMapper.updateById(account);
        profileMapper.updateById(profile);

        return CommonResponse.createForSuccess(accountVO);
    }

    //插入账号
    @Override
    public CommonResponse<AccountVO> insertAccount(String username, String password) {
        CommonResponse<AccountVO> response = getAccount(username);
        //用户名已经存在
        if(response.isSuccess())
            return CommonResponse.createForError("用户名已存在");

        //用户名不存在
        Account account = new Account();
        Profile profile = new Profile();
        SignOn signOn = new SignOn();

        account.setUsername(username);
        account.setPassword(password);
        accountMapper.insert(account);

        profile.setUsername(username);
        profileMapper.insert(profile);

        signOn.setUsername(username);
        signOn.setPassword(password);
        signOnMapper.insert(signOn);

        AccountVO accountVO = entityToVO(account, profile, null);
        return CommonResponse.createForSuccess(accountVO);
    }

    @Override
    public CommonResponse<AccountVO> getAccountByPhone(String phoneNumber) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phoneNumber);
        Account account = accountMapper.selectOne(queryWrapper);
        if(account == null)
            return CommonResponse.createForError("用户不存在！");

        Profile profile = profileMapper.selectById(account.getUsername());
        BannerData bannerData = bannerDataMapper.selectById(profile.getFavouriteCategoryId());

        AccountVO accountVO = entityToVO(account, profile, bannerData);
        return CommonResponse.createForSuccess(accountVO);
    }


    private AccountVO entityToVO(Account account, Profile profile, BannerData bannerData){
        AccountVO accountVO = new AccountVO();
        //将三个对象合并成一个
        if(account != null){
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
        }
        if(profile != null){
            accountVO.setFavouriteCategoryId(profile.getFavouriteCategoryId());
            accountVO.setLanguagePreference(profile.getLanguagePreference());
            accountVO.setListOption(profile.isListOption());
            accountVO.setBannerOption(profile.isBannerOption());
        }
        if(bannerData != null){
            accountVO.setBannerName(bannerData.getBannerName());
        }
        return accountVO;
    }

}
