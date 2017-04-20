<?php 
 require_once 'koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	$id = $_POST['id'];

 	$query = "DELETE FROM tb_biodata WHERE id ='$id'";

 	$exeQuery = mysqli_query($konek, $query); 

 	echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil Menghapus data')) :  json_encode(array('kode' =>2, 'pesan' => 'data gagal dihapu'));
 }
 else
 {
 	 echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
 }

 ?>