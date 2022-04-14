/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Random;

/**
 *
 * @author Laptop
 */
public class RecoveryCode {
      private String code;

    public RecoveryCode() {
    }

    public RecoveryCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void recoveryCde() {
        Random random = new Random();
        int rad =99999;
         
        while (rad!=0) {      
            int result =random.nextInt(1000000);
            if(rad>100000&& rad<1000000){
                this.code=String.valueOf(random.nextInt(1000000));
                break;
            }
            rad++;
            }
    }
}
