package com.example.springjpa4.controller;

import com.example.springjpa4.repository.EmployeesRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * Spring Data JPA Sample02 のコントローラ。
 */
@Controller
@RequestMapping("/sample02")
@RequiredArgsConstructor
public class Sample02Controller {

    /**
    * 社員テーブルのリポジトリ。
    * <p>Lombokの{@code @RequiredArgsConstructor}でコンストラクタインジェクションされる。
    * コンストラクタが1つの場合、コンストラクタに付ける{@code @Autowired}は省略可。
    */
    private final EmployeesRepository empRepo;

    /**
     * トップページ。
     */
    @GetMapping("/")
    public String index() {
        return "sample02/index";
    }

    /**
     * 名前でLIKE検索。
     */
    @GetMapping("/searchByNameLike")
    public String searchByNameLike(Model model) {
        model.addAttribute("employees", empRepo.searchByNameLike("1"));
        return "sample02/result-table";
    }

    /**
     * 退社していない20代の社員を給料の降順で検索。
     */
    @GetMapping("/searchByNotLeaveAndAge20sOrderBySalaryDesc")
    public String searchByNotLeaveAndAge20sOrderBySalaryDesc(Model model) {
        model.addAttribute("employees", empRepo.searchByNotLeaveAndAge20sOrderBySalaryDesc());
        return "sample02/result-table";
    }

    /**
     * 部署名と入社年（以降）で検索。
     */
    @GetMapping("/searchByDepartmentNameAndLeaveDateGreaterThanEqual")
    public String searchByDepartmentNameAndLeaveDateGreaterThanEqual(Model model) {
        model.addAttribute("employees", empRepo.searchByDepartmentNameAndLeaveDateGreaterThanEqual("開発", "2015"));
        return "sample02/result-table";
    }

}
