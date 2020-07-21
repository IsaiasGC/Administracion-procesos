
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prueva
 */
public class TextFileManager {
    private BufferedReader br;
    private BufferedWriter bw;
    private String fileIn;
    private String fileOut;
    
    public TextFileManager(String fileIn){
        //super(fileIn, fileOut);
        this.fileIn=fileIn;
        this.fileOut="temp.txt";
    }
    public void writeLine(String texto) throws IOException{
        texto=getText()+texto;
        try{
            bw=new BufferedWriter(new FileWriter(fileOut, true));
            bw.write(texto);
            bw.newLine();
            bw.close();
            rename();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public String getText() throws FileNotFoundException, IOException{
        String line="";
        try{
            br=new BufferedReader(new FileReader(fileIn));
            while(br.ready()){
                line+=br.readLine()+"\n";
            }
            br.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally{
            return line;
        }
    }
    public Vector<String[]> getLines(){
        Vector<String[]> lines=new Vector<>();
        try{
            br=new BufferedReader(new FileReader(fileIn));
            while(br.ready()){
                lines.add(br.readLine().split("\t"));
            }
            br.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally{
            return lines;
        }
    }
    public void rename(){
        File arch=new File(fileIn);
        File arch2=new File(fileOut);
        arch.delete();
        arch2.renameTo(arch);
    }
}
