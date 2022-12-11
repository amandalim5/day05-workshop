package milton;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main{

  public static final String HEADER = "word, count\n";
  public static void main(String[] args) throws Exception {

    
    if(args[0].equals("paradise_lost.txt")){
      System.out.println("Processing paradise_lost.txt");
    }


    String filename = args[0];
    System.out.printf("Processing %s\n", filename);
    String line;
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);
    
    FileWriter fw = new FileWriter("word.csv");

    FileOutputStream fou = new FileOutputStream("word.csv");
    OutputStreamWriter osw = new OutputStreamWriter(fou);

    Integer i = 1;
    Integer count = 0;
    Byte nextChar = 0;
    Integer totalwords = 0;
    Map<String, Integer> map = new HashMap<>();
    while(i <= 100){
      line = br.readLine();

      if(line == null){
        break;
      }
      String[] words = line.trim().split(" ");
      totalwords += words.length;
      System.out.printf("%d: %s\n", i,line.toUpperCase());
      i++;
      // for (String w: words)
      for(int j=0; j<words.length; j++){
        String currentWord = words[j].replace(",", "");
        // String currentWord = words[j];
        if(currentWord.equals("")){
          break;
        }
        if(map.containsKey(currentWord)){
          Integer value = map.get(currentWord);
          value ++;
          map.replace(currentWord, value);
        } else{
          map.put(currentWord, 1);
        }
      }

      // nextChar = (byte) br.read();
      // if(nextChar == -1){
      //   break;
      // }
      // if(nextChar == 32){
      //   count++;
      // }
      // System.out.printf(Byte.toString(nextChar));

      // if(nextChar == 10){
      //   i++;
      // }

      // System.out.println("the number of words is " + count);


      
    }
    System.out.println("The total number of unique words: " + map.size());

    osw.write(HEADER);
    for(String key: map.keySet()){
      System.out.printf("%s: %d\n", key,map.get(key));
      //String line = String.format("%s, %d", key, map.get(key))
      // fw.write(line);
      osw.write(key +", " + map.get(key) + "\n");
    };

    fw.flush();
    fw.close();

    br.close();
    fr.close();

    System.out.printf("The number of words in first 100 lines: %d\n",totalwords);


    FileInputStream fin = null;
    FileOutputStream fout = null;
    try{
      fin = new FileInputStream("paradise_lost.txt");
      fout = new FileOutputStream("null.txt");
      OutputStream bout = new BufferedOutputStream(null);

        byte[] input = fin.readAllBytes();
        fout.write(input);


      fout.flush();
      
    } catch (IOException e){
      System.out.println(e);
    }
    

  }
 }