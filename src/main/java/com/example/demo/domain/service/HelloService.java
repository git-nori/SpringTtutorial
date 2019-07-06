package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.HelloRepository;

@Service
public class HelloService {

    @Autowired
    HelloRepository repository;

    /**
     * insert用メソッド.
     */
    public boolean insert(User user) {

        // insert実行
        int rowNumber = repository.insertOne(user);

        // 判定用変数
        boolean result = false;

        if (rowNumber > 0) {
            // insert成功
            result = true;
        }

        return result;
    }

    /**
     * 全件取得用メソッド.
     */
    public List<User> selectMany() {
        //全件取得
        return repository.selectMany();
    }

    /**
     * １件取得用メソッド.
     */
    public User selectOne(Long userId) {
        // selectOne実行
        return repository.selectOne(userId);
    }

    /**
     * １件更新用メソッド.
     */
    public boolean updateOne(User user) {

        //判定用変数
        boolean result = false;

        // １件更新
        int rowNumber = repository.updateOne(user);

        if (rowNumber > 0) {
            //update成功
            result = true;
        }

        return result;
    }

    /**
     * １件削除用メソッド.
     */
    public boolean deleteOne(Long userId) {

        //１件削除
        int rowNumber = repository.deleteOne(userId);

        //判定用変数
        boolean result = false;

        if (rowNumber > 0) {
            //delete成功
            result = true;
        }
        return result;
    }
}
