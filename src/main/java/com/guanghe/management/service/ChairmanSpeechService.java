package com.guanghe.management.service;

import com.guanghe.management.dao.ChairmanSpeechDao;
import com.guanghe.management.entity.bo.ChairmanSpeechBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by yxw on 2018/7/18.
 */
@Service("ChairmanSpeechService")
@Transactional
public class ChairmanSpeechService {
    @Resource
    private ChairmanSpeechDao chairmanSpeechDao;
    public ChairmanSpeechBo queryChairmanSpeechDetailById(Integer id){
       return chairmanSpeechDao.queryChairmanSpeechDetailById(id);
    }
    public  void updateChairmanSpeech (ChairmanSpeechBo chairmanSpeech){
        chairmanSpeechDao.updateChairmanSpeech(chairmanSpeech);
    }
    public  void deleteChairmanSpeech(Integer id){
        chairmanSpeechDao.deleteChairmanSpeech(id);
    }
    public  void addChairmanSpeech(ChairmanSpeechBo chairmanSpeech){
        chairmanSpeechDao.addChairmanSpeech(chairmanSpeech);
    }
    public ChairmanSpeechBo queryChairmanSpeechDetail(){
        return  chairmanSpeechDao.queryChairmanSpeechDetail();
    }
}
