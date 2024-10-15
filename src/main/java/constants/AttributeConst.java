package constants;

/**
 * 画面の項目値などを定義するEnumクラス
 *
 */
public enum AttributeConst {

    //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中のユーザー
    LOGIN_USER("login_user"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //ユーザー管理
    USER("user"),
    USERS("users"),
    USER_COUNT("users_count"),
    USER_ID("id"),
    USER_PASS("password"),

    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //ブログ管理
    BLOG("blog"),
    BLOGS("blogs"),
    BLOG_COUNT("blog_count"),
    BLOG_ID("id"),
    BLOG_DATE("blog_date"),
    BLOG_TITLE("blog_title"),
    BLOG_CONTENT("blog_content"),
    BLOG_IMAGE("blog_image");

    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text=text;
        this.i=null;
    }

    private AttributeConst(final Integer i) {
        this.text=null;
        this.i=i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }

}
