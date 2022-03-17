package com.bookmngsys.dao;

import com.bookmngsys.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Newway
 */
public interface CategoryDao extends JpaRepository<Category, Integer> {
}
