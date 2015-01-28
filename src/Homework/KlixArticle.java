package Homework;

public class KlixArticle {

	private String title;
	private String content;
	
	public KlixArticle(String title, String content){
		this.title = title;
		this.content = content;
	}
	
	public String getTittle(){
		return title;
	}
	
	public String getContent(){
		return content;
	}
	
	public String printArticle(){
		return "Title:[ " +title +" ]"  +"\n" +content;
	}
}
