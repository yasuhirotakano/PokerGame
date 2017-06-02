package PokerGame;

import java.util.Scanner;

/** ロジック処理クラス */
public class HighAndLow {

	/** 変数定義
	 * deck = 今のトランプのデッキ
	 * correntCard = 今のカード
	 * nextCard = 引いたカード
	 * point = 今のポイント
	 * choice = HighまたはLowの選択　0=High 1=Low
	 * bOrS = 選択したHighまたはLowの表示
	 * result = 結果を表示 Win　または　Lose
	 * chiceR = 選択結果のHighまたはLowの表示
	 * eCheck = 入力が0か1の半角数値チェック
	 * vod = 勝敗 true=勝ち false=負け
	 * continuation = 続けるかどうかの選択 true=続ける false=やめる
	 */
	private Trump deck;
	private Card correntCard;
	private Card nextCard;
	private Chip point;
	private int choice;
	private String bOrS;
	private String result;
	private String choiceR;
	private String eCheck;
	private boolean vod;
	private boolean continuation;

	/** getter/setterの定義 */
	public Trump getDeck() {
		return this.deck;
	}
	public void setDeck(Trump deck) {
		this.deck = deck;
	}
	public Card getCorrentCard() {
		return this.correntCard;
	}
	public void setCorrentCard(Card correntCard) {
		this.correntCard = correntCard;
	}
	public Card getNextCard() {
		return this.nextCard;
	}
	public void setNextCard(Card nextCard) {
		this.nextCard = nextCard;
	}
	public Chip getPoint() {
		return this.point;
	}
	public void setPoint(Chip point) {
		this.point = point;
	}
	public int getChoice() {
		return this.choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public String getBOrS() {
		return this.bOrS;
	}
	public void setBOrS(String bOrS) {
		this.bOrS = bOrS;
	}
	public String getResult() {
		return this.result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getChoiceR() {
		return this.choiceR;
	}
	public void setChoiceR(String choiceR) {
		this.choiceR = choiceR;
	}
	public String getECheck() {
		return this.eCheck;
	}
	public void setECheck(String eCheck) {
		this.eCheck = eCheck;
	}
	public boolean isVod() {
		return this.vod;
	}
	public void setVod(boolean vod) {
		this.vod = vod;
	}
	public boolean getContinuation() {
		return this.continuation;
	}
	public void setContinuation(boolean continuation) {
		this.continuation = continuation;
	}

	/** メソッドの定義 */
	/** ゲームを開始するメソッド */
	public void start() {

		/** デッキをシャッフルしてリセット */
		deck.reset();
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");

		/** デッキからカードを1枚引く */
		correntCard = deck.drawCard();
		System.out.println("現在のカード: " + correntCard);
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");

		/** choiceメソッドを呼び出し */
		choice();
	}

	/** 0か1のエラーチェックメソッド */
	public void check() {
		while(true) {
			eCheck = new Scanner(System.in).nextLine();
			if(eCheck.matches("0|1") == true) {
				choice = Integer.parseInt(eCheck);
				if(choice == 0) {
					continuation = true;
					break;
				}else {
					continuation = false;
					break;
				}
			}else {
				System.err.println("半角数字の0あるいは1のみ入力してください");
			}
		}
	}

	/** ゲームを続けるかどうかのYesかNoの選択メソッド */
	public void gameChoice() {
		System.out.println("");
		System.out.println("");
		System.out.println("＊＊＊＊＊＊＊＊現在のチップ枚数＊＊＊＊＊＊＊＊");
		System.out.println("統計: " + point.getNowPoint() + "([10]:" + point.getTenChip() +"枚、[1]:" + point.getOneChip() + "枚");
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.println("[ゲームを続けますか？]: 0:Yes 1:No");

		/** checkメソッドの呼び出し */
		check();
	}

	/** BigorSmallを選択するメソッド */
	public void choice() {

		/** BigorSmallを選択 */
		while(true) {
			System.out.println("■High or Low選択");
			System.out.println("現在のカード: " + correntCard);
			System.out.println("[High or Low]: 0:High 1:Low");
			eCheck = new Scanner(System.in).nextLine();
			if(eCheck.matches("0|1") == true) {
				choice = Integer.parseInt(eCheck);
				break;
			}else {
				System.err.println("半角数字の0あるいは1のみ入力してください");
			}
		}

		/** カードを1枚引く */
		nextCard = deck.drawCard();

		/** Bigを選択したときの勝利パターン */
		if(choice == 0) {
			bOrS = "Big";
			if(nextCard.getNumber() > correntCard.getNumber()
			   || nextCard.getNumber() == correntCard.getNumber() && nextCard.getMark() < correntCard.getMark()) {
				result = "Win!!";
				choiceR = "Big";
				vod = true;
			}else {
				result = "Lose..";
				choiceR = "Small";
				vod = false;
			}

			/** Smallを選択したときの勝利パターン */
		}else {
			bOrS="Small";
			if(nextCard.getNumber() < correntCard.getNumber()
			   || nextCard.getNumber() == correntCard.getNumber() && nextCard.getMark() > correntCard.getMark()) {
				result = "Win!!";
				choiceR = "Small";
				vod = true;
			}else {
				result = "Lose..";
				choiceR = "Big";
				vod = false;
			}
		}

		/** 結果の表示 */
		System.out.println("＊＊＊＊＊＊＊＊High and Low＊＊＊＊＊＊＊＊＊");
		System.out.println("BET数: " + point.getBetPoint());
		System.out.println("あなたの選択: " + bOrS);
		System.out.println("現在のカード: " + correntCard);
		System.out.println("引いたカード: " + nextCard);
		System.out.println(nextCard + " は " + correntCard + " より " + choiceR);
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.println("");
		System.out.println(result);

		/** liquidationメソッドを呼び出し */
		liquidation();
	}

	/** 勝敗に応じたチップを清算し続けるかどうかを選択するメソッド */
	public void liquidation() {

		/** 引いたカードを今のカードにする */
		correntCard = nextCard;

		/** Winの時の続行の選択 */
		if(vod == true){
			point.setBetPoint(point.getBetPoint() * 2);
			System.out.println("チップ" + point.getBetPoint() + "枚を獲得しました");
			System.out.println("");
			System.out.println("");
			System.out.println("[獲得したチップ" + point.getBetPoint() + "枚でHigh and Lowを続けますか？]: 0:Yes 1:No");

			/** checkメソッドの呼び出し */
			check();

			/** Yesを選んで続ける処理 */
			if(continuation == true) {
				choice();

				/** Noを選んだ後ゲームを続けるかどうかの選択 */
			}else {

				/**チップの清算 */
				int win = point.getNowPoint() + point.getBetPoint();
				point.setNowPoint(win);
				point.setTenChip(win / 10);
				point.setOneChip(win % 10);

				/** gameChoiceメソッドの呼び出し */
				gameChoice();
			}

		/** Loseの時の処理　負けた後ゲームを続けるかどうかを選択 */
		}else {

			/** チップが0の時に終わらせる処理 */
			if(point.getNowPoint() != 0) {
				gameChoice();
			}
		}
	}
}