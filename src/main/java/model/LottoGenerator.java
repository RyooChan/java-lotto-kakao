package model;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
    private final List<LottoNumbers> lottoNumbers;

    public LottoGenerator(List<LottoNumbers> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public static LottoGenerator generateAll(int wholeCount, List<LottoNumbers> manualLottos) {
        validateStock(wholeCount, manualLottos.size());
        List<LottoNumbers> allLottos = new ArrayList<>();
        LottoGenerator lottoGenerator = generateRandom(wholeCount - manualLottos.size());
        allLottos.addAll(manualLottos);
        allLottos.addAll(lottoGenerator.lottoNumbers);
        return new LottoGenerator(allLottos);
    }

    public static LottoGenerator generateRandom(int count) {
        List<LottoNumbers> lottoNumberList = new ArrayList<>();

        for (int i=0; i<count; i++) {
            LottoNumbers lottoNumbers =
                new LottoNumbers(LottoNumbers.generateRandomNumbers(new RandomNumberGenerator() {}));
            lottoNumberList.add(lottoNumbers);
        }

        return new LottoGenerator(lottoNumberList);
    }

    private static void validateStock(int wholeCount, int manualCount) {
        if (wholeCount < manualCount){
            throw new IllegalArgumentException("총 구매 가능 갯수를 초과했습니다.");
        }
    }

    public int calculateCount() {
        return lottoNumbers.size();
    }

    public List<LottoNumbers> getLottoNumbers() {
        return lottoNumbers;
    }

    public int getWholeLottoSize() {
        return lottoNumbers.size();
    }
}
