package com.example.demo.domain.repository.repositoryImple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.HelloRepository;

@Repository
public class HelloRepositoryImple implements HelloRepository{

    @Autowired
    private JdbcTemplate jdbc;

    // Userテーブルにデータを1件insert.
    @Override
    public int insertOne(User user) throws DataAccessException {

        //１件登録
        int rowNumber = jdbc.update("INSERT INTO m_user(user_id,"
                + " user_name) "
                + " VALUES(?, ?)",
                user.getUserId(),
                user.getUserName());

        return rowNumber;
    }

    // Userテーブルのデータを１件取得
    @Override
    public User selectOne(Long userId) throws DataAccessException {

        // １件取得
        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_user"
                + " WHERE user_id = ?", userId);

        // 結果返却用の変数
        User user = new User();

        // 取得したデータを結果返却用の変数にセットしていく
        user.setUserId((Long) map.get("user_id")); //ユーザーID
        user.setUserName((String) map.get("user_name")); //ユーザー名

        return user;
    }

    // Userテーブルの全データを取得.
    @Override
    public List<User> selectMany() throws DataAccessException {

        // M_USERテーブルのデータを全件取得
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");

        // 結果返却用の変数
        List<User> userList = new ArrayList<>();

        // 取得したデータを結果返却用のListに格納していく
        for (Map<String, Object> map : getList) {

            //Userインスタンスの生成
            User user = new User();

            // Userインスタンスに取得したデータをセットする
            user.setUserId((Long) map.get("user_id")); //ユーザーID
            user.setUserName((String) map.get("user_name")); //ユーザー名

            //結果返却用のListに追加
            userList.add(user);
        }

        return userList;
    }

    // Userテーブルを１件更新.
    @Override
    public int updateOne(User user) throws DataAccessException {

        //１件更新
        int rowNumber = jdbc.update("UPDATE m_user"
                + " SET"
                + " user_name = ?"
                + " WHERE user_id = ?",
                user.getUserName(),
                user.getUserId());

        return rowNumber;
    }

    // Userテーブルを１件削除.
    @Override
    public int deleteOne(Long userId) throws DataAccessException {

        //１件削除
        int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId);

        return rowNumber;
    }
}