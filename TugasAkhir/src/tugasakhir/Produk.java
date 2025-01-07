package tugasakhir;

public class Produk {
    
    private String namaP;
    private int type;
    private int harga;
    private String keterangan;
    
    public Produk(String namaP, int type,int harga, String keterangan){
        this.namaP      = namaP;
        this.type       = type;
        this.harga      = harga;
        this.keterangan = keterangan;
    }
    
    public String getNama() { return namaP; }
    public int getType() { return type; }
    public int getHarga() { return harga; }
    public String getKeterangan() { return keterangan; }
    
}
