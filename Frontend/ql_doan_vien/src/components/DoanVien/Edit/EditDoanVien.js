import React ,{ useEffect, useState, useRef } from 'react';
import { useParams } from 'react-router-dom';
import './EditDoanVien.css';
import { Link } from "react-router-dom";
import { UilArrowLeft} from '@iconscout/react-unicons';
import { detailDoanVien, updatePhoto, updateDoanVien } from '../../api/DoanVienService';
import { getChiDoanSelect } from "../../api/ChiDoanService";
import {toastSuccess , toastError} from "../../api/ToastService";
import { useNavigate } from 'react-router-dom';
const EditDoanVien = () => {
  const tokenLogin = window.localStorage.getItem('token');
  const navigate = useNavigate();
  const inputRef = useRef();
  const [chiDoanList, setChiDoanList] = useState([]);

    const {id} = useParams();
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
      phanQuyen_id: ''
});

 //Xử lý lấy chi tiết thông tin đoàn viên
 const fetchDetailEditDoanVien = async (id) => {
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
  fetchDetailEditDoanVien(id);
},[]);

 const selectedImage = () =>{
  inputRef.current.click();
 };

 //Xử lý thay đổi ảnh 
 const changePhoto = async(file) => {
    try {
      const formData = new FormData();
        formData.append('id', id);
        formData.append('file', file, file.name);  
       const {data} = await updatePhoto(formData);
       console.log(data);
        setDoanVien((prev) => ({...prev, hinhAnh: data}));
       toastSuccess("Đã cập nhật ảnh thành công!!")

    } catch(error) {
      console.log(error)
    }
 }




//Xử lý load chi đoàn vào select-option
  const getChiDoan = async () => {
      try {    
          const {data} = await getChiDoanSelect();
          setChiDoanList(data);
      } catch (error) {
        console.log(error);
      }
  }

  useEffect(() => {
    getChiDoan();
 }, []);


 //Xử lý change input
 const onchange = (event) => {
  setDoanVien({...doanvien, [event.target.name]: event.target.value});
 }

