package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * userのDTOモデル
 *
 */

@Table(name = JpaConst.TABLE_USER)
@NamedQueries({
        @NamedQuery(
                //全てのユーザーのidを降順に取得する
                name = JpaConst.Q_USER_GET_ALL,
                query = JpaConst.Q_USER_GET_ALL_DEF),
        @NamedQuery(
                //全てのユーザーの件数を取得する
                name = JpaConst.Q_USER_COUNT,
                query = JpaConst.Q_USER_COUNT_DEF),
        @NamedQuery(
                //ハッシュ化済みパスワードを条件にユーザーを取得する
                name = JpaConst.Q_USER_GET_BY_PASS,
                query = JpaConst.Q_USER_GET_BY_PASS_DEF)

})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.USER_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * password
     */
    @Column(name = JpaConst.USER_COL_PASS, length = 64, nullable = false)
    private String password;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.USER_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

}
