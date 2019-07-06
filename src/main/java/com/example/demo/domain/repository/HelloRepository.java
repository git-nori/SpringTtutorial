package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.domain.model.User;

public interface HelloRepository {

    // Userテーブルにデータを1件insert.
    public int insertOne(User user) throws DataAccessException;

    // Userテーブルのデータを１件取得
    public User selectOne(Long userId) throws DataAccessException;

    // Userテーブルの全データを取得.
    public List<User> selectMany() throws DataAccessException;

    // Userテーブルを１件更新.
    public int updateOne(User user) throws DataAccessException;

    // Userテーブルを１件削除.
    public int deleteOne(Long userId) throws DataAccessException;
}
