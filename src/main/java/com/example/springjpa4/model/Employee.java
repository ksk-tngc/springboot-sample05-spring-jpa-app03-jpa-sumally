package com.example.springjpa4.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 社員テーブルのエンティティ。
 */
@Table(name = "EMPLOYEES") // DBのテーブル名を指定
@Entity
@Getter
@Setter
@ToString
public class Employee {

    /**
     * ID
     */
    @Id // 主キー
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DBのシーケンスで自動採番
    private Long id;

    /**
     * 名前
     */
    @NotBlank // 必須
    @NotNull // DB側もNOT NULL制約を付与
    @Size(max = 40) // 40文字まで
    private String name;

    /**
     * 年齢
     */
    @NotNull // 必須
    private Integer age;

    /**
     * 給料
     */
    @NotNull // 必須
    @NumberFormat(pattern = "#,###")
    private Integer salary;

    /**
     * 入社日
     */
    @NotNull // 必須
    @DateTimeFormat(iso = ISO.DATE) // "yyyy-MM-dd"形式
    private LocalDate hireDate;

    /**
     * 退社日
     */
    @DateTimeFormat(iso = ISO.DATE) // "yyyy-MM-dd"形式
    private LocalDate leaveDate;

    /**
     * 部署ID
     */
    @NotNull // 必須
    @ManyToOne // 多対一
    private Department department;

}
