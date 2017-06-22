package com.bupt.ltb.sem.corre.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CorreUtils {

	public static String generateCorrespondenceStr(String correEnglish) {
		StringBuilder result = new StringBuilder();
		Stack<String> stack = new Stack<String>();
		String[] item = correEnglish.split("");
		for (int i = item.length - 1; i >= 0; i--) {
			stack.push(item[i]);
		}
		while (!stack.isEmpty()) {
			String outLayer = stack.pop();
			switch (outLayer) {
			// 需要提示的信息
			case "{":
				String content = "";
				String tipContent = "";
				while (!stack.isEmpty() && !"}".equals(stack.peek())) {
					String inLayer = stack.pop();
					if (!"<".equals(inLayer)) {
						content += inLayer;
					} else if ("<".equals(inLayer)) {
						while (!stack.isEmpty() && !stack.peek().equals(">")) {
							tipContent += stack.pop();
						}
						stack.pop();
					}
				}
				if (!stack.isEmpty()) {
					stack.pop();
					result.append("<a style='color:blue' title='" + tipContent + "'>" + content + "</a>");
				}
				break;
			case "[":
				String content1 = "";
				while (!stack.isEmpty() && !"]".equals(stack.peek())) {
					content1 += stack.pop();
				}
				stack.pop();
				String[] temp = content1.split("\\|");
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < temp.length; i++) {
					if (temp[i].contains("{")) {
						String front = temp[i].substring(0, temp[i].indexOf("{"));
						String middle = temp[i].substring(temp[i].indexOf("{") + 1, temp[i].indexOf("<"));
						String tip = temp[i].substring(temp[i].indexOf("<") + 1, temp[i].indexOf(">"));
						String back = temp[i].substring(temp[i].indexOf("}") + 1, temp[i].length());
						temp[i] = front + "<a style='color:blue' title='" + tip + "'>" + middle + "</a>" + back;
					}
					list.add(temp[i]);
				}
				result.append(temp[0]);
				break;
			default:
				String content2 = outLayer;
				while (!stack.isEmpty() && !"{".equals(stack.peek()) && !"[".equals(stack.peek())) {
					content2 += stack.pop();
				}
				result.append(content2);
				break;
			}
		}
		return result.toString();
	}
}
