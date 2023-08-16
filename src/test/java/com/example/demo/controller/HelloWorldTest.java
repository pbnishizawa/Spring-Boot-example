package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.Bean.HelloWorldBean;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



//テスト対象のサーバを起動して、Controllerのテストを行えるようにするアノテーション
@SpringBootTest
public class HelloWorldTest {
    private MockMvc mockMvc;

    //テスト対象のクラスをDIコンテナに登録    
    @Autowired
    private HelloWorld target;

    //クラス内にある各テストメソッド実行前に@BeforeEachがついたメソッドが実行される
    //値を返さないためvoid
    //MockMvcBuilders.standaloneSetup(target).build()でspringMVCの動作を再現するための準備
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }
     //カスタムされた表示名を宣言できる
    @DisplayName("/helloにリクエストした際にクエリパラメータのnameを取得してHello, nameを返す")


    //テストメソッドであることを意味する。値を返さないためvoid
    @Test
    public void getHelloTest() throws Exception {
    //mockMvcのperformを使ってリクエストを実行
    // /helloにGetでアクセス、指定したパラメータを入力
        mockMvc.perform(get("/hello")
               .param("name", "test"))
           //HTTPステータスがOKであることを確認
               .andExpect(status().isOk())
           //レスポンス本文のコンテンツを文字列としてアサート
               .andExpect(content().string("Hello, test"));

    }


     //テスト対象のクラスをDIコンテナに登録    
    @Autowired
    private HelloWorldBean bean;

    @Test
    @DisplayName("/helloにリクエストした際にPOSTでリクエストボディからnameを取得してHello, nameを返す ")

    public void postMethodTest() throws Exception {

        HelloWorldBean bean = new HelloWorldBean();

        mockMvc.perform(post("/hello")
               .param("name", "test")
         // JSON を送信する場合、ContentType()でContent-Type: application/jsonとなるように設定 
               .contentType(MediaType.APPLICATION_JSON)
         //リクエストボディに JSON を設定するには、contentの設定が必要
               .content(bean))
        // リクエストボディを指定
               .content(ObjectMapper.writeValueAsString(bean)) 
         //HTTPステータスがOKであることを確認
               .andExpect(status().isOk())
         //レスポンス本文のコンテンツを文字列としてアサート
               .andExpect(content().string("Hello, test"));
    }

}
