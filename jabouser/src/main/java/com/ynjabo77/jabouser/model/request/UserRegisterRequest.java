package com.ynjabo77.jabouser.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author 恩嗯
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -6130388121790758164L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
