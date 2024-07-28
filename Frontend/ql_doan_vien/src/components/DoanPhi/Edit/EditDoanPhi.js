import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import { UilArrowLeft} from '@iconscout/react-unicons';
import { Link } from "react-router-dom";
import {toastSuccess , toastError} from "../../api/ToastService";
import { detailDoanPhi, updateDoanPhi } from '../../api/DoanPhiService';
import { useNavigate } from 'react-router-dom';
const EditDoanPhi = () => {
  const tokenLogin = window.localStorage.getItem('token');
  const navigate = useNavigate();
    const {id} = useParams();
    const [doanphi, setDoanPhi] = useState({
        tenPhi: '',
        ngayNop: '',
        soTien: '',
    });

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


//Xử lý change input
const onchangeDoanPhi = (event) => {
    setDoanPhi({...doanphi, [event.target.name]: event.target.value});
   }

  //Hàm xử lý update chi đội
const handleUpdateDoanPhi = async (doanPhi) => {
    try {
      const { data } = await updateDoanPhi(doanPhi)
      toastSuccess(data);
    } catch(error) {
      console.log(error);
      toastError(error.response.data);
    }
  }
  const OnupdateDoanPhi = async (event) => {
    event.preventDefault();
    handleUpdateDoanPhi(doanphi);
  
  }
  return (
    <section className='main EditDoanVien'>
    <div className='BackArrow'>
      <Link className='Arrow' to='/qldv/doanphi'>< UilArrowLeft/></Link>
    </div>
    <h1 className='title__info'>Sửa thông tin đoàn phí</h1>
    <div className='box'>
    
    <div className='info__box infoChiDoi'>
    <form onSubmit={OnupdateDoanPhi} className="form formEdit editChiDoi" id="form-1">
    <div className="form-group" style={{display: 'none'}}>
            <div className="form-group__containt">
            <div className="form-group__item">
                      <label htmlFor="id" className="form-label">Id</label>
                      <input 
                        defaultValue={doanphi.id} 
                        id="id" name="id" required
                        type="hidden" placeholder="" className="form-control"/>
                    <span className="form-message"></span>
                
                </div>
            </div>
           
          </div>

          <div className="form-group">
            <div className="form-group__containt">
              <div className="form-group__item">
                <label htmlFor="tenPhi" className="form-label">Tên đoàn phí</label>
                <input required
                      onChange={onchangeDoanPhi}
                      minLength={3}
                      value={doanphi.tenPhi} 
                       id="tenPhi" 
                       name="tenPhi" 
                       type="text" placeholder="VD: đoàn phí hk1" className="form-control"/>
                 <span className="form-message"></span>
              </div>
           
              <div className="form-group__item">
                <label htmlFor="khoa_id" className="form-label">Tên người đóng</label>
                <input
                disabled
                  value={doanphi.tenDoanVien}
                  name="doanVien_id" 
                  className="form-control" 
                  >
                </input>
                <span className="form-message"></span>
              </div>
            </div>
           
          </div>
      

          <div className="form-group">
           <div className="form-group__containt">
              <div className="form-group__item">
                  <label htmlFor="dienThoai" className="form-label">Số tiền</label>
                  <input required
                  title='Hãy nhập đúng định dạng số'
                  pattern='^[1-9]\d*$'
                  onChange={onchangeDoanPhi}
                    value={doanphi.soTien} 
                    id="dienThoai" name="soTien" 
                    type="text" placeholder="VD: 0123456789" className="form-control"/>        
              </div>

              <div className="form-group__item">
                <label htmlFor="ngayNop" className="form-label">ngày nộp</label>
                <input required
                onChange={onchangeDoanPhi}
                  value={doanphi.ngayNop} 
                  id="ngayNop" name="ngayNop" 
                  type="date" placeholder="VD" className="form-control"/>        
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

export default EditDoanPhi