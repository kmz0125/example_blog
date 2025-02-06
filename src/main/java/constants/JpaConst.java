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

    //BlogTable
    String TABLE_COL_BLOG = "blog";//テーブル名

    //BlogTableColumun
    String BLOG_COL_ID = "id";//id
    String BLOG_COL_DATE = "blogdate";//いつのブログか示す日付
    String BLOG_COL_TITLE = "title";//タイトル
    String BLOG_COL_CONTENT = "content";//コンテンツ
    String BLOG_COL_CREATED_AT = "created_at";//投稿日時
    String BLOG_COL_UPDATED_AT = "updated_at";//更新日時

    //Entity名
    String ENTITY_BLOG = "blog";//ブログ

    //NameQueryのnameとquery
    //全てのブログをidの降順に取得する
    String Q_BLOG_GET_ALL = ENTITY_BLOG + ".getAll";
    String Q_BLOG_GET_ALL_DEF = "SELECT b FROM Blog AS b ORDER BY b.id DESC";
    //全てのブログの件数を取得する
    String Q_BLOG_COUNT = ENTITY_BLOG + ".count";
    String Q_BLOG_COUNT_DEF = "SELECT COUNT(b) FROM Blog AS b";
}
