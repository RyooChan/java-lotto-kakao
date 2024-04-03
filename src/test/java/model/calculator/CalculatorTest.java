package model.calculator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.Ball;
import model.random.LottoNumbers;
import model.winningLottery.Lottery;
import model.winningLottery.Ranking;
import model.winningLottery.WinningBonusNumber;
import model.winningLottery.WinningNumbers;

import static model.Ball.*;
import static model.winningLottery.Ranking.FIFTH;
import static model.winningLottery.Ranking.FIRST;
import static model.winningLottery.Ranking.FOURTH;
import static model.winningLottery.Ranking.NONE;
import static model.winningLottery.Ranking.SECOND;
import static model.winningLottery.Ranking.THIRD;
import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    void 로또번호들을_저장한다() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(createBallSet(Set.of(1,2,3,4,5,6)));
        LottoResult lottoResult = new LottoResult(lottoNumbers);
        Calculator calculator = new Calculator(List.of(lottoResult));

        WinningNumbers winningNumbers = new WinningNumbers(createBallSet(Set.of(1,2,3,4,5,6)));
        WinningBonusNumber bonusNumber = new WinningBonusNumber(new Ball(7));
        Lottery lottery = new Lottery(winningNumbers, bonusNumber);

        LottoResult expectedLottoResult = new LottoResult(lottoNumbers, FIRST);
        Calculator expectedResult = new Calculator(List.of(expectedLottoResult));

        // when
        calculator.saveRanking(lottery);

        // then
        assertThat(calculator).isEqualTo(expectedResult);

    }

    @Test()
    void 각_등수를_가진_로또번호들의_갯수를구한다() {
        // given
        LottoResult firstOne = new LottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6))), FIRST);
        LottoResult firstTwo = new LottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6))), FIRST);
        LottoResult secondOne = new LottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 7))), SECOND);
        Calculator calculator = new Calculator(List.of(firstOne, firstTwo, secondOne));

        Map<Ranking, Integer> expectedEnumMap = new EnumMap<>(Ranking.class);
        expectedEnumMap.put(FIRST, 2);
        expectedEnumMap.put(SECOND, 1);
        expectedEnumMap.put(THIRD, 0);
        expectedEnumMap.put(FOURTH, 0);
        expectedEnumMap.put(FIFTH, 0);
        expectedEnumMap.put(NONE, 0);

        // when
        calculator.calculateRankingCount();
        Map<Ranking, Integer> rankingCountMap = calculator.getRankingCountMap();

        // then
        assertThat(rankingCountMap).isEqualTo(expectedEnumMap);
    }

    @Test()
    void 각_등수를_통해_전체_수익률을_구한다() {
        // given
        LottoResult firstOne = new LottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6))), FIRST);
        LottoResult firstTwo = new LottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6))), FIRST);
        LottoResult secondOne = new LottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 7))), SECOND);
        Calculator calculator = new Calculator(List.of(firstOne, firstTwo, secondOne));

        // when
        calculator.calculateRankingCount();
        double profitRate = calculator.calculateProfitRate();

        // then
        assertThat(profitRate).isEqualTo(1343333.33);
    }

}