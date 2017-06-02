package PokerGame;

/** ポーカークラス */
public class Poker {

	/** ポーカークラスの変数
	 * score = 役の点数
	 * pokerPriza = 役の名前
	 * num = カードが入ってる配列の番号
	 */
	private int score;
	private String pokerPrize;
	private int num;

	/** getter/setterの定義 */
	public int getScore() {
		return this.score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getPokerPrize() {
		return this.pokerPrize;
	}
	public void setPokerPrize(String pokerPrize) {
		this.pokerPrize = pokerPrize;
	}
	public int getNum() {
		return this.num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	/** フラッシュ判定メソッド
	 * @param hand = 手札の配列
	 * @return フラッシュならtrue
	 */
	public  boolean isFlush(Card[] hand) {
		for(int i = 0; i <hand.length - 1; i++) {
			if(hand[i].getMark() != hand[i + 1].getMark()) {
				return false;
			}
		}
		return true;
	}


	/** ストレート判定メソッド
	 * @param dist = 手札のカード分布配列
	 * @return ストレートなら配列の開始位置 違うなら-1を返す
	 */
	public int isStart(int[] dist){
		for(int i = 0; i <= 8; i++){
			if(dist[i] == 1){
				for(int j = 1; j < 5; j++){
					if(dist[i + j] != 1){
						return -1;
					}
				}
				return i;
			}
		}
		return -1;
	}

	/** nカードの役を判定を判定するメソッド
	 * @param n = カード役の数字
	 * @param dist = 手札のカード分布配列
	 * @return nカードならtrue
	 */
	public boolean isNoCard(int n, int[] dist){
		num = 0;
		for (int i = 0; i < dist.length; i++) {
			if(dist[i] == n) {
				num = i;
				return true;
			}
		}
		return false;
	}

	/** nペアの役を判定するメソッド
	 * @param n = ペア役の数字
	 * @param dist = 手札のカード分布配列
	 * @return nペアならtrue
	 */
	public boolean isNoPair(int n, int[] dist){
		num = 0;
		int ret = 0;
		for (int i = 0; i < dist.length; i++) {
			if(dist[i] == 2) {
				ret++;
				num = i;
			}
		}
		return ret == n;
	}

	/** 手札の役を判定するメソッド
	 * @param hand = 手札の配列
	 */
	public void judgeHand(Card[] hand) {

		/** カードの分布を判定するための配列 */
		int[] dist = new int[13];
		score = 0;
		pokerPrize = "役なし";
		for(Card c : hand) {
			dist[c.getNumber()]++;
		}

		/** 役の判定 */
		boolean isFlush = isFlush(hand);
		int startNo = isStart(dist);

		if(isFlush && startNo != -1){
			score = 8;
			pokerPrize = "ストレートフラッシュ";
		} else if(isNoCard(4, dist)){
			score = 7;
			pokerPrize = "フォーカード";
		} else if(isNoCard(3, dist) && isNoCard(2, dist)){
			score = 6;
			pokerPrize = "フルハウス";
		} else if(isFlush){
			score = 5;
			pokerPrize = "フラッシュ";
		} else if(startNo != -1){
			score = 4;
			pokerPrize = "ストレート";
		} else if(isNoCard(3, dist)){
			score = 3;
			pokerPrize = "スリーカード";
		} else if(isNoPair(2, dist)){
			score = 2;
			pokerPrize = "ツーペア";
		} else if(isNoPair(1, dist)){
			score = 1;
			pokerPrize = "ワンペア";
		}
	}
}