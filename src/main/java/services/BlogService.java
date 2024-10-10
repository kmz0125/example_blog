package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.BlogConverter;
import actions.views.BlogView;
import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.Blog;
import models.validators.BlogValidator;

/**
 * Blogテーブルの操作に関わる処理を行うクラス
 *
 */
public class BlogService extends ServiceBase {

    /**
     * 指定したユーザーが作成したBlogのデータを指定されたページ数の一覧画面に表示する分取得しBlogViewのリストで返却する
     * @param user
     * @param page
     * @return 一覧画面に表示するデータのリスト
     */
    public List<BlogView> getMinePerPage(UserView user, int page) {

        List<Blog> blog = em.createNamedQuery(JpaConst.Q_BLOG_GET_ALL_MINE, Blog.class)
                .setParameter(JpaConst.JPQL_PARM_USER, UserConverter.toModel(user))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return BlogConverter.toViewList(blog);
    }

    /**
     * 指定したユーザーが作成したBlogデータの件数を取得し、返却する
     * @param user
     * @return Blogデータの件数
     */
    public long countAllMine(UserView user) {

        long count = (long) em.createNamedQuery(JpaConst.Q_BLOG_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_USER, UserConverter.toModel(user))
                .getSingleResult();

        return count;
    }

    /**
     * 指定されたページ数の一覧画面に表示するBlogデータをを取得し、BlogViewのリストで返却する
     * @param page
     * @return 一覧画面に表示するデータのリスト
     */
    public List<BlogView> getAllPerPage(int page) {

        List<Blog> blog = em.createNamedQuery(JpaConst.Q_BLOG_GET_ALL, Blog.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return BlogConverter.toViewList(blog);
    }

    /**
     * Blogテーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long blog_count = (long) em.createNamedQuery(JpaConst.Q_BLOG_COUNT, Long.class)
                .getSingleResult();
        return blog_count;
    }

    /**
     * idを条件に取得したデータをBlogViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public BlogView findOne(int id) {
        return BlogConverter.toView(findOneInternal(id));
    }

    /**
     *画面から入力されたBlogの登録内容をもとにデータを1件取得し、Blogテーブルに登録する
     *@param bv Blogの登録内容
     *@return バリデーションで発生したエラーのリスト
     */
    public List<String> create(BlogView bv) {
        List<String> errors = BlogValidator.validate(bv);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            bv.setCreatedAt(ldt);
            bv.setUpdatedAt(ldt);
            createInternal(bv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された日報の登録内容をもとにBlogデータを更新する
     * @param bv Blogの更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(BlogView bv) {

        //バリデーションを行う
        List<String> errors = BlogValidator.validate(bv);

        if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            bv.setUpdatedAt(ldt);

            updateInternal(bv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Blog findOneInternal(int id) {
        return em.find(Blog.class, id);
    }

    /**
     * Blogデータを1件登録する
     * @param bv Blogデータ
     */
    private void createInternal(BlogView bv) {

        em.getTransaction().begin();
        em.persist(BlogConverter.toModel(bv));
        em.getTransaction().commit();
    }

    /**
     * Blogデータを更新する
     * @param bv Blogデータ
     */
    private void updateInternal(BlogView bv) {

        em.getTransaction().begin();
        Blog b = findOneInternal(bv.getId());
        BlogConverter.copyViewToModel(b, bv);
        em.getTransaction().commit();
    }
}