//Hàm xử lý update đoàn viên
const handleUpdateDoanVien = async (doanvien) => {
  try {
    const { data } = await updateDoanVien(doanvien);
    console.log(data);
    toastSuccess(data);
  } catch(error) {
    console.log(error);
    toastError(error.response.data);
  }
}
const OnupdateDoanVien = async (event) => {
  event.preventDefault();
  handleUpdateDoanVien(doanvien);

}

  
  return (
    <>
    <section className='main EditDoanVien'>
    <div className='BackArrow'>
      <Link className='Arrow' to='/qldv/doanvien'>< UilArrowLeft/></Link>
    </div>
    <h1 className='title__info'>Sửa thông tin đoàn viên {doanvien.tenDoanVien}</h1>
    <div className='box'>
    <div className='image__box'>
      <img className='img__Pic' alt={doanvien.tenDoanVien} src={doanvien.hinhAnh}/>
      <h3 className='img__info'>{doanvien.tenDoanVien}</h3>
      <button className='btn btnChangeImg' onClick={selectedImage}>Thay đổi ảnh</button>
    </div>

    <div className='info__box'>
    <form onSubmit={OnupdateDoanVien} className="form formEdit" id="form-1">
    <div className="form-group" style={{display: 'none'}}>
            <div className="form-group__containt">
            <div className="form-group__item">
                      <label htmlFor="id" className="form-label">Id</label>
                      <input 
                        defaultValue={doanvien.id} 
                        id="id" name="id" required
                        type="hidden" placeholder="" className="form-control"/>
                    <span className="form-message"></span>
                
                </div>
              <div className="form-group__item">
                <label htmlFor="maDoanVien" className="form-label">Mã đoàn viên</label>
                <input required
                      defaultValue={doanvien.maDoanVien} 
                       id="maDoanVien" 
                       name="maDoanVien" 
                       type="hidden" placeholder="VD: Xuân Trung" className="form-control"/>
                <span className="form-message"></span>
              </div>
           
                <div className="form-group__item">
                      <label htmlFor="password" className="form-label">Mật khẩu</label>
                      <input required
                        defaultValue={doanvien.password} 
                        id="password" name="password"  
                        type="hidden" placeholder="VD: email@domain.com" className="form-control"/>
                       <span className="form-message"></span>
                </div>
            </div>
           
          </div>

          <div className="form-group">
            <div className="form-group__containt">
              <div className="form-group__item">
                <label htmlFor="tenDoanVien" className="form-label">Tên đầy đủ</label>
                <input required
                      onChange={onchange}
                      minLength={3}
                      value={doanvien.tenDoanVien} 
                       id="tenDoanVien" 
                       name="tenDoanVien" 
                       type="text" placeholder="VD: Xuân Trung" className="form-control"/>
                 <span className="form-message"></span>
              </div>
           
                <div className="form-group__item">
                      <label htmlFor="email" className="form-label">Email</label>
                      <input required
                      disabled
                      pattern='[^@\s]+@[^@\s]+\.[^@\s]+'
                      title='Vui lòng nhập đúng định dạng email'
                      onChange={onchange}
                        value={doanvien.email} 
                        id="email" name="email" 
                        type="text" placeholder="VD: email@domain.com" className="form-control"/>
                       <span className="form-message"></span>
                </div>
            </div>
           
          </div>
      
          <div className="form-group">
           <div className="form-group__containt">
              <div className="form-group__item">
                  <label htmlFor="ngaySinh" className="form-label">Ngày sinh</label>
                  <input required
                  rules='required'
                  onChange={onchange}
                    value={doanvien.ngaySinh} 
                    id="ngaySinh" name="ngaySinh" 
                    type="date" placeholder="" className="form-control"/>
                </div>

              <div className="form-group__item">
                <label htmlFor="ngayVaoDoan" className="form-label">Ngày vào đoàn</label>
                <input required
                onChange={onchange}
                  value={doanvien.ngayVaoDoan} 
                  id="ngayVaoDoan" name="ngayVaoDoan" 
                  type="date" placeholder="" className="form-control"/>    
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
                  onChange={onchange}
                    value={doanvien.dienThoai} 
                    id="dienThoai" name="dienThoai" 
                    type="text" placeholder="VD: 0123456789" className="form-control"/>        
              </div>

              <div className="form-group__item">
                <label htmlFor="danToc" className="form-label">Dân tộc</label>
                <input required
                onChange={onchange}
                  value={doanvien.danToc} 
                  id="danToc" name="danToc" 
                  type="text" placeholder="VD: Ê đê" className="form-control"/>        
              </div>
  
           </div>
          </div>


          <div className="form-group">
           <div className="form-group__containt form-group__item--select">
              <div className="form-group__item">
                <label htmlFor="chiDoan_id" className="form-label">Chi đoàn</label>
                <select
                required
                onChange={onchange}
                  value={doanvien.chiDoan_id}
                  name="chiDoan_id" 
                  className="form-control" 
                  >
                 <option value="">-- Chọn chi đoàn --</option>
                {chiDoanList.map((chiDoan) => (
                    <option key={chiDoan.id} value={chiDoan.id}>
                        {chiDoan.tenChiDoan}
                    </option>
                ))}
                </select>
                <span className="form-message"></span>
              </div>

              <div className="form-group__item">
                <label htmlFor="chucVu_id" className="form-label">Chức vụ</label>
                <select 
                required
                onChange={onchange}
                  value={doanvien.chucVu_id}
                  id="chucVu_id" name="chucVu_id" 
                  className="form-control" 
                  > 
                  <option value=''>--Chọn--</option>               
                  <option value={1}>Bí Thư</option>
                  <option value={2}>Phó Bí Thư</option>
                  <option value={3}>Đoàn Viên</option>
                </select>
                <span className="form-message"></span>
              </div>

           </div>
          </div>

          <div className="form-group">
           <div className="form-group__containt form-group__item--select">
                    
              <div className="form-group__item">
                <label htmlFor="phanQuyen_id" className="form-label">Phân Quyền</label>
                <select required
                onChange={onchange}
                  value={doanvien.phanQuyen_id}
                  id="phanQuyen_id" name="phanQuyen_id" 
                  className="form-control" 
                  > 
                  <option value=''>--Chọn--</option>               
                  <option value={1}>ADMIN</option>
                  <option value={2}>USER</option>
                </select>
                <span className="form-message"></span>
              </div>

              <div className="form-group__item">
                <label htmlFor="gioiTinh" className="form-label">Giới tính</label>
                <select required
                onChange={onchange}
                  value={doanvien.gioiTinh} 
                  id="gioiTinh" 
                  name="gioiTinh" 
                  className="form-control" 
                  >
                  <option value=''>--Chọn--</option>  
                  <option value={"true"}>Nam</option>
                  <option value={"false"}>Nữ</option>
                </select>
                <span className="form-message"></span>
              </div>
      
           </div>
          </div>
       
          <button type="submit" className="form-submit">Chỉnh sửa</button>
        </form>
    </div>
    </div>  
    </section>
    <form style={{display:'none'}}>
      <input type='file' ref={inputRef}
            name='file'
            accept='image/*'
            onChange={(event) => {changePhoto(event.target.files[0]); console.log('đã đổi ảnh')}}
      />
    </form>
    </>
  )
}

export default EditDoanVien