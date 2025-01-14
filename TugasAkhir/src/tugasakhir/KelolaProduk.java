package tugasakhir;

import java.io.IOException;
import java.util.ArrayList;

public class KelolaProduk {
    
    static ArrayList<Produk> listProduk = new ArrayList<>();
    static {
        listProduk.add(new Produk("Facial RF", 1, 250000, 1, "Untuk mengencangkan, mencerahkan, dan meniruskan"));
        listProduk.add(new Produk("Facial Premium White", 1, 300000, 1, "Untuk mencerahkan, melembabkan, dan menutrisi kulit"));
        listProduk.add(new Produk("Diamond Micro Lips+Laser", 1, 230000, 1, "Untuk mencerahkan, melembabkan, melindungi bibir"));
        listProduk.add(new Produk("Peeling Tangan", 1, 100000, 1, "Membuat tangan menjadi lebih halus, cerah, dan kencang"));
        listProduk.add(new Produk("Peeling Kaki", 1, 120000, 1, "Membuat kaki menjadi lebih halus, cerah, dan kencang"));
        listProduk.add(new Produk("Facial Wash", 0, 50000, 53, "Untuk membersihkan wajah dari kotoran"));
        listProduk.add(new Produk("Moisturiser", 0, 50000, 28, "Untuk melembabkan dan menyejukkan kulit"));
        listProduk.add(new Produk("Glow Serum", 0, 125000, 10, "Untuk mencerahkan dan melembabkan kulit"));
        listProduk.add(new Produk("Micellar Water", 0, 60000, 40, "Untuk membersihkan dan menghapus makeup"));
        listProduk.add(new Produk("Brightening Night Cream", 0, 110000, 0, "Krim malam untuk mencerahkan wajah"));
    }
    
    public static void showAll(){
        System.out.println();
        int index = 0;
        System.out.println("Daftar produk dan treatment kami :");
        System.out.println("+ - - -+- - - - - - - - - - - - - + - - - - - + - - -+ - - - - - + - - - - - - - - - - - - - - - - - - - - - - - - - - - -+");
        System.out.println("| Kode |       Nama  Produk       |   Type    | Stok |   Harga   |                       Keterangan                       |");
        System.out.println("+ - - -+- - - - - - - - - - - - - + - - - - - + - - -+ - - - - - + - - - - - - - - - - - - - - - - - - - - - - - - - - - -+");
        for (Produk p : listProduk){
            index++;
            String type = (p.getType()==0) ? "Produk" : "Treatment";
            System.out.printf("| %-4d | %-24s | %-9s | %-4d | Rp %-6d | %-54s |%n", index, p.getNama(), type, p.getStok(), p.getHarga(), p.getKeterangan());
        }
        System.out.println("+ - - -+- - - - - - - - - - - - - + - - - - - + - - -+ - - - - - + - - - - - - - - - - - - - - - - - - - - - - - - - - - -+");
        System.out.println();
    }
    
    public static void create() throws IOException {
        boolean ulang = true;
        do {
            
            System.out.println();
            
            try {
                
                String nama = Utility.emptyInput("Masukan nama Produk/Treatment : ");
                int type = Utility.minMaxNumber("Masukan type produk = 0 / treatment = 1 : ", 0, 1);
                int harga = Utility.number("Masukan harga produk/treatment : ");
                int stok = 1;
                if (type == 0) {
                    stok = Utility.number("Masukan stok produk : ");
                }
                String keterangan = Utility.emptyInput("Berikan keterangan : ");
                listProduk.add(new Produk(nama, type, harga, stok, keterangan));
                String type2 = (type==0)?"Produk":"Treatment"; 
                System.out.println("-- "+type2+" berhasil ditambahkan --");
                
            } catch (Exception e) {
                System.err.println("Error : " + e.getMessage());
            }
            
            System.out.println();
            String jawab = Utility.emptyInput("Buat Transaksi lagi? (y/t) : ");
            ulang = (jawab.equalsIgnoreCase("y"));
            
        } while (ulang);
    }
    
