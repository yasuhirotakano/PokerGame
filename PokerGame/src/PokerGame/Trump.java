package PokerGame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** トランプクラス */
public class Trump {

	/** トランプクラスの変数
	 * now = 現在のカードを引いた回数
	 * cardStack = 52枚のトランプデッキ
	 */
	private int now = 0;
	private Card[] cardStack = new Card[52];

	/**トランプのコンストラクタ */
	public Trump() {
		for(int i = 0; i < cardStack.length; i++) {
			cardStack[i] = new Card(i/13, i%13);
		}
	}

	/** getter/setterの定義 */
	public int getNow() {
		return this.now;
	}
	public void setNow(int now) {
		this.now = now;
	}
	public Card[] getCardStack() {
		return this.cardStack;
	}
	public void setCardStack(Card[] cardStack) {
		this.cardStack = cardStack;
	}

	/** カードを１枚デッキの上から引くメソッド */
	public Card drawCard() {
		return cardStack[now++];
	}

	/** シャッフルして初期化するメソッド */
	public void reset() {
		List<Card>list = Arrays.asList(cardStack);
		Collections.shuffle(list);
		cardStack = (Card[]) list.toArray(new Card[52]);
	}
}