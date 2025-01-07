package tugasakhir;

import java.io.IOException;
import static tugasakhir.TugasAkhir.input;

public class Validation {
    
    public static String emptyInput(String message) throws IOException {
        String value;
        do {
            System.out.print(message);
            value = input.readLine();
            
            if (value == null || value.trim().isEmpty()) {
                System.err.println("Input tidak boleh kosong. Silakan coba lagi.");
            }
        } while (value == null || value.trim().isEmpty());
        
        return value;
    }
    
    public static int number(String message) {
        while (true) {
            try {
                System.out.print(message);
                int number = Integer.parseInt(input.readLine());
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Input tidak dapat kurang dari 1");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Harap masukkan angka.");
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
    }
    
    public static int minMaxNumber(String message, int min, int max) throws Exception {
        int value;

        while (true) {
            try {
                System.out.print(message);
                value = Integer.valueOf(input.readLine());

                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Masukkan angka antara " + min + " hingga " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka antara " + min + " hingga " + max + ".");
            } 
        }
    }
    
}
