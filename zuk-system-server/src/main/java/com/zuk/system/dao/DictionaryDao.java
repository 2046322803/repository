package com.zuk.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zuk.system.entity.Dictionary;

public interface DictionaryDao extends JpaRepository<Dictionary, String>, JpaSpecificationExecutor<Dictionary> {

}
