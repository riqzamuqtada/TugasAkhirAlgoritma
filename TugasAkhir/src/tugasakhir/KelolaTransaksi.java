package tugasakhir;

import java.io.IOException;
import java.util.Scanner;
import static tugasakhir.TugasAkhir.listTransaksi;
import static tugasakhir.KelolaProduk.listProduk;

public class KelolaTransaksi {
    
    public static void showAll(){
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
                    int diskon = Utility.countDiskon(totalHarga);
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
            System.out.printf("|                         Total keseluruhan                                         | %-11d | %-9d | %-24d |%n",
                    allTotalHarga, allDiskon, allTotalBayar);
            System.out.println("+ - -+ - - - - - - - + - - - - - - - - - - - - -+ - - - - - + - - - - - - -+ - - - -+ - - - - - - + - - - - - + - - - - - - + - - - - - -+");
            System.out.println();
            
        } else {
            System.out.println();
            System.err.println("-- Belum ada riwayat transaksi --");
            System.out.println();
        }
    }
    
    public static void create() throws IOException{
        boolean ulang = true;
        do {

            System.out.println();
            try {
                String nama = Utility.emptyInput("Masukan nama pelangan : ");
                System.out.println();
                KelolaProduk.showAll();
                System.out.println();
                
                while (ulang) {
                    int idProduk = Utility.minMaxNumber("Pilih produk/treatment yang dibeli [1-"+listProduk.size()+"] : ", 1, listProduk.size())-1;
                    Produk p = listProduk.get(idProduk);
                    if (p.getType() == 0) {
                        if (p.getStok() > 0) {
                            while (ulang) {
                                int jumlah = Utility.number("Berapa jumlahnya? : ");
                                if (p.getStok() > jumlah) {
                                    String tanggal = Utility.getInputTanggal();
                                    listTransaksi.add(new Transaksi(nama, idProduk, jumlah, tanggal));
                                    p.editStok(p.getStok()-jumlah);
                                    System.out.println("Transaksi berhasil ditambahkan");
                                    cetakStrukLangsung(new Transaksi(nama, idProduk, jumlah, tanggal));
                                    ulang = false;
                                } else {
                                    System.err.println("Stok produk tidak mecukupi!");
                                }
                            }
                        } else {
                            System.err.println("Stok produk/treatment yang dibeli kosong!");
                        }
                    } else {
                        int jumlah = Utility.number("Berapa jumlahnya? : ");
                        String tanggal = Utility.getInputTanggal();
                        listTransaksi.add(new Transaksi(nama, idProduk, jumlah, tanggal));
                        System.out.println("Transaksi berhasil ditambahkan");
                        cetakStrukLangsung(new Transaksi(nama, idProduk, jumlah, tanggal));
                        ulang = false;
                    }
                }

            }catch (Exception e) {
                System.err.println("Error : " + e.getMessage());
            }

            System.out.println();
            
            String jawab = Utility.emptyInput("Buat Transaksi lagi? (y/t) : ");
            ulang = (jawab.equalsIgnoreCase("y"));
            
        } while (ulang);
    }
    
    static void update() throws IOException{
        if (!listTransaksi.isEmpty()) {
            
            showAll();
            OUTER:
            while(true){
                int index = Utility.number("Pilih nomor transaksi : ")-1;
                if (index < listTransaksi.size() && index >= 0) {
                    Transaksi rowEdit = listTransaksi.get(index);
                    try {
                        System.out.println("-- Pilih kolom yang ingin diubah --"
                                + "\n1 = Nama               : "+rowEdit.getNama()
                                + "\n2 = Produk/Treatment   : "+listProduk.get(rowEdit.getIdProduk()).getNama()
                                + "\n3 = Jumlah             : "+rowEdit.getJumlah()
                                + "\n4 = Tanggal            : "+rowEdit.getDate());
                        int indexKolom = Utility.minMaxNumber("Pilih kolom (1-4) : ", 1, 4)-1;
                        switch (indexKolom){
                            case 0:
                                String newNama = Utility.emptyInput("Masukan nama : ");
                                rowEdit.editNama(newNama);
                                System.out.println("Data berhasil diubah");
                                break OUTER;
                            case 1:
                                KelolaProduk.showAll();
                                int newProduk = Utility.minMaxNumber("Masukan kode produk/treatment : ", 1, listProduk.size())-1;
                                rowEdit.editIdProduk(newProduk);
                                System.out.println("Data berhasil diubah");
                                break OUTER;
                            case 2:
                                int newJumlah = Utility.number("Masukan jumlah baru : ");
                                rowEdit.editJumlah(newJumlah);
                                System.out.println("Data berhasil diubah");
                                break OUTER;
                            case 3:
                                String newDate = Utility.getInputTanggal();
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
    
    static void delete() throws IOException{
        if (!listTransaksi.isEmpty()){
           showAll();
           while (true) {
                int index = Utility.number("Pilih nomor untuk dihapus : ")-1;
                if (index < listTransaksi.size() && index >= 0) {
                    
                    String jawab = Utility.emptyInput("Apakah yakin ingin menghapus data pelangan "+listTransaksi.get(index).getNama()+" (y/t) : ");
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
    
    public static void deleteAll() throws IOException {
        if (!listTransaksi.isEmpty()) {
            String jawab = Utility.emptyInput("Apakah yakin ingin menghapus semua data transaksi? (y/t) : ");
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
    
    public static void cetakStrukLangsung(Transaksi data) throws IOException{
        String cetak = Utility.emptyInput("Cetak struk? (y/t) : ");
        boolean jawab = (cetak.equalsIgnoreCase("y"));
        if (jawab) {
            Utility.printStruk(data);
            Utility.simpanStruk(data);
            System.out.println("Struk berhasil dicetak dan disimpan");
        } else {
            System.err.println("Struk gagal dicetak dan disimpan");
        }
    }
    
    public static void cetakStruk() throws IOException{
        if (!listTransaksi.isEmpty()) {
            showAll();
            int index = Utility.number("Pilih nomor untuk dicetak : ")-1;
            Utility.printStruk(listTransaksi.get(index));
            Utility.simpanStruk(listTransaksi.get(index));
            System.out.println("Struk berhasil dicetak dan disimpan");
        } else {
            System.out.println();
            System.err.println("-- Belum ada data --");
            System.out.println(); 
        }
    }
    
}
