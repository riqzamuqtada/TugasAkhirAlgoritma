# Aplikasi Kasir Sederhana

Aplikasi Kasir Sederhana adalah program berbasis console yang dibuat menggunakan bahasa pemrograman Java. Proyek ini dibuat untuk memenuhi tugas akhir mata kuliah Algoritma dan Pemrograman. Aplikasi ini dirancang untuk membantu proses transaksi sederhana pada sebuah toko atau usaha kecil.

## Fitur

- Kelola Transaksi
- Kelola Produk
- Cetak Struk

## Teknologi yang Digunakan

- **Bahasa Pemrograman:** Java
- **Lingkungan Pengembangan:** IDE NetBeans

## Cara Menggunakan

1. download TugasAkhirAlgoritma.exe yang ada dalam repository ini
   ```bash
   TugasAkhirAlgoritma.exe
   ```
2. Aplikasi dapat digunakan secara langsung
3. Jika ada peringatan dari windows
4. Klik more info, lalu run anyway

## Struktur Proyek

```
|-- src
    |-- TugasAkhir.java      // Class untuk menu utama
    |-- KelolaTransaksi.java // Class untuk logika & CRUD Transaksi
    |-- KelolaProduk.java    // Class untuk logika & CRUD Produk
    |-- Transaksi.java       // Dataclass utama untuk transaksi
    |-- Produk.java          // Dataclass kedua untuk produk yang dijual
    |-- Utility.java         // Class yang utility function
```
Untuk struktur proyek lebih detail bisa melihat :
```
Psudocode.txt
```

## Contoh Tampilan

Menu Utama :
```
-------------------------------------------------------
== Selamat Datang di Aplikasi Kasir Elra Skin Clinic ==

silahkan pilih menu yang tersedia :
[1] Kelola Transaksi
[2] Kelola Produk
[3] Keluar
-------------------------------------------------------
Pilih menu>>
```
Menu Kelola Transaksi :
```
-------------------------------------------------------
===== Menu Transaksi =====

[1] Lihat semua transaksi
[2] Buat transaksi
[3] Edit transaksi
[4] Hapus transaksi
[5] Hapus semua transaksi
[6] Cetak struk
[7] Clear
[8] Kembali
-------------------------------------------------------
Pilih menu>>
```
Menu Kelola Produk :
```
-------------------------------------------------------
===== Menu Produk =====

[1] Lihat semua produk
[2] Buat produk
[3] Edit produk
[4] Hapus produk
[5] Hapus semua produk
[6] Clear
[7] Kembali
-------------------------------------------------------
Pilih menu>>
```

---

**Dibuat oleh:** Muh Riqza Muqtada
Nim: 24.240.0100
Mata Kuliah: Algoritma dan Pemrograman  
Institusi: Institut Widya Pratama
