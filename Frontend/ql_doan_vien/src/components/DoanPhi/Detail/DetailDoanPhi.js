import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import { UilArrowLeft} from '@iconscout/react-unicons';
import { Link } from "react-router-dom";
import { detailDoanPhi } from '../../api/DoanPhiService';
import { useNavigate } from 'react-router-dom';
const DetailDoanPhi = () => {
    const {id} = useParams();
    const [doanphi, setDoanPhi] = useState({});
    const tokenLogin = window.localStorage.getItem('token');
    const navigate = useNavigate();
     //Xử lý lấy chi tiết thông tin đoàn phí
  const fetchDetailDoanPhi = async (id) => {
    try{
      const {data} = await detailDoanPhi(id);
      setDoanPhi(data);
    } catch(error) {
      console.log(error);
    }
  }

  useEffect(() => {
    if(!tokenLogin) {
      navigate('/')
    }
    fetchDetailDoanPhi(id);
  },[]);
  return (
    <section className='main'>
    <div className='BackArrow'>
      <Link className='Arrow' to='/qldv/doanphi'>< UilArrowLeft/></Link>
    </div>
    <h1 className='title__info'>Thông tin đoàn phí tên "{doanphi.tenPhi}" của đoàn viên {doanphi.tenDoanVien}</h1>
    <div className='box'>
  
    <div className='info__box infoChiDoi'>
      <div className='info__user'>
        <div className='info__item'>
        <p>Tên đoàn phí : {doanphi.tenPhi}</p>   
        </div>

        <div className='info__item'>      
          <p>Tên người đóng: {doanphi.tenDoanVien}</p>
          <p>Mã đoàn viên: {doanphi.maDoanVien}</p>
        </div>

        <div className='info__item'>
        <p>Số tiền {doanphi.soTien} VND</p> 
        <p>Ngày đóng: {doanphi.ngayNop}</p>
        </div>

      </div>
    </div>
    <div className='btnBox'>
      <Link to={`/qldv/doanphi/edit/${doanphi.id}`} className='btn btnEdit'>Sửa</Link>
    </div>
    </div>  
   </section>
  )
}

export default DetailDoanPhi