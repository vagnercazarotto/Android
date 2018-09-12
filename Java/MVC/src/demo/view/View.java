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
		goodbyeButton = new JButton("Goodbye");
				
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx=1;
		gc.gridy=1;
		gc.weightx=1;
		gc.weighty=1;
		gc.fill=GridBagConstraints.NONE;
		
		add(helloButton, gc);
		
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx=1;
		gc.gridy=2;
		gc.weightx=1;
		gc.weighty=1;
		gc.fill=GridBagConstraints.NONE;
		
		add(goodbyeButton,gc);
		
		
		
		helloButton.addActionListener(this);
		goodbyeButton.addActionListener(this);
		
		
		goodbyeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Annon class");
			}
			
		});
		
		
		setSize(600,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JButton source = (JButton) e.getSource();
		
		
		if (source == helloButton) {
			System.out.println("Right button clicked");
		} else if (source == goodbyeButton) {
			System.out.println("Good bye clicked");
		}
		
		System.out.println("Hello there!!");
	}
	
	
	
	
	
	
	

}
