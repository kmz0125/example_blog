package services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.User;
import models.validators.UserValidator;
import utils.EncryptUtil;

/**
 * ユーザーテーブルの操作に関わる処理を行うクラス
 *
 */
public class UserService extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示するデータを取得し、UserViewのリストで返却する
     * @param page ページ数
     * @return 表示するデータのリスト
     */
    public List<UserView> getPerPage(int page) {
        List<User> users = em.createNamedQuery(JpaConst.Q_USER_GET_ALL, User.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return UserConverter.toViewList(users);
    }

    /**
     * ユーザーテーブルのデータの件数を取得し、返却する
     * @return ユーザーテーブルのデータの件数
     */
    public long countAll() {
        long userCount = (long) em.createNamedQuery(JpaConst.Q_USER_COUNT, Long.class)
                .getSingleResult();

        return userCount;
    }

    /**
     * passwordを条件に取得したデータをUserViewのインスタンスで返却する
     * @param plainPass パスワード文字列
     * @param pepper pepper文字列
     * @return 取得データのインスタンス 取得できない場合null
     */
    public UserView findOne(String id, String plainPass, String pepper) {
        User u = null;
        try {
            //パスワードのハッシュ化
            String pass = EncryptUtil.getPasswordEncrypt(plainPass, pepper);

            //ハッシュ化済みパスワードを条件にユーザーを取得する
            u = em.createNamedQuery(JpaConst.Q_USER_GET_BY_PASS, User.class)
                    .setParameter(JpaConst.JPQL_PARM_PASS, pass)
                    .getSingleResult();
        } catch (NoResultException ex) {
        }
        return UserConverter.toView(u);
    }

    /**
     * idを条件に取得したデータをUserViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public UserView findOne(int id) {
        User u = findOneInternal(id);
        return UserConverter.toView(u);
    }

    /**
     * 画面から入力されたユーザーの登録内容をもとにデータを１件取得し、Userテーブルに登録する
     * @param uv 画面から入力されたユーザーの登録内容
     * @param pepper pepper文字列
     * @return バリデーションや登録処理中に発生したエラーのリスト
     */
    public List<String> create(UserView uv, String pepper) {

        //パスワードをハッシュ化して設定
        String pass = EncryptUtil.getPasswordEncrypt(uv.getPassword(), pepper);
        uv.setPassword(pass);

        //登録日時を設定する
        LocalDateTime now = LocalDateTime.now();
        uv.setCreatedAt(now);

        //登録内容のバリデーションを行う
        List<String> errors = UserValidator.validate(this, uv, true);

        //バリデーションエラーがなければデータを登録する
        if (errors.size() == 0) {
            create(uv);
        }

        //エラーを返却（エラーがなければ0件の空リスト)
        return errors;
    }

    /**
     * パスワードを条件に検索し、データが取得できるかどうかで認証結果を返却する
     * @param plainPass パスワード
     * @param pepper pepper文字列
     * @return 認証結果を返却する(成功:true 失敗:false)
     */
    public Boolean validateLogin(String id, String plainPass, String pepper) {

        boolean isValidUser = false;
        if (plainPass != null && !plainPass.equals("")) {
            UserView uv = findOne(id, plainPass, pepper);

            if (uv != null && uv.getId() != null) {

                //データが取得できた場合、認証成功
                isValidUser = true;
            }
        }

        //認証結果を返却する
        return isValidUser;
    }

    /**
     * idを条件にデータを１件取得し、Userのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private User findOneInternal(int id) {
        User u = em.find(User.class, id);

        return u;
    }

    /**
     * ユーザーデータを１件登録する
     * @param uv ユーザーデータ
     * @return 登録結果(成功:true 失敗:false)
     */
    private void create(UserView uv) {
        em.getTransaction().begin();
        em.persist(UserConverter.toModel(uv));
        em.getTransaction().commit();
    }
}