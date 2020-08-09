package com.power.article.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("comment")
public class Comment implements Serializable {
    @Id
    private String _id;
    private String c_id;
    private String articleid;
    private String content;
    private String userid;
    private String parentid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishdate;
    private Integer thumbup;
}
