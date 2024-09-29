package constants;

/**
 * 画面の項目値などを定義するEnumクラス
 *
 */
public enum AttributeConst {

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中のユーザー
    LOGIN_USER("loguin_user"),

    //ログイン画面
    LOGIN_ERR("loguinError"),

    //ユーザー管理
    USER("user"),
    USER_ID("id"),
    USER_PASS("password"),

    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //ブログ管理
    BLOG("blog"),
    BLOG_TITLE("blogTitle"),
    BLOG_CONTENT("blogContent");

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
