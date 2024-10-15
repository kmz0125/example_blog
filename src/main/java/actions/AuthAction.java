package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.UserService;

/**
 * 認証に関する処理を行うActionクラス
 *
 */
public class AuthAction extends ActionBase {

    private UserService service;

    @Override
    public void process() throws ServletException, IOException {

        service = new UserService();

        //メソッドを実行
        invoke();

        service.close();
    }

    /**
     * ログイン画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void showLogin() throws ServletException, IOException {

        //CSFR対策用トークンを設定
        putRequestScope(AttributeConst.TOKEN, getTokenId());

        //セッションにフラッシュメッセージが登録されている場合はリクエストスコープに設定する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //ログイン画面を表示
        forward(ForwardConst.FW_LOGIN);
    }

    /**
     * ログイン処理を行う
     * @throws ServletException
     * @throws IOException
     */
    public void login() throws ServletException, IOException {

        String id = getRequestParam(AttributeConst.USER_ID);
        String plainPass = getRequestParam(AttributeConst.USER_PASS);
        String pepper = getContextScope(PropertyConst.PEPPER);

        //有効なユーザーか認証する
        Boolean isValidUser = service.validateLogin(plainPass, pepper);

        if (isValidUser) {
            //認証成功の場合

            //CSFR対策 tokenのチェック
            if (checkToken()) {

                //ログインしたユーザーのDBデータを取得
                UserView uv = service.findOne(plainPass, pepper);
                //セッションにログインしたユーザーを設定
                putSessionScope(AttributeConst.LOGIN_USER, uv);
                //セッションにログイン完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_LOGINED.getMessage());
                //トップページへリダイレクト
                redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);
            }
        }else {
            //認証失敗の場合

            //CSRF対策用のトークンを設定
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            //認証失敗エラーメッセージ表示フラグをたてる
            putRequestScope(AttributeConst.LOGIN_ERR, true);
            //入力されたユーザーIDを設定
            putRequestScope(AttributeConst.USER_ID, id);

            //ログイン画面を表示
            forward(ForwardConst.FW_LOGIN);
        }
    }

}
