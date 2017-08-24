package com.bupt.ltb.sem.vouching.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.type.ErrorCode;
import com.bupt.ltb.common.util.CommonUtils;
import com.bupt.ltb.common.util.StringUtils;
import com.bupt.ltb.sem.vouching.frame.Consts;
import com.bupt.ltb.sem.vouching.frame.GlobalContext;
import com.bupt.ltb.sem.vouching.frame.PageSize;
import com.bupt.ltb.sem.vouching.frame.Question;
import com.bupt.ltb.sem.vouching.mapper.CompetitionMapper;
import com.bupt.ltb.sem.vouching.mapper.RadioMapper;
import com.bupt.ltb.sem.vouching.mapper.UserMapper;
import com.bupt.ltb.sem.vouching.pojo.Competition;
import com.bupt.ltb.sem.vouching.pojo.Radio;
import com.bupt.ltb.sem.vouching.pojo.User;
import com.bupt.ltb.sem.vouching.service.CompetitionService;
import com.bupt.ltb.sem.vouching.type.OptionType;
import com.bupt.ltb.sem.vouching.type.RankType;
import com.bupt.ltb.sem.vouching.type.error.PracticeError;
import com.bupt.ltb.sem.vouching.util.PageJsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 竞技模块业务层实现
 * 
 * @author Hogan
 *
 */
