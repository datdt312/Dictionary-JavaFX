package dictionarythemeapplication;

/**
 * DictionaryManagement la chuong trinh quan ly tu dien
 *
 * @author Dat va HaiDB :v
 * @version 10.0.8
 * @since 2018 - 09 - 25
 */
import java.util.*;
import java.io.*;

public class DictionaryManagement {

    /**
     * tao 1 tree set luu tru danh sach tu dien
     */
    public static TreeSet<Word> dic_list = new TreeSet<Word>();
    public static List<String> copy_list = new ArrayList<String>();

    /**
     * ham ben duoi la ham co chuc nang nhap lieu
     *
     * @return res
     */
    public static String insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        String res = sc.next();
        sc.close();
        return res;
    }

    /**
     * ben duoi la ham nhap du lieu tu file text
     */
    public static void insertFromFile() {
        final String filename = "dictionaries.txt";
        String line;
        int d = 0;
        try {
            FileReader inputFile = new FileReader(filename);
            try {
                Scanner sc = new Scanner(inputFile);
                while (sc.hasNextLine()) {
                    //d++;
                    line = sc.nextLine();
                    int firstTab = line.indexOf("\t", 0);
                    if (firstTab != -1) {
                        d++;
                        //System.out.println(line.substring(0, firstTab));
                        String real_target = line.substring(0, firstTab);
                        String real_explain = line.substring(firstTab + 1, line.length());
                        
                        Word n_word = new Word(real_target, real_explain);
                        dic_list.add(n_word);
                        //System.out.println(line);
                    } else {
                        d++;
                        System.out.println(d);
                    }
                }
            } finally {
                inputFile.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(filename + " not found");
        } catch (IOException e) {
            System.out.println("Unexpected I/O error occured");
        }

        System.out.println("Số từ: " + d);
    }

    /**
     * có chức năng xuất dữ liệu từ điển hiện tại ra file
     *
     * @param filename tên file cần xuất dữ liệu ra
     */
    public static void dictionaryExportToFile(String filename) {
        //final String filename = "new_dictionaries.txt";

        try {
            FileWriter fileWriter = new FileWriter(filename);

            BufferedWriter fout = new BufferedWriter(fileWriter);

            for (Word w : dic_list) {
                fout.write(w.word_target + "\t" + w.word_explain + "\n");
            }

            fout.close();
        } catch (IOException e) {
        }

        System.out.println("Da xuat tat ca ra file. ");
    }

    /**
     * hàm bên dưới có chức năng tra từ điện từ dòng lệnh
     *
     * @param wordLooking từ cần tìm
     * @return vị trí của từ cần tìm trong danh sách
     */
    public static int dictionaryLookup(String wordLooking) {
        int indexFinding = -1;
        boolean checkFinding = false;
        for (Word w : dic_list) {
            indexFinding++;
            if (wordLooking.equals(w.word_target)) {
                return indexFinding;
            }
        }

        return -1;
    }

    /**
     * Hàm lấy nghĩa của 1 từ cần tìm
     *
     * @param wordLooking từ cần tìm
     * @return nghĩa của từ cần tìm
     */
    public static String getExplainOfWord(String wordLooking) {
        int indexExplainWord = dictionaryLookup(wordLooking);
        //System.out.println("indexExplainWord: " + indexExplainWord);
        String explain_word;
        if (indexExplainWord == -1) {
            explain_word = "Khong tim thay tu !";
        } else {
            explain_word = new ArrayList<>(dic_list).get(indexExplainWord).word_explain;
        }

        return explain_word.replaceAll("\t", "\n");
    }

    /**
     * hàm bên dưới có chức năng xóa từ
     *
     * @param remove_word từ cần xóa
     */
    public static void removeWord(String remove_word) {
        int indexRemove = dictionaryLookup(remove_word);

        if (indexRemove != -1) {
            Word r_word = new ArrayList<>(dic_list).get(indexRemove);
            dic_list.remove(r_word);
        }
    }

    /**
     * Hàm có chức năng thêm từ
     *
     * @param w từ cần thêm
     */
    public static void addWord(Word w) {
        dic_list.add(w);
    }

    public static void updateListFromTreeSet() {
        copy_list.clear();

        for (Word w : dic_list) {
            copy_list.add(w.word_target);
        }
    }
}
