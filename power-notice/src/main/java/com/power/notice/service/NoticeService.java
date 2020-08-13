package com.power.notice.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.power.notice.dao.NoticeDao;
import com.power.notice.dao.NoticeFreshDao;
import com.power.notice.pojo.Notice;
import com.power.notice.pojo.NoticeFresh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private NoticeFreshDao noticeFreshDao;

    @Autowired
    private IdWorker idWorker;
    public Notice findById(String id) {
        return noticeDao.selectById(id);
    }

    public Page<Notice> findByPage(Integer page, Integer size, Notice notice) {
        Page<Notice> pageData=new Page<>(page,size);

        List<Notice> notices = noticeDao.selectPage(pageData, new EntityWrapper<>(notice));

        pageData.setRecords(notices);
        return pageData;
    }

    @Transactional
    public boolean save(Notice notice) {
        String id=idWorker.nextId()+"";
        notice.setId(id);
        notice.setCreatetime(new Date());
        notice.setState("0");
        Integer insert = noticeDao.insert(notice);

        NoticeFresh noticeFresh=new NoticeFresh();
        noticeFresh.setNoticeId(notice.getId());
        noticeFresh.setUserId(notice.getReceiverId());

        insert=noticeFreshDao.insert(noticeFresh);
        if(insert>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean updateById(Notice notice) {
        Integer i=noticeDao.updateById(notice);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public Page<NoticeFresh> fresh(Integer page, Integer size, String userId) {
        Page<NoticeFresh> pageData=new Page<>(page,size);
        NoticeFresh noticeFresh=new NoticeFresh();
        noticeFresh.setUserId(userId);
        List<NoticeFresh> reCords = noticeFreshDao.selectPage(pageData, new EntityWrapper<>(noticeFresh));
        pageData.setRecords(reCords);
        return pageData;
    }
}
