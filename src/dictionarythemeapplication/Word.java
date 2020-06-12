package dictionarythemeapplication;

/**
 * Word la chuong trinh dinh nghia 1 tu trong tu dien
 *
 * @author Dat va HaiDB :v
 * @version 1.0
 * @since 2018 - 09 - 25
 */
public class Word implements Comparable<Word> {

    /**
     * target : Từ tiếng anh explain : Nghĩa của từ
     */
    String word_target;
    String word_explain;

    // Constructor
    public Word() {

    }

    /**
     * Constructor khởi tạo một từ giống từ đã cho
     *
     * @param w từ đã cho
     */
    public Word(Word w) {
        this.word_target = w.word_target;
        this.word_explain = w.word_explain;
    }

    /**
     * Constructor khởi tạo 1 từ có từ tiếng anh và nghĩa của nó
     *
     * @param wtarget từ tiếng anh
     * @param wexplain nghĩa của từ tiếng anh
     */
    public Word(String wtarget, String wexplain) {
        this.word_target = wtarget;
        this.word_explain = wexplain;
    }

    /**
     * Hàm so sánh 2 từ
     *
     * @param w từ cần so sánh tới
     * @return kết quả so sánh [1 : lớn hơn; 0 : bằng nhau; -1 : bé hơn]
     */
    @Override
    public int compareTo(Word w) {
        return this.word_target.compareTo(w.word_target);
    }
}
