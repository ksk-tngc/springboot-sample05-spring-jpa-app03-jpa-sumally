package com.example.springjpa4.config;

import java.time.LocalDate;
import java.util.Random;

import com.example.springjpa4.model.Department;
import com.example.springjpa4.model.Employee;
import com.example.springjpa4.repository.DepartmentsRepository;
import com.example.springjpa4.repository.EmployeesRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * データローダコンポーネント。
 * 
 * <p>DBの初期データを登録するコンポーネント。
 * Spring Boot 起動時にコマンドラインとして実行される。（{@link CommandLineRunner}）
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    /**
    * 社員テーブルのリポジトリ。
    * <p>Lombokの{@code @RequiredArgsConstructor}でコンストラクタインジェクションされる。
    * コンストラクタが1つの場合、コンストラクタに付ける{@code @Autowired}は省略可。
    */
    private final EmployeesRepository empRepo;

    /**
     * 部署テーブルのリポジトリ。
     * <p>{@code EmployeesRepository}同様、コンストラクタインジェクションされる。
     */
    private final DepartmentsRepository deptRepo;

    /**
     * Spring Boot 起動時にコマンドラインとして実行する処理。
     * <p>DBの初期データを登録する。
     */
    @Override
    public void run(String... args) throws Exception {
        // 部署テーブル
        Department dpt1 = new Department();
        dpt1.setName("営業");
        deptRepo.save(dpt1); // DB登録

        Department dpt2 = new Department();
        dpt2.setName("開発");
        deptRepo.save(dpt2); // DB登録

        Department dpt3 = new Department();
        dpt3.setName("経理");
        deptRepo.save(dpt3); // DB登録

        // 社員テーブル
        for (int i = 1; i <= 10; i++) {
            Employee emp = new Employee();
            emp.setName("社員" + i); // 名前
            emp.setAge(22 + i); // 年齢
            emp.setSalary(200000 + (10000 * i)); // 給料
            emp.setHireDate(LocalDate.of(2021, 4, 1).minusYears(i)); // 入社日
            // 退社日
            if (i % 3 == 0) {
                emp.setLeaveDate(emp.getHireDate().plusYears(1).minusDays(1)); // 退社日
            }
            // 部署
            switch (new Random().nextInt(3)) { // 0-2
            case 0:
                emp.setDepartment(dpt1); // 部署：営業
                break;
            case 1:
                emp.setDepartment(dpt2); // 部署：開発
                break;
            case 2:
                emp.setDepartment(dpt3); // 部署：経理
                break;
            default:
                break;
            }
            empRepo.save(emp); // DB登録
        }
    }

}
