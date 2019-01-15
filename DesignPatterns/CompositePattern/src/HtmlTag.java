import java.util.List;

public abstract class HtmlTag {
	
	//The HtmlTag class is the component class which defines all the methods used by the composite and the leaf class.

	//just returns the tag name and should be used by both child classes
	public abstract String getTagName();
	
	
	//Every html element should have a start tag and an end tag, the methods setStartTag and setEndTag are used to set the
	//start and end tag of an html element and should be implemented by both the child classes, so they are kept abstract in the above class.
	public abstract void setStartTag(String tag);
	public abstract void setEndTag(String tag);
	
	
	
	//There are methods which are useful only to the composite class and are useless to the leaf class. Just provide the default
	//implementation to these methods, throwing an exception is a good implementation of these methods to avoid any accidental call
	//to these methods by the object which should not support them.
	public void setTagBody(String tagBody){
		throw new UnsupportedOperationException("Current operation is not support for this object");
	}
	
	public void addChildTag(HtmlTag htmlTag){
		throw new UnsupportedOperationException("Current operation is not support for this object");
	}
	
	public void removeChildTag(HtmlTag htmlTag){
		throw new UnsupportedOperationException("Current operation is not support for this object");
	}
	
	public List<HtmlTag>getChildren(){
		throw new UnsupportedOperationException("Current operation is not support for this object");
	}

	
	public abstract void generateHtml();
}
