package demo;

import javax.swing.SwingUtilities;

import demo.controller.Controller;
import demo.model.Model;
import demo.view.View;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("app");
		
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				runApp();}
		
		});
		
	}
		
		
	public static void runApp() {
		Model model = new Model();
		View view = new View(model);
		
		//Controller controller = new Controller(view, model);
	}

}
