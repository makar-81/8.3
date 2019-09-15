package graphs;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        try {
        String temp;

        FileReader fr = new FileReader("./test1.txt");
        FileWriter wr = new FileWriter("./test2.txt");

        BufferedWriter  bw = new BufferedWriter(wr);
        BufferedReader br = new BufferedReader(fr);

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            List<List<String>> final_store = new ArrayList<>();
            List<List<String>> begin_store = new ArrayList<>();
            int line_num = 0;

            try {
            while ((temp = br.readLine()) != null) {
                while (!temp.isEmpty()) {
                    int pos1 = temp.indexOf("\t");
                    int pos2 = temp.indexOf("\t",pos1+1);
                    ArrayList<String> line = new ArrayList<String>();
                    if (pos1 > 0 && pos2>0) {
                            line.add(temp.substring(0,pos1));
                            line.add(temp.substring(pos1+1,pos2));
                            line.add(temp.substring(pos2+1,temp.length()));
                            begin_store.add(line);
                        }
                    temp="";
                    }
                }

                while (!begin_store.isEmpty()) {
                    int start = 0;
                    List<String> temp_list = new ArrayList<>(begin_store.get(start));
                    String double_lines ="";
                    begin_store.remove(start);
                    for (Integer j=0;j<begin_store.size();j++){
                        if ((begin_store.get(j).get(0).equals(temp_list.get(0))) && !temp_list.get(1).equals("INF")
                                && !(begin_store.get(j).get(1).equals("INF"))) {
                            Integer min = Integer.parseInt((temp_list.get(1)));
                            if (min > Integer.parseInt(begin_store.get(j).get(1))) {
                                temp_list.set(1,begin_store.get(j).get(1));
                            }

                            if (!(begin_store.get(j).get(2).equals("{}"))){
                                temp_list.set(2,begin_store.get(j).get(2));
                            }
                            double_lines = double_lines + j.toString() + ",";

                        }
                        else if ((begin_store.get(j).get(0).equals(temp_list.get(0))) && !temp_list.get(1).equals("INF")
                                && (begin_store.get(j).get(1).equals("INF"))) {
                            if (!(begin_store.get(j).get(2).equals("{}"))){
                                temp_list.set(2,begin_store.get(j).get(2));
                            }
                            double_lines = double_lines + j.toString() + ",";
                        }

                        else if ((begin_store.get(j).get(0).equals(temp_list.get(0))) && temp_list.get(1).equals("INF")
                                && !(begin_store.get(j).get(1).equals("INF"))) {
                            if (!(begin_store.get(j).get(2).equals("{}"))){
                                temp_list.set(1,begin_store.get(j).get(1));
                                temp_list.set(2,begin_store.get(j).get(2));
                            }
                            double_lines = double_lines + j.toString() + ",";
                        }
                        else if ((begin_store.get(j).get(0).equals(temp_list.get(0))) && temp_list.get(1).equals("INF")
                                && (begin_store.get(j).get(1).equals("INF"))) {
                            if (!(begin_store.get(j).get(2).equals("{}"))){
                                temp_list.set(2,begin_store.get(j).get(2));
                            }
                            double_lines = double_lines + j.toString() + ",";
                        }
                    }
                    // здесь конечная обработка и добавление в финальный массив результата
                    int iter =0;
                    while (!double_lines.isEmpty() && begin_store.size() > 0) {
                        int pos4 = double_lines.indexOf(",");
                        if (pos4 > 0){
                            begin_store.remove(Integer.parseInt(double_lines.substring(0,pos4))-iter);
                            double_lines = double_lines.substring(pos4+1,double_lines.length());
                            iter++;
                        }
                    }
                    final_store.add(temp_list);
                }

                for (int j = 0; j< final_store.size();j++){
                    System.out.println((final_store.get(j)).get(0) + "\t" + (final_store.get(j)).get(1) + "\t" + (final_store.get(j)).get(2) + "\n");
                    bw.write((final_store.get(j)).get(0) + "\t" + (final_store.get(j)).get(1) + "\t" + (final_store.get(j)).get(2) + "\n");
                    bw.flush();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }

    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

