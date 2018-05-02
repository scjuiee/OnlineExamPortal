package org.accenture.oep.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class CreateDOM {
	
	
	private static final Logger logger = LogManager.getLogger(CreateDOM.class);
	
	public static Document getDOM(String test) throws SAXException,ParserConfigurationException,IOException, URISyntaxException
	{
		Document dom = null;
		File quizFile = null;

		String currentDirectory = new File("").getAbsolutePath();
		quizFile = new File(currentDirectory + "/Quizzes/" + test + "-quiz-1.xml");
		System.out.println("Quiz File Absolute Path " + quizFile.getAbsolutePath());
		
		logger.debug("Quiz File Absolute Path " + quizFile.getAbsolutePath());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		try {
			dom = db.parse(quizFile);
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("Error : Quiz File Not Found " + fileNotFound);
		}
		dom.getDocumentElement().normalize();
		return dom;
	}

}
