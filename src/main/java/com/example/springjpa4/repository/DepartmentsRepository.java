package com.example.springjpa4.repository;

import com.example.springjpa4.model.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 部署テーブルのリポジトリインターフェース。
 * 
 * <p>管理するエンティティクラスとIDの型を型パラメータで指定する。
 */
@Repository // 省略可
public interface DepartmentsRepository extends JpaRepository<Department, Long> {
}
