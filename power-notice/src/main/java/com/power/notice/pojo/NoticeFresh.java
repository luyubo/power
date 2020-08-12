package com.power.notice.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@TableName("tb_notice_fresh")
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeFresh implements Serializable {

    private static final long serialVersionUID = -7727597013884108210L;

    private String userId;
    private String noticeId;
}
