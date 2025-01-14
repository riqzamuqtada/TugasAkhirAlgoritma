package tugasakhir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import static tugasakhir.TugasAkhir.input;

public class Utility {
    
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
    
    public static int countDiskon(int totalHarga){
        if (totalHarga > 100000 && totalHarga <= 300000) {
            return totalHarga*5/100;
        } else if (totalHarga > 300000 && totalHarga <= 500000) {
            return totalHarga*1/10;
        } else if (totalHarga > 500000) {
            return totalHarga*15/100;
        } else {
            return 0;
        }
    }
    
    public static String countPersentaseDiskon(int totalHarga){
        if (totalHarga > 100000 && totalHarga <= 300000) {
            return "5%";
        } else if (totalHarga > 300000 && totalHarga <= 500000) {
            return "10%";
        } else if (totalHarga > 500000) {
            return "15%";
        } else {
            return "0%";
        }
    }
    
    public static String getInputTanggal() {
        Scanner scanner = new Scanner(System.in);
        LocalDate tanggal = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (tanggal == null) {
            System.out.print("Masukkan tanggal (format: dd-MM-yyyy) atau tekan Enter untuk menggunakan tanggal hari ini: ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                tanggal = LocalDate.now();
            } else {
                try {
                    tanggal = LocalDate.parse(input, formatter);
                } catch (DateTimeParseException e) {
                    System.err.println("Format tanggal salah! Harap masukkan dalam format dd-MM-yyyy.");
                }
            }
        }
        return tanggal.format(formatter);
    }
    
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void printStruk(Transaksi data) {
        
        Produk p = KelolaProduk.listProduk.get(data.getIdProduk());
        int totalHarga = p.getHarga()*data.getJumlah();
        int totalBayar = totalHarga - countDiskon(totalHarga);
        
        System.out.println("\n+ - - - - - - - - - - - - - - - - - - - - - +");
        System.out.println("|               STRUK PEMBELIAN             |");
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - +");
        System.out.printf("| %-15s : %-20s    |\n", "Tanggal", data.getDate());
        System.out.printf("| %-15s : %-20s    |\n", "Nama Pelanggan", data.getNama());
        System.out.printf("| %-15s : %-20s    |\n", "Nama Produk", p.getNama());
        System.out.printf("| %-15s : Rp %-20d |\n", "Harga Produk", p.getHarga());
        System.out.printf("| %-15s : %-20d    |\n", "Jumlah", data.getJumlah());
        System.out.printf("| %-15s : %-20s    |\n", "Diskon ", countPersentaseDiskon(totalHarga));
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - +");
        System.out.printf("| %-15s : Rp %-20d |\n", "Total Harga", totalHarga);
        System.out.printf("| %-15s : RP %-20d |\n", "Potongan Diskon", countDiskon(totalHarga));
        System.out.printf("| %-15s : Rp %-20d |\n", "Total Bayar", totalBayar);
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - +");
        System.out.println("|      Terima kasih atas pembelian Anda!    |");
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - +");
    }

    public static void simpanStruk(Transaksi data) throws IOException {
        
        String fileName = data.getNama()+System.currentTimeMillis()+".txt";
        File folder = new File("print_struk");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder, fileName);
        FileWriter writer = new FileWriter(file);
        Produk p = KelolaProduk.listProduk.get(data.getIdProduk());
        int totalHarga = p.getHarga()*data.getJumlah();
        int totalBayar = totalHarga-countDiskon(totalHarga);

        
        writer.write("+ - - - - - - - - - - - - - - - - - - - - - +\n");
        writer.write("|              STRUK PEMBELIAN              |\n");
        writer.write("+ - - - - - - - - - - - - - - - - - - - - - +\n");
        writer.write(String.format("| %-15s : %-20s    |\n", "Tanggal", data.getDate()));
        writer.write(String.format("| %-15s : %-20s    |\n", "Nama Pelanggan", data.getNama()));
        writer.write(String.format("| %-15s : %-20s    |\n", "Nama Produk", p.getNama()));
        writer.write(String.format("| %-15s : Rp %-20d |\n", "Harga Produk", p.getHarga()));
        writer.write(String.format("| %-15s : %-20d    |\n", "Jumlah", data.getJumlah()));
        writer.write(String.format("| %-15s : %-20s    |\n", "Diskon ", countPersentaseDiskon(totalHarga)));
        writer.write("+ - - - - - - - - - - - - - - - - - - - - - +\n");
        writer.write(String.format("| %-15s : Rp %-20d |\n", "Total Harga", totalHarga));
        writer.write(String.format("| %-15s : Rp %-20d |\n", "Potongan Diskon", countDiskon(totalHarga)));
        writer.write(String.format("| %-15s : Rp %-20d |\n", "Total Bayar", totalBayar));
        writer.write("+ - - - - - - - - - - - - - - - - - - - - - +\n");
        writer.write("|      Terima kasih atas pembelian Anda!    |\n");
        writer.write("+ - - - - - - - - - - - - - - - - - - - - - +");

        writer.close();

    }
    
}
