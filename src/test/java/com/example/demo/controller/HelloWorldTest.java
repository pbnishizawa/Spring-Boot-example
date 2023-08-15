package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//テスト対象のサーバを起動して、Controllerの テストを行えるようにするアノテーション
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
    public void test() throws Exception {
    //mockMvcのperformを使ってリクエストを実行
    // /helloにGetでアクセス、指定したパラメータを入力
    mockMvc.perform(get("/hello").param("name", ))
           .andExpect(status().isOk())
           .andExpect(content().string(expectedContent:"Hello, test")

    }

}
