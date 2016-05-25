import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class mainFrame extends JPanel implements ActionListener {
	JPanel middle;
	
	mainFrame(){
		
		super();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 180));

		middle = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER,2,2);
		middle.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		layout.setAlignment(FlowLayout.CENTER);
		middle.setPreferredSize(new Dimension(500, 180));
		middle.setBackground(Color.LIGHT_GRAY);
		
		middle.setLayout(new FlowLayout());
		middle.add(new JDial(0,100,100,65535));
		middle.add(new JDial(0,100,100,100));
		middle.add(new JDial(0,100,100,50));
		middle.add(new JDial(0,100,100,20));
		
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		
		left.setBackground(Color.LIGHT_GRAY);
		left.setPreferredSize(new Dimension(330,180));
		right.setBackground(Color.LIGHT_GRAY);
		right.setPreferredSize(new Dimension(330,180));
		
		setBackground(Color.LIGHT_GRAY);
		
		setLayout(layout);
		add(left);
		add(middle);
		add(right);
		//pack();
		repaint();
		setVisible(true);
	}
	
	public void update(){
		//pack();
		setVisible(true);	
	}
	
	
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {


		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

				JFrame test = new JFrame();
				test.setPreferredSize(new Dimension(1200,800));

				JDesktopPane desktop = new JDesktopPane();
				desktop.setPreferredSize(new Dimension(1200,800));
				//desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
				
				JInternalFrame windowVoxx = new JInternalFrame("Voxx" ,true,true, true, true);
				windowVoxx.setSize(new Dimension(520,520));
				windowVoxx.setLocation(300, 0);
				windowVoxx.setVisible(true);
				windowVoxx.pack();
				
				JInternalFrame windowPlot = new JInternalFrame("Plot" ,true,true, true, true);
				windowPlot.add(new JButton("test"));
				windowPlot.setSize(new Dimension(500,520));
				windowPlot.setLocation(670, 0);
				windowPlot.setVisible(true);
				windowPlot.pack();
				
				mainFrame frame = new mainFrame();
//				frame.pack();
				frame.repaint();
				frame.setVisible(true);
				//frame.setResizable(false);

				desktop.add(windowPlot);
				desktop.add(windowVoxx);
				//desktop.add(frame);
				desktop.setVisible(true);

				test.setLayout(new BorderLayout());
				
				
				//test.setContentPane(desktop);
				test.add(desktop,BorderLayout.CENTER);
				test.add(frame, BorderLayout.SOUTH);
				test.pack();
				test.setVisible(true);

            }
            });

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}
}
