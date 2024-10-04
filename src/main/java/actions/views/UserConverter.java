package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.User;

/**
 * ユーザーデータのDTOモデル⇔Viewのモデルの変換を行うクラス
 *
 */
public class UserConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param uv UserViewのインスタンス
     * @return Userのインスタンス
     */
    public static User toModel(UserView uv) {

        return new User(
                uv.getId(),
                uv.getPassword(),
                uv.getCreatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param u Userのインスタンス
     * @return UserViewのインスタンス
     */
    public static UserView toView(User u) {
        if(u==null) {
            return null;
        }

        return new UserView(
                u.getId(),
                u.getPassword(),
                u.getCreatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param List DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<UserView> toViewList(List<User> list){
        List<UserView> uvs=new ArrayList<>();

        for(User u : list) {
            uvs.add(toView(u));
        }
        return uvs;
    }

    /**
     * viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル（コピー先）
     * @param uv Viewモデル（コピー先）
     */
    public static void copyViewToModel(User u, UserView uv) {
        u.setId(uv.getId());
        u.setPassword(uv.getPassword());
        u.setCreatedAt(uv.getCreatedAt());
    }
}
