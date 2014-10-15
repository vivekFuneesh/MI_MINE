
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import javax.swing.JFrame;

/**
 *
 * @author vivek
 */




public class MineFrame extends JFrame {
    
    static MineFrame mf;
    static MinePanel mp;
    static MineGUI mg;
    static int pWidth,pHeight;
   
     public static void main(String[] arg){
        
        mf=new MineFrame();
    }
    
     MineFrame(){
    super("MI_MINE");
       
    
    setResizable(false);
    calculateSizes(); 
    setResizable(true);   
    Container c=getContentPane();
    mp=new MinePanel(pWidth,pHeight);
    mg=new MineGUI(mp);
    Main.setMIINEPANEL(mp);
    setUndecorated(true);
    c.add(mp,BorderLayout.CENTER); 
   // c.add(new JLabel("I am South"),BorderLayout.SOUTH); 
    pack();
    setResizable(false);
    setJMenuBar(MineGUI.jmb);
    MinePanel.setGUI(mg);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    //System.out.println(pWidth+" "+pHeight);
    }

     private void calculateSizes(){
    
        GraphicsConfiguration gc=getGraphicsConfiguration();
        Rectangle screenRect=gc.getBounds();
        pWidth=screenRect.width;
        pHeight=screenRect.height;
        //Toolkit tk=Toolkit.getDefaultToolkit();
        //Insets desktopInsets=tk.getScreenInsets(gc);
        //Insets frameInsets=getInsets();//System.out.println(frameInsets);
        //-(desktopInsets.left+desktopInsets.right)-(frameInsets.left+frameInsets.right);
        //-(desktopInsets.bottom+desktopInsets.top)-(frameInsets.bottom+frameInsets.top);
                                 }
     
}

