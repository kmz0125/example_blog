package actions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import actions.views.BlogView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.BlogService;

/**
 * Blogに関する処理を行うActionクラス
 *
 */
@WebServlet("/upload")
@MultipartConfig(location = "/tmp", maxFileSize = 1048576)

public class BlogAction extends ActionBase {

    private BlogService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new BlogService();

        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示するBlogデータを取得
        int page = getPage();
        List<BlogView> blog = service.getAllPerPage(page);

        //全Blogデータの件数を取得
        Long blogCount = service.countAll();

        putRequestScope(AttributeConst.BLOG, blog);//取得したBlogデータ
        putRequestScope(AttributeConst.BLOG_COUNT, blogCount);//全てのBlogデータの件数
        putRequestScope(AttributeConst.PAGE, page);//ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);//1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションから削除
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_BLOG_INDEX);

    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId());//CSFR対策用トークン

        //ブログ情報の空インスタンスに、ブログの日付＝今日の日付を設定する
        BlogView bv = new BlogView();
        bv.setBlogDate(LocalDate.now());
        putRequestScope(AttributeConst.BLOG_DATE, bv);//日付のみ設定済みのブログインスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_BLOG_NEW);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("img");// 画像ファイルを取得
        String fileName = getFileName(filePart);// ファイル名の取得
        String uploadPath = System.getProperty("java.io.tmpdir"); // 一時ディレクトリ
        filePart.write(uploadPath + File.separator + fileName);// ファイルを指定パスに保存

        response.sendRedirect("view/index..jsp");// アップロード完了後にリダイレクト
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");// Content-Dispositionヘッダーを取得
        for (String token : contentDisposition.split(";")) {// ヘッダーの値をセミコロンで分割
            if (token.trim().startsWith("filename")) {//"filename" で始まる部分を探す
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");// ファイル名を抽出
            }
        }
        return null;// ファイル名が見つからなければ null を返す
    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSFR対策 tokenのチェック
        if (checkToken()) {

            //日報の日付が入力されていなければ、今日の日付を設定
            LocalDate day = null;
            if (getRequestParam(AttributeConst.BLOG_DATE).equals("")) {
                day = LocalDate.now();
            } else {
                day = LocalDate.parse(getRequestParam(AttributeConst.BLOG_DATE));
            }


            //セッションからログイン中の従業員情報を取得
            //UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

            //パラメータの値をもとにブログ情報のインスタンスを作成する
            BlogView bv = new BlogView(
                    null, //id
                    getRequestParam(AttributeConst.BLOG_TITLE), //title
                    day,//日付
                    getRequestParam(AttributeConst.BLOG_CONTENT), //content
                    null, //image
                    null, //createdAt
                    null, //updatedAt
                    null);//user_id

            //ブログ情報登録
            List<String> errors = service.create(bv);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId());//CSFR対策用トークン
                putRequestScope(AttributeConst.BLOG, bv);//入力されたブログ情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_BLOG_NEW);
            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //SHOW画面を表示
                forward(ForwardConst.FW_BLOG_SHOW);
            }
        }
    }

}
