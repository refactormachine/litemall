package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallFeedbackMapper;
import org.linlinjava.litemall.db.domain.LitemallFeedback;
import org.linlinjava.litemall.db.domain.LitemallFeedbackExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Yogeek
 * @date 2018/8/27 11:39
 */
@Service
public class LitemallFeedbackService {
    @Autowired
    private LitemallFeedbackMapper feedbackMapper;

    //提交
    public Integer add(LitemallFeedback feedback) {
        return feedbackMapper.insertSelective(feedback);
    }

    public List<LitemallFeedback> querySelective(Integer userId, String username, Integer page, Integer limit, String sort, String order) {
        LitemallFeedbackExample example = new LitemallFeedbackExample();
        LitemallFeedbackExample.Criteria criteria = example.createCriteria();

        if(userId !=  null){
            criteria.andUserIdEqualTo(userId);
        }
        if(!StringUtils.isEmpty(username)){
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return feedbackMapper.selectByExample(example);
    }

    public int countSelective(Integer userId, String username, Integer page, Integer limit, String sort, String order) {
        LitemallFeedbackExample example = new LitemallFeedbackExample();
        LitemallFeedbackExample.Criteria criteria = example.createCriteria();

        if(userId !=  null){
            criteria.andUserIdEqualTo(userId);
        }
        if(!StringUtils.isEmpty(username)){
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);
        return (int)feedbackMapper.countByExample(example);
    }

    public LitemallFeedback findById(Integer id) {
        return feedbackMapper.selectByPrimaryKey(id);
    }

    public void updateById(LitemallFeedback feedback) {
        feedbackMapper.updateByPrimaryKeySelective(feedback);
    }

    public void delete(Integer id) {
        feedbackMapper.logicalDeleteByPrimaryKey(id);
    }
}
