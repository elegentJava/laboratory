package com.bupt.ltb.sem.vouching.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.type.ErrorCode;
import com.bupt.ltb.sem.vouching.frame.Consts;
import com.bupt.ltb.sem.vouching.frame.GlobalContext;
import com.bupt.ltb.sem.vouching.frame.PageSize;
import com.bupt.ltb.sem.vouching.frame.Question;
import com.bupt.ltb.sem.vouching.mapper.BlankMapper;
import com.bupt.ltb.sem.vouching.mapper.ChapterMapper;
import com.bupt.ltb.sem.vouching.mapper.ClozeMapper;
import com.bupt.ltb.sem.vouching.mapper.PhraseMapper;
import com.bupt.ltb.sem.vouching.mapper.PracticeMapper;
import com.bupt.ltb.sem.vouching.mapper.RadioMapper;
import com.bupt.ltb.sem.vouching.mapper.TranslateMapper;
import com.bupt.ltb.sem.vouching.pojo.Chapter;
import com.bupt.ltb.sem.vouching.pojo.Practice;
import com.bupt.ltb.sem.vouching.pojo.Radio;
import com.bupt.ltb.sem.vouching.pojo.User;
import com.bupt.ltb.sem.vouching.service.PracticeService;
import com.bupt.ltb.sem.vouching.type.LevelType;
import com.bupt.ltb.sem.vouching.type.OptionType;
import com.bupt.ltb.sem.vouching.type.QuestionCategory;
import com.bupt.ltb.sem.vouching.type.error.ExamError;
import com.bupt.ltb.sem.vouching.type.error.PracticeError;
import com.bupt.ltb.sem.vouching.util.PageJsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 练习平台业务实现
 *
 * @author Hogan
 */
@Service("practiceService")
public class PracticeServiceImpl implements PracticeService {

    @Resource
    private ChapterMapper chapterMapper;

    @Resource
    private RadioMapper radioMapper;

    @Resource
    private BlankMapper blankMapper;

    @Resource
    private ClozeMapper clozeMapper;

    @Resource
    private TranslateMapper translateMapper;

    @Resource
    private PhraseMapper phraseMapper;

    @Resource
    private PracticeMapper practiceMapper;

    @Resource
    private GlobalContext globalContext;

    @Override
    public LJSONObject loadPracticeSelectInfo(JSONObject jParams) {
        LJSONObject result = new LJSONObject();
        JSONObject detail = new JSONObject();
        List<QuestionType> categorys = new ArrayList<QuestionType>();
        List<QuestionLevel> levels = new ArrayList<QuestionLevel>();
        List<Chapter> chapters = chapterMapper.findAllChaptersByStatus(Consts.ACTIVE);
        for (QuestionCategory qc : QuestionCategory.values()) {
            categorys.add(new QuestionType(qc.getId(), qc.getTag(), qc.getName()));
        }
        for (LevelType e : LevelType.values()) {
            levels.add(new QuestionLevel(e.getId(), e.getDescription()));
        }
        detail.put("categorys", categorys);
        detail.put("levels", levels);
        detail.put("chapters", chapters);
        result.setDetail(detail);
        result.setErrorCode(ErrorCode.SUCCESS);
        return result;
    }

    @Override
    public LJSONObject generatePracticePaper(JSONObject jParams, HttpSession session) {
        LJSONObject result = new LJSONObject();
        Integer chapterId = jParams.getInteger("chapterId");
        Integer categoryId = jParams.getInteger("categoryId");
        Integer levelId = jParams.getInteger("levelId");
        Integer count = jParams.getInteger("count");
        if (chapterId != null && categoryId != null && levelId != null && count != null) {
            List<? extends Question> questions = null;
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("level", levelId);
            map.put("random", count);
            map.put("chapterId", chapterId);
            switch (QuestionCategory.byId(categoryId)) {
                case BLANK:
                    questions = blankMapper.findBlanksByRandom(map);
                    break;
                case RADIO:
                    questions = radioMapper.findRadiosByRandom(map);
                    break;
                case CLOZE:
                    questions = clozeMapper.finClozesByRandom(map);
                    break;
                case PHRASE:
                    questions = phraseMapper.findPhrasesByRandom(map);
                    break;
                case TRANSLATE:
                    questions = translateMapper.findTranslatesByRandom(map);
                    break;
                default:
                    result.setErrorCode(ExamError.QUESTION_CATEGORY_INVALID);
                    return result;
            }
            User user = (User) session.getAttribute(Consts.SESSION_USER);
            globalContext.getCurrentPracticePaper().put(user.getUserId(), questions);
            result.setErrorCode(ErrorCode.SUCCESS);
        }
        return result;
    }

