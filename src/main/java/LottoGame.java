import java.util.List;
import java.util.Map;

import model.Amount;
import model.Ball;
import model.calculator.Calculator;
import model.calculator.LottoResult;
import model.random.LottoGenerator;
import model.winningLottery.Lottery;
import model.winningLottery.Ranking;
import model.winningLottery.WinningBonusNumber;
import model.winningLottery.WinningNumbers;
import view.InputView;
import view.OutputView;

import static java.util.stream.Collectors.*;

public class LottoGame {
    public static void main(String[] args) {
        int amountInput = InputView.amountInput();

        // logic
        Amount amount = Amount.costInput(amountInput);
        LottoGenerator lottoGenerator = LottoGenerator.generate(amount);

        OutputView.printPurchaseCount(lottoGenerator.calculateCount());
        OutputView.printLottoNumberList(lottoGenerator.getLottoNumberList());

        WinningNumbers winningNumbers = WinningNumbers
            .createWinningNumbers(InputView.winningNumbersInput());

        WinningBonusNumber winningBonusNumber = WinningBonusNumber
            .createWinningBonusNumber(winningNumbers, new Ball(InputView.winningBonusNumberInput()));

        Lottery lottery = new Lottery(winningNumbers, winningBonusNumber);

        List<LottoResult> lottoResultList = lottoGenerator.getLottoNumberList().stream()
            .map(
                LottoResult::new
            ).collect(toList());

        Calculator calculator = new Calculator(lottoResultList);
        calculator.saveRanking(lottery);

        Map<Ranking, Integer> rankingCountMap = calculator.getRankingCountMap();
        OutputView.printRankingResult(rankingCountMap);

        double profitRate = calculator.calculateProfitRate();
        OutputView.printProfitRateResult(profitRate);
    }
}
