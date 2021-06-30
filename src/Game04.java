import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Game04 {


    public static void main (String args []) {
   	   MyScreen myscreen = new MyScreen();
    	myscreen.setVisible(true);
    }
}
    class MyScreen extends JFrame implements ActionListener{
    	JButton btn_left, btn_right, btn_up, btn_down, btn_run,
    	        btn_quit, btn_reset,btn_debug, btn_set,btn_del;
    	String IMAGE_FILE_NAME ="star.jpg";
    	BufferedImage bufferedImage = null;
    	int FRAME_LENGTH = 800;
    	int x=180, y=180, line=0;
    	//ArrayList com_data = new ArrayList<Comand>();
    	String[][] com_list = new String[50][4];
    	JComboBox combo ;
    	JTextField p1, p2, p3, p4;
    	JTable com_tbl;
    	DefaultTableModel tbl_model;
    	
    	public MyScreen(){


    		setTitle("複雑なレイアウトのテスト");
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		setSize(800, 600);

    		setLayout(new BorderLayout());
    		JPanel northpane = new JPanel();
    		northpane.setLayout(new GridLayout(1,2));

    		WestPanel westpane = new WestPanel();
    		EastPanel eastpane = new EastPanel();
    		northpane.add(westpane);
    		northpane.add(eastpane);
    		SouthPanel southpane = new SouthPanel();

    		add(northpane,BorderLayout.CENTER);
    		add(southpane,BorderLayout.SOUTH);


    		btn_left.addActionListener(this);
    		btn_up.addActionListener(this);
    		btn_down.addActionListener(this);
    		btn_right.addActionListener(this);
    		btn_reset.addActionListener(this);
    		btn_quit.addActionListener(this);
    		btn_run.addActionListener(this);
    		btn_debug.addActionListener(this);
    		btn_quit.addActionListener(this);
    		btn_set.addActionListener(this);
       		btn_del.addActionListener(this);

    		setVisible(true);
    	}
    	public void actionPerformed(ActionEvent e){
    		String cmd = e.getActionCommand();
    		if (cmd.equals("Left")){
    			x-=40;
    		}else if(cmd.equals("Right")){
    			x+=40;
    		}else if(cmd.equals("Up")){
    			y-=40;
    		}else if(cmd.equals("Down")){
    			y+=40;
    		}else if(cmd.equals("Set")){
    			int line= Integer.parseInt(p1.getText());
    			int w_row= line -1;
    			String[] ins_row = new String[4];
    			ins_row[0]= ""+line;
    			ins_row[1]= (String)combo.getSelectedItem();
    			ins_row[2] = p2.getText();
    			ins_row[3] = p3.getText();
    			tbl_model.insertRow(w_row, ins_row);
    			//com_tbl.setValueAt(""+line, w_row, 0);
    			//com_tbl.setValueAt((String)combo.getSelectedItem(), w_row, 1);
    			//com_tbl.setValueAt(p2.getText(),w_row,2);
    			//com_tbl.setValueAt(p3.getText(),w_row,3);
    			//com_data.add(new Comand(line,(String)combo.getSelectedItem(),0));
    		}else if(cmd.equals("Delete")){
    			int w_row = com_tbl.getSelectedRow();
    			tbl_model.removeRow(w_row);
    		}else if(cmd.equals("Quit")){
    			System.exit(0);
    		}

    		repaint();

    	}



 public class WestPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

    		try (InputStream inputStream = this.getClass().getResourceAsStream(IMAGE_FILE_NAME);){
    			bufferedImage = ImageIO.read(inputStream);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}

		    g.drawImage(bufferedImage, x, y, this);
		    for (int i=0; i<9; i++){
		    	for (int j=0; j<9; j++){
		    		g.drawRect(i*40+20, j*40+20,40,40);
		    	}
		    }

		}
	}

public class EastPanel extends JPanel
                        implements MouseListener{
        		String [] com_columns = {"Line_no","comand","p1","p2"};
        		public EastPanel(){
                    tbl_model= new DefaultTableModel(com_list,com_columns);

        			com_tbl = new JTable(tbl_model);
                    com_tbl.addMouseListener(this);
 
        			JScrollPane sp = new JScrollPane(com_tbl);
        			sp.setPreferredSize(new Dimension(350,400));
        			add(sp);

        			JPanel code = new JPanel();
        		    String[] combodat1 = {"Transfer", "Goback", "Camera", "Shot"};
        		    p1 = new JTextField();
        		    p1.setPreferredSize(new Dimension(50, 30));
        		    code.add(p1);
        		    combo = new JComboBox(combodat1);
        		    combo.setPreferredSize(new Dimension(80, 30));
        		    code.add(combo);
        		    p2 = new JTextField();
        		    p2.setPreferredSize(new Dimension(50, 30));
        		    code.add(p2);
        		    p3 = new JTextField();
        		    p3.setPreferredSize(new Dimension(50, 30));
        		    code.add(p3);
        		    add(code);

        		    JPanel cmd_btn = new JPanel();
        		    btn_set = new JButton("Set");
        		    cmd_btn.add(btn_set);
        		    btn_del = new JButton("Delete");
        		    cmd_btn.add(btn_del);
        		    add(cmd_btn);
        		}
            	public void mouseClicked(MouseEvent e) {
            		int w_row = com_tbl.getSelectedRow();
            		p1.setText(""+(w_row+1));
            		p2.setText(com_tbl.getValueAt(w_row, 2).toString());
            		p3.setText(com_tbl.getValueAt(w_row, 3).toString());

            	}
            	public void mouseEntered(MouseEvent e){
            	    
            	  }

            	public void mouseExited(MouseEvent e){
            	    
            	  }

            	public void mousePressed(MouseEvent e){
            	    
            	  }

            	public void mouseReleased(MouseEvent e){
            	   
            	  }
}

public class SouthPanel extends JPanel{

        		public SouthPanel(){
        	       setLayout(new GridLayout(2, 4));
        		   btn_left = new JButton("Left");
        		   btn_right = new JButton("Right");
        		   btn_up = new JButton("Up");
        		   btn_down = new JButton("Down");
        		   btn_reset = new JButton("Reset");
        		   btn_quit =new JButton("Quit");
        		   btn_run = new JButton("Run");
        		   btn_debug = new JButton("Debug");

        	       add(btn_left);
        	       add(btn_right);
        	       add(btn_quit);
        	       add(btn_reset);
        	       add(btn_up);
        	       add(btn_down);
           	       add(btn_run);
        	       add(btn_debug);

        		}
}
    }










