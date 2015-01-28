package Homework;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class which takes xml file from klix.ba rss. After parsin file it reads titles and contents
 * of each article. In main method i made method which prints all titles and givings user option to chooce
 * which article he wants to read. Once user choose number text is displayed and program ends.
 * 
 * @author vedadzornic
 *
 */
public class KlixReader {

	/**
	 * Main method parsing rss xml file from klix.ba and reading it.
	 * @param args
	 * @throws ParserConfigurationException
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ParserConfigurationException,	MalformedURLException, SAXException, IOException {
		DocumentBuilder docRead = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document xmldoc = docRead.parse(new URL("http://www.klix.ba/rss/svevijesti").openStream());

		NodeList klixXML = xmldoc.getElementsByTagName("item");			//NodeList of klix.ba rss xml file.
		LinkedList<KlixArticle> articles = new LinkedList<KlixArticle>();//LinkedList of articles
		LinkedList<Element> items = new LinkedList<Element>();			//Element list of item tags on klix.ba
		
		//Loop where I added new KlixArticles into articles linked list. 
		for (int i = 0; i < klixXML.getLength(); i++) {
			Node current = klixXML.item(i);			//Creating node current
			if (current instanceof Element) {       //Checking instance of
				Element currentElement = (Element) current;		//Casting node into Element
				items.add(currentElement);			//Adding element into linked list						

				if (current.hasChildNodes()) { 		//Now checking if current has child nodes ( not necessary)
					NodeList titleList = ((Element) current).getElementsByTagName("title");		//Node list of titles
					NodeList contentList = ((Element) current).getElementsByTagName("clanak");	//Node list of content
					for (int j = 0; j < titleList.getLength(); j++) {			//loop which will add title and text to article linked list
						Node currentTitle = titleList.item(j);
						Node currentContent = contentList.item(j);

						if (currentTitle instanceof Element) {
							Element titleElement = (Element) currentTitle;
							Element contentElement = (Element) currentContent;
							String title = titleElement.getTextContent();		//Getting text from title
							String content = contentElement.getTextContent();	//Getting text from content
							articles.add(new KlixArticle(title, content));		//Adding into linked list.
						}

					}

				}
			}

		}
		
		/*
		 * From now on all I done is making program user friendly.
		 * Printed all titles and give user choice which title he wants to print on console.
		 */
		System.out.println("List of titles at klix.ba: \n");
		for(int i=0; i<articles.size();i++){
			System.out.println(i +": " +articles.get(i).getTittle() );
		}
			int choice = -1;
			while(choice <0 || choice >= articles.size()){
				System.out.println("Choose which article you want to read: ");
				choice = TextIO.getInt();			
			}	
			System.out.println(articles.get(choice).printArticle());					
	}	
}
