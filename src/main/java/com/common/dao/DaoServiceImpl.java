package com.common.dao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by voodoo on 16. 8. 19.
 */
@Repository
public class DaoServiceImpl implements DaoService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public List selectList(String query) {
        return (List) sqlSession.selectList(query);
    }

    @Override
    public List selectList(String query, Object vo) {
        return (List) sqlSession.selectList(query, vo);
    }

    @Override
    public Object selectOne(String query, Object vo) {
        return sqlSession.selectOne(query, vo);
    }

    @Override
    public void insert(String query, Object vo) {
        sqlSession.insert(query, vo);
    }

    @Override
    public void delete(String query, Object vo) {
        sqlSession.delete(query, vo);
    }

    @Override
    public int update(String query, Object vo) {
        return sqlSession.update(query, vo);
    }
}
