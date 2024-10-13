package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Blog;

/**
 * BlogデータのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */

public class BlogConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param bv BlogViewのインスタンス
     * @return Blogのインスタンス
     */
    public static Blog toModel(BlogView bv) {
        return new Blog(
                bv.getId(),
                bv.getTitle(),
                bv.getBlogDate(),
                bv.getContent(),
                bv.getImage(),
                bv.getCreatedAt(),
                bv.getUpdatedAt(),
                bv.getUser_id());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param b Blogのインスタンス
     * @return BlogViewのインスタンス
     */
    public static BlogView toView(Blog b) {
        if (b == null) {
            return null;
        }

        return new BlogView(
                b.getId(),
                b.getTitle(),
                b.getBlogDate(),
                b.getContent(),
                b.getImage(),
                b.getCreatedAt(),
                b.getUpdatedAt(),
                b.getUser_id());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param List DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<BlogView> toViewList(List<Blog> list) {
        List<BlogView> lbv = new ArrayList<>();

        for (Blog b : list) {
            lbv.add(toView(b));
        }

        return lbv;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param b DTOモデル（コピー先）
     * @param bv Viewモデル（コピー元）
     */
    public static void copyViewToModel(Blog b, BlogView bv) {
        b.setId(bv.getId());
        b.setTitle(bv.getTitle());
        b.setBlogDate(bv.getBlogDate());
        b.setContent(bv.getContent());
        b.setImage(bv.getImage());
        b.setCreatedAt(bv.getCreatedAt());
        b.setUpdatedAt(bv.getUpdatedAt());
        b.setUser_id(bv.getUser_id());
    }
}
