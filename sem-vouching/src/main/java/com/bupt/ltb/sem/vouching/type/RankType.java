package com.bupt.ltb.sem.vouching.type;

import java.util.HashMap;
import java.util.Map;

/**
 * 名次类型
 * 
 * @author Hogan
 *
 */
public enum RankType {

	/**
	 * 第一名3分
	 */
	RANK_ONE(1, 3),
	/**
	 * 第二名2分
	 */
	RANK_TWO(2, 2),
	/**
	 * 第三名1分
	 */
	RANK_THREE(3, 1),
	/**
	 * 第四名0分
	 */
	RANK_FOUR(4, 0),
	/**
	 * 第五名0分
	 */
	RANK_FIVE(5, 0),;

	private Integer rank;
	private Integer score;

	RankType(Integer rank, Integer score) {
		this.rank = rank;
		this.score = score;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	private static final Map<Integer, RankType> DIRC = new HashMap<>();

	static {
		for (RankType e : RankType.values()) {
			DIRC.put(e.getRank(), e);
		}
	}

	public static RankType byRank(int id) {
		return DIRC.get(id);
	}

}
