

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author vivek
 */
public class Main {
    
    static int[][] MINEMAT,MINEFLAG;
    static Random r;
   // static Scanner s;
    static boolean GAMEOVER=false;
    static int min_CLICKS=0,User_CLICKS=0,onlyPrint=0,XLIMIT,YLIMIT;
    static double CELLCOUNT=0,TotalCount=0,Score=0;
    static MinePanel mp=null;
    //-2  BOMB
    //-1  NOT REVEALED
    //if FLAG ==-3 CELL HAS BEEN FLAGGED i.e. CHECKED i.e MAT is in 0-8
    
    public static void main(String[] arg){
        
        
        //createMatrix(5,5);
        //GENERATE_RANDOM_MATRIX(5,5);
        //FIND_MINIMUM_NO_OF_CLICKS(5,5);
       // START_GAME(MINEMAT,5,5,0,0);
        
    }
    
    public static void createMatrix(int X_LIMIT,int Y_LIMIT){
        MINEMAT=new int[X_LIMIT][Y_LIMIT];
        MINEFLAG=new int[X_LIMIT][Y_LIMIT];
        XLIMIT=X_LIMIT;YLIMIT=Y_LIMIT;
    } 
    
    public static void GENERATE_RANDOM_MATRIX(int X_LIMIT,int Y_LIMIT){
        r=new Random();
        int prob;
        
        for(int i=0;i<X_LIMIT;i++){
            for(int j=0;j<Y_LIMIT;j++){
              prob=r.nextInt(5);
              if(prob==2)//-------BOMB_IS_THERE-----
              {
                  MINEMAT[i][j]=-2;MINEFLAG[i][j]=-2;
              }
              else//------NO_BOMB----
              {
                 MINEMAT[i][j]=-1;MINEFLAG[i][j]=-1;
                 CELLCOUNT++;
              }
            }
         }
        TotalCount=CELLCOUNT;
        
    }
    
    public static void START_GAME(int[][] arr,int X_LIMIT,int Y_LIMIT,int XCord,int YCord){
        
        
        /*
            System.out.println("Enter XCord of Cell And YCord of Cell..");
            XCord=s.nextInt();
            YCord=s.nextInt();
          */
      //  System.out.println("Cordinates recieved are::"+XCord+","+YCord);
            User_CLICKS++;
            revealCell(arr,XCord,YCord,0);
            if(CELLCOUNT==0){
                if(GAMEOVER==false)//As This Method might not return to MinePanel...
             //   JOptionPane.showMessageDialog(null, "YOU_WON");
                GAMEOVER=true;
            }
            else{//----PLAYER_DID_NOT_WON_BUT_EITHER_LOST_OR_GAME_IS_TO_BE_CONTINUED----
                /*
            if(GAMEOVER==false){
               
                printMINEMAT(X_LIMIT,Y_LIMIT,false);
            }*/
            if(GAMEOVER==true){
                
                printMINEMAT(X_LIMIT,Y_LIMIT,true);
                
            }
            }
        
    }
    
