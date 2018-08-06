package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.KnowledgeTrainingBo;

import java.util.List;

/**
 * Created by yxw on 2018/7/31.
 */
public interface KnowledgeTrainingDao {
    public KnowledgeTrainingBo queryknowledgeTraining(Integer id);

    public List<KnowledgeTrainingBo> queryknowledgeTrainingDetail();
    public void updateknowledgeTraining(KnowledgeTrainingBo knowledgeTraining);
    public  void addknowledgeTraining(KnowledgeTrainingBo knowledgeTraining);
    public void deleteknowledgeTraining(Integer Id);

}
