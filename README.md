# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 요구사항
* 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
* 로또 1장의 가격은 1000원이다.
* 구입 금액에 맞춰 랜덤 로또 결과가 출력된다.
* 저번주 당첨 숫자에 맞춰 맞춘 숫자와 금액, 수익률이 출력된다.

## 기능 구현
* [x] 금액은 1000원 단위로 구입금액을 입력받는다.
    * [x] 금액은 단위가 1000단위가 아니면 Exception 을 돌려준다.
    * [x] 로또번호 생성기는 1 ~ 45사이 서로 다른 숫자로 구성된 로또 번호 6개가 구매 갯수만큼 생성한다.
* [x] 당첨번호 입력기는 당첨용 번호 6개 + 보너스 번호 1개를 입력한다.
* [x] 금액 계산 시뮬레이터는 당첨 번호와 각각의 로또 번호를 비교하여 당첨 통계를 계산한다.
  * [x] 로터리 시뮬레이터는 당첨용 번호화 로또번호를 비교해서 등수를 계산한다.
* [x] 금액 계산 시뮬레이터는 총 금액과 입력 금액을 비교하여 수익률을 계산한다.
* [x] 뷰는 당첨 통계와 수익률을 출력한다.
* [x] 사용자 수동으로 로또 번호를 입력할 수 있다.
* [x] 사용자가 입력한 로또 번호와 랜덤으로 생성된 로또 번호를 추가하여 계산을 진행한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)