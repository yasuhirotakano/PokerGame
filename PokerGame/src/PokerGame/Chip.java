package PokerGame;

import java.util.Scanner;

/** チップクラス */
public class Chip {

	/** チップクラスの変数
	 * tenChip = 10点のチップ　初期は10枚
	 * oneChip = 1点のチップ　初期は0枚
	 * nowPoint = 今持ってるチップの合計点
	 * betPoint = 賭ける点数
	 * differencePoint = 清算後の点数
	 * cCheck = 入力の半角数値チェック
	 */
	private int tenChip = 10;
	private int oneChip = 0;
	private int nowPoint = tenChip * 10 + oneChip * 1;
	private int betPoint;
	private int differencePoint;
	private String cCheck;

	/** getter/setterの定義 */
	public int getTenChip() {
		return this.tenChip;
	}
	public void setTenChip(int tenChip) {
		this.tenChip = tenChip;
	}
	public int getOneChip() {
		return this.oneChip;
	}
	public void setOneChip(int oneChip) {
		this.oneChip = oneChip;
	}
	public int getNowPoint() {
		return this.nowPoint;
	}
	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}
	public int getBetPoint() {
		return this.betPoint;
	}
	public void setBetPoint(int betPoint) {
		this.betPoint = betPoint;
	}
	public int getDifferencePoint() {
		return this.differencePoint;
	}
	public void setDifferencePoint(int differencePoint) {
		this.differencePoint = differencePoint;
	}
	public String getCCheck() {
		return this.cCheck;
	}
	public void setCCheck(String cCheck) {
		this.cCheck = cCheck;
	}

	/** betを賭けるメソッド */
	public void bet() {
		System.out.println("■BETするチップ数を入力してください(最低1～最大20)");
		while(true) {
			cCheck = new Scanner(System.in).nextLine();
			if(cCheck.matches("^[0-9]+$") == true) {
				betPoint = Integer.parseInt(cCheck);
				if(betPoint > 20 || betPoint < 1) {
					System.err.println("チップ数は半角数字の1〜20を入力してください");
				}else if(betPoint < 21 && betPoint > nowPoint) {
					System.err.println("BETするチップ数は総計のチップ数以下で入力してください");
					System.err.println("BETできるチップ数は"+ nowPoint + "までになる");
				}else {
					break;
				}
			}else {
				System.err.println("チップ数は半角数字の1〜20を入力してください");
			}
		}
		/** チップの清算 */
		differencePoint = nowPoint - betPoint;
		tenChip = differencePoint / 10;
		oneChip = differencePoint % 10;
		nowPoint = tenChip * 10 + oneChip * 1;
	}

	/**
	 * チップを戻す
	 * @param point 今の点数
	 */
	public void reverse(Chip point) {
		differencePoint = nowPoint + betPoint;
		tenChip = differencePoint / 10;
		oneChip = differencePoint % 10;
		nowPoint = tenChip * 10 + oneChip * 1;
	}
}