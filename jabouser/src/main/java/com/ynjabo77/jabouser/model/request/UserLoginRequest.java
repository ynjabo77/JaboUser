package com.ynjabo77.jabouser.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户登陆请求体
 *
 * @author 恩嗯
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 2019110906208778780L;

    private String userAccount;

    private String userPassword;

}
