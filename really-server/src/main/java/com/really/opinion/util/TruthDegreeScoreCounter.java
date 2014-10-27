/**
 * @author zhangjx
 * @time 2014年8月31日 上午8:53:41
 * @description:
 */
package com.really.opinion.util;

import com.really.enumeration.TruthDegree;

/**
 * @author zhangjx
 * @date 2014年8月31日 上午8:53:41
 *
 */
public class TruthDegreeScoreCounter {

	public static Double getScore(int truthDegree, double userTruthDegreeScore) {
		Double score = null;
		switch(truthDegree) {
		case TruthDegree.FAKE :
			score = 10D;
			break;
		case TruthDegree.DUBIOUS :
			score = 30D;
			break;
		case TruthDegree.INDIFFERENT :
			score = 50D;
			break;
		case TruthDegree.CREDIBLE :
			score = 70D;
			break;
		case TruthDegree.BELIEVABLE :
			score = 90D;
			break;
		default :
			break;
		}
		if(score != null) {
			score = score * userTruthDegreeScore / 100;
		}
		return score;
	}
}
