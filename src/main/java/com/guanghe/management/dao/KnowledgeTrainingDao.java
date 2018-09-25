package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.KnowledgeTrainingBo;

import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/7/31.
 */
public interface KnowledgeTrainingDao {
    public KnowledgeTrainingBo queryknowledgeTraining(Integer id);

    public List<KnowledgeTrainingBo> queryknowledgeTrainingDetail(Map<String,Object> map);
    public void updateknowledgeTraining(KnowledgeTrainingBo knowledgeTraining);
    public  void addknowledgeTraining(KnowledgeTrainingBo knowledgeTraining);
    public void deleteknowledgeTraining(Integer Id);
    int queryCount();

}
