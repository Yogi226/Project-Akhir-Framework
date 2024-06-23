<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Tambah Produk</title>
    <!-- Link Bootstrap CSS (CDN) untuk mengimpor gaya Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <!-- Heading untuk halaman -->
    <h3 class="mt-3 mb-4">Tambah Menu Baru</h3>
    <!-- Tombol untuk kembali ke halaman sebelumnya -->
    <a href="/atur_menu" class="btn btn-secondary">Kembali</a>
    <br/>
    <!-- Form untuk menambahkan produk baru -->
    <form action="/insert_produk" method="POST" enctype="multipart/form-data">
        <!-- Token CSRF untuk keamanan -->
        @csrf
        <!-- Input untuk kode produk, hanya baca karena sudah di-generate otomatis -->
        <div class="form-group">
            <label for="kode_produk">Kode Produk:</label>
            <input type="text" class="form-control" id="kode_produk" name="kode_produk" value="{{ $kodeProduk }}" readonly>
        </div>

        <!-- Input untuk nama produk -->
        <div class="form-group">
            <label for="nama_produk">Nama Produk:</label>
            <input type="text" class="form-control" id="nama_produk" name="nama_produk" value="{{ old('nama_produk') }}" required>
        </div>

        <!-- Input untuk gambar produk -->
        <div class="form-group">
            <label for="gambar">Gambar:</label>
            <input type="file" class="form-control-file" id="gambar" name="gambar" required>
        </div>

        <!-- Textarea untuk deskripsi produk -->
        <div class="form-group">
            <label for="deskripsi" class="form-label">Deskripsi Produk</label>
            <textarea id="deskripsi" class="form-control" name="deskripsi" required></textarea>
        </div>

        <!-- Input untuk harga produk -->
        <div class="form-group">
            <label for="harga_jual">Harga Produk:</label>
            <input type="number" class="form-control" name="harga_jual" placeholder="Harga Jual Produk" required>
        </div>

        <!-- Input untuk stock produk -->
        <div class="form-group">
            <label for="stock">Stock Produk:</label>
            <input type="number" class="form-control" name="stock" placeholder="Stock Produk" required>
        </div>

        <!-- Input untuk link marketplace produk -->
        <div class="form-group">
            <label for="link_marketplace">Link Produk:</label>
            <input type="text" class="form-control" name="link_marketplace" placeholder="Link Marketplace" required>
        </div>

        <!-- Tombol untuk submit form -->
        <button type="submit" class="btn btn-primary">Tambah</button>
    </form>
</div>

<!-- Link Bootstrap JS (opsional, tergantung kebutuhan) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
