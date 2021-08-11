## 概要

以下の要素を含む簡単な Spring Boot アプリケーションです。

* Spring Data JPA でメソッド名からクエリを自動生成
* `@Query`アノテーションでネイティブSQLを実行
* 名前付きクエリで外部ファイルのネイティブSQLを実行
* CommandLineRunner を実装したコンポーネントを定義し、  
  Spring Boot 起動時に DB 初期データを登録するコマンドラインを実行
* Thymeleaf Layout Dialect で画面レイアウトの共通化
* WebJars で Bootstrap, Bootstrap Icons 導入

メソッド名に使用したJPAクエリキーワード：
* `And`, `Or`, `Not`, `In`, `NotIn`, `Like`, `NotLike`, `LessThan`, `GreaterThanEqual`, `IsNull`, `IsNotNull`, `Between`, `OrderByDesc`

## 画面

### トップページ  

<img width="1200" src="https://user-images.githubusercontent.com/59589496/128970836-85d67824-e503-45b6-9ac8-42061473d2f6.png">  

### Sample01 - メソッド名からクエリを自動生成

index.html  
<img width="1200" src="https://user-images.githubusercontent.com/59589496/128970913-e2d5f51b-5bb5-4e2d-a654-30822b1c8869.png">  

検索結果ページ  
<img width="1200" src="https://user-images.githubusercontent.com/59589496/128970985-ddfb4af9-1b96-4947-8cc6-3a728cce8bad.png">  

### Sample02 - @QueryアノテーションでネイティブSQLを実行

index.html
<img width="1200" src="https://user-images.githubusercontent.com/59589496/128971212-f738cb6a-9111-42d4-970d-965e1113d37f.png">  

検索結果ページ  
<img width="1200" src="https://user-images.githubusercontent.com/59589496/128971248-8f9b5ede-16f9-4527-927f-abec1b3148f5.png">  

### Sample03 - 名前付きクエリで外部ファイルのネイティブSQLを実行

index.html  
<img width="1200" src="https://user-images.githubusercontent.com/59589496/128971345-99700b09-65ab-4d7b-b556-8fbd4db1f90d.png">  

条件入力・検索ボタン押下  
<img width="1200" src="https://user-images.githubusercontent.com/59589496/128972323-32f2a82b-1bfd-481e-b726-76cc7ed7feb2.png">  

検索結果表示  
<img width="1200" src="https://user-images.githubusercontent.com/59589496/128972207-12865e52-1feb-4501-be37-6de1d15953be.png">  

## フォルダ構成

<img width="300" src="https://user-images.githubusercontent.com/59589496/128973054-c838e43a-1d04-450a-aee9-8c146a032fd7.png">  

## 依存関係

* Spring Boot DevTools
* Thymeleaf
* Thymeleaf Layout Dialect
* Spring Web
* Validation
* Spring Data JPA
* H2 Database
* Lombok
* WebJars
  - Bootstrap
  - Bootstrap Icons
