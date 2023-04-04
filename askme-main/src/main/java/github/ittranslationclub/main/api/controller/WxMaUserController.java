package github.ittranslationclub.main.api.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import cn.hutool.json.JSONUtil;
import cn.dev33.satoken.stp.StpUtil;
import github.ittranslationclub.main.infrastructure.constant.WxMiniConstant;
import github.ittranslationclub.utils.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
@RestController
@RequestMapping("/wxmini/user")
@Tag(name = "微信小程序用户信息对外接口", description = "提供登录/获取用户信息/获取用户手机号等功能接口")
public class WxMaUserController {

    WxMaService wxMaService;

    @Autowired
    public WxMaUserController(WxMaService wxMaService) {
        this.wxMaService = wxMaService;
    }

    @Operation(summary = "提交用户验证信息,登录小程序")
    @GetMapping("/login")
    public Result<String> login(@RequestParam String appid, @RequestParam String code) throws Exception {
        if (StpUtil.isLogin()) {
            return Result.ok(StpUtil.getTokenValue());
        }
        if (StringUtils.isBlank(code) || StringUtils.isBlank(appid))
            throw new Exception(WxMiniConstant.LOGIN_APP_ID_MISS);

        if (!wxMaService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }

        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            // 根据openId进行登录
            StpUtil.login(session.getSessionKey());
            log.info("用户登录成功 openId: {}, sessionKey: {}", session.getOpenid(), session.getSessionKey());
            log.info("用户登录成功 openId: {}, sessionKey: {}", session.getOpenid(), session.getSessionKey());
            System.out.println(StpUtil.getTokenValue());
            return Result.ok(StpUtil.getTokenValue());
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw new Exception(e);
        } finally {
            WxMaConfigHolder.remove();//清理ThreadLocal
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public String info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        if (!wxMaService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }

        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            WxMaConfigHolder.remove();//清理ThreadLocal
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        WxMaConfigHolder.remove();//清理ThreadLocal
        return JSONUtil.toJsonStr(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    public String phone(@PathVariable String appid, String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        if (!wxMaService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }

        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            WxMaConfigHolder.remove();//清理ThreadLocal
            return "user check failed";
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        WxMaConfigHolder.remove();//清理ThreadLocal
        return JSONUtil.toJsonStr(phoneNoInfo);
    }

}
