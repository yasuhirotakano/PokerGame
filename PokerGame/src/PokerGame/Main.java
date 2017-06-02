package PokerGame;

/** メインクラス */
public class Main {
	/** クラスのインスタンス化 */
	public static Trump trumpDeck = new Trump();
	public static Chip point = new Chip();
	public static HighAndLow game = new HighAndLow();
	public static Poker playPoker = new Poker();
	public static Poker cpuPoker = new Poker();

	/** プレイヤーの手札 */
	public static Card playHand[] = new Card[5];

	/** CPUの手札 */
	public static Card cpuHand[] = new Card[5];

	/** メインメソッド */
	public static void main(String[] args) {
		/** gameStartメソッド呼び出し */
		gameStart();
	}

	/** ポーカーゲーム開始メソッド */
	public static void gameStart() {

		/** トランプデッキをシャッフル */
		trumpDeck.reset();
		trumpDeck.setNow(0);

		/** お互い5枚ずつドロー */
		for(int i = 0; i < playHand.length; i++) {
			playHand[i] = trumpDeck.drawCard();
			cpuHand[i] = trumpDeck.drawCard();
		}

		/** ゲームスタート */
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.println("　　　+++++++++++++++");
		System.out.println("　　　+ ポーカーゲーム +");
		System.out.println("　　　+++++++++++++++");
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.println("あなたの手札　　:" + playHand[0]  + " " + playHand[1] + " " + playHand[2] + " " + "□" + " " + "□");
		System.out.println("あなたのチップ数:" + point.getNowPoint() + "([10]:" + point.getTenChip() +"枚、[1]:" + point.getOneChip() + "枚)");
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");

		/** チップを賭ける */
		point.bet();

		/** 手札の役を判定 */
		playPoker.judgeHand(playHand);
		cpuPoker.judgeHand(cpuHand);

		System.out.println("＊＊＊＊＊＊＊＊＊＊　結果　＊＊＊＊＊＊＊＊＊＊");
		System.out.println("あなたの手札　　:" + playHand[0]  + " " + playHand[1] + " " + playHand[2] + " " + playHand[3] + " " + playHand[4]);
		System.out.println("役:" + playPoker.getPokerPrize());
		System.out.println("　　VS");
		System.out.println("相手の手札　　　:" + cpuHand[0]  + " " + cpuHand[1] + " " + cpuHand[2] + " " + cpuHand[3] + " " + cpuHand[4]);
		System.out.println("役:" + cpuPoker.getPokerPrize());
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");

		/** judgeメソッド呼び出し */
		judge();
	}

	/** 勝敗の判定メソッド */
	public static void judge() {

		/** 勝敗を決める */
		if(playPoker.getScore() > cpuPoker.getScore()) {
			System.out.println("");
			System.out.println("　　　＿人人人人人人人人＿");
			System.out.println("　　　＞　あなたの勝ち！＜");
			System.out.println("　　　￣^Y^Y^Y^Y^Y^Y^￣");
			System.out.println("");
			highAndLow();
		}else if(playPoker.getScore() < cpuPoker.getScore()) {
			point.setBetPoint(0);
			System.out.println("");
			System.out.println("　　　＿人人人人人人人人＿");
			System.out.println("　　　＞　あなたの負け！＜");
			System.out.println("　　　￣^Y^Y^Y^Y^Y^Y^￣");
			System.out.println("");
			choice();
		}else if(playPoker.getScore() == cpuPoker.getScore()) {
			if(playPoker.getNum() > cpuPoker.getNum()) {
				System.out.println("");
				System.out.println("　　　＿人人人人人人人人＿");
				System.out.println("　　　＞　あなたの勝ち！＜");
				System.out.println("　　　￣^Y^Y^Y^Y^Y^Y^￣");
				System.out.println("");
				highAndLow();
			}else if(playPoker.getNum() < cpuPoker.getNum()) {
				point.setBetPoint(0);
				System.out.println("");
				System.out.println("　　　＿人人人人人人人人＿");
				System.out.println("　　　＞　あなたの負け！＜");
				System.out.println("　　　￣^Y^Y^Y^Y^Y^Y^￣");
				System.out.println("");
				choice();
			}else if(playPoker.getNum() == cpuPoker.getNum()) {
				System.out.println("");
				System.out.println("　　　＿人人人人人人人人＿");
				System.out.println("　　　＞　　引き分け！　＜");
				System.out.println("　　　￣^Y^Y^Y^Y^Y^Y^￣");
				System.out.println("");
				point.reverse(point);
				choice();
			}
		}
	}

	/** 継続選択メソッド */
	public static void choice() {
		System.out.println("");
		System.out.println("");
		System.out.println("＊＊＊＊＊＊＊＊現在のチップ枚数＊＊＊＊＊＊＊＊");
		System.out.println("統計: " + point.getNowPoint() + "([10]:" + point.getTenChip() +"枚、[1]:" + point.getOneChip() + "枚");
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.println("[ゲームを続けますか？]: 0:Yes 1:No");

		/** BigOrSmallクラスのcheckメソッドの呼び出し */
		game.check();

		/** Yesを選んだ後の処理 */
		if(game.getContinuation() == true) {
			gameStart();

		/** Noを選択後ゲーム終了 */
		}else {
			System.out.println("");
			System.out.println("");
			System.out.println("END");
		}
	}

	/** ハイ&ローでダブルアップ実行メソッド */
	public static void highAndLow() {
		System.out.println("賭けたチップでダブルアップを行いますか？: 0:Yes 1:No");

		/** check()メソッドでエラーチェック */
		game.check();

		/** Yesを選んだ後の処理 */
		if(game.getContinuation() == true) {
			game.setDeck(trumpDeck);
			game.setPoint(point);
			game.start();
			point = game.getPoint();

			/** ポイントが0かどうかの判断、0ならゲーム終了 */
			if(point.getNowPoint() == 0) {
				System.out.println("");
				System.out.println("");
				System.out.println("END");
			}else {
				if(game.getContinuation() == true) {
					gameStart();
				}else {
					System.out.println("");
					System.out.println("");
					System.out.println("END");
				}
			}

		/** Noを選んだ後の処理 */
		}else {
			point.reverse(point);
			choice();
		}
	}
}