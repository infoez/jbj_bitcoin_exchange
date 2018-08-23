package com.common.dao;

import java.util.List;

/**
 * Created by voodoo on 16. 8. 19.
 */
public interface DaoService {

    List selectList(String query);
    List selectList(String query, Object vo);
    Object selectOne(String query, Object vo);
    void insert(String query, Object vo);
    void delete(String query, Object vo);
    int update(String query, Object vo);
}
