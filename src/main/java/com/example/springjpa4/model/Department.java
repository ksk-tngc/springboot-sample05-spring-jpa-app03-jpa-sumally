package com.example.springjpa4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 部署テーブルのエンティティ。
 */
@Table(name = "DEPARTMENTS") // DBのテーブル名を指定
@Entity
@Getter
@Setter
@ToString
public class Department {

    /**
     * ID
     */
    @Id // 主キー
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DBのシーケンスで自動採番
    private Long id;

    /**
     * 部署名
     */
    @NotBlank // 必須
    @Size(max = 40) // 40文字まで
    private String name;

}
