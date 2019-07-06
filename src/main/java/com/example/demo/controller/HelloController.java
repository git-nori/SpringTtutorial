package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.User;
import com.example.demo.domain.model.UserForm;
import com.example.demo.domain.service.HelloService;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    /**
     * GET用の処理.
     */
    @GetMapping("/hello")
    public String getHello() {

        // hello.htmlに画面遷移
        return "hello";
    }

    /**
     * ユーザー一覧画面のGETメソッド用処理.
     */
    @GetMapping("/home")
    public String getHome(Model model) {

        //ユーザー一覧の生成
        List<User> userList = helloService.selectMany();

        model.addAttribute("userList", userList);

        return "home";
    }

    /**
     * 編集画面のGETメソッド用処理.
     */
    @GetMapping("/edit/{id:.+}")
    public String getEdit(@ModelAttribute UserForm form,
            Model model,
            @PathVariable("id") Long userId) {

        // ユーザーIDのチェック
        if (userId != null) {

            // ユーザー情報を取得
            User user = helloService.selectOne(userId);

            // Userクラスをフォームクラスに変換
            form.setUserId(user.getUserId()); //ユーザーID
            form.setUserName(user.getUserName()); //ユーザー名

            // Modelに登録
            model.addAttribute("userForm", form);
        }

        return "edit";
    }

    /**
     * 更新用処理.
     */
    @PostMapping(value = "/edit", params = "update")
    public String postUpdate(@ModelAttribute UserForm form, Model model) {

        //Userインスタンスの生成
        User user = new User();

        //フォームクラスをUserクラスに変換
        user.setUserId(form.getUserId());
        user.setUserName(form.getUserName());

        try {
            //更新実行
            boolean result = helloService.updateOne(user);

            // ユーザー更新結果の判定
            if (result == true) {
                model.addAttribute("result", "更新成功");
            } else {
                model.addAttribute("result", "更新失敗");
            }
        } catch(DataAccessException e) {
            model.addAttribute("result", "更新失敗");
        }

        //ユーザー一覧画面を表示
        return getHome(model);
    }

    /**
     * 削除用処理.
     */
    @PostMapping(value = "/edit", params = "delete")
    public String postDelete(@ModelAttribute UserForm form, Model model) {

        try {
            //削除実行
            boolean result = helloService.deleteOne(form.getUserId());

            // ユーザー削除結果の判定
            if (result == true) {
                model.addAttribute("result", "削除成功");
            } else {
                model.addAttribute("result", "削除失敗");
            }
        } catch(DataAccessException e) {
            model.addAttribute("result", "削除失敗");
        }

        //ユーザー一覧画面を表示
        return getHome(model);
    }
    /**
     * 登録画面のGETメソッド用処理.
     */
    @GetMapping("/create")
    public String getCreate(@ModelAttribute UserForm form, Model model) {

        // create.htmlに画面遷移
        return "create";
    }

    /**
     * 登録画面のPOSTメソッド用処理.
     */
    @PostMapping("/create")
    public String postSignUp(@ModelAttribute UserForm form, Model model) {

        User user = new User();

        user.setUserId(form.getUserId()); //ユーザーID
        user.setUserName(form.getUserName()); //ユーザー名

        try {
            // ユーザー登録処理
            boolean result = helloService.insert(user);

            // ユーザー登録結果の判定
            if (result == true) {
                model.addAttribute("result", "insert成功");
            } else {
                model.addAttribute("result", "insert失敗");
            }
        } catch(DataAccessException e) {
            model.addAttribute("result", "insert失敗");
        }

        //ユーザー一覧画面を表示
        return getHome(model);
    }
}
