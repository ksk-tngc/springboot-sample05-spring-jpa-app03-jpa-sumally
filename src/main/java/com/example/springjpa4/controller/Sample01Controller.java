package com.example.springjpa4.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.springjpa4.model.Employee;
import com.example.springjpa4.repository.EmployeesRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * Spring Data JPA Sample01 のコントローラ。
 */
@Controller
@RequestMapping("/sample01")
@RequiredArgsConstructor
public class Sample01Controller {

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
        return "sample01/index";
    }

    /**
     * IDの昇順で全件取得。
     */
    @GetMapping("/findAllOrderById")
    public String findAllOrderById(Model model) {
        model.addAttribute("employees", empRepo.findAll(Sort.by("id")));
        return "sample01/result-table";
    }

    /**
     * IDの降順で全件取得。
     */
    @GetMapping("/findAllOrderByIdDesc")
    public String findAllOrderByIdDesc(Model model) {
        model.addAttribute("employees", empRepo.findAll(Sort.by(Direction.DESC, "id")));
        return "sample01/result-table";
    }

    /**
     * テーブルの件数を取得。
     */
    @GetMapping("/count")
    public String count(Model model) {
        model.addAttribute("result", empRepo.count());
        return "sample01/result";
    }

    /**
     * IDが10のデータを取得。
     */
    @GetMapping("/findById")
    public String findById(Model model) {
        List<Employee> empList = new ArrayList<>(Arrays.asList(empRepo.findById(10L).get()));
        model.addAttribute("employees", empList);
        return "sample01/result-table";
    }

    /**
     * 部署が経理のデータを給料の降順で取得。
     */
    @GetMapping("/findByDepartmentNameOrderBySalaryDesc")
    public String findByDepartmentNameOrderBySalaryDesc(Model model) {
        model.addAttribute("employees", empRepo.findByDepartmentNameOrderBySalaryDesc("経理"));
        return "sample01/result-table";
    }

    /**
     * 部署が開発か経理のデータを部署名とIDの昇順で取得。
     */
    @GetMapping("/findByDepartmentNameIn")
    public String findByDepartmentNameIn(Model model) {
        model.addAttribute("employees", empRepo.findByDepartmentNameIn(Arrays.asList("開発", "経理"),
                Sort.by(Direction.ASC, "departmentName", "Id")));
        return "sample01/result-table";
    }

    /**
     * 部署が開発と経理以外のデータを取得。
     */
    @GetMapping("/findByDepartmentNameNotIn")
    public String findByDepartmentNameNotIn(Model model) {
        model.addAttribute("employees", empRepo.findByDepartmentNameNotIn(Arrays.asList("開発", "経理")));
        return "sample01/result-table";
    }

    /**
     * 名前に"1"が含まれるデータを取得。
     */
    @GetMapping("/findByNameLike")
    public String findByNameLike(Model model) {
        model.addAttribute("employees", empRepo.findByNameLike("%1%"));
        return "sample01/result-table";
    }

    /**
     * 名前に"1"が含まれないデータを取得。
     */
    @GetMapping("/findByNameNotLike")
    public String findByNameNotLike(Model model) {
        model.addAttribute("employees", empRepo.findByNameNotLike("%1%"));
        return "sample01/result-table";
    }

    /**
     * 年齢が30未満かつ部署が開発のデータを取得。
     */
    @GetMapping("/findByAgeLessThanAndDepartmentName")
    public String findByAgeLessThanAndDepartmentName(Model model) {
        model.addAttribute("employees", empRepo.findByAgeLessThanAndDepartmentName(30, "開発"));
        return "sample01/result-table";
    }

    /**
     * 年齢が30以上または給料が25万以上のデータを取得。
     */
    @GetMapping("/findByAgeGreaterThanEqualOrSalaryGreaterThanEqual")
    public String findByAgeGreaterThanEqualOrSalaryGreaterThanEqual(Model model) {
        model.addAttribute("employees", empRepo.findByAgeGreaterThanEqualOrSalaryGreaterThanEqual(30, 250000));
        return "sample01/result-table";
    }

    /**
     * 部署が経理でないデータを取得。
     */
    @GetMapping("/findByDepartmentNameNot")
    public String findByDepartmentNameNot(Model model) {
        model.addAttribute("employees", empRepo.findByDepartmentNameNot("経理"));
        return "sample01/result-table";
    }

    /**
     * 退社日がNULLのデータを取得。
     */
    @GetMapping("/findByLeaveDateIsNull")
    public String findByLeaveDateIsNull(Model model) {
        model.addAttribute("employees", empRepo.findByLeaveDateIsNull());
        return "sample01/result-table";
    }

    /**
     * 退社日がNULLでないデータを取得。
     */
    @GetMapping("/findByLeaveDateIsNotNull")
    public String findByLeaveDateIsNotNull(Model model) {
        model.addAttribute("employees", empRepo.findByLeaveDateIsNotNull());
        return "sample01/result-table";
    }

    /**
     * 年齢が20代のデータを取得。
     */
    @GetMapping("/findByAgeBetween")
    public String findByAgeBetween(Model model) {
        model.addAttribute("employees", empRepo.findByAgeBetween(20, 29));
        return "sample01/result-table";
    }

}
