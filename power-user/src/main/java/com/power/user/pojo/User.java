package com.power.user.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@TableName("tb_user")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.INPUT)
    private String id;
    private String mobile;
    private String password;
    private String nickname;
    private String sex;
    private Date birthday;
    private String avatar;
    private String email;
    private Date regdate;
    private Date updatedate;
    private Date lastdate;
    private Long online;
    private String interest;
    private String personality;
    private Integer fanscount;
    private Integer followcount;
}
