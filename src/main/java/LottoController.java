import java.util.List;
import java.util.Map;

import model.Amount;
import model.Ball;
import model.calculator.Calculator;
import model.calculator.LottoResult;
import model.random.LottoGenerator;
import model.winningLottery.WinMatch;
import model.winningLottery.Ranking;
import model.winningLottery.WinningBonusNumber;
import model.winningLottery.WinningNumbers;
import view.InputView;
import view.OutputView;

import static java.util.stream.Collectors.toList;

public class LottoController {
    public static void run() {
        Amount amount = getAmount();

        LottoGenerator lottoGenerator = generateLottoNumbers(amount);

        WinningNumbers winningNumbers = inputWinningNumbers();

        WinningBonusNumber winningBonusNumber = inputBonusNumber(winningNumbers);

        WinMatch winMatch = new WinMatch(winningNumbers, winningBonusNumber);
        Calculator calculator = calculateLottery(winMatch, lottoGenerator);

        printRankingProfit(calculator);
    }

    private static Amount getAmount() {
        int amountInput = InputView.amountInput();
        Amount amount = new Amount(amountInput);
        return amount;
    }

    private static LottoGenerator generateLottoNumbers(Amount amount) {
        LottoGenerator lottoGenerator = LottoGenerator.generate(amount);
        OutputView.printPurchaseCount(lottoGenerator.calculateCount());
        OutputView.printLottoNumberList(lottoGenerator.getLottoNumberList());
        return lottoGenerator;
    }

    private static WinningNumbers inputWinningNumbers() {
        WinningNumbers winningNumbers = WinningNumbers
            .createWinningNumbers(InputView.winningNumbersInput());
        return winningNumbers;
    }

    private static WinningBonusNumber inputBonusNumber(WinningNumbers winningNumbers) {
        WinningBonusNumber winningBonusNumber = WinningBonusNumber
            .createWinningBonusNumber(winningNumbers, Ball.createBallOrThrowException(InputView.winningBonusNumberInput()));
        return winningBonusNumber;
    }

    private static Calculator calculateLottery(WinMatch winMatch, LottoGenerator lottoGenerator) {
        List<LottoResult> lottoResultList = lottoGenerator.getLottoNumberList().stream()
            .map(
                lottoNumbers -> LottoResult.createLottoResult(lottoNumbers, winMatch)
            )
            .collect(toList());
        return Calculator.createCalculator(lottoResultList, winMatch);
    }

    private static void printRankingProfit(Calculator calculator) {
        Map<Ranking, Integer> rankingCountMap = calculator.getRankingCountMap();
        OutputView.printRankingResult(rankingCountMap);

        double profitRate = calculator.calculateProfitRate();
        OutputView.printProfitRateResult(profitRate);
    }
}


