
public class HtmlElement extends HtmlTag {
	
	//The HtmlElement is the leaf class, and its main job is to implement the operation method, which in this example is the
	//generateHtml method. It prints the startTag, optionally tagBody if have, and the endTag of the child element.
	
	private String tagName;
	private String startTag;
	private String endTag;
	private String tagBody;
	
	public HtmlElement(String tagName){
		this.tagName = tagName;
		this.tagBody = "";
		this.startTag = "";
		this.endTag = "";
	}
	
	@Override
	public String getTagName() {
		return tagName;
	}

	@Override
	public void setStartTag(String tag) {
		this.startTag = tag;
	}

	@Override
	public void setEndTag(String tag) {
		this.endTag = tag;
	}
	
	@Override
	public void setTagBody(String tagBody){
		this.tagBody = tagBody;
	}

	@Override
	public void generateHtml() {
		System.out.println(startTag+""+tagBody+""+endTag);
	}
}
