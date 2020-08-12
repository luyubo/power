package com.power.notice.service;

import com.power.notice.dao.NoticeDao;
import com.power.notice.dao.NoticeFreshDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private NoticeFreshDao noticeFreshDao;
}
