package constants;

/*
 * リクエストパラメーターの変数名、変数値、jspファイルの名前等画面遷移に関わる値を定義するEnumクラス
 */
public enum ForwardConst {
    //action
    ACT("action"),
    ACT_LOGIN("Login"),
    ACT_BLOG("Blog"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDAATE("update"),
    CMD_DESTROY("destroy"),

    //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_BLOG_INDEX("blog/index"),
    FW_BLOG_SHOW("blog/show"),
    FW_BLOG_NEW("blog/new"),
    FW_BLOG_EDIT("blog/edit");

    /*
     * 文字列
     */
    private final String text;

    /*
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text=text;
    }

    /*
     * 値（文字列）取得
     */
    public String getValue() {
        return this.text;
    }
}
