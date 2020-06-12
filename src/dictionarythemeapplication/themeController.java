/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionarythemeapplication;

import com.darkprograms.speech.translator.GoogleTranslate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import static javafx.scene.input.KeyEvent.KEY_RELEASED;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import static javafx.scene.media.MediaPlayer.Status.READY;

/**
 * Chương trình điều khiển hoạt động của giao diện từ điển
 *
 * @since 2018 - 09 - 25
 * @version 1.0
 * @author haidb va dathn
 */
public class themeController implements Initializable {

    @FXML
    private ImageView btn_find, btn_addandremove, btn_export, btn_author, btn_close;
    @FXML
    private AnchorPane top_find, top_addandremove, top_export, top_author, top_close, top_hddict, top_explain, top_dichvb;
    @FXML
    private JFXButton yes_close, no_close;
    @FXML
    private TextField word_add, mean_add, word_delete;
    @FXML
    private JFXButton btn_add, btn_remove, btn_savetofile;
    @FXML
    private ImageView btn_translate1, btn_speaker1, btn_dichVB1, btn_back1;
    @FXML
    private Label btn_translate2, btn_speaker2, btn_dichVB2, btn_back2;
    @FXML
    private Label btn_dvb;
    @FXML
    private ImageView spk_inp, spk_out;
    @FXML
    private Label lb_spk_inp, lb_spk_out;
    @FXML
    private JFXTextArea explain_area;
    @FXML
    private TextArea input_text, output_text;
    @FXML
    private JFXTextField filename;
    @FXML
    private AutoCompleteTextField auto_tf = new AutoCompleteTextField();

    //Create TextToSpeech
    TextToSpeech tts = new TextToSpeech();
    final String musicFile = "sound/kcgddh.mp3";

    /**
     * Nhận các sự kiện trên thanh top
     *
     * @param event sự kiện chuột
     */
    @FXML
    private void handleTopBarAction(MouseEvent event) {
        if (event.getTarget() == btn_find) {
            top_find.setVisible(true);
            top_addandremove.setVisible(false);
            top_export.setVisible(false);
            top_author.setVisible(false);
            top_close.setVisible(false);
            top_hddict.setVisible(false);
            top_dichvb.setVisible(false);
        } else if (event.getTarget() == btn_addandremove) {
            top_find.setVisible(false);
            top_addandremove.setVisible(true);
            top_export.setVisible(false);
            top_author.setVisible(false);
            top_close.setVisible(false);
            top_hddict.setVisible(false);
            top_dichvb.setVisible(false);
        } else if (event.getTarget() == btn_export) {
            top_find.setVisible(false);
            top_addandremove.setVisible(false);
            top_export.setVisible(true);
            top_author.setVisible(false);
            top_close.setVisible(false);
            top_hddict.setVisible(false);
            top_dichvb.setVisible(false);
        } else if (event.getTarget() == btn_author) {
            top_find.setVisible(false);
            top_addandremove.setVisible(false);
            top_export.setVisible(false);
            top_author.setVisible(true);
            top_close.setVisible(false);
            top_hddict.setVisible(false);
            top_dichvb.setVisible(false);
        } else if (event.getTarget() == btn_close) {
            top_find.setVisible(false);
            top_addandremove.setVisible(false);
            top_export.setVisible(false);
            top_author.setVisible(false);
            top_close.setVisible(true);
            top_hddict.setVisible(false);
            top_dichvb.setVisible(false);
        }
    }

    /**
     * Nhận sự kiện nút Có trong thanh Close
     *
     * @param event
     */
    @FXML
    private void handleButtonYesClose(MouseEvent event) {
        if (event.getTarget() == yes_close) {
            Platform.exit();
            System.exit(0);
        }
    }

    /**
     * Nhận sự kiện nút Không trong thanh Close
     *
     * @param event
     */
    @FXML
    private void handleButtonNoClose(MouseEvent event) {
        if (event.getTarget() == no_close) {
            top_find.setVisible(false);
            top_addandremove.setVisible(false);
            top_export.setVisible(false);
            top_author.setVisible(false);
            top_close.setVisible(false);
            top_hddict.setVisible(true);
            top_dichvb.setVisible(false);
        }
    }

