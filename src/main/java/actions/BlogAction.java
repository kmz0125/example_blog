package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.BlogView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.BlogService;

/**
 * Blogに関する処理を行うActionクラス
 *
 */
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
        //BlogView bv = new BlogView();
        //bv.setBlogDate(LocalDate.now());
        //putRequestScope(AttributeConst.BLOG, bv);//日付のみ設定済みのブログインスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_BLOG_NEW);
    }

}
