package com.example.springjpa4.repository;

import java.util.Collection;
import java.util.List;

import com.example.springjpa4.model.Employee;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 社員テーブルのリポジトリインターフェース。
 * 
 * <p>管理するエンティティクラスとIDの型を型パラメータで指定する。
 * <p>{@code @Query}でクエリを明示的に記述していないクエリメソッドは、Spring Data JPA が
 * メソッド名に含まれるキーワードからクエリを自動生成してくれる。 
 */
@Repository // 省略可
public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    /**
     * 部署名で検索し、給料の降順でソートするクエリメソッド。
     * @param name 部署名
     * @return 社員エンティティのコレクション。クエリメソッドの戻り値の型は
     *         {@code Collection<T>}や{@code List<T>}がサポートされている
     */
    Collection<Employee> findByDepartmentNameOrderBySalaryDesc(String name);

    /**
     * 部署名をINで検索するクエリメソッド。
     * @param names 部署名を格納したコレクション。InやNotInは、配列や可変引数、
     *              {@code Collection<T>}や{@code List<T>}を受け取る
     * @param sort ソート指定
     * @return 社員エンティティのリスト。クエリメソッドの戻り値の型は
     *         {@code Collection<T>}や{@code List<T>}がサポートされている
     */
    List<Employee> findByDepartmentNameIn(Collection<String> names, Sort sort);

    /**
     * 部署名をNOT INで検索するクエリメソッド。
     * @param names 部署名を格納したリスト。InやNotInは、配列や可変引数、
     *              {@code Collection<T>}や{@code List<T>}を受け取る
     * @return 社員エンティティのリスト
     */
    List<Employee> findByDepartmentNameNotIn(List<String> names);

    /**
     * 名前をLIKE検索するクエリメソッド。
     * @param name 名前
     * @return 社員エンティティのリスト
     */
    List<Employee> findByNameLike(String name);

    /**
    * 名前をNOT LIKE検索するクエリメソッド。
    * @param name 名前
    * @return 社員エンティティのリスト
    */
    List<Employee> findByNameNotLike(String name);

    /**
     * 年齢が{@code age}未満、かつ部署名が{@code name}のデータを検索するクエリメソッド。
     * @param age 年齢（未満）
     * @param name 部署名
     * @return 社員エンティティのリスト
     */
    List<Employee> findByAgeLessThanAndDepartmentName(Integer age, String name);

    /**
     * 年齢が{@code age}以上、または給料が{@code salary}以上のデータを検索するクエリメソッド。
     * @param age 年齢（以上）
     * @param salary 給料（以上）
     * @return 社員エンティティのリスト
     */
    List<Employee> findByAgeGreaterThanEqualOrSalaryGreaterThanEqual(Integer age, Integer salary);

    /**
     * 部署名をNOTで検索するクエリメソッド。
     * @param name 部署名（NOT）
     * @return 社員エンティティのリスト
     */
    List<Employee> findByDepartmentNameNot(String name);

    /**
     * 退社日がNULLのデータを検索するクエリメソッド。
     * @return 社員エンティティのリスト
     */
    List<Employee> findByLeaveDateIsNull();

    /**
     * 退社日がNULLでないデータを検索するクエリメソッド。
     * @return 社員エンティティのリスト
     */
    List<Employee> findByLeaveDateIsNotNull();

    /**
     * 年齢をBETWEENで検索するクエリメソッド。
     * @param ageFrom 年齢（FROM）
     * @param ageTo 年齢（TO）
     * @return 社員エンティティのリスト
     */
    List<Employee> findByAgeBetween(Integer ageFrom, Integer ageTo);

    /**
     * 名前でLIKE検索するクエリメソッド。
     * <p>{@code @Query}アノテーションでネイティブSQLをバインド。
     * @param name 名前
     * @return 社員エンティティのリスト
     */
    @Query(value = "SELECT * FROM EMPLOYEES WHERE NAME LIKE %:name%", nativeQuery = true)
    List<Employee> searchByNameLike(String name);

    /**
     * 退社していない20代の社員を給料の降順で検索するクエリメソッド。
     * <p>{@code @Query}アノテーションでネイティブSQLをバインド。
     * @return 社員エンティティのリスト
     */
    @Query(value = "SELECT * FROM EMPLOYEES a WHERE a.LEAVE_DATE IS NULL AND a.AGE BETWEEN 20 AND 29 ORDER BY a.SALARY DESC", nativeQuery = true)
    List<Employee> searchByNotLeaveAndAge20sOrderBySalaryDesc();

    /**
     * 部署名と入社年（以降）を指定して検索するクエリメソッド。
     * <p>{@code @Query}アノテーションでネイティブSQLをバインド。
     * @param departmentName 部署名
     * @param hireYear 入社年（以降）
     * @return 社員エンティティのリスト
     */
    @Query(value = "SELECT * FROM EMPLOYEES a INNER JOIN DEPARTMENTS b ON b.ID = a.DEPARTMENT_ID WHERE b.NAME = :departmentName AND TO_CHAR(a.HIRE_DATE, 'YYYY') >= :hireYear", nativeQuery = true)
    List<Employee> searchByDepartmentNameAndLeaveDateGreaterThanEqual(String departmentName, String hireYear);

    /**
     * 社員検索画面用のクエリメソッド。
     * <p>{@code @Query}アノテーションで名前付きクエリ（ネイティブSQL）をバインド。
     * {@code name}に指定している名前付きクエリの実態（SQL）は、
     * 外部ファイル「resources/META-INF/jpa-named-queries.properties」に記述。
     * <p>このメソッドの引数はすべてnull指定可能。
     * @param id ID
     * @param name 名前
     * @param age 年齢
     * @param salary 給料
     * @param hireDate 入社日（yyyy-MM-dd 形式の文字列）
     * @param leaveDate 退社日（yyyy-MM-dd 形式の文字列）
     * @param departmentId 部署ID
     * @return 社員エンティティのリスト
     */
    // @Query(name = "Employee.searchEmployees", nativeQuery = true) 
    @Query(nativeQuery = true) // 「エンティティ名.メソッド名」が「jpa-named-queries.properties」のクエリ名と一致しているため name は省略可
    List<Employee> searchEmployees(Long id, String name, Integer age, Integer salary, String hireDate, String leaveDate,
            Long departmentId);

}
