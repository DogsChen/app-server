package com.wxs.code.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wxs.code.core.api.VO.RspMsg;
import com.wxs.code.core.controller.BaseController;
import com.wxs.code.entity.system.SysUser;
import com.wxs.code.system.entity.DTO.SysUserDTO;
import com.wxs.code.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import org.dromara.hutool.core.date.DateTime;
import org.dromara.hutool.json.jwt.JWTUtil;
import org.dromara.hutool.json.jwt.signers.JWTSignerUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Tag(name = "用户接口", description = "BIZ模块-用户接口")
public class UserController extends BaseController<SysUser> {

    @Autowired
    ISysUserService baseService;
    /**
     * 私人密钥
     */
    @Value("${app.token.secretKey}")
    public String sign;
    @PostMapping("login")
    public RspMsg login(@RequestBody SysUser sysUser) {
        logger.info("用户名：[{}]", sysUser.getName());
        logger.info("邮件：[{}]", sysUser.getEmail());
        SysUser dbu = baseService.getOne(Wrappers.lambdaQuery(sysUser));
        if(dbu== null)
            return RspMsg.error("账户或密码错误");
        Map<String, Object> map = new HashMap<>();
        // id-name-email
        map.put("iss",sign);
        map.put("name",dbu.getName());
        map.put("email",dbu.getEmail());
        map.put("jti", dbu.getId());
        map.put("iat", DateTime.now().getTime());

        logger.info(map.get("iat").toString());
        return RspMsg.ok(JWTUtil.createToken(map, JWTSignerUtil.hs512(sign.getBytes())));
    }

    @PostMapping("/jwttest")
    public String test(HttpServletRequest request) {
        logger.info("当前token为：[{}]", request.getHeader("X-Auth-Token"));
        return request.getHeader("X-Auth-Token");
    }


    /**
     * 用户注册功能
     * @param dto
     * @return
     */
    @PostMapping("/register")
    public RspMsg register(@RequestBody SysUserDTO dto) {
        return baseService.register(dto);
    }




}
