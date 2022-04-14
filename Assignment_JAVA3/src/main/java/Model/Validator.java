/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JEditorPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
/**
 *
 * @author Laptop
 */
public class Validator {
     public static void validateEmpty(JTextField field, StringBuilder sb, String error){
        if(field.getText().equals("")){
            sb.append(error).append("\n");
            field.requestFocus();
        }
    }
    
    public static void validateEmpty(JRadioButton jRadioButton1,JRadioButton jRadioButton2, StringBuilder sb, String error){
        if(jRadioButton1.isSelected()== false && jRadioButton2.isSelected()== false){
            sb.append(error).append("\n");
        }
    }
    
    public static void validateEmpty(JPasswordField field, StringBuilder sb, String error){
        String password = new String(field.getPassword());
        if(password.equals("")){
            sb.append(error).append("\n");
        }
    }
    
    public static void validateEmpty(JEditorPane jep, StringBuilder sb, String error){
        if(jep.getText().equals("")){
            sb.append(error).append("\n");
        }
    }
    
    public static void parseEmail(String email,StringBuilder sb, String error) throws Exception{
        String mau = "\\w+@\\w+(\\.\\w+){1,2}";
        Pattern pattern = Pattern.compile(mau);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches() == false)
            sb.append(error).append("\n");
    }
    
    public static void checkPhone(String phone ,StringBuilder sb, String error) throws Exception{
    
        String mau = "[0-9]{10}";
        Pattern pattern = Pattern.compile(mau);
        Matcher matcher = pattern.matcher(phone);
        if(matcher.matches() == false)
            sb.append(error).append("\n");
    }
}
