package com.java.blog.service;

import com.java.blog.bean.Comments;
import com.java.blog.dao.CommentSaveDao;

public class CommentSaveService {
    public void saveComment(Comments comments) {
        CommentSaveDao commentSaveDao = new CommentSaveDao();
        commentSaveDao.saveComment(comments);
    }
}
