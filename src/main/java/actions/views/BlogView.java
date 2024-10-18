package actions.views;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.User;

/**
 * Blog情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogView {

    /**
     * id
     */
    private Integer id;

    /**
     * blog_title
     */
    private String title;

    /**
     * いつのブログかを示す日付
     */
    private LocalDate blogDate;

    /**
     * blog_content
     */
    private String content;

    /**
     * blog_image

    private byte[] image;
     */

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;

    /**
     * blog_user_id
     */
    private User user;

}
