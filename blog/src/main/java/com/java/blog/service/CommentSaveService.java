package com.java.blog.service;

import com.java.blog.bean.Comments;
import com.java.blog.dao.CommentDao;

public class CommentSaveService {
    public void saveComment(Comments comments) {
        CommentDao commentSaveDao = new CommentDao();
        commentSaveDao.saveComment(comments);
    }
}
