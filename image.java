


/**********************
  *************************
        @author::Vivek Mangla
**************************
*****************************/


import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


/**
 *
 * @author vivek
 */
public class image extends JPanel{
    ImageIcon img;
    static String imag;
    static int count=0;
    image(int index){setBackground(Color.black);
        //--------FULL_SCREEN_WIDTH/2&&FULL_SCREEN_HEIGHT/2
        imag="";
    if(index==0){setLayout(new GridLayout(1,2));add(new Multi(0));}
    else if(index==1)imag="Image/pappu.png";
    else if(index==2)imag="Image/Puzzie.png";
    else if(index==3)imag="Image/Challenge.png";
    else if(index==4){
        setLayout(new GridLayout(3,1));
        add(new Multi(0));add(new Multi(1));add(new Multi(2));//add(new Multi(3));
    }
    else if(index==5){
        setLayout(new GridLayout(2,1));
        add(new Multi(5));add(new Multi(7));
    }
    
    img=new ImageIcon(imag);
    
    }
    
    
    @Override
    public void paintComponent(Graphics g){super.paintComponent(g);
    setBackground(Color.black);
    img.paintIcon(this, g, 0,40);
    
    }
    
}
class Multi extends JPanel{
    
    static String imag;
    ImageIcon img;
    
   Multi(int index){setBackground(Color.black);
        //--------FULL_SCREEN_WIDTH/2&&FULL_SCREEN_HEIGHT/2
        imag="";
    if(index==0)imag="Image/Ganit.png";
    img=new ImageIcon(imag);
    
    }
    
    
    @Override
    public void paintComponent(Graphics g){super.paintComponent(g);
    setBackground(Color.black);
    img.paintIcon(this, g, 0,40);
    
    }

}
