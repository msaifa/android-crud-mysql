<?php namespace App\Controllers;

use App\Models\User;

class DataUser extends BaseController
{

	public function tambah()
	{
        $model = new User();
        
        $cekEmail = $model->cekEmail($_POST['uemail']);

        if (!$cekEmail){
            return json_encode([
                'status' => 0,
                'message'=> "Email telah digunakan! Silahkan menggunakan email yang lain.",
            ]) ; 
        }

        $data = array(
            'unama'     => $_POST['unama'],
			'uemail'    => $_POST['uemail'],
            'utelp'     => $_POST['utelp'],
            'ujk'       => $_POST['ujk'],
            'ualamat'   => $_POST['ualamat']
        );
        $result = $model->tambahData($data);

        return json_encode([
            'status' => $result ? 1 : 0,
            'message'=> $result ? "Berhasil menambahkan data": "Gagal menambahkan data."
        ]) ;
    }
    
    public function ubah()
	{
        $model = new User();
        
        $cekEmail = $model->cekEmail($_POST['uemail'], $_POST['uid']);

        if (!$cekEmail){
            return json_encode([
                'status' => 0,
                'message'=> "Email telah digunakan! Silahkan menggunakan email yang lain."
            ]) ; 
        }

        $data = array(
            'unama'     => $_POST['unama'],
			'uemail'    => $_POST['uemail'],
            'utelp'     => $_POST['utelp'],
            'ujk'       => $_POST['ujk'],
            'ualamat'   => $_POST['ualamat']
        );
		$result = $model->ubahData($_POST['uid'], $data);

        return json_encode([
            'status' => $result ? 1 : 0,
            'message'=> $result ? "Berhasil mengubah data": "Gagal mengubah data."
        ]) ;
    }
    
    public function hapus()
	{
        $model = new User();

		$result = $model->hapusData($_POST['uid']);

        return json_encode([
            'status' => $result ? 1 : 0,
            'message'=> $result ? "Berhasil menghapus data": "Gagal menghapus data."
        ]) ;
    }

    public function ambil()
	{
        $model = new User();

		$result = $model->ambilData($_POST['pencarian']);

        return json_encode([
            'status' => $result ? 1 : 0,
            'message'=> $result ? "Berhasil mengambil data": "Gagal mengambil data.",
            'data' => $result
        ]) ;
    }
    
    

	//--------------------------------------------------------------------
}