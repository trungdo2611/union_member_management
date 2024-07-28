import React, {useState, useEffect} from 'react';
import { UilArrowLeft} from '@iconscout/react-unicons';
import { Link } from "react-router-dom";
import { useParams } from 'react-router-dom';
import { detailKhoa } from '../../api/KhoaService';
import { useNavigate } from 'react-router-dom';

const DetailKhoa = () => {
  const tokenLogin = window.localStorage.getItem('token');
  const navigate = useNavigate();
    const {id} = useParams();
    const [khoa, setKhoa] = useState({});

    //Xử lý lấy chi tiết thông tin khoa
 const fetchDetailKhoa = async (id) => {
   try{
     const {data} = await detailKhoa(id);
     setKhoa(data);
   } catch(error) {
     console.log(error);
   }
 }

 useEffect(() => {
  if(!tokenLogin) {
    navigate('/')
  }
   fetchDetailKhoa(id);
 },[]);
  return (
    <section className='main'>
    <div className='BackArrow'>
      <Link className='Arrow' to='/qldv/khoa'>< UilArrowLeft/></Link>
    </div>
    <h1 className='title__info'>Chi tiết thông tin Khoa {khoa.tenKhoa}</h1>
    <div className='box'>
  
    <div className='info__box infoChiDoi'>
      <div className='info__user'>
        <div className='info__item'>      
          <p>Mã khoa: {khoa.maKhoa}</p>
          <p>Tên khoa : {khoa.tenKhoa}</p>
        </div>
       
        <div className='info__item'>
        <p>Email: {khoa.email}</p>
        <p>Điện thoại: {khoa.dienThoai}</p>
        </div>

      </div>
    </div>
    <div className='btnBox'>
      <Link to={`/qldv/khoa/edit/${khoa.id}`} className='btn btnEdit'>Sửa</Link>
    </div>
    </div>  
   </section>
  )
}

export default DetailKhoa