    @Override
    public LJSONObject loadStartPracticeInfo(JSONObject jParams, HttpSession session) {
        LJSONObject result = new LJSONObject();
        JSONObject detail = new JSONObject();
        User user = (User) session.getAttribute(Consts.SESSION_USER);
        List<? extends Question> questions = globalContext.getCurrentPracticePaper().get(user.getUserId());
        if (questions != null && questions.size() > 0) {
            detail.put("questions", questions);
            detail.put("chapterId", questions.get(0).getChapterId());
            result.setDetail(detail);
            result.setErrorCode(ErrorCode.SUCCESS);
        } else {
            result.setErrorCode(PracticeError.NO_MATCH_QUESTION);
        }
        return result;
    }

    @Override
    public LJSONObject loadPracticeRecord(JSONObject jParams, HttpSession session) {
        LJSONObject result = new LJSONObject();
        JSONObject detail = new JSONObject();
        Integer pageNum = jParams.getInteger("pageNum");
        PageHelper.startPage(pageNum, PageSize.TEST_RECORD);
        User user = (User) session.getAttribute(Consts.SESSION_USER);
        List<Practice> practices = practiceMapper.findPracticesByUserId(user.getUserId());
        PageInfo<Practice> pageInfo = new PageInfo<Practice>(practices);
        detail.put("records", pageInfo.getList());
        result.setDetail(detail);
        result.setPager(PageJsonUtil.generatePageJson(pageInfo));
        result.setErrorCode(ErrorCode.SUCCESS);
        return result;
    }

    @Override
    public LJSONObject showAnswerAndScore(JSONObject jParams, HttpSession session) {
        LJSONObject result = new LJSONObject();
        JSONObject detail = new JSONObject();
        User user = (User) session.getAttribute(Consts.SESSION_USER);
        String[] answers = jParams.getObject("answers", String[].class);
        Integer chapterId = jParams.getInteger("chapterId");
        Integer level = jParams.getInteger("level");
        List<Practice> practices = practiceMapper.findPracticesByUserId(user.getUserId());
        Integer score = 0;
        Integer count = 0;
        //totalCount为做过所有练习的总题数
        Integer totalCount = 0;
        //totalScore为做过所有练习的总得分
        Integer totalScore = 0;
        String[] answer = new String[5];
        //practicePassed为0时练习未通过，为1是通过
        Integer practicePassed = 0;
        for (Practice practice : practices) {
            totalCount = totalCount + practice.getPractised();
            totalScore = totalScore + practice.getScore();
        }
        if (answers != null) {
            List<? extends Question> questions = globalContext.getCurrentPracticePaper().get(user.getUserId());
            for (Question question : questions) {
                if (answers[count] != null) {
                    if (question instanceof Radio) {
                        if (OptionType.byId(Integer.parseInt(answers[count])).getDescription().equals(question.getAnswer())) {
                            score++;
                        }
                    } else {
                        if (answers[count].equals(question.getAnswer())) {
                            score++;
                        }
                    }
                }
                answer[count] = question.getAnswer();
                count++;
            }
            if (count + totalCount >= 200 &&((totalScore+score )/ (totalCount+count)) > (3 / 4)) {
                practicePassed = 1;
                user.setPracticePassed(1);
            }
            Practice practice = new Practice();
            practice.setChapterId(chapterId);
            practice.setDate(new Date());
            practice.setScore(score);
            practice.setUserId(user.getUserId());
            practice.setLevel(level);
            if (practiceMapper.addPractice(practice) == Consts.DATA_SINGLE_SUCCESS) {
                detail.put("answers", answer);
                detail.put("score", score);
                result.setDetail(detail);
                result.setErrorCode(ErrorCode.SUCCESS);
            } else {
                result.setErrorCode(PracticeError.SHOW_ANSWER_FAILD);
            }
        } else {
            result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
        }

        return result;
    }

    /**
     * 问题类型实体
     *
     * @author Hogan
     */
    public class QuestionType {

        private Integer id;
        private String tag;
        private String name;

        public QuestionType(Integer id, String tag, String name) {
            this.id = id;
            this.tag = tag;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    /**
     * 问题难易程度实体
     *
     * @author Hogan
     */
    public class QuestionLevel {

        private Integer id;
        private String name;

        public QuestionLevel(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
