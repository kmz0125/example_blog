package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.BlogView;
import constants.MessageConst;

/**
 * Blogインスタンスに設定されている値のバリデーションを行うクラス
 *
 */
public class BlogValidator {

    /**
     * Blogインスタンスの各項目についてバリデーションを行う
     * @param bv Blogインスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(BlogView bv){
        List<String> errors =new ArrayList<String>();

        //titleのチェック
        String titleError = validateTitle(bv.getTitle());
        if(!titleError.equals("")) {
            errors.add(titleError);
        }

        //contentのチェック
        String contentError =validateContent(bv.getContent());
        if(!contentError.equals("")) {
            errors.add(contentError);
        }

        return errors;
    }

    /**
     * Titleに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param title タイトル
     * @return エラーメッセージ
     */
    private static String validateTitle(String title) {
        if (title == null || title.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * Contentに劉緑地があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validateContent(String content) {
        if (content == null || content.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}
