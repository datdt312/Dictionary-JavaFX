package dictionarythemeapplication;

/**
 * Chương trình xử lý các yêu cầu
 * @since 2018 - 09 - 25
 * @version 1.0
 * @author haidb va dathn
 */
import java.util.*;

public class DictionaryCommandline {

    /**
     * Ham hien thi tat ca tu trong danh sach
     */
    public static void showAllWords() {
        System.out.println();
        System.out.println("Cac tu trong danh sach la: ");
        for (Word w : DictionaryManagement.dic_list) {
            System.out.println(w.word_target + "\t" + w.word_explain);
        }
    }

    /**
     * Ham dung de goi ham showAllWords va insertFromCommandline
     *
     * @param inp_list quan ly tu dien
     */
    public static void dictionaryBasic(DictionaryManagement inp_list) {
        inp_list.insertFromCommandline();
        showAllWords();
    }

    /**
     * Ham de goi ham insertFromFile, showAllWords va insertFromCommandline
     *
     * @param inp_list quan ly tu dien
     */
    public static void dictionaryAdvanced() {
        DictionaryManagement.insertFromFile();
        showAllWords();

        int check_lookup = DictionaryManagement.dictionaryLookup("tree");
        if (check_lookup != -1) {
            System.out.println(check_lookup);
        } else {
            System.out.println("Khong tim thay tu !");
        }
    }

    /**
     * Ham tao ra cac lưa chon
     *
     * @return lua chon cua nguoi dung
     */
    public static int getChoice() {
        System.out.println("1. Tra tu.");
        System.out.println("2. Them tu.");
        System.out.println("3. Xoa tu.");
        System.out.println("4. Xem tat ca tu.");
        System.out.println("5. Xuat tat ca tu ra file.");
        System.out.println("6. Thoat.");

        System.out.println();
        System.out.printf("Lua chon cua ban la: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        return choice;
    }

    /**
     * Hàm tìm ra các từ có bắt đầu bằng pre_word
     *
     * @param pre_word từ cho trước
     * @return danh sách các từ có bắt đầu bằng pre_word
     */
    public static ArrayList<String> dictionarySearcher(String pre_word) {
        ArrayList<String> result = new ArrayList<String>();

        int start = Collections.binarySearch(DictionaryManagement.copy_list, pre_word);
        int index = start;

        while (index < DictionaryManagement.copy_list.size() & DictionaryManagement.copy_list.get(index).startsWith(pre_word)) {
            result.add(DictionaryManagement.copy_list.get(index));
            index++;
        }

        return result;

    }

    /**
     * Ham thuc hien lua chon nhap vao tu ban phim
     *
     * @param inp_list quan ly tu dien
     */
    public static void dictionaryRun(String export_filename) {
        boolean dictionaryRunning = true;
        int _choice = 0;
        while (dictionaryRunning && _choice == 0) {
            System.out.println();
            _choice = getChoice();

            if (_choice == 1) {
                System.out.printf("Nhap tu can tra: ");
                String find_word = DictionaryManagement.insertFromCommandline();
                //System.out.println("Word need to find: " + find_word);
                String explain_of_find_word = DictionaryManagement.getExplainOfWord(find_word);
                System.out.println("Meaning: " + explain_of_find_word);
            }
            if (_choice == 2) {
                System.out.printf("Tu can them: ");
                String add_target_word = DictionaryManagement.insertFromCommandline();
                System.out.printf("Nghia cua tu: ");
                String add_explain_word = DictionaryManagement.insertFromCommandline();
                Word add_word = new Word(add_target_word, add_explain_word);
                DictionaryManagement.addWord(add_word);
            }
            if (_choice == 3) {
                System.out.printf("Tu can xoa: ");
                String delete_word = DictionaryManagement.insertFromCommandline();
                DictionaryManagement.removeWord(delete_word);
                System.out.println("Da xoa tu: " + delete_word);
            }
            if (_choice == 4) {
                showAllWords();
            }
            if (_choice == 5) {
                DictionaryManagement.dictionaryExportToFile(export_filename);
            }
            if (_choice == 6) {
                dictionaryRunning = false;
            }
            _choice = 0;
        }
    }

    /**
     * main la ham xu ly cac yeu cau tren
     *
     * @param args
     */
    /*
	public static void main(String[] args) 
	{
            DictionaryManagement dic_Mana = new DictionaryManagement();
            DictionaryCommandline dic_command = new DictionaryCommandline();
            dic_command.dictionaryAdvanced(dic_Mana);
            String export_filename = "new_dictionaries.txt";

            
            //System.out.println("\n***Start Remove***\n");
            //dic_Mana.removeWord("car");
            //dic_Mana.removeWord("book");
            //dic_command.showAllWords(dic_Mana);

            //System.out.println("\n***Start Add***\n");
            //dic_Mana.addWord(new Word("car","O to"));
            //dic_Mana.addWord(new Word("book","Quyen sach"));
            //dic_command.showAllWords(dic_Mana);
           
            dic_command.dictionaryRun(dic_Mana, export_filename);
	}
     */
}
