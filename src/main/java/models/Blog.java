package models;

import java.time.LocalDate;
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
              //全てのブログをidの降順に取得する
                name = JpaConst.Q_BLOG_GET_ALL,
                query = JpaConst.Q_BLOG_GET_ALL_DEF),
        @NamedQuery(
              //全てのブログの件数を取得する
                name = JpaConst.Q_BLOG_COUNT,
                query = JpaConst.Q_BLOG_COUNT_DEF),
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
     * いつのブログかを示す日付
     */
    @Column(name=JpaConst.BLOG_COL_DATE, nullable=false)
    private LocalDate blogDate;

    /**
     * blog_content
     */
    @Lob
    @Column(name=JpaConst.BLOG_COL_CONTENT, nullable=false)
    private String content;

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


}
