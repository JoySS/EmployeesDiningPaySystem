package com.sz7road.eat.api.entity.biz;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sz7road.eat.api.base.IBaseModel;

import java.io.Serializable;


/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Panda.Zh
 * @since 2017-03-24
 */
@TableName("t_biz_user")
public class TBizUser extends IBaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 邀请码
     */
    @TableField("invitation_code")
    private String invitationCode;
    /**
     * 被邀请码
     */
    @TableField("be_invitation_code")
    private String beInvitationCode;
    /**
     * 渠道
     */
    private String channel;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getBeInvitationCode() {
        return beInvitationCode;
    }

    public void setBeInvitationCode(String beInvitationCode) {
        this.beInvitationCode = beInvitationCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}