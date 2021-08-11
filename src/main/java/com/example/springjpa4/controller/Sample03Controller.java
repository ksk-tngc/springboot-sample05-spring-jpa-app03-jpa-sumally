package com.example.springjpa4.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.springjpa4.model.Employee;
import com.example.springjpa4.repository.EmployeesRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * Spring Data JPA Sample03 のコントローラ。
 */
@Controller
@RequestMapping("/sample03")
@RequiredArgsConstructor
public class Sample03Controller {

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
    public String index(@ModelAttribute Employee employee, Model model) {
        model.addAttribute("employees", new ArrayList<Employee>()); // 初期表示時は空のListをModelに追加
        return "sample03/index";
    }

    /**
     * 検索処理。
     */
    @GetMapping("/search")
    public String search(@ModelAttribute Employee employee, Model model) {
        // ==========================================
        // 画面の入力値を編集
        // ==========================================
        // Stringの空文字をnullに変換 
        // 名前
        String name = employee.getName().trim().equals("") ? null : employee.getName().trim();
        // LocalDateをyyyy-MM-ddの文字列に変換
        // 入社日
        String hireDate = employee.getHireDate() == null ? null
                : employee.getHireDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        // 退社日
        String leaveDate = employee.getLeaveDate() == null ? null
                : employee.getLeaveDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        // その他編集
        // 部署ID
        Long departmentId = employee.getDepartment() == null ? null : employee.getDepartment().getId();

        // 画面の条件でDB検索
        List<Employee> empList = empRepo.searchEmployees(employee.getId(), name, employee.getAge(),
                employee.getSalary(), hireDate, leaveDate, departmentId);

        model.addAttribute("employees", empList);
        return "sample03/index";
    }

}
