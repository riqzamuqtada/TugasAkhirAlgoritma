package tugasakhir;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class TugasAkhir {

    static boolean isRunning = true;
    static boolean menuAktif;
    static InputStreamReader r = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(r);
    static ArrayList<Transaksi> listTransaksi = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        do {
            mainMenu();
        } while(isRunning);
    }
    
    static void mainMenu() throws IOException{
        Utility.clearConsole();
        System.out.println("-------------------------------------------------------");
        System.out.println("== Selamat Datang di Aplikasi Kasir Elra Skin Clinic ==\n");  
        System.out.println("silahkan pilih menu yang tersedia :");
        System.out.println("[1] Kelola Transaksi");
        System.out.println("[2] Kelola Produk");
        System.out.println("[3] Keluar");
        System.out.println("-------------------------------------------------------");
        int selectedMenu = Utility.number("Pilih menu>> ");
        switch(selectedMenu) {
            case 1:
                menuAktif = true;
                menuTransaksi();
                break;
            case 2:
                menuAktif = true;
                menuProduk();
                break;
            case 3:
                keluar();
                break;
            default:
                System.err.println("Pilih menu yang tersedia!");
        }
    }
    
    static void menuTransaksi() throws IOException{
        do {
            
            System.out.println("-------------------------------------------------------");
            System.out.println("===== Menu Transaksi =====\n");  
            System.out.println("[1] Lihat semua transaksi");
            System.out.println("[2] Buat transaksi");
            System.out.println("[3] Edit transaksi");
            System.out.println("[4] Hapus transaksi");
            System.out.println("[5] Hapus semua transaksi");
            System.out.println("[6] Cetak struk");
            System.out.println("[7] Clear");
            System.out.println("[8] Kembali");
            System.out.println("-------------------------------------------------------");
            int selectedMenu = Utility.number("Pilih menu>> ");
            switch(selectedMenu) {
                case 1:
                    KelolaTransaksi.showAll();
                    break;
                case 2:
                    KelolaTransaksi.create();
                    break;
                case 3:
                    KelolaTransaksi.update();
                    break;
                case 4:
                    KelolaTransaksi.delete();
                    break;
                case 5:
                    KelolaTransaksi.deleteAll();
                    break;
                case 6:
                    KelolaTransaksi.cetakStruk();
                    break;
                case 7:
                    Utility.clearConsole();
                    break;
                case 8:
                    menuAktif = false;
                    break;
                case 9:
                    dataDummy();
                    break;
                default:
                    System.err.println("Pilih menu yang tersedia!");
            }
            
        } while (menuAktif);
    }
    
    static void menuProduk() throws IOException{
        do {
            
            System.out.println("-------------------------------------------------------");
            System.out.println("===== Menu Produk =====\n");  
            System.out.println("[1] Lihat semua produk");
            System.out.println("[2] Buat produk");
            System.out.println("[3] Edit produk");
            System.out.println("[4] Hapus produk");
            System.out.println("[5] Hapus semua produk");
            System.out.println("[6] Clear");
            System.out.println("[7] Kembali");
            System.out.println("-------------------------------------------------------");
            int selectedMenu = Utility.number("Pilih menu>> ");
            switch(selectedMenu) {
                case 1:
                    KelolaProduk.showAll();
                    break;
                case 2:
                    KelolaProduk.create();
                    break;
                case 3:
                    KelolaProduk.update();
                    break;
                case 4:
                    KelolaProduk.delete();
                    break;
                case 5:
                    KelolaProduk.deleteAll();
                    break;
                case 6:
                    Utility.clearConsole();
                    break;
                case 7:
                    menuAktif = false;
                    break;
                case 8:
                    dataDummy();
                    break;
                default:
                    System.err.println("Pilih menu yang tersedia!");
            }
            
        } while (menuAktif);
    }
    
    public static void keluar() throws IOException{
        System.out.println();
        String jawab = Utility.emptyInput("Apakah anda yakin ingin keluar? (y/t) : ");
        boolean konfirmasi = (jawab.equalsIgnoreCase("y"));
        if (konfirmasi) {
            isRunning = false;
        }
        System.out.println();
    }
    
    public static void dataDummy(){
        listTransaksi.add(new Transaksi("Omori", 0, 1, "12-08-2024"));
        listTransaksi.add(new Transaksi("Hamsky", 1, 1, "02-12-2024"));
        listTransaksi.add(new Transaksi("Black Dark", 5, 3, "01-11-2024"));
        listTransaksi.add(new Transaksi("Kaisel", 1, 2, "03-01-2025"));
        listTransaksi.add(new Transaksi("Sopia", 9, 2, "21-05-2024"));
        listTransaksi.add(new Transaksi("Aspire", 3, 1, "30-09-2024"));
        listTransaksi.add(new Transaksi("Nana", 6, 1, "23-09-2024"));
        listTransaksi.add(new Transaksi("Vina", 8, 1, "12-08-2024"));

    }
    
}
