package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.Bean.HelloWorldBean;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//テスト対象のサーバを起動して、Controllerのテストを行えるようにするアノテーション
@SpringBootTest
public class HelloWorldTest {
    private MockMvc mockMvc;

    // テスト対象のクラスをDIコンテナに登録
    @Autowired
    private HelloWorld helloWorld;

    // クラス内にある各テストメソッド実行前に@BeforeEachがついたメソッドが実行される
    // 値を返さないためvoid
    // MockMvcBuilders.standaloneSetup(target).build()でspringMVCの動作を再現するための準備
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(helloWorld).build();
    }

    // カスタムされた表示名を宣言できる
    @DisplayName("/helloにリクエストした際にクエリパラメータのnameを取得してHello, nameを返す")

    // テストメソッドであることを意味する。値を返さないためvoid
    @Test
    public void getHelloTest() throws Exception {
        // mockMvcのperformを使ってリクエストを実行
        // /helloにGetでアクセス、指定したパラメータを入力
        mockMvc.perform(get("/hello")
                //クエリパラメータに値を渡す
                .param("name", "test"))
                // HTTPステータスがOKであることを確認
                .andExpect(status().isOk())
                // レスポンス本文のコンテンツを文字列としてアサート
                .andExpect(content().string("Hello, test"));

    }

    //ObjectMapperをDIコンテナに登録、自動生成
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("/helloにリクエストした際にPOSTでリクエストボディからnameを取得してHello, nameを返す ")

    public void postMethodTest() throws Exception {

        //今回はSpringBootがHellowWorldBeanを認識できず自動注入できないため、@Autowired1を使わずに手動でnewする
        HelloWorldBean bean = new HelloWorldBean();
        //nameにtestという文字列をセットする
        bean.setName("test");
        //jsonという変数に、beanをStringに変換→Json文字列に変換
        String json = objectMapper.writeValueAsString(bean);

        MockHttpServletRequestBuilder request = post("/hello")
                
                // JSON を送信する場合、ContentType()でContent-Type: application/jsonとなるように設定
                .contentType(MediaType.APPLICATION_JSON)
                // リクエストボディに JSON を設定するには、contentの設定が必要
                .content(json);
        ResultActions result = mockMvc.perform(request);
        result
        // HTTPステータスがOKであることを確認
        .andExpect(status().isOk())
        // レスポンス本文のコンテンツを文字列としてアサート
        .andExpect(content().string("Hello, test"));
    }


    @Test
    @DisplayName("/hello/nameにリクエストした際にGETでパスパラメータからnameを取得してHello, nameを返す ")
    
    public void helloTest() throws Exception {

        mockMvc.perform(get("/hello/{name}"))
               .andExpect(request().attribute("name", is("test")))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello, test"));
    }

}
