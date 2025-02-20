package constants;

/**
 * 各出力メッセージを定義する
 *
 */
public enum MessageConst {

    //DB更新
    I_REGISTERED("新規記事の投稿が完了しました。"),
    I_UPDATED("更新が完了しました。"),

    //バリデーション
    E_NOTITLE("タイトルを入力してください。"),
    E_NOCONTENT("内容を入力してください");

    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private MessageConst(final String text) {
        this.text=text;
    }

    /**
     * 値(文字列)
     */
    public String getMessage() {
        return this.text;
    }
}
