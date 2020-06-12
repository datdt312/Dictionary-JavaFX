/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionarythemeapplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Chương trình chuyển đổi file fxml cũ thành file fxml đúng chuẩn để có thể
 * chạy đc
 *
 * @author Hope
 */
public class convertEditableThemeToRunableTheme {
    
    /**
     * Hàm chuyển đổi
     * @return kết quả chuyển đổi [ true: thành công, false: thất bại]
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static boolean convertEditableToRunable() throws FileNotFoundException, IOException {
        FileReader inputFile = new FileReader("editable_theme.fxml");
        File file = new File("runable_theme.fxml");
        file.setWritable(true);
        FileWriter fout = new FileWriter(file);
        Scanner sc = new Scanner(inputFile);
        //BufferedWriter fout = new BufferedWriter(fileWriter);
        String line;
        int d = 0;
        while (sc.hasNextLine()) {
            d++;

            line = sc.nextLine();
            if (d == 3) {
                fout.write("\n<?import dictionarythemeapplication.AutoCompleteTextField?>\n");
            } else {
                fout.write(line + "\n");
            }
            fout.flush();
        }
        fout.write("</AnchorPane>");
        
        return true;
    }
}
