import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;




public final class JDial extends JComponent implements MouseListener, MouseMotionListener, ActionListener {
	
	
	private double theta;
	private int dialWidth;
	private int dialHeight;
	private int compWidth;
	private int compHeight;
	private int dialRange;
	private int stepCount;
	private int stepSize;
	private float stepAngle;
	private int dialOffset;
	
	private JTextField valueInsert;
	
	private boolean changed;
	
	private int value;
	
	
	
	private Number[] steps;
	
	
	JDial (double angle, int height, int width, int range){
		
		super();
		
		

		theta = angle*(Math.PI/180);
		dialWidth = width;
		dialHeight = height;
		dialOffset = 10;
		compHeight = dialHeight+80;
		compWidth = dialWidth+2*dialOffset;
		dialRange = range;
		
		
		
		
		if(dialRange >= 200){
			
			stepAngle = 1;
			stepSize = 1;
			
			
		} else {
			stepAngle = 360/dialRange;
			stepCount = Math.round(360/stepAngle);

		}
		setPreferredSize(new Dimension(dialWidth+1, compHeight));
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		repaint();
		setVisible(true);	
	}
	
	public double getAngle(){
		return theta;
	}
	
	public void setValue(int var){
		theta = (var-90)*(Math.PI/180);
		repaint();
	}
	


	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);

		
		Graphics2D g2 = (Graphics2D)g; 
	
		AffineTransform rotation = new AffineTransform();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.DARK_GRAY);

		
		if(dialRange > 360){
			g2.drawString("<"+ dialRange/360 + "steps/°", 5, compHeight-55);
			g2.drawString(Math.round((theta*(180/Math.PI)+90)*((float)dialRange/360)) + " of " + dialRange, 5, compHeight-40);
		}else{
			g2.drawString(stepAngle + "°/step", 25, compHeight-55);
			if(Math.round((theta*(180/Math.PI)+90)/stepAngle) > dialRange){
			//value = dialRange;
			g2.drawString(dialRange + " of " + dialRange, 25, compHeight-40);
			}else{
			g2.drawString(Math.round((theta*(180/Math.PI)+90)/stepAngle) + " of " + dialRange, 25, compHeight-40);}
		}
		g2.fillOval(1, 1, dialWidth, dialHeight);
		

		rotation.rotate(theta, (dialWidth/2), (dialHeight/2));


		
		g2.setTransform(rotation);

		
		float[] dist = {0.0f, 1.0f};
	    Color[] colors = {new Color(187,187,187), Color.GRAY};
		
		RadialGradientPaint gradientFill = new RadialGradientPaint(dialWidth/2, dialHeight/2, dialHeight-5, dist, colors);
		g2.setPaint(gradientFill);
		g2.fill (new Ellipse2D.Double(0, 0, dialWidth, dialHeight));
		Color[] colorsInside = {Color.LIGHT_GRAY, Color.GRAY};

		
		int pointerWidth = dialWidth/3;
		int pointerHeight = dialHeight/3;
		int pointerSpacing = dialWidth/15;
		
		g2.setColor(Color.LIGHT_GRAY);
		
		g2.fill (new Ellipse2D.Double(dialWidth-pointerWidth-pointerSpacing, dialHeight/2-pointerHeight/2-1, pointerWidth-3, pointerHeight-3));
		gradientFill = new RadialGradientPaint(dialWidth-pointerWidth/2+pointerSpacing/2, dialHeight/2-pointerHeight/2, pointerHeight-5, dist, colorsInside);
		g2.setPaint(gradientFill);
		g2.fill (new Ellipse2D.Double(dialWidth-pointerWidth-pointerSpacing, dialHeight/2-pointerHeight/2, pointerWidth-5, pointerHeight-5));	
		repaint();
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		//theta = (Math.atan2((dialWidth/2)-e.getX(), e.getY()-(dialHeight/2)))+90*(Math.PI/180);
		//repaint();	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	
		theta = (Math.atan2((dialWidth/2)-e.getX(), e.getY()-(dialHeight/2)))+90*(Math.PI/180);
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		
	}
	


}
