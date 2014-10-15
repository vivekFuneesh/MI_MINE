
/**
 *
 * @author vivek
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;



/**
 *
 * @author vivek
 */

class AboutFrame extends JFrame implements ActionListener{
    
    static JButton Close;
    static JPanel buttonPanel;
   
    
    AboutFrame(){
       
        super("MI_MINE");
       
       
    setUndecorated(true);
    setLayout(new BorderLayout());
    Container c=getContentPane();
    
    c.add(new AboutPane(),BorderLayout.CENTER);
    Close=new JButton("Close");
    buttonPanel=new JPanel();buttonPanel.add(Close);
    c.add(buttonPanel,BorderLayout.EAST);Close.addActionListener(this);
    pack();setSize(MineFrame.pWidth,MineFrame.pHeight);
    setResizable(false);setVisible(true);
    addWindowListener(
       new WindowAdapter(){
       @Override public void windowClosing(WindowEvent s){setEnabled(false);dispose();setVisible(false);}});
       
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==Close){setEnabled(false);setVisible(false);this.dispose();}
    }
     
    
}

class AboutPane extends JTabbedPane{

    static AboutPanel AbP1,AbP2;
    static String name;
    static String msg=""; 
    AboutPane(){
       
            AbP1=new AboutPanel();
            AbP1.setLayout(new GridLayout(1,2));
            AbP1.add(new image(0));
            AbP1.add(new JScrollPane(create(0)));
            addTab(getName(0),AbP1);
           
        
        
    }
    
  //-SET FONT-STYLE,SIZE,BACKGROUNDCOLOR,FOREGROUNDCOLOR  
    
     JTextArea create(int i){
    
        JTextArea jt;
        Font font;
        font = new Font(Font.SERIF, Font.BOLD, 50);
        jt=new JTextArea(getInfo(i),5,5);
        jt.setFont(font);jt.setEditable(false);
        jt.setBackground(Color.black);jt.setLineWrap(true);jt.setWrapStyleWord(true);
        jt.setForeground(Color.MAGENTA);
        return jt;
        
    }
    
     String getName(int i){name="";
    if(i==0)return "MI_MINE";
    else if(i==1)return "Pappu_Ka_GhodAAA";
    else if(i==2)return "PuZZIE";
    else if(i==3)return "CHallenge";
    else return "Adventure";
        
    }
    
     String getInfo(int i){
         
         if(i==0){
             msg=""
                     + "Play IT Using Simple Logical Reasoning...??**@**??"
                     + " Simply Click on a box to reveal the number inside it.."
                     + "\n if number is 0 it will automatically go for next blocks.."
                     + "  and will stop till no  block with number  other than 0 is found ."
                     + "EXACTLY SAME AS Microsoft's MineSweeper ";
             
             return msg;}
         
         return "Hey_"+i+"_YOU_ARE_SEEING_ME";
     }
    
}
