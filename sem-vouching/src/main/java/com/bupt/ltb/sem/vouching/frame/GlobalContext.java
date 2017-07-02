package com.bupt.ltb.sem.vouching.frame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 全局上下文
 * 
 * @author Hogan
 * 
 */
@Component("globalContext")
@Scope("singleton")
public class GlobalContext {

	private Map<Integer, Integer[]> currentEmailIndex = new HashMap<>();

	private Map<Integer, List<? extends Question>> currentPracticePaper = new HashMap<>();

	public Map<Integer, Integer[]> getCurrentEmailIndex() {
		return currentEmailIndex;
	}

	public Map<Integer, List<? extends Question>> getCurrentPracticePaper() {
		return currentPracticePaper;
	}

}
