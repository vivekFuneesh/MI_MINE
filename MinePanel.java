
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author vivek
 */

public class MinePanel extends JPanel implements ActionListener,Runnable
{
    
    static int syncFlag[],syncTurn=0;
    static int  level,fontSize=25;
    static int blockWidth,blockHeight,pWidth,pHeight;
    static MouseAdapter ma;
    static JButton[][] jb;
    static MineGUI GUI=null;
    static Thread t=null;
    //-------------------------SLEEPING--TIME--VARIABLES-------------------------------------
    //static long beforetime,aftertime,timediff,sleeptime,oversleeptime,excess;
    //static int skip=0;
    //static long period=(long)Math.pow(10, 9)/20;
  //--------------------------------------------------------------------  
    
    MinePanel(int pWidth,int pHeight){
        MinePanel.pWidth=pWidth;MinePanel.pHeight=pHeight;
        setPreferredSize(new Dimension(pWidth,pHeight));
        setLayout(new GridLayout());
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.BLACK);
        Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 40);
        g.setFont(font);
        FontMetrics fm=getFontMetrics(font);
        
        String str="MI_MINE";
        setForeground(Color.red);
        g.drawString(str, (pWidth-fm.stringWidth(str))/2,pHeight/2);
            
        
    }
    
    void resetPanel(){
        
      
      Main.GAMEOVER=true;
      t=null;
      if(ma!=null)removeMouseListener(ma);ma=null;
      removeAll();
      GUI.Activate();
      repaint();
    }
    
    void ActivatePanel(){
        
        level=0;
        
        //-------SETTING Main VARIABLES FOR GAME PLAY---------------------------
        Main.GAMEOVER=false;
        if(MineGUI._5x5.isSelected()){Main.createMatrix(5,5);level=5;}
        if(MineGUI._7x7.isSelected()){Main.createMatrix(7,7);level=7;}
        if(MineGUI._10x10.isSelected()){Main.createMatrix(10,10);level=10;}
        Main.Score=0;
        //---------END SETTING FOR Main VARIABLES-------------------------------
        
        blockWidth=pWidth/10;
        blockWidth*=10;
        blockWidth/=level;
        
        
        blockHeight=pHeight/10;
        blockHeight*=10;
        blockHeight/=level;
        
        System.out.println("Block dimensions are::"+blockWidth+","+blockHeight);
        syncFlag=new int[2];
        syncFlag[0]=syncFlag[1]=0;
        //--------PREPARING THIS PANEL FOR DRAWING----
       
        setLayout(new GridLayout(level,level));
        
        jb=new JButton[level][level];
        this.setBackground(Color.black);
       
        syncFlag[0]=1;syncTurn=1;
        
        processMouseEvent();
        
        for(int i=0;i<level;i++){
            for(int j=0;j<level;j++){
                jb[i][j]=new JButton("");
                add(jb[i][j]);
                //
                jb[i][j].addMouseListener(ma);
                jb[i][j].setEnabled(true);
            }
        }
       
       
        Main.GENERATE_RANDOM_MATRIX(level,level);
        Main.FIND_MINIMUM_NO_OF_CLICKS(level,level);
       
        
       syncFlag[0]=0; 
       
       t=new Thread(this);
       t.start();
       
    }
  
    @Override
    public void run(){
        Random r=new Random();int j=0;
          for(int i=0;i<level;i++){
              
             j=r.nextInt(level);
                jb[i][j].setEnabled(false);
                try{Thread.sleep(100);}catch(Exception er){}
                jb[i][j].setEnabled(true);
            
        }
      
        while(Main.GAMEOVER==false){
            
            syncFlag[0]=1;syncTurn=1;
            while(true){if((syncFlag[1]!=1)||(syncTurn!=1))break;}
            if(Main.GAMEOVER==true)break;
     
       //---------WAITING FOR GUI EVENT TO COMPLETE-------------------
              //-------------------------------------------------------------     
            
            
            //------------
            syncFlag[0]=0;
        }
                  if(ma!=null)
                  removeMouseListener(ma);
                   saveScores();
                   try{//Thread.sleep(2000);
                   }catch(Exception er){}
                    resetALL();
                   
                   
               
    
    }
    
    //ASSUMING THAT THREADS OF SWING WILL RETURN SAFELY WITHIN TIME??
    
    void processMouseEvent(){
         
         this.addMouseListener(ma=new MouseAdapter(){
             
             @Override
             public void mouseClicked(final MouseEvent me){
               
               syncFlag[1]=1;
               syncTurn=0;
               while(true){if((syncFlag[0]!=1)||(syncTurn!=0))break;}
               if(ma!=null)removeMouseListener(ma);
               
               if(Main.GAMEOVER==false)//----GAME NOT OUT WHILE WAITING----
               {
                   System.out.println("SENDING CORDS::"+me.getXOnScreen()+","+me.getYOnScreen()+"");
                   Main.START_GAME(Main.MINEMAT, level,level, (me.getYOnScreen()/blockHeight),(me.getXOnScreen()/blockWidth));
                   
                   //repaint();
               }//THIS THREAD MAY NOT RETURN AT APPROPRIATE TIME AFTER START_GAME(-,-,-,)CALLING...
             
               if((ma==null)&&(Main.GAMEOVER!=true)){processMouseEvent();}
              
               syncFlag[1]=0;
             }
         }
                 );
     
     }

    public void actionPerformed(ActionEvent e) {
        
   
        
        
    }

    public void saveScores(){
        
        System.out.println("total="+Main.TotalCount+" and revealed="+Main.CELLCOUNT);
        Main.Score=(Main.TotalCount-Main.CELLCOUNT)*100;
                   
        Main.Score/=Main.TotalCount;
        int tmpScore=(int)Main.Score;
         
        try{
            String name11="PLAYER_1",stage="";
            
            if(MineGUI._5x5.isSelected()){stage="_5x5_";}
            else if(MineGUI._7x7.isSelected()){stage="_7x7_";}
            else {stage="_10x10_";}
        try{    
        name11=JOptionPane.showInputDialog("Winning %age =::"+tmpScore+"\n Enter-_UR_-NAME::",name11);
        }catch(Exception er){name11="PAPPU";}
        if(name11==null)name11="BY_MISTAKE";
        Scores.write(name11, tmpScore,stage);
        
        }catch(Exception er){}
        
        
    }
    
    public void resetALL(){
        
        
         resetPanel();
        Main.resetMain();
    }
 
    public static void setGUI(MineGUI gui){
        GUI=gui;
    }
    
}

