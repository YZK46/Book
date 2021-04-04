package com.yzk46.book.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: Book
 * @description: 书籍实体类
 * @author: yzk46
 * @create: 2021-02-23 21:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book  implements Serializable {
    private static final long serialVersionUID = -1239139131588989917L;
    private Long id;
    private String title;
    private String author;
    private String date;
    private String press;
    private String remark;
    private String cover;
    private Long tagId;
    private Long price;
    private Long pageNum;
}
