package Applet;

import java.awt.Image;

import processing.core.PApplet;
import processing.core.PImage;

public class MyApplet extends PApplet {
	String url = "http://im.rediff.com/news/2015/dec/24tpoty20.jpg";
	private PImage backgroundImg;
	
	public void draw(){
		int[] color = sunColorSec(second());
		fill(color[0],color[1],color[2]);
		ellipse(width/5, height/5, width/5, height/5);
	}
	
	
	public void setup(){
		size(600,600);
		background(200,200,200);
		stroke(0);
		backgroundImg = loadImage(url,"jpg");
		backgroundImg.resize(600, 600);
		image(backgroundImg,0,0);
	}
	
	public int[] sunColorSec(float seconds){
		int[] rgb = new int[3];
		float diffFrom30 = Math.abs(30-seconds);
		
		float ratio = diffFrom30/30;
		rgb[0] = (int) (255*ratio);
		rgb[1] = (int) (255*ratio);
		rgb[2] = 0;
		
		return rgb;
	}
	
}
