package com.cherish.mm.dao;

import com.cherish.mm.pojo.Catalog;

import java.util.List;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/03
 */
public interface CatalogDao {
    Long findCount(Integer id);
    List<Catalog> findAll(Integer courseId);
}
