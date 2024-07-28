import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import { detailChiDoan } from '../../api/ChiDoanService';
import { UilArrowLeft} from '@iconscout/react-unicons';
import { Link } from "react-router-dom";
import './DetailChiDoi.css';
import { useNavigate } from 'react-router-dom';
const DetailChiDoi = () => {
  const tokenLogin = window.localStorage.getItem('token');
  const navigate = useNavigate();

    const {id} = useParams();
    console.log(id);
    const [chidoi, setChiDoi] = useState({});

     //Xử lý lấy chi tiết thông tin chi đội
  const fetchDetailChiDoi = async (id) => {
    try{
      const {data} = await detailChiDoan(id);
      setChiDoi(data);
    } catch(error) {
      console.log(error);
    }
  }

  useEffect(() => {
    if(!tokenLogin) {
      navigate('/')
    }
    fetchDetailChiDoi(id);
  },[]);
  return (
    <section className='main'>
    <div className='BackArrow'>
      <Link className='Arrow' to='/qldv/chidoi'>< UilArrowLeft/></Link>
    </div>
    <h1 className='title__info'>Chi tiết thông tin chi đội {chidoi.tenChiDoan}</h1>
    <div className='box'>
  
    <div className='info__box infoChiDoi'>
      <div className='info__user'>
        <div className='info__item'>      
          <p>Mã chi đội: {chidoi.maChiDoan}</p>
        </div>

        <div className='info__item'>
        <p>Đoàn khoa : {chidoi.tenKhoa}</p>
        <p>Chi đội: {chidoi.tenChiDoan}</p>     
        </div>

        <div className='info__item'>
        <p>Địa chỉ: {chidoi.diaChi}</p>
        <p>Điện thoại: {chidoi.dienThoai}</p>
        </div>

      </div>
    </div>
    <div className='btnBox'>
      <Link to={`/qldv/chidoi/edit/${chidoi.id}`} className='btn btnEdit'>Sửa</Link>
    </div>
    </div>  
   </section>
  )
}

export default DetailChiDoi