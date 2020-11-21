<?php namespace App\Models;
 
use CodeIgniter\Model;
 
class User extends Model
{
 
    public function tambahData($data){
        $query = $this->db->table('mas_user')->insert($data);
        return $query->resultID;
    }

    public function ubahData($uid, $data){
        $query = $this->db->table('mas_user')
        ->where('uid', $uid)
        ->update($data);

        return $query;
    }

    public function hapusData($uid){
        $query = $this->db->table('mas_user')
        ->where('uid', $uid)
        ->delete();

        return $query;
    }

    public function ambilData($pencarian = ""){
        $builder = $this->db->table('mas_user');
        $builder->select('*')
        ->like('unama', $pencarian, "both")
        ->orLike('uemail', $pencarian, "both")
        ->orLike('ualamat', $pencarian, "both"); 

        return $builder->get()->getResult();
    }

    public function cekEmail($uemail, $uid = 0)
    {
        $builder = $this->db->table('mas_user');
        $builder->select('uid');
        $builder->where('uemail', $uemail); 
        
        if ($uid != 0){
            $builder->where('uid !=', $uid); 
        }

        $res = $builder->get()->getResult();

        return count($res) == 0 ? true : false ;
    }
   
}