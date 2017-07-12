package com.bupt.ltb.sem.vouching.frame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bupt.ltb.sem.vouching.pojo.Exam;
import com.bupt.ltb.sem.vouching.service.impl.CompetitionServiceImpl.CompetitionBean;

/**
 * 全局上下文
 * 
 * @author Hogan
 * 
 */
@Component("globalContext")
@Scope("singleton")
public class GlobalContext {

	/**
	 * 当前邮件的坐标
	 */
	private Map<Integer, Integer[]> currentEmailIndex = new HashMap<>();

	/**
	 * 当前用户的练习题
	 */
	private Map<Integer, List<? extends Question>> currentPracticePaper = new HashMap<>();

	/**
	 * 当前用户在进行的考试
	 */
	private Map<Integer, Exam> currentExam = new HashMap<>();

	/**
	 * 匹配队列
	 */
	private Map<String, CompetitionBean> competitionQueue = new ConcurrentHashMap<>();

	/**
	 * 未匹配完的队列索引
	 */
	private Set<String> unMatchedIndex = new HashSet<>();

	/**
	 * 匹配完的队列索引
	 */
	private Set<String> matchedIndex = new HashSet<>();

	/**
	 * 计算过分数的队列索引
	 */
	private Set<String> scoredIndex = new HashSet<>();

	public Map<Integer, Integer[]> getCurrentEmailIndex() {
		return currentEmailIndex;
	}

	public Map<Integer, List<? extends Question>> getCurrentPracticePaper() {
		return currentPracticePaper;
	}

	public Map<Integer, Exam> getCurrentExam() {
		return currentExam;
	}

	public Map<String, CompetitionBean> getCompetitionQueue() {
		return competitionQueue;
	}

	public Set<String> getUnMatchedIndex() {
		return unMatchedIndex;
	}

	public Set<String> getMatchedIndex() {
		return matchedIndex;
	}

	public Set<String> getScoredIndex() {
		return scoredIndex;
	}

}