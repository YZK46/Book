package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: book
 * @description: 标签实体类
 * @author: yzk46
 * @create: 2021-03-17 22:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Serializable {
    private static final long serialVersionUID = -95198375338562425L;
    private int tagId;
    private String tagName;
    private String tagGroup;
}
