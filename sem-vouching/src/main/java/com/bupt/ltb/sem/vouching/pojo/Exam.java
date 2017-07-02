package com.bupt.ltb.sem.vouching.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.bupt.ltb.common.util.StringUtils;
import com.bupt.ltb.sem.vouching.frame.Consts;

/**
 * 试卷实体
 * 
 * @author Hogan
 * 
 */
public class Exam {

	private Integer examId;
	private String name;
	private Date createDate;
	private String bak;
	private String radioId;
	private String blankId;
	private String phraseId;
	private String translateId;
	private String clozeId;
	private Integer teacherId;
	private Integer classId;
	private Integer isActive;
	
	private Integer radioScore;
	private Integer blankScore;
	private Integer phraseScore;
	private Integer translateScore;
	private Integer clozeScore;

	private List<Integer> radioIds;
	private List<Integer> blankIds;
	private List<Integer> phraseIds;
	private List<Integer> translateIds;
	private List<Integer> clozeIds;

	private Class clas;
	private String formatCreateDate;

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getBak() {
		if (StringUtils.isNullOrBlank(this.bak)) {
			this.bak = "无备注信息";
		}
		return bak;
	}

	public void setBak(String bak) {
		this.bak = bak;
	}

	public String getRadioId() {
		return radioId;
	}

	public void setRadioId(String radioId) {
		this.radioId = radioId;
	}

	public String getBlankId() {
		return blankId;
	}

	public void setBlankId(String blankId) {
		this.blankId = blankId;
	}

	public String getPhraseId() {
		return phraseId;
	}

	public void setPhraseId(String phraseId) {
		this.phraseId = phraseId;
	}

	public String getTranslateId() {
		return translateId;
	}

	public void setTranslateId(String translateId) {
		this.translateId = translateId;
	}

	public String getClozeId() {
		return clozeId;
	}

	public void setClozeId(String clozeId) {
		this.clozeId = clozeId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getRadioScore() {
		return radioScore;
	}

	public void setRadioScore(Integer radioScore) {
		this.radioScore = radioScore;
	}

	public Integer getBlankScore() {
		return blankScore;
	}

	public void setBlankScore(Integer blankScore) {
		this.blankScore = blankScore;
	}

	public Integer getPhraseScore() {
		return phraseScore;
	}

	public void setPhraseScore(Integer phraseScore) {
		this.phraseScore = phraseScore;
	}

	public Integer getTranslateScore() {
		return translateScore;
	}

	public void setTranslateScore(Integer translateScore) {
		this.translateScore = translateScore;
	}

	public Integer getClozeScore() {
		return clozeScore;
	}

	public void setClozeScore(Integer clozeScore) {
		this.clozeScore = clozeScore;
	}

	public List<Integer> getRadioIds() {
		if (!StringUtils.isNullOrBlank(this.radioId)) {
			radioIds = generateIds(this.radioId);
		}
		return radioIds;
	}

	public void setRadioIds(List<Integer> radioIds) {
		this.radioIds = radioIds;
	}

	public List<Integer> getBlankIds() {
		if (!StringUtils.isNullOrBlank(this.blankId)) {
			blankIds = generateIds(this.blankId);
		}
		return blankIds;
	}

	public void setBlankIds(List<Integer> blankIds) {
		this.blankIds = blankIds;
	}

	public List<Integer> getPhraseIds() {
		if (!StringUtils.isNullOrBlank(this.phraseId)) {
			phraseIds = generateIds(this.phraseId);
		}
		return phraseIds;
	}

	public void setPhraseIds(List<Integer> phraseIds) {
		this.phraseIds = phraseIds;
	}

	public List<Integer> getTranslateIds() {
		if (!StringUtils.isNullOrBlank(this.translateId)) {
			translateIds = generateIds(this.translateId);
		}
		return translateIds;
	}

	public void setTranslateIds(List<Integer> translateIds) {
		this.translateIds = translateIds;
	}

	public List<Integer> getClozeIds() {
		if (!StringUtils.isNullOrBlank(this.clozeId)) {
			clozeIds = generateIds(this.clozeId);
		}
		return clozeIds;
	}

	public void setClozeIds(List<Integer> clozeIds) {
		this.clozeIds = clozeIds;
	}

	public Class getClas() {
		return clas;
	}

	public void setClas(Class clas) {
		this.clas = clas;
	}

	public String getFormatCreateDate() {
		if (this.createDate != null) {
			formatCreateDate = DateFormatUtils.format(this.createDate, Consts.DATE_SIMPLE_PATTERN);
		}
		return formatCreateDate;
	}

	public void setFormatCreateDate(String formatCreateDate) {
		this.formatCreateDate = formatCreateDate;
	}

	/**
	 * 形成id串
	 * 
	 * @param id
	 * @return
	 */
	private List<Integer> generateIds(String id) {
		List<Integer> result = new ArrayList<Integer>();
		String[] ids = id.split(Consts.COMMON_SEPARATOR);
		for (int i = 0; i < ids.length; i++) {
			result.add(Integer.parseInt(ids[i]));
		}
		return result;
	}

}
