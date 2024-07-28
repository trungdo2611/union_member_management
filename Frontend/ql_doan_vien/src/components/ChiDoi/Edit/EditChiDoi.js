import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import { detailChiDoan, updateChiDoan } from '../../api/ChiDoanService';
import { UilArrowLeft} from '@iconscout/react-unicons';
import { Link } from "react-router-dom";
import {toastSuccess , toastError} from "../../api/ToastService";
import { getKhoaSelect } from '../../api/KhoaService';
import './EditChiDoi.css';
import { useNavigate } from 'react-router-dom';

const EditChiDoi = () => {
    const tokenLogin = window.localStorage.getItem('token');
    const navigate = useNavigate();
    const {id} = useParams();
    const [chidoi, setChiDoi] = useState({
        tenChiDoan: '',
        diaChi: '',
        dienThoai: '',
        khoa_id: '',
    });
    const [khoaList, setKhoaList] = useState([]);

     //Xử lý lấy chi tiết thông tin đoàn viên
  const fetchEditChiDoi = async (id) => {
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
    fetchEditChiDoi(id);
  },[]);

//Xử lý load khoa vào select-option
const getKhoa = async () => {
    try {    
        const {data} = await getKhoaSelect();
        setKhoaList(data);
    } catch (error) {
      console.log(error);
    }
}

useEffect(() => {
    getKhoa();
}, []);


 //Xử lý change input
 const onchangeChiDoi = (event) => {
    setChiDoi({...chidoi, [event.target.name]: event.target.value});
    console.log(chidoi);
   }

  //Hàm xử lý update chi đội
const handleUpdateChiDoi = async (chiDoan) => {
    try {
      const { data } = await updateChiDoan(chiDoan);
      console.log(data);
      toastSuccess(data);
    } catch(error) {
      console.log(error);
      toastError(error.response.data);
    }
  }
  const OnupdateChiDoan = async (event) => {
    event.preventDefault();
    handleUpdateChiDoi(chidoi);
  
  } 

  return (
    <section className='main EditDoanVien'>
    <div className='BackArrow'>
      <Link className='Arrow' to='/qldv/chidoi'>< UilArrowLeft/></Link>
    </div>
    <h1 className='title__info'>Sửa thông tin chi đoàn {chidoi.tenChiDoan}</h1>
    <div className='box'>
    
    <div className='info__box infoChiDoi'>
    <form onSubmit={OnupdateChiDoan} className="form formEdit editChiDoi" id="form-1">
    <div className="form-group" style={{display: 'none'}}>
            <div className="form-group__containt">
            <div className="form-group__item">
                      <label htmlFor="id" className="form-label">Id</label>
                      <input 
                        defaultValue={chidoi.id} 
                        id="id" name="id" required
                        type="hidden" placeholder="" className="form-control"/>
                    <span className="form-message"></span>
                
                </div>
              <div className="form-group__item">
                <label htmlFor="maChiDoan" className="form-label">Mã chi đoàn</label>
                <input required
                      defaultValue={chidoi.maChiDoan} 
                       id="maChiDoan" 
                       name="maChiDoan" 
                       type="hidden" placeholder="VD:" className="form-control"/>
                <span className="form-message"></span>
              </div>
            </div>
           
          </div>

          <div className="form-group">
            <div className="form-group__containt">
              <div className="form-group__item">
                <label htmlFor="tenChiDoan" className="form-label">Tên chi đoàn</label>
                <input required
                      onChange={onchangeChiDoi}
                      minLength={3}
                      value={chidoi.tenChiDoan} 
                       id="tenChiDoan" 
                       name="tenChiDoan" 
                       type="text" placeholder="VD: K13THO1" className="form-control"/>
                 <span className="form-message"></span>
              </div>
           
              <div className="form-group__item">
                <label htmlFor="khoa_id" className="form-label">Đoàn khoa</label>
                <select
                required
                onChange={onchangeChiDoi}
                  value={chidoi.khoa_id}
                  name="khoa_id" 
                  className="form-control" 
                  >
                 <option value="">-- Chọn Khoa --</option>
                {khoaList.map((khoa) => (
                    <option key={khoa.id} value={khoa.id}>
                        {khoa.tenKhoa}
                    </option>
                ))}
                </select>
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
                  onChange={onchangeChiDoi}
                    value={chidoi.dienThoai} 
                    id="dienThoai" name="dienThoai" 
                    type="text" placeholder="VD: 0123456789" className="form-control"/>        
              </div>

              <div className="form-group__item">
                <label htmlFor="danToc" className="form-label">Địa chỉ</label>
                <input required
                onChange={onchangeChiDoi}
                  value={chidoi.diaChi} 
                  id="diaChi" name="diaChi" 
                  type="text" placeholder="VD: Bình Thuận" className="form-control"/>        
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

export default EditChiDoi