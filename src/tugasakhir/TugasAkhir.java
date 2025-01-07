package tugasakhir;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TugasAkhir {

    static boolean isRunning = true;
    static InputStreamReader r = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(r);
    static ArrayList<Transaksi> listTransaksi = new ArrayList<>();
    static ArrayList<Produk> listProduk = new ArrayList<>();
    static {
        listProduk.add(new Produk("Facial RF", 1, 250000, "Untuk mengencangkan, mencerahkan, dan meniruskan"));
        listProduk.add(new Produk("Facial Premium White", 1, 300000, "Untuk mencerahkan, melembabkan, dan menutrisi kulit"));
        listProduk.add(new Produk("Diamond Micro Lips+Laser", 1, 230000, "Untuk mencerahkan, melembabkan, melindungi bibir"));
        listProduk.add(new Produk("Peeling Tangan", 1, 100000, "Membuat tangan menjadi lebih halus, cerah, dan kencang"));
        listProduk.add(new Produk("Peeling Kaki", 1, 120000, "Membuat kaki menjadi lebih halus, cerah, dan kencang"));
        listProduk.add(new Produk("Facial Wash", 0, 50000, "Untuk membersihkan wajah dari kotoran"));
        listProduk.add(new Produk("Moisturiser", 0, 50000, "Untuk melembabkan dan menyejukkan kulit"));
        listProduk.add(new Produk("Glow Serum", 0, 125000, "Untuk mencerahkan dan melembabkan kulit"));
        listProduk.add(new Produk("Micellar Water", 0, 60000, "Untuk membersihkan dan menghapus makeup"));
        listProduk.add(new Produk("Brightening Night Cream", 0, 110000, "Krim malam untuk mencerahkan wajah"));
    }

    
    public static void main(String[] args) throws IOException {
        do {
            showMenu();
        } while(isRunning);
    }
    
    static void showMenu() throws IOException{
        System.out.println("-------------------------------------------------------");
        System.out.println("== Selamat Datang di Aplikasi Kasir Elra Skin Clinic ==\n");  
        System.out.println("silahkan pilih menu yang tersedia :"); 
        System.out.println("[1] Lihat semua transaksi");
        System.out.println("[2] Buat transaksi");
        System.out.println("[3] Edit transaksi");
        System.out.println("[4] Hapus transaksi");
        System.out.println("[5] Hapus semua transaksi");
        System.out.println("[6] Clear");
        System.out.println("[7] Keluar");
        System.out.println("-------------------------------------------------------");
        int selectedMenu = Validation.number("Pilih menu>> ");
        switch(selectedMenu) {
            case 1:
                showAllTransaksi();
                break;
            case 2:
                buatTransaksi();
                break;
            case 3:
                editTransaksi();
                break;
            case 4:
                hapusTransaksi();
                break;
            case 5:
                hapusAll();
                break;
            case 6:
                clearConsole();
                break;
            case 7:
                keluar();
                break;
            case 8:
                dataDummy();
                break;
            default:
                System.out.println("Pilihan salah!");
        }
    }
    
    public static void dataDummy(){
        listTransaksi.add(new Transaksi("Omori", 0, 1, "12-08-2024"));
        listTransaksi.add(new Transaksi("Hamsky", 1, 1, "02-12-2024"));
        listTransaksi.add(new Transaksi("Black Dark", 5, 3, "01-11-2024"));
        listTransaksi.add(new Transaksi("Kaisel", 1, 2, "03-01-2025"));
        listTransaksi.add(new Transaksi("Benjod", 9, 2, "21-05-2024"));
        listTransaksi.add(new Transaksi("Aspire", 3, 1, "30-09-2024"));
        listTransaksi.add(new Transaksi("Nana", 6, 1, "23-09-2024"));
        listTransaksi.add(new Transaksi("Vina", 8, 1, "12-08-2024"));

    }
    
    public static void showAllTransaksi(){
        if (!listTransaksi.isEmpty()) {
            
            int itemsPerPage = 3;
            int totalPage = (int)Math.ceil(listTransaksi.size() / (double)itemsPerPage);
            Scanner scanner = new Scanner(System.in);
            
            int index = 0;
            int allTotalHarga = 0;
            int allDiskon = 0;
            int allTotalBayar = 0;
            
            System.out.println();
            System.out.println("Riwayat Transaksi   :");
            for (int pageAktif = 1; pageAktif <= totalPage; pageAktif++) {
                
                int subTotalHarga = 0;
                int subDiskon = 0;
                int subTotalBayar = 0;
                
                System.out.println("Hal "+pageAktif);
                System.out.println("+ - -+ - - - - - - - + - - - - - - - - - - - - -+ - - - - - + - - - - - - -+ - - - -+ - - - - - - + - - - - - + - - - - - - + - - - - - -+");
                System.out.println("| No | Nama Pelangan |     Produk/Treatment     |   Type    | Harga Satuan | Jumlah | Total Harga |  Diskon   | Total Bayar |   Tanggal  |");
                System.out.println("+ - -+ - - - - - - - + - - - - - - - - - - - - -+ - - - - - + - - - - - - -+ - - - -+ - - - - - - + - - - - - + - - - - - - + - - - - - -+");
                
                for (int i = (pageAktif-1)*itemsPerPage; i < Math.min(pageAktif*itemsPerPage, listTransaksi.size()); i++) {
                    
                    Transaksi row = listTransaksi.get(i);
                    index++;
                    
                    String produk = listProduk.get(row.getIdProduk()).getNama();
                    String type = (listProduk.get(row.getIdProduk()).getType()==0)?"Produk":"Treatment";
                    int harga = listProduk.get(row.getIdProduk()).getHarga();
                    int totalHarga = row.getJumlah()*harga;
                    int diskon = countDiskon(totalHarga);
                    int totalBayar = totalHarga-diskon;
                    
                    subTotalHarga += totalHarga;
                    subDiskon += diskon;
                    subTotalBayar += totalBayar;
                    
                    allTotalHarga += totalHarga;
                    allDiskon += diskon;
                    allTotalBayar += totalBayar;
                    
                    System.out.printf("| %-2d | %-13s | %-24s | %-9s | %-12d | %-6d | %-11d | %-9d | %-11d | %-10s |%n",
                        index, row.getNama(), produk, type, harga, row.getJumlah(), totalHarga, diskon, totalBayar, row.getDate()); 
                
                }
                System.out.println("+ - -+ - - - - - - - + - - - - - - - - - - - - -+ - - - - - + - - - - - - -+ - - - -+ - - - - - - + - - - - - + - - - - - - + - - - - - -+");
                System.out.printf("|                     Subtotal hal %d                                                | %-11d | %-9d | %-24d |%n",
                        pageAktif, subTotalHarga, subDiskon, subTotalBayar);
                System.out.println("+ - -+ - - - - - - - + - - - - - - - - - - - - -+ - - - - - + - - - - - - -+ - - - -+ - - - - - - + - - - - - + - - - - - - + - - - - - -+");
                
                if (pageAktif < totalPage) {
                    System.out.print("Tekan Enter untuk melihat halaman berikutnya...");
                    scanner.nextLine();
                    System.out.println();
                }
            }
            System.out.printf("|                     Subtotal keseluruhan                                          | %-11d | %-9d | %-24d |%n",
                    allTotalHarga, allDiskon, allTotalBayar);
            System.out.println("+ - -+ - - - - - - - + - - - - - - - - - - - - -+ - - - - - + - - - - - - -+ - - - -+ - - - - - - + - - - - - + - - - - - - + - - - - - -+");
            System.out.println();
            
        } else {
            System.out.println();
            System.err.println("-- Belum ada riwayat transaksi --");
            System.out.println();
        }
    }
    
    public static void buatTransaksi() throws IOException{
        boolean ulang = true;
        do {

            System.out.println();
            try {
                String nama = Validation.emptyInput("Masukan nama pelangan : ");
                System.out.println();
                printProduk();
                System.out.println();
                int idProduk = Validation.minMaxNumber("Pilih produk/treatment yang dibeli [1-10] : ", 1, 10)-1;
                int jumlah = Validation.number("Berapa jumlahnya? : ");
                String tanggal = getInputTanggal();

                listTransaksi.add(new Transaksi(nama, idProduk, jumlah, tanggal));
                System.out.println("Transaksi berhasil ditambahkan");

            }catch (Exception e) {
                System.err.println("Error : " + e.getMessage());
            }

            System.out.println();
            
            String jawab = Validation.emptyInput("Buat Transaksi lagi? (y/t) : ");
            ulang = (jawab.equalsIgnoreCase("y"));
            
        } while (ulang);
    }
    
    static void editTransaksi() throws IOException{
        if (!listTransaksi.isEmpty()) {
            
            showAllTransaksi();
            OUTER:
            while(true){
                int index = Validation.number("Pilih nomor transaksi : ")-1;
                if (index < listTransaksi.size() && index >= 0) {
                    Transaksi rowEdit = listTransaksi.get(index);
                    try {
                        System.out.println("-- Pilih kolom yang ingin diubah --"
                                + "\n1 = Nama               : "+rowEdit.getNama()
                                + "\n2 = Produk/Treatment   : "+listProduk.get(rowEdit.getIdProduk()).getNama()
                                + "\n3 = Jumlah             : "+rowEdit.getJumlah()
                                + "\n4 = Tanggal            : "+rowEdit.getDate());
                        int indexKolom = Validation.minMaxNumber("Pilih kolom (1-4) : ", 1, 4)-1;
                        switch (indexKolom){
                            case 0:
                                String newNama = Validation.emptyInput("Masukan nama : ");
                                rowEdit.editNama(newNama);
                                System.out.println("Data berhasil diubah");
                                break OUTER;
                            case 1:
                                printProduk();
                                int newProduk = Validation.minMaxNumber("Masukan kode produk/treatment : ", 1, listProduk.size())-1;
                                rowEdit.editIdProduk(newProduk);
                                System.out.println("Data berhasil diubah");
                                break OUTER;
                            case 2:
                                int newJumlah = Validation.number("Masukan jumlah baru : ");
                                rowEdit.editJumlah(newJumlah);
                                System.out.println("Data berhasil diubah");
                                break OUTER;
                            case 3:
                                String newDate = getInputTanggal();
                                rowEdit.editDate(newDate);
                                System.out.println("Data berhasil diubah");
                                break OUTER;
                            default:
                                break;
                        }
                    } catch (Exception e) {
                    }
                } else {
                    System.out.println("Nomor tidak ditemukan!");
                }
            }
            
        } else {
            System.out.println();
            System.err.println("-- Belum ada riwayat transaksi --");
            System.out.println();
        }
    }
    
    static void hapusTransaksi() throws IOException{
        if (!listTransaksi.isEmpty()){
           showAllTransaksi();
           while (true) {
                int index = Validation.number("Pilih nomor untuk dihapus : ")-1;
                if (index < listTransaksi.size() && index >= 0) {
                    
                    String jawab = Validation.emptyInput("Apakah yakin ingin menghapus data pelangan "+listTransaksi.get(index).getNama()+" (y/t) : ");
                    boolean konfirmasi = (jawab.equalsIgnoreCase("y"));
                    
                    if (konfirmasi) {
                        listTransaksi.remove(index);
                        System.out.println("Berhasil menghapus data");   
                    } else {
                        System.err.println("Gagal menghapus data");
                    }
                    break;
                    
                } else {
                    System.out.println("Nomor tidak ditemukan!");
                }
           }
        } else {
            System.out.println();
            System.err.println("- Belum ada data -");
            System.out.println();
        }
    }
    
    public static void hapusAll() throws IOException {
        if (!listTransaksi.isEmpty()) {
            String jawab = Validation.emptyInput("Apakah yakin ingin menghapus semua data (y/t) : ");
            boolean konfirmasi = (jawab.equalsIgnoreCase("y"));

            if (konfirmasi) {
                listTransaksi.clear();
                System.out.println("Berhasil menghapus data");   
            } else {
                System.err.println("Gagal menghapus data");
            }
        } else {
            System.out.println();
            System.err.println("-- Data sudah kosong, tidak perlu dihapus --");
            System.out.println();
        }
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
    
    public static void keluar() throws IOException{
        System.out.println();
        String jawab = Validation.emptyInput("Apakah anda yakin ingin keluar? (y/t) : ");
        boolean konfirmasi = (jawab.equalsIgnoreCase("y"));
        if (konfirmasi) {
            isRunning = false;
        }
        System.out.println();
    }
    
    public static void printProduk(){
        int index = 0;
        System.out.println("Daftar produk dan treatment kami :");
        System.out.println("+ - - -+- - - - - - - - - - - - - + - - - - - + - - - - - + - - - - - - - - - - - - - - - - - - - - - - - - - - - -+");
        System.out.println("| Kode |       Nama  Produk       |   Type    |   Harga   |                       Keterangan                       |");
        System.out.println("+ - - -+- - - - - - - - - - - - - + - - - - - + - - - - - + - - - - - - - - - - - - - - - - - - - - - - - - - - - -+");
        for (Produk p : listProduk){
            index++;
            String type = (p.getType()==0) ? "Produk" : "Treatment";
            System.out.printf("| %-4d | %-24s | %-9s | Rp %-6d | %-54s |%n", index, p.getNama(), type, p.getHarga(), p.getKeterangan());
        }
        System.out.println("+ - - -+- - - - - - - - - - - - - + - - - - - + - - - - - + - - - - - - - - - - - - - - - - - - - - - - - - - - - -+");
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
    
}
