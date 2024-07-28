import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import { UilArrowLeft} from '@iconscout/react-unicons';
import { Link } from "react-router-dom";
import {toastSuccess , toastError} from "../../api/ToastService";
import { updateKhoa,detailKhoa } from '../../api/KhoaService';
import { useNavigate } from 'react-router-dom';

const EditKhoa = () => {
  const tokenLogin = window.localStorage.getItem('token');
  const navigate = useNavigate();
    const {id} = useParams();
    const [khoa, setKhoa] = useState({
        tenKhoa: '',
        email: '',
        dienThoai: '',
    });


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

  //Xử lý change input
 const onchangeKhoa = (event) => {
    setKhoa({...khoa, [event.target.name]: event.target.value});
    console.log(khoa);
   }

  //Hàm xử lý update chi đội
const handleUpdateKhoa = async (khoa) => {
    try {
      const { data } = await updateKhoa(khoa);
      console.log(data);
      toastSuccess(data);
    } catch(error) {
      console.log(error);
      toastError(error.response.data);
    }
  }
  const OnupdateKhoa = async (event) => {
    event.preventDefault();
    handleUpdateKhoa(khoa);
  
  } 

  return (
    <section className='main EditDoanVien'>
    <div className='BackArrow'>
      <Link className='Arrow' to='/qldv/khoa'>< UilArrowLeft/></Link>
    </div>
    <h1 className='title__info'>Sửa thông tin Khoa {khoa.tenKhoa}</h1>
    <div className='box'>
    
    <div className='info__box infoChiDoi'>
    <form onSubmit={OnupdateKhoa} className="form formEdit editChiDoi" id="form-1">
    <div className="form-group" style={{display: 'none'}}>
            <div className="form-group__containt">
            <div className="form-group__item">
                      <label htmlFor="id" className="form-label">Id</label>
                      <input 
                        defaultValue={khoa.id} 
                        id="id" name="id" required
                        type="hidden" placeholder="" className="form-control"/>
                    <span className="form-message"></span>
                
                </div>
              <div className="form-group__item">
                <label htmlFor="maKhoa" className="form-label">Mã</label>
                <input required
                      defaultValue={khoa.maKhoa} 
                       id="maKhoa" 
                       name="maKhoa" 
                       type="hidden" placeholder="VD:" className="form-control"/>
                <span className="form-message"></span>
              </div>
            </div>
           
          </div>

          <div className="form-group">
            <div className="form-group__containt">
              <div className="form-group__item" style={{width: '100%'}}>
                <label htmlFor="tenKhoa" className="form-label">Tên khoa</label>
                <input required
                      onChange={onchangeKhoa}
                      minLength={3}
                      value={khoa.tenKhoa} 
                       id="tenKhoa" 
                       name="tenKhoa" 
                       type="text" placeholder="VD: K13THO1" className="form-control"/>
                 <span className="form-message"></span>
              </div>
            </div>
           
          </div>
      

          <div className="form-group">
           <div className="form-group__containt">
              <div className="form-group__item">
                  <label htmlFor="dienThoai" className="form-label">Điện thoại</label>
                  <input required
                  maxLength="10"
                  title='Hãy nhập đúng định dạng số điện thoại'
                  pattern='[0-9]{1}[0-9]{9}'
                  onChange={onchangeKhoa}
                    value={khoa.dienThoai} 
                    id="dienThoai" name="dienThoai" 
                    type="text" placeholder="VD: 0123456789" className="form-control"/>        
              </div>

              <div className="form-group__item">
                <label htmlFor="email" className="form-label">Email</label>
                <input required
                onChange={onchangeKhoa}
                  value={khoa.email} 
                  id="email" name="email" 
                  type="text" placeholder="VD:test@gmail.com" className="form-control"/>        
              </div>
  
           </div>
          </div>

          <button type="submit" className="form-submit">Chỉnh sửa</button>
        </form>
    </div>
    </div>  
    </section>
  )
}

export default EditKhoa