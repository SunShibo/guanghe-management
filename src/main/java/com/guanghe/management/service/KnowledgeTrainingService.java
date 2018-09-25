package com.guanghe.management.service;

import com.guanghe.management.dao.KnowledgeTrainingDao;
import com.guanghe.management.entity.bo.KnowledgeTrainingBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/7/31.
 */
@Service("KnowledgeTrainingService")
@Transactional
public class KnowledgeTrainingService {
    @Resource
    private KnowledgeTrainingDao knowledgeTrainingDao;
    public KnowledgeTrainingBo queryknowledgeTraining(Integer id){
        return  knowledgeTrainingDao.queryknowledgeTraining(id);
    }

    public List<KnowledgeTrainingBo> queryknowledgeTrainingDetail(Map<String,Object> map){
         return knowledgeTrainingDao.queryknowledgeTrainingDetail(map);
    }
    public void updateknowledgeTraining(KnowledgeTrainingBo knowledgeTraining){
              knowledgeTrainingDao.updateknowledgeTraining(knowledgeTraining);
    }
    public  void addknowledgeTraining(KnowledgeTrainingBo knowledgeTraining){
             knowledgeTrainingDao.addknowledgeTraining(knowledgeTraining);
    }
    public void deleteknowledgeTraining(Integer id){
         knowledgeTrainingDao.deleteknowledgeTraining(id);
    }
    public int queryCount(){
        return  knowledgeTrainingDao.queryCount();
    }
}
