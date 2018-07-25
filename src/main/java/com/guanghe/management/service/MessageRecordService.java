package com.guanghe.management.service;

import com.guanghe.management.dao.MessageRecordDao;
import com.guanghe.management.entity.bo.MessageRecordBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by shishiming on 2018/7/18.
 */
@Service("messageRecordService")
@Transactional
public class MessageRecordService {

    @Resource
    private MessageRecordDao messageRecordDao;

    public int addMessageRecord(MessageRecordBO messageRecordBO){
        if (messageRecordBO == null){
            return -1;
        }
        return messageRecordDao.addMessageRecord(messageRecordBO);
    }

}
