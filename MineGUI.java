
/**
 *
 * @author vivek
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author vivek
 */

public class MineGUI implements ActionListener,ItemListener{
    static JMenuBar jmb;//-------------------------------------------INCLUDE INTRO,HELP,ABOUT.....-------------------------------------
    static JMenu Game,Noise,High_Scores,Theme,Help,Difficult,matrix;
    static JMenuItem Play,Quit,rOYALTITES,About,Developer;
    static JCheckBoxMenuItem Music,Sound,MAKEiT;
    static ButtonGroup matrixG,ThemeG;
    static JRadioButtonMenuItem _7x7,_5x5,_10x10,Bageecha,Gulabo,Haldi;
    static AboutFrame w;
    static DeveloperPanel dp;
    static MinePanel MinePanel;
    
    MineGUI(MinePanel MinePanel){
    MineGUI.MinePanel=MinePanel;
     jmb=new JMenuBar();
         
           
            Game=new JMenu("Game");
            Theme=new JMenu("Themess");
            Noise=new JMenu("Voice");
            High_Scores=new JMenu("Royalties");
            Help=new JMenu("Help");
            Difficult=new JMenu("HARD");
            matrix=new JMenu("SIZE");
            
            //Game------------------------------------------------------------------------
            Game.add(Play=new JMenuItem("PLAY"));Play.addActionListener(this);
            Game.add(Quit=new JMenuItem("QUIT"));Quit.addActionListener(this);
            jmb.add(Game);
            //------------------------------------------------------------------------------------
            //Stage--------------------------------------------------------------------------------
            matrix.add(_5x5=new JRadioButtonMenuItem("25",true));_5x5.addActionListener(this);
            matrix.add(_7x7=new JRadioButtonMenuItem("49"));_7x7.addActionListener(this);
            matrix.add(_10x10=new JRadioButtonMenuItem("100"));_10x10.addActionListener(this);
            matrixG=new ButtonGroup();
            matrixG.add(_5x5);
            matrixG.add(_7x7);
            matrixG.add(_10x10);
            
            jmb.add(matrix);
            //--------------------------------------------------------------------------------------
            //---Difficult-----------------------------------------------------------------------------
            Difficult.add(MAKEiT=new JCheckBoxMenuItem("JUST_Do_IT"));MAKEiT.addItemListener(this);
            //jmb.add(Difficult);
            //----------------------------------------------------------------------------------------
            //-------Theme----------------------------------------------------------------------------
            Theme.add(Bageecha=new JRadioButtonMenuItem("GarDen"));Bageecha.addActionListener(this);
            Theme.add(Gulabo=new JRadioButtonMenuItem("PiNk"));Gulabo.addActionListener(this);
            Theme.add(Haldi=new JRadioButtonMenuItem("SKY",true));Haldi.addActionListener(this);
            ThemeG=new ButtonGroup();
            ThemeG.add(Bageecha);
            ThemeG.add(Gulabo);
            ThemeG.add(Haldi);
            jmb.add(Theme);
            //----------------------------------------------------------------------------------------
            //Noise----------------------------------------------------------------------------------
            Noise.add(Music=new JCheckBoxMenuItem("Music"));Music.addItemListener(this);
            Noise.add(Sound=new JCheckBoxMenuItem("Sound"));Sound.addItemListener(this);
            //jmb.add(Noise);
            //---------------------------------------------------------------------
            //High_Scores---------------------------------------------------------
            High_Scores.add(rOYALTITES=new JMenuItem("..DISABLED_WITH_4MOONS"));rOYALTITES.addActionListener(this);
            jmb.add(High_Scores);
            //--------------------------------------------------------------------
            //------Help-----------------------------------------------------
            Help.add(About=new JMenuItem("INFO"));About.addActionListener(this);
            Help.add(Developer=new JMenuItem("DeveloPER"));Developer.addActionListener(this);
            jmb.add(Help);
            //--------------------------------------------------------------------
    
    
    }

    public void actionPerformed(ActionEvent ue){
      if(ue.getSource()==Play){
                                  //if(Music.isSelected()){kf.playMusic();}    
                           //remove(panel1);
                           reset();
                           MinePanel.ActivatePanel();                            
                            }
      else if(ue.getSource()==Quit){System.exit(0);}
      else if(ue.getSource()==rOYALTITES){Scores.retrieve(6);
      }
      else if(ue.getSource()==About){
         w=new AboutFrame();
                                    }
        else if(ue.getSource()==Developer){
          dp=new DeveloperPanel();  
            
            
        }
                                             }

   public void itemStateChanged(ItemEvent i){}
    
   void Activate(){
       jmb.setEnabled(true);MineGUI.jmb.setVisible(true);
   }
   
   void reset(){
       MineGUI.jmb.setEnabled(false);
       MineGUI.jmb.setVisible(false);
   }
   
   
   
}
