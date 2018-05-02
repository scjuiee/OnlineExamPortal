package org.accenture.oep.bean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Exam {
	Document dom;
	public int currentQuestion = 0;

	public Map<Integer, Integer> selections = new LinkedHashMap<Integer, Integer>();
	public ArrayList<QuizQuestion> questionList = new ArrayList<QuizQuestion>(10);

	public Exam(String test) throws SAXException, ParserConfigurationException, IOException, URISyntaxException {
		dom = CreateDOM.getDOM(test);
	}

	public void setQuestion(int i) {
		int number = i;
		String options[] = new String[4];
		String question = null;
		int correct = 0;
		System.out.println("Dom " + dom);
		NodeList qList = dom.getElementsByTagName("question");
		NodeList childList = qList.item(i).getChildNodes();

		int counter = 0;

		for (int j = 0; j < childList.getLength(); j++) {
			Node childNode = childList.item(j);
			if ("answer".equals(childNode.getNodeName())) {
				options[counter] = childList.item(j).getTextContent();
				counter++;
			} else if ("quizquestion".equals(childNode.getNodeName())) {
				question = childList.item(j).getTextContent();
			} else if ("correct".equals(childNode.getNodeName())) {
				correct = Integer.parseInt(childList.item(j).getTextContent());
			}

		}
		System.out.println("Retrieving Question Number " + number);
		System.out.println("Question is : " + question);
		for (String a : options) {
			System.out.println(a);
		}
		System.out.println("Correct answer index : " + correct);

		QuizQuestion q = new QuizQuestion();
		q.setQuestionNumber(number);
		q.setQuestion(question);
		q.setCorrectOptionIndex(correct);
		q.setQuestionOptions(options);
		questionList.add(number, q);

	}

	public ArrayList<QuizQuestion> getQuestionList() {
		return this.questionList;
	}

	public int getCurrentQuestion() {
		return currentQuestion;
	}

	public Map<Integer, Integer> getSelections() {
		return this.selections;
	}

	public UserResultModel calculateResult(Exam exam) {
		int totalCorrect = 0;
		String userAnswers = "";
		Map<Integer, Integer> userSelectionsMap = exam.selections;
		Map<Integer, Integer> correctAnswersMap = new HashMap<Integer,Integer>();
		List<Integer> userSelectionsList = new ArrayList<Integer>(10);
		for (Map.Entry<Integer, Integer> entry : userSelectionsMap.entrySet()) {
			userSelectionsList.add(entry.getKey());
		}
		List<QuizQuestion> questionList = exam.questionList;
	
		List<Integer> correctAnswersList = new ArrayList<Integer>(10);
		for (QuizQuestion question : questionList) {
			System.out.println("current question..."+question.getQuestion());
			System.out.println("current question no..."+question.getQuestionNumber());
			System.out.println("Correct Option:..."+question.getCorrectOptionIndex());
			correctAnswersList.add(question.getCorrectOptionIndex());
			correctAnswersMap.put(question.getQuestionNumber(), question.getCorrectOptionIndex());
		}

		for (int i = 0; i < userSelectionsList.size(); i++) {
			System.out.println(userSelectionsList.get(i) );
			Integer key = userSelectionsList.get(i);
			Integer correctAnswer = correctAnswersMap.get(key);
			if ((userSelectionsMap.get(key) == correctAnswer)) {
				System.out.println("");
				totalCorrect++;
				System.out.println("Answer is Correct");
				
			}
			if (userAnswers.length() == 0) {					
				userAnswers = userSelectionsMap.get(key) + "-" + correctAnswersMap.get(key);
			} else {
				userAnswers = userAnswers + "|" + userSelectionsMap.get(key) + "-" + correctAnswersMap.get(key);
			}
		}
		UserResultModel model = new UserResultModel();
		model.setUserScore(String.valueOf(totalCorrect));
		model.setUserAnswers(userAnswers);
		System.out.println("You Got " + totalCorrect + " Correct");
		return model;
	}

}
