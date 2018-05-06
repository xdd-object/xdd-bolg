package com.java.blog.service;

import com.java.blog.bean.Comments;
import com.java.blog.dao.CommentDao;

public class CommentService {
    public void saveComment(Comments comments) {
        CommentDao commentDao = new CommentDao();
        commentDao.saveComment(comments);
    }
}
