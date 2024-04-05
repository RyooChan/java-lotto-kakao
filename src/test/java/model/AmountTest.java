package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AmountTest {

    @Test
    void 금액은_천원_단위로_입력받는다() {
        int cost = 14000;
        Amount amount = new Amount(cost);
        Assertions.assertThat(amount.getCost()).isEqualTo(cost);
    }

    @Test
    void 금액은_천원_단위가_아니면_exception_출력() {
        int cost = 14500;

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                Amount amount = new Amount(cost);
            }).withMessage("1000원 단위로 입력해주세요.");
    }

    @Test
    void 금액은_천원_이상만_가능() {
        int cost = 0;

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                Amount amount = new Amount(cost);
            }).withMessage("금액은 1000원 이상이여야 합니다.");
    }
}
