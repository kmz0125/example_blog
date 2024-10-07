package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.mysql.cj.jdbc.Blob;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ブログデータのDTOモデル
 */
@Table(name = JpaConst.TABLE_COL_BLOG)
@NamedQueries({
        @NamedQuery(
                name = JpaConst.Q_BLOG_GET_ALL,
                query = JpaConst.Q_BLOG_GET_ALL_DEF),
        @NamedQuery(
                name = JpaConst.Q_BLOG_COUNT,
                query = JpaConst.Q_BLOG_COUNT_DEF),
        @NamedQuery(
                name = JpaConst.Q_BLOG_GET_ALL_MINE,
                query = JpaConst.Q_BLOG_GET_ALL_MINE_DEF),
        @NamedQuery(
                name = JpaConst.Q_BLOG_COUNT_ALL_MINE,
                query = JpaConst.Q_BLOG_COUNT_ALL_MINE_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Blog {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.BLOG_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * blog_title
     */
    @Column(name=JpaConst.BLOG_COL_TITLE, length=255, nullable=false)
    private String title;

    /**
     * blog_content
     */
    @Lob
    @Column(name=JpaConst.BLOG_COL_CONTENT, nullable=false)
    private String content;

    /**
     * blog_image
     */
    @Column(name=JpaConst.BLOG_COL_IMAGE)
    private Blob image;

    /**
     * 投稿日時
     */
    @Column(name=JpaConst.BLOG_COL_CREATED_AT, nullable=false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name=JpaConst.BLOG_COL_UPDATED_AT, nullable=false)
    private LocalDateTime updatedAt;

    /**
     * blog_user_id
     */
    @Column(name=JpaConst.BLOG_COL_USER_ID, nullable=false)
    private Integer blog_user_id;
}
