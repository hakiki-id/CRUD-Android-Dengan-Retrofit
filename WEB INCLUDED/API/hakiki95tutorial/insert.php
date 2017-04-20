<?php 
 require_once 'koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	$nama = $_POST['nama'];
 	$usia = $_POST['usia'];
 	$domisili = $_POST['domisili'];

 	$query = "INSERT INTO tb_biodata (nama, usia, domisili) VALUES ('$nama','$usia','$domisili')";

 	$exeQuery = mysqli_query($konek, $query); 

 	echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil menambahkan data')) :  json_encode(array('kode' =>2, 'pesan' => 'data gagal ditambahkan'));
 }
 else
 {
 	 echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
 }

 ?>