@Service("competitionService")
public class CompetitionServiceImpl implements CompetitionService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private RadioMapper radioMapper;

	@Resource
	private CompetitionMapper competitionMapper;

	@Resource
	private GlobalContext globalContext;

	@Override
	public LJSONObject loadRankInfo(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		List<User> users = userMapper.findUserByCredit(PageSize.COMPETITION_RANK_RESULT);
		detail.put("ranks", users);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject joinQueue(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		joinQueue(user);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject matching(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		String queueIndex = user.getCompetitionIndex();
		detail.put("users", globalContext.getCompetitionQueue().get(queueIndex).getUsers());
		detail.put("isDone", globalContext.getMatchedIndex().contains(queueIndex));
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject loadCompetitionExam(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		String competitionIndex = user.getCompetitionIndex();
		List<? extends Question> questions = globalContext.getCompetitionQueue().get(competitionIndex).getQuestions();
		detail.put("questions", questions);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject loadCompetitionRecord(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		Integer pageNum = jParams.getInteger("pageNum");
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		PageHelper.startPage(pageNum, PageSize.COMPETITION_RECORD);
		List<Competition> competitions = competitionMapper.findCompetitionsByUserId(user.getUserId());
		PageInfo<Competition> pageInfo = new PageInfo<Competition>(competitions);
		detail.put("records", pageInfo.getList());
		result.setDetail(detail);
		result.setPager(PageJsonUtil.generatePageJson(pageInfo));
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject showAnswer(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		String[] answers = jParams.getObject("answers", String[].class);
		Integer score = 0;
		Integer count = 0;
		Integer competitionPassed=0;
		if (answers != null) {
			String competitionIndex = user.getCompetitionIndex();
			if (competitionIndex != null) {
				CompetitionBean competitionBean = globalContext.getCompetitionQueue().get(competitionIndex);
				if (competitionBean != null) {
					List<? extends Question> questions = competitionBean.getQuestions();
					for (Question question : questions) {
						if (answers[count] != null) {
							if (question instanceof Radio) {// 暂时只有选择题
								if (OptionType.byId(Integer.parseInt(answers[count])).getDescription()
										.equals(question.getAnswer())) {
									score++;
								}
							}
							count++;
						}
					}
					// 暂存用户的竞技得分，通过独立线程进行处理
					List<User> users = competitionBean.getUsers();
					for (User e : users) {
						if (e.getUserId() == user.getUserId()) {
							e.setCompetitionScore(score);
							break;
						}
					}
					if (count >= 200 && (score / count) > (3 / 4)) {
						competitionPassed = 1;
						user.setCompetitionPassed(1);
					}
					Competition competition = new Competition();
					competition.setDate(new Date());
					competition.setScore(score);
					competition.setUserId(user.getUserId());
					if (competitionMapper.addCompetition(competition) == Consts.DATA_SINGLE_SUCCESS) {
						competitionBean.getScoredCount().incrementAndGet();
						detail.put("answers", competitionBean.getAnswers());
						detail.put("score", score);
						result.setDetail(detail);
						result.setErrorCode(ErrorCode.SUCCESS);
					} else {
						result.setErrorCode(PracticeError.SHOW_ANSWER_FAILD);
					}
				}
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public LJSONObject creditHandle(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		Set<String> scoredIndex = globalContext.getScoredIndex();
		String competitionIndex = user.getCompetitionIndex();
		Integer credit = 0;
		if (scoredIndex.contains(competitionIndex)) {
			List<User> users = globalContext.getCompetitionQueue().get(competitionIndex).getUsers();
			for(User e : users){
				if(e.getUserId() == user.getUserId()){
					credit = e.getCredit();
					break;
				}
			}
			detail.put("credit", credit);
			detail.put("isDone", true);
		} else {
			detail.put("isDone", false);
		}
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@PostConstruct
	private void loadAllListeners() {
		competitionListener();
		creditListener();
	}

	/**
	 * 匹配监听器
	 */
	private void competitionListener() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Map<String, CompetitionBean> competitionQueue = globalContext.getCompetitionQueue();
				Set<String> unMatchedIndex = globalContext.getUnMatchedIndex();
				Set<String> matchedIndex = globalContext.getMatchedIndex();
				for (int i = 0; i < Consts.QUEUE_INIT_SIZE; i++) {
					String competitionIndex = CommonUtils.generateUUID();
					competitionQueue.put(competitionIndex, new CompetitionBean());
					unMatchedIndex.add(competitionIndex);
				}
				for (;;) {
					for (String queueIndex : competitionQueue.keySet()) {
						CompetitionBean competitionBean = competitionQueue.get(queueIndex);
						if (competitionBean.getMatchedCount().get() == Consts.QUEUE_MATCH_SIZE) {
							unMatchedIndex.remove(queueIndex);
							matchedIndex.add(queueIndex);
							randomGenerateQuestions(competitionBean);
						}
					}
				}
			}
		}).start();
	}

	/**
	 * 积分计算监听器
	 */
	private void creditListener() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Map<String, CompetitionBean> competitionQueue = globalContext.getCompetitionQueue();
				Set<String> scoredIndex = globalContext.getScoredIndex();
				for (;;) {
					for (String competitionIndex : competitionQueue.keySet()) {
						if (!scoredIndex.contains(competitionIndex)) {
							CompetitionBean competitionBean = competitionQueue.get(competitionIndex);
							if (competitionBean.getScoredCount().get() == Consts.QUEUE_MATCH_SIZE) {
								// 积分规则
								// 第一3分，第二2分，第三1分，其余没分
								int[] scores = new int[Consts.QUEUE_MATCH_SIZE];
								List<User> users = competitionBean.getUsers();
								Map<Integer, Integer> rankMap = new HashMap<>();
								int preRank = 0;
								int preScore = 0;
								for (int i = 0; i < Consts.QUEUE_MATCH_SIZE; i++) {
									scores[i] = users.get(i).getCompetitionScore();
									rankMap.put(scores[i], -1);
								}
								Arrays.sort(scores);
								preScore = scores[0];
								preRank = rankMap.size();
								for (int j = 0; j < scores.length; j++) {
									if (preScore != scores[j]) {
										preRank--;
									}
									rankMap.put(scores[j], preRank);
									preScore = scores[j];
								}
								for (User user : users) {
									user.setCredit(RankType.byRank(rankMap.get(user.getCompetitionScore())).getScore());
									userMapper.updateCredit(user);
								}
								scoredIndex.add(competitionIndex);
								competitionQueue.remove(competitionIndex);
							}
						}
					}
				}
			}
		}).start();
	}

	/**
	 * 加入匹配队列
	 * 
	 * @param user
	 */
	private void joinQueue(User user) {
		Set<String> unMatchedIndex = globalContext.getUnMatchedIndex();
		if (!unMatchedIndex.isEmpty()) {
			for (String queueIndex : unMatchedIndex) {
				CompetitionBean competitionBean = globalContext.getCompetitionQueue().get(queueIndex);
				user.setCompetitionIndex(queueIndex);
				competitionBean.getUsers().add(user);
				competitionBean.getMatchedCount().incrementAndGet();
				break;
			}
		}
	}

	/**
	 * 随机生成一套试题
	 * 
	 * @param
	 */
	private void randomGenerateQuestions(CompetitionBean competitionBean) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		/*
		 * map.put("chapterId", (int) (Math.random()*5)); map.put("level", (int)
		 * (Math.random()*3));
		 */
		map.put("chapterId", 1);
		map.put("level", 0);
		map.put("random", Consts.QUESTION_COUNT);
		List<? extends Question> questions = radioMapper.findRadiosByRandom(map);
		competitionBean.setQuestions(questions);
	}

	/**
	 * 匹配业务实体
	 * 
	 * @author Hogan
	 *
	 */
	public static class CompetitionBean {

		private List<User> users;
		private List<? extends Question> questions;
		private String answers;
		private String date;
		private AtomicInteger matchedCount = new AtomicInteger(0);
		private AtomicInteger scoredCount = new AtomicInteger(0);

		public CompetitionBean() {
			users = new ArrayList<>();
		}

		public List<User> getUsers() {
			return users;
		}

		public void setUser(List<User> users) {
			this.users = users;
		}

		public List<? extends Question> getQuestions() {
			return questions;
		}

		public void setQuestions(List<? extends Question> questions) {
			this.questions = questions;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public AtomicInteger getMatchedCount() {
			return matchedCount;
		}

		public void setMatchedCount(AtomicInteger matchedCount) {
			this.matchedCount = matchedCount;
		}

		public String getAnswers() {
			if (StringUtils.isNullOrBlank(this.answers)) {
				if (this.questions != null && this.questions.size() > 0) {
					for (int i = 0; i < this.questions.size(); i++) {
						answers += this.questions.get(i).getAnswer();
					}
				}
			}
			return answers;
		}

		public void setAnswers(String answers) {
			this.answers = answers;
		}

		public AtomicInteger getScoredCount() {
			return scoredCount;
		}

		public void setScoredCount(AtomicInteger scoredCount) {
			this.scoredCount = scoredCount;
		}

	}

}
