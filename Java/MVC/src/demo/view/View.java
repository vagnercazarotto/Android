package demo.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import demo.model.Model;

public class View extends JFrame implements ActionListener {
	
	private Model model;
	private JButton helloButton; 
	private JButton goodbyeButton;
	
	public View(Model view) {
		super("MVC demo");
		this.model = model;
		
		helloButton = new JButton("Click Me!!");
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx=1;
		gc.gridy=1;
		gc.weightx=1;
		gc.weighty=1;
		gc.fill=GridBagConstraints.NONE;
		
		add(helloButton, gc);
		
		helloButton.addActionListener(this);
		
		
		setSize(600,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Hello there!!");
	}
	
	
	
	
	
	
	

}