    public static void revealCell(int[][] arr,int i,int j,int Count){
        
        if((Count==0)&&(arr[i][j]==-2)){
            GAMEOVER=true;
           // JOptionPane.showMessageDialog(null,"YOU_LOST");
            System.out.println("LOST");
        }
        else if(arr[i][j]!=-2){
            
            //As this block will be called
            
            
                
                if(CELLCOUNT==0)return;
                
                if(arr[i][j]==-1){
                    FIND_ENTRY(arr,i,j);
                    CELLCOUNT--;
                    if(arr[i][j]==0){revealAll(arr,i,j);}
                }
                //--Flag  this cell--
              //  if(onlyPrint==0)System.out.println("Cord "+i+","+j+" has been revealed");
                //if(arr[i][j]!=-2){
                    
                    
                    Font font = new Font(Font.SERIF, Font.BOLD, 60);
                    String str="8";
                    FontMetrics fm=mp.getFontMetrics(font);
                   
                    int xCord=(j*mp.blockWidth+5)+(mp.blockWidth-10-fm.stringWidth(str))/2;
                    int yCord=(i*mp.blockHeight+5)+(fm.getAscent()+(mp.blockHeight-10-(fm.getAscent()+fm.getDescent()))/2);
            
                    if(MineGUI.Bageecha.isSelected())
                    MinePanel.jb[i][j].setBackground(Color.GREEN);
                    else if(MineGUI.Gulabo.isSelected())
                    MinePanel.jb[i][j].setBackground(Color.PINK);
                    if(MineGUI.Haldi.isSelected())
                    MinePanel.jb[i][j].setBackground(Color.CYAN);
                    
                    MinePanel.jb[i][j].setFont(font);//tContentAreaFilled(false);
                    if(arr[i][j]==0){MinePanel.jb[i][j].setText("");}
                    else if(arr[i][j]!=-2){MinePanel.jb[i][j].setText(Integer.toString(MINEMAT[i][j]));}
                    else MinePanel.jb[i][j].setText("BOMB");
                    MinePanel.jb[i][j].validate();
                    MinePanel.jb[i][j].setEnabled(false);
                    
            
        }
        
        
    }
    
    public static void FIND_ENTRY(int[][] arr,int i,int j){
        
        int mineCOUNT=0;
        if(i-1>=0)if(arr[i-1][j]==-2)mineCOUNT+=1;
        if(j-1>=0)if(arr[i][j-1]==-2)mineCOUNT+=1;
        if(i+1<XLIMIT)if(arr[i+1][j]==-2)mineCOUNT+=1;
        if(j+1<YLIMIT)if(arr[i][j+1]==-2)mineCOUNT+=1;
        if((i-1>=0)&&(j-1>=0))if(arr[i-1][j-1]==-2)mineCOUNT+=1;
        if((i-1>=0)&&(j+1<YLIMIT))if(arr[i-1][j+1]==-2)mineCOUNT+=1;
        if((i+1<XLIMIT)&&(j-1>=0))if(arr[i+1][j-1]==-2)mineCOUNT+=1;
        if((i+1<XLIMIT)&&(j+1<YLIMIT))if(arr[i+1][j+1]==-2)mineCOUNT+=1;
        
        arr[i][j]=(int)mineCOUNT;
    }
    
    public static void revealAll(int[][] arr,int i,int j){
        
        if(i-1>=0)if(arr[i-1][j]==-1)revealCell(arr,i-1,j,1);
        if(j-1>=0)if(arr[i][j-1]==-1)revealCell(arr,i,j-1,1);
        if(i+1<XLIMIT)if(arr[i+1][j]==-1)revealCell(arr,i+1,j,1);
        if(j+1<YLIMIT)if(arr[i][j+1]==-1)revealCell(arr,i,j+1,1);
        if((i-1>=0)&&(j-1>=0))if(arr[i-1][j-1]==-1)revealCell(arr,i-1,j-1,1);
        if((i-1>=0)&&(j+1<YLIMIT))if(arr[i-1][j+1]==-1)revealCell(arr,i-1,j+1,1);
        if((i+1<XLIMIT)&&(j-1>=0))if(arr[i+1][j-1]==-1)revealCell(arr,i+1,j-1,1);
        if((i+1<XLIMIT)&&(j+1<YLIMIT))if(arr[i+1][j+1]==-1)revealCell(arr,i+1,j+1,1);
        
    }
    