    /**
     * Dịch từ tiếng anh
     *
     * @param event sự kiện chuột
     */
    @FXML
    private void translateWord(MouseEvent event) {
        if (event.getTarget() == btn_translate1 || event.getTarget() == btn_translate2) {
            String m_word_target = auto_tf.getText().trim();
            String m_word_explain = DictionaryManagement.getExplainOfWord(m_word_target);
            //System.out.println("m_word_target + \t + m_word_explain");
            //System.out.println(DictionaryManagement.dictionaryLookup(m_word_target));
            //System.out.println(m_word_target + "\n" + m_word_explain);
            explain_area.clear();
            explain_area.appendText(m_word_explain);
        }
    }

    /**
     * Thêm từ vào từ điển
     *
     * @param event sự kiện chuột
     */
    @FXML
    private void AddWord(MouseEvent event) {
        if (event.getTarget() == btn_add) {
            String new_word_target = word_add.getText().trim();
            String new_word_explain = mean_add.getText().trim();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Bạn có muốn thêm từ này vào từ điển?");
            //alert.setContentText("");
            ButtonType buttonTypeYes = new ButtonType("Có", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("Không", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonTypeYes) {
                Alert noti = new Alert(Alert.AlertType.INFORMATION);
                noti.setTitle("Thông báo");
                if (new_word_target.equals("") && new_word_explain.equals("")) {
                    noti.setHeaderText("Vui lòng điền đủ thông tin từ thêm vào.");
                } else if (new_word_target.equals("")) {
                    noti.setHeaderText("Vui lòng điền từ tiếng anh cần thêm vào.");
                } else if (new_word_explain.equals("")) {
                    noti.setHeaderText("Vui lòng điền nghĩa của từ cần thêm vào.");
                } else {
                    Word _wrod = new Word();
                    _wrod.word_target = new_word_target;
                    _wrod.word_explain = new_word_explain;

                    DictionaryManagement.addWord(_wrod);
                    DictionaryManagement.updateListFromTreeSet();
                    ObservableList _list = FXCollections.observableArrayList();
                    for (String s : DictionaryManagement.copy_list) {
                        _list.add(s);
                    }
                    //auto_tf.getEntries().clear();
                    auto_tf.getEntries().addAll(_list);
                    noti.setHeaderText("Đã thêm từ vào từ điển.");
                }
                noti.show();
            } else if (result.get() == buttonTypeNo) {
                Alert noti = new Alert(Alert.AlertType.INFORMATION);
                noti.setTitle("Thông báo");
                noti.setHeaderText("Bạn đã không thêm từ này vào từ điển.");
                noti.show();
            }
        }
    }

    /**
     * Xóa từ khỏi từ điển
     *
     * @param event sự kiện chuột
     */
    @FXML
    private void RemoveWord(MouseEvent event) {
        if (event.getTarget() == btn_remove) {
            String remove_word = word_delete.getText().trim();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Bạn có muốn xóa từ này khỏi từ điển?");
            //alert.setContentText("");
            ButtonType buttonTypeYes = new ButtonType("Có", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("Không", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonTypeYes) {
                Alert noti = new Alert(Alert.AlertType.INFORMATION);
                noti.setTitle("Thông báo");
                if (remove_word.equals("")) {
                    noti.setHeaderText("Vui lòng điền từ cần xóa.");
                } else {
                    DictionaryManagement.removeWord(remove_word);
                    DictionaryManagement.updateListFromTreeSet();
                    ObservableList _list = FXCollections.observableArrayList();
                    for (String s : DictionaryManagement.copy_list) {
                        _list.add(s);
                    }
                    //auto_tf.getEntries().clear();
                    auto_tf.getEntries().addAll(_list);
                    noti.setHeaderText("Đã xóa từ " + remove_word);
                }
                noti.show();
            } else if (result.get() == buttonTypeNo) {
                Alert noti = new Alert(Alert.AlertType.INFORMATION);
                noti.setTitle("Thông báo");
                noti.setHeaderText("Bạn đã không xóa từ này khỏi từ điển.");
                noti.show();
            }

        }
    }

    /**
     * Xóa ô dịch nghĩa
     */
    @FXML
    private void clear_Text_Area() {
        if (auto_tf.getText().trim().equals("")) {
            explain_area.clear();
        }
    }

    /**
     * Xuất tất cả từ trong từ điển ra file
     *
     * @param event sự kiện chuột
     */
    @FXML
    private void exportToFile(MouseEvent event) {
        if (event.getTarget() == btn_savetofile) {
            if (!filename.getText().trim().equals("")) {
                DictionaryManagement.dictionaryExportToFile(filename.getText().trim() + ".txt");
            }
        }
    }

    /**
     * Mở tab dịch văn bản bằng api google
     *
     * @param event sự kiện chuột
     */
    @FXML
    private void openDichVanBanWindow(MouseEvent event) {
        if (event.getTarget() == btn_dichVB1 || event.getTarget() == btn_dichVB2) {
            top_dichvb.setVisible(true);
            top_find.setVisible(false);
            top_addandremove.setVisible(false);
            top_export.setVisible(false);
            top_author.setVisible(false);
            top_close.setVisible(false);
            top_hddict.setVisible(false);
        }
    }

    /**
     * Đóng tab dịch văn bản bằng api google
     *
     * @param event sự kiện chuột
     */
    @FXML
    private void closeDichVanBanWindow(MouseEvent event) {
        if (event.getTarget() == btn_back1 || event.getTarget() == btn_back2) {
            top_dichvb.setVisible(false);
            top_find.setVisible(true);
            top_addandremove.setVisible(false);
            top_export.setVisible(false);
            top_author.setVisible(false);
            top_close.setVisible(false);
            top_hddict.setVisible(false);
        }
    }

    /**
     * Hàm dịch văn bản tự động bằng api google
     */
    @FXML
    private void dichVanBan() {
        //output_text.setText(GoogleTranslate.translate("en", "vi", input_text.getText()));
        try {
            output_text.setText(GoogleTranslate.translate("en", "vi", input_text.getText()));
            System.out.println(input_text.getText());
        } catch (IOException ex) {
        }
    }

    /**
     * Hàm đọc văn bản tự động bằng api google
     *
     * @param event sự kiện chuột
     */
    @FXML
    private void docVanBan(MouseEvent event) {
        if (event.getTarget() == spk_inp || event.getTarget() == lb_spk_inp) {
            Utility.playSound(input_text.getText());
        } else if (event.getTarget() == spk_out || event.getTarget() == lb_spk_out) {
            Utility.playSound(output_text.getText());
        }
    }

    /**
     * Hàm phát âm từ tiếng anh cần dịch
     *
     * @param event sự kiện chuột
     */
    @FXML
    private void speaker_word(MouseEvent event) {
        if (event.getTarget() == btn_speaker1 || event.getTarget() == btn_speaker2) {
            tts.setVoice("cmu-rms-hsmm");

            List<String> arrayList = Arrays.asList(auto_tf.getText().trim());
            if (auto_tf.getText().trim().equals("")) {
                Utility.playSound("Không có gì để đọc hết!");
            } else {
                //Loop infinitely
                arrayList.forEach(word -> tts.speak(word, 2.0f, false, true));
            }
        }
    }

    /**
     * Ghi đè hàm khởi tạo của Initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DictionaryManagement.insertFromFile();
        DictionaryManagement.updateListFromTreeSet();
        ObservableList _list = FXCollections.observableArrayList();
        for (String s : DictionaryManagement.copy_list) {
            _list.add(s);
        }

        auto_tf.getEntries().addAll(_list);

        top_find.setVisible(false);
        top_addandremove.setVisible(false);
        top_export.setVisible(false);
        top_author.setVisible(false);
        top_close.setVisible(false);
        top_dichvb.setVisible(false);
    }

}
