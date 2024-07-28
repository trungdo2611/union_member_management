import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import './DetailDoanVien.css';
import { UilArrowLeft, UilNotebooks} from '@iconscout/react-unicons';
import { Link } from "react-router-dom";
import { detailDoanVien } from '../../api/DoanVienService';
import { useNavigate } from 'react-router-dom';
const DetailDoanVien = () => {
  const tokenLogin = window.localStorage.getItem('token');
  const navigate = useNavigate();
  const [doanvien, setDoanVien] = useState({
        tenDoanVien: '',
        ngaySinh: '',
        gioiTinh: '',
        email: '',
        dienThoai: '',
        ngayVaoDoan: '',
        chiDoan_id: '',
        chucVu_id: '',
        danToc: '',
        maDoanVien: '',
        hinhAnh: '',
        password: '',
  });
  const {id} = useParams();
  
  //Xử lý lấy chi tiết thông tin đoàn viên
  const fetchDetailDoanVien = async (id) => {
    try{
      const {data} = await detailDoanVien(id);
      setDoanVien(data);
    } catch(error) {
      console.log(error);
    }
  }

  useEffect(() => {
    if(!tokenLogin) {
      navigate('/')
    }
    fetchDetailDoanVien(id);
  },[]);

  return (
   <section className='main'>
    <div className='BackArrow'>
      <Link className='Arrow' to='/qldv/doanvien'>< UilArrowLeft/></Link>
    </div>
    <h1 className='title__info'>Chi tiết thông tin đoàn viên {doanvien.tenDoanVien}</h1>
    <div className='box boxDoanVien'>
    <div className='image__box'>
      <img className='img__Pic' alt={doanvien.tenDoanVien} src={doanvien.hinhAnh}/>
      <h3 className='img__info'>{doanvien.tenDoanVien}</h3>
    </div>

    <div className='info__box'>
      <div className='info__user'>
        <div className='info__item'>
          <p>Đoàn Khoa: {doanvien.tenKhoa}</p>
          <p>Chi đoàn: {doanvien.tenChiDoan}</p>
        </div>

        <div className='info__item'>
          <p>Mã đoàn viên: {doanvien.maDoanVien}</p>
          <p>Chức vụ : {doanvien.tenChucVu} - Quyền : {doanvien.tenQuyen}</p>
        </div>

        <div className='info__item'>
        <p>Email: {doanvien.email}</p>
        <p>Mật khẩu : {doanvien.password}</p>
        <p>Điện thoại : {doanvien.dienThoai}</p>
        </div>

        <div className='info__item'>
          <p>Ngày sinh: {doanvien.ngaySinh}</p>
          <p>Ngày vào đoàn: {doanvien.ngayVaoDoan}</p>
          <p>Giới tính : {doanvien.gioiTinh ? "Nam" : "Nữ"} - Dân tộc : {doanvien.danToc}</p>
        </div>
      </div>
    </div>

    <div className='btnBox btnDoanVien'>
      <Link to={`/qldv/sotay/${doanvien.id}`} className='btn btnSoTay'>
      <span><UilNotebooks/></span><span>Sổ tay đv</span>
      </Link>
      <Link to={`/qldv/doanvien/edit/${doanvien.id}`} className='btn btnEdit btnEditDoanVien'>Sửa</Link>
    </div>
    </div>  
   </section>
  )
}

export default DetailDoanVien