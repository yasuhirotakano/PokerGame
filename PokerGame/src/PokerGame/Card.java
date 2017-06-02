package PokerGame;

/** カードクラス */
public class Card {

	/** カードインスタンス変数
	 * mark カードのマーク
	 * number カードの数字
	 */
	private int mark;
	private int number;

	/** カードクラスのコンストラクタ */
	public Card(int mark, int number) {
		this.mark = mark;
		this.number = number;
	}

	/** getter/setterの定義 */
	public int getMark() {
		return this.mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public int getNumber() {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	/** toStringのオーバーライト
	 * markとnumberを文字列に変換
	 */
	@Override
	public String toString() {
		String[] markName = {"♠︎", "♥︎", "♦︎", "♣︎"};
		String[] numberName = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		return markName[mark] + numberName[number];
	}
}