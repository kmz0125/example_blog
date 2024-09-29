package constants;

/**
 * DB関連の項目値を定義するインターフェイス
 *
 */
public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "example_blog";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 5;

    //UserLoginTable
    String TABLE_USER = "users";//テーブル名
    String USER_ID = "id";//id
    String USER_PASS = "password";//パスワード
    String USER_CREATED_AT = "created_at";//登録日時

    int USER_DEL_TRUE = 1;//削除フラグON(削除済み）
    int USER_DEL_FALSE = 0;//削除フラグOFF（現役）

    //BlogTable
    String TABLE_BLOG = "blog";//テーブル名
    String BLOG_ID = "id";//id
    String BLOG_TITLE = "title";//タイトル
    String BLOG_CONTENT = "content";//コンテンツ
    String BLOG_IMAGE = "image";//画像
    String BLOG_CREATED_AT = "blog_created_at";//投稿日時
    String BLOG_USER_ID = "blog_user_id";//ブログユーザーid

    //Entity名
    String ENTITY_USER = "user";//ユーザー
    String ENTITY_BLOG = "blog";//ブログ

    //JPQL内パラメータ
    String JPQL_PARAM_PASS = "passward";//パスワード
    String JPQL_PARM_USER = "user";//ユーザー

    //NameQueryのnameとquery
    //全てのユーザーのidを降順に取得する
    String Q_USER_GET_ALL = ENTITY_USER + ".getAll";
    String Q_USER_GET_ALL_DEF = "SELSECT u FROM Users AS u ORDER BY u.id DESC+";
    //全てのブログをidの降順に取得する
    String Q_BLOG_GET_ALL = ENTITY_BLOG + ".getAll";
    String Q_BLOG_GET_ALL_DEF = "SELECT b FROM Blog AS b ORDER BY b.id DESC";
    //全てのブログの件数を取得する
    String Q_BLOG_COUNT = ENTITY_BLOG + ".getAll";
    String Q_BLOG_COUNT_DEF = "SELECT COUNT(b) FROM Blog AS b";
    //指定したユーザーが作成したブログを全件idの降順で取得する
    String Q_BLOG_GET_ALL_MINE = ENTITY_BLOG + "get.AllMine";
    String Q_BLOG_GET_ALL_MINE_DEF = "SERECT b FROM Blog AS b WHERE b.user=:" + JPQL_PARM_USER + "ORDER BY b.id DESC";
    //指定したユーザーが作成したブログの件数を取得する
    String Q_BLOG_COUNT_ALL_MINE = ENTITY_BLOG + "coutAllMine";
    String Q_BLOG_COUNT_ALL_MINE_DEF = "SELECT COUNT(b) FROM Blog AS b WHERE b.user=:" + JPQL_PARM_USER;

}