    static void update(){
        if (!listProduk.isEmpty()) {
            
            showAll();
            OUTER:
            while(true){
                int index = Utility.number("Pilih nomor produk/treatment : ")-1;
                if (index < listProduk.size() && index >= 0) {
                    Produk rowEdit = listProduk.get(index);
                    String type = (rowEdit.getType()==0)?"Produk":"Treatment";
                    try {
                        System.out.println("-- Pilih kolom yang ingin diubah --"
                                + "\n1 = Nama               : "+rowEdit.getNama()
                                + "\n2 = Type               : "+type
                                + "\n3 = Harga              : "+rowEdit.getHarga()
                                + "\n4 = Stok               : "+rowEdit.getStok()
                                + "\n5 = Keterangan         : "+rowEdit.getKeterangan()
                        );
                        int indexKolom = Utility.minMaxNumber("Pilih kolom (1-5) : ", 1, 5)-1;
                        switch (indexKolom){
                            case 0:
                                String newNama = Utility.emptyInput("Masukan nama : ");
                                rowEdit.editNama(newNama);
                                System.out.println("-- Data berhasil diubah --");
                                break OUTER;
                            case 1:
                                int newType = Utility.minMaxNumber("Masukan type produk = 0 / treatment = 1 : ", 0, 1);
                                if (rowEdit.getType() == newType) {
                                    System.out.println("Type tidak dapat diubah, karena sama"); 
                                } else {
                                    rowEdit.editType(newType);
                                    System.out.println("-- Data berhasil diubah --");
                                }
                                break OUTER;
                            case 2:
                                int newHarga = Utility.number("Masukan harga baru : ");
                                rowEdit.editHarga(newHarga);
                                System.out.println("-- Data berhasil diubah --");
                                break OUTER;
                            case 3:
                                int newStok = Utility.number("Masukan stok baru : ");
                                rowEdit.editStok(newStok);
                                System.out.println("-- Data berhasil diubah --");
                                break OUTER;
                            case 4:
                                String newKeterangan = Utility.emptyInput("Masukan keterangan baru : ");
                                rowEdit.editKeterangan(newKeterangan);
                                System.out.println("-- Data berhasil diubah --");
                                break OUTER;
                            default:
                                break;
                        }
                    } catch (Exception e) {
                    }
                } else {
                    System.err.println("-- Nomor tidak ditemukan! --");
                }
            }
            System.out.println();
            
        } else {
            System.out.println();
            System.err.println("-- Belum ada produk/treatment --");
            System.out.println();
        }
    }
    
    static void delete() throws IOException{
        if (!listProduk.isEmpty()){
           showAll();
           while (true) {
                int index = Utility.number("Pilih nomor untuk dihapus : ")-1;
                if (index < listProduk.size() && index >= 0) {
                    
                    String jawab = Utility.emptyInput("Apakah yakin ingin menghapus data produk/treatment "+listProduk.get(index).getNama()+" (y/t) : ");
                    boolean konfirmasi = (jawab.equalsIgnoreCase("y"));
                    
                    if (konfirmasi) {
                        listProduk.remove(index);
                        System.out.println("-- Berhasil menghapus data --");   
                    } else {
                        System.err.println("-- Gagal menghapus data --");
                    }
                    break;
                    
                } else {
                    System.err.println("-- Nomor tidak ditemukan! --");
                }
           }
        } else {
            System.out.println();
            System.err.println("-- Belum ada data --");
            System.out.println();
        }
    }
    
    public static void deleteAll() throws IOException {
        if (!listProduk.isEmpty()) {
            String jawab = Utility.emptyInput("Apakah yakin ingin menghapus semua data produk? (y/t) : ");
            boolean konfirmasi = (jawab.equalsIgnoreCase("y"));

            if (konfirmasi) {
                listProduk.clear();
                System.out.println("-- Berhasil menghapus data --");   
            } else {
                System.err.println("-- Gagal menghapus data --");
            }
        } else {
            System.out.println();
            System.err.println("-- Data sudah kosong, tidak perlu dihapus --");
            System.out.println();
        }
    }
    
}
