
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author vivek
 */
public class Scores {
    


    static ObjectOutputStream oos;
    static ObjectInputStream ois;
    static File file;
    static FileInputStream fis;
    static FileOutputStream fos;
    static data dt_read,dt_write;
    static String msg="";
    static int arrayEmpty=0;
    
    public static void main(String[] arg){
        
      //print("Stage1");write("vjvjv",800,"Stage7");print("Stage7");
    }
    
    static void reset(){ois=null;oos=null;dt_read=dt_write=null;fis=null;fos=null;file=null;}
    
   static void retrieve(int i){
   String totalscores="";
       
       totalscores+=print("_5x5_","");
       JOptionPane.showMessageDialog(null, totalscores);
       totalscores="";
       totalscores+=print("_7x7_","");
       JOptionPane.showMessageDialog(null, totalscores);
       totalscores="";
       totalscores+=print("_10x10_","");
       JOptionPane.showMessageDialog(null, totalscores);
       
   }
    
    static String print(String stage,String msg){
        String mas="TOP_SCORERS oF "+stage+" \n";
        try{
            reset();
            file=new File(stage+".data");
            
            if(file.isFile()){
            fis=new FileInputStream(stage+".data");
            ois=new ObjectInputStream(fis);
            dt_read=(data)ois.readObject();
            dt_write=new data();
            for(int i=0;i<5;i++){
                
                dt_write.score[i]=dt_read.score[i];
                dt_write.name[i]=dt_read.name[i];
                
            }
            
            if(ois!=null)ois.close();if(file!=null)file=null;if(fis!=null)fis.close();
            for(int i=0;i<5;i++)
                mas+=(dt_write.name[i]+"  "+dt_write.score[i]+"  "+"\n");
            mas+="\n"+msg+"\n";
            //JOptionPane.showMessageDialog(null, mas);
            dt_read=dt_write=null;
            }
                         
        }catch(Exception er){try{if(ois!=null)ois.close();}catch(Exception ert){}}
        return mas;
        
    }
    
    
    static void read(String stage){
        try{
            reset();
            file=new File(stage+".data");
            if(!file.isFile()){//IF FILE DOES NOT EXIST
                file.createNewFile();arrayEmpty=1;file=null;}
            else{arrayEmpty=0;
            fis=new FileInputStream(stage+".data");
            ois=new ObjectInputStream(fis);
            dt_read=(data)ois.readObject();
            dt_write=new data();
            for(int i=0;i<5;i++){
                
                dt_write.score[i]=dt_read.score[i];
                dt_write.name[i]=dt_read.name[i];
               
            }
            
            if(ois!=null)ois.close();if(file!=null)file=null;if(fis!=null)fis.close();
            
            dt_read=null;dt_read=dt_write;dt_write=null;
            }
            
        }catch(Exception er){try{if(ois!=null)ois.close();}catch(Exception ert){}}
        
        
        
    }
    
    //NOTE::oos SHOULD BE CREATED ONLY WHEN YOU ARE SURE DATA 
    //IS TO BE WRITTEN IN NEXT INSTRUCTION AND INSTRUCTION IS TO BE EXECUTED NEXT  
    
    static void write(String name,int score,String stage){
        
        try{msg="";
            try{read(stage);}catch(Exception err){}
            if(name=="null")name="PLAYER";
            if(arrayEmpty==1){arrayEmpty=0;
                fos=new FileOutputStream(stage+".data");
                oos=new ObjectOutputStream(fos);    
                dt_write=new data();
                dt_write.setName(name, 0);dt_write.setScore(score,0);
                for(int i=1;i<5;i++){
                dt_write.setName("---------", i);dt_write.setScore(0,i);
                
                }
             //   oos.flush();
                oos.writeObject(dt_write);
            }
          else  if(dt_read==null){//IF FILE EXISTS AND SOMEBODY HAS DELETED DATA FROM IT
            file=new File(stage+".data");
            if(!file.isFile()){file.createNewFile();}
            fos=new FileOutputStream(stage+".data");
            oos=new ObjectOutputStream(fos);    
                dt_write=new data();
                dt_write.setName(name, 0);
                dt_write.setScore(score,0);
                for(int i=1;i<5;i++){
                dt_write.setName("---------", i);dt_write.setScore(0,i);
                
                }
             //   oos.flush();
                oos.writeObject(dt_write);
                
            }
            else{
                int count=0,i=0,tmp1=score,tmp2;String nm1=name,nm2,st1=stage,st2;
                for( i=0;i<5;i++)
                {
                  if(dt_read.score[i]<score){count=1;break;}
                }
                if(count==1){file=new File(stage+".data");
            if(!file.isFile())file.createNewFile();
            fos=new FileOutputStream(stage+".data");
            oos=new ObjectOutputStream(fos);
                    for(int k=i;k<5;k++)
                    {
                        tmp2=dt_read.score[k];dt_read.score[k]=tmp1;tmp1=tmp2;
                        nm2=dt_read.name[k];dt_read.name[k]=nm1;nm1=nm2;
                    }
                    
                
                    oos.writeObject(dt_read);
                }
                else{msg="TRY_TO_THINK_BETTER_?!!**@BestOfLuck  ";}
            }
        }catch(Exception er){}
        finally{JOptionPane.showMessageDialog(null, print(stage,msg));try{if(oos!=null)oos.close();}catch(Exception ert){}}
        
    }
    
    
}
 class data implements Serializable{
     
     int[] score;
     String[] name;
     
     
     data(){score=new int[5];name=new String[5];}
     
     void setScore(int Scores,int i){score[i]=Scores;}
     
     void setName(String Name,int i){name[i]=Name;}
     
     
 
 
 }