    public static void printMINEMAT(int X_LIMIT,int Y_LIMIT,boolean GameOver){
        
        if(GameOver==false){
            
            for(int i=0;i<X_LIMIT;i++){
                for(int j=0;j<Y_LIMIT;j++){
                    if((MINEMAT[i][j]==-2))
                    System.out.print(" @");//------SHOW_BOMB---
                    else if(MINEMAT[i][j]==-1)System.out.print(" +");//--_-DO_NOT_SHOW_UNCOVERED_NUMBER----
                    else System.out.print(" "+MINEMAT[i][j]);
                    
                }//System.out.println();
            }
        }
        else{onlyPrint=1;
            for(int i=0;i<X_LIMIT;i++){
                for(int j=0;j<Y_LIMIT;j++){
                    
                    
                    if((MINEMAT[i][j]==-2)){
                    MinePanel.jb[i][j].setText("BOOM");
                    MinePanel.jb[i][j].validate();
                    MinePanel.jb[i][j].setEnabled(false);
                        //System.out.print(" @");//------SHOW_BOMB---
                    }
                    
                    
                }//System.out.println();
            }
            
        }
        
    }
    
    
    
    //________________CALCULATION_OF_MINIMUM_NO_OF_CLICKS___________________________
    
    public static void FIND_MINIMUM_NO_OF_CLICKS(int X_LIMIT,int Y_LIMIT){
        
        
      /*  for(int i=0;i<X_LIMIT;i++){
            for(int j=0;j<Y_LIMIT;j++){
                if(MINEFLAG[i][j]==-2){incrementAllSurroundCell(MINEFLAG,i,j);}
            }
        }*/
        for(int i=0;i<X_LIMIT;i++){
            for(int j=0;j<Y_LIMIT;j++){
                if(MINEFLAG[i][j]==-1){
                    FIND_ENTRY(MINEFLAG,i,j);
                    if(MINEFLAG[i][j]==0)
                    {
                    revealFLAGCell(MINEFLAG,i,j,1);
                    min_CLICKS++;
                    }
                }
                
            }
        }//System.out.println("Due to 0 no of clicks are="+min_CLICKS);
         for(int i=0;i<X_LIMIT;i++){
            for(int j=0;j<Y_LIMIT;j++){
                if((MINEFLAG[i][j]!=-3)&&((MINEFLAG[i][j]!=-2))){min_CLICKS++;}
            }
        }
        System.out.println("MINIMUM NO OF CLICKS REQUIRED ARE::"+min_CLICKS);
        
       
       
    }
    
    public static void revealFLAGCell(int[][] arr,int i,int j,int Count){
                
        if(arr[i][j]!=-3){//---If This Cell has not been checked previously---
                if(arr[i][j]==-1){FIND_ENTRY(arr,i,j);}
                if(arr[i][j]==0){
                    if(arr[i][j]!=-2)arr[i][j]=-3;//Flag this cell to avoid recursion
                    revealAllFLAG(arr,i,j);
                }//--Flag  this cell--
                //if(onlyPrint==0)System.out.println("Cord "+i+","+j+" has been revealed");
                if(arr[i][j]!=-2)arr[i][j]=-3;
        }
        
    }
    
    public static void revealAllFLAG(int[][] arr,int i,int j){
        
        if(i-1>=0)revealFLAGCell(arr,i-1,j,1);
        if(j-1>=0)revealFLAGCell(arr,i,j-1,1);
        if(i+1<XLIMIT)revealFLAGCell(arr,i+1,j,1);
        if(j+1<YLIMIT)revealFLAGCell(arr,i,j+1,1);
        if((i-1>=0)&&(j-1>=0))revealFLAGCell(arr,i-1,j-1,1);
        if((i-1>=0)&&(j+1<YLIMIT))revealFLAGCell(arr,i-1,j+1,1);
        if((i+1<XLIMIT)&&(j-1>=0))revealFLAGCell(arr,i+1,j-1,1);
        if((i+1<XLIMIT)&&(j+1<YLIMIT))revealFLAGCell(arr,i+1,j+1,1);
        
    }
    
    //________________--------------------------------------_________________________
 
    
    public static void setMIINEPANEL(MinePanel mp){
     Main.mp=mp;   
    }
    
    public static void resetMain(){
        min_CLICKS=0;
        User_CLICKS=0;CELLCOUNT=0;
        onlyPrint=0;
        Score=0;
        MINEMAT=null;
        MINEFLAG=null;
    }
    
}
