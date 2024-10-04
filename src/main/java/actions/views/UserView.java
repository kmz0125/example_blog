package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ユーザー情報について画面の入力値・出力値を扱うViewモデル
 * @author KM
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserView {

    /**
     * id
     */
    private Integer id;

    /**
     * password
     */
    private String password;

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;
}
