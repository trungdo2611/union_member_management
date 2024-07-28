import React, {useEffect, useState} from 'react';
import HeaderKhoa from '../HeaderKhoa/HeaderKhoa';
import ListKhoa from '../ListKhoa/ListKhoa';
import { deleteKhoa, getKhoas, saveKhoa } from '../../api/KhoaService';
import { useFormik } from "formik";
import * as Yup from 'yup';
import { toastError, toastSuccess } from "../../api/ToastService";
import { UilX } from "@iconscout/react-unicons";
import { useNavigate } from 'react-router-dom';

const IndexKhoa = () => {
  const tokenLogin = window.localStorage.getItem('token');
  const navigate = useNavigate();
  const [dataKhoa,setDataKhoa] = useState({});
  const[currentPageKhoa, setCurrentPageKhoa] = useState(0);
  const [searchKhoa, setSearchKhoa] = useState('');

   //Xử lý load khoa
   const getAllKhoas = async (page = 0, size = 6, search) => {
    try{
        setCurrentPageKhoa(page);
     const { data } = await getKhoas(searchKhoa,page,size);
     setDataKhoa(data);
    } catch(error) {
        console.log(error);
    }
};

useEffect(() => {
  if(!tokenLogin) {
    navigate('/')
  }
  getAllKhoas();
}, []);

//Xử lý search
const handleSearchKhoa = (searchKhoa) => {
  getAllKhoas(0,6,searchKhoa);
};


    //Xử lý thêm mới đoàn viên
    const handleNewKhoa = async (values) => {
      try {
        const { data } = await saveKhoa(values);  
        getAllKhoas();
        toastSuccess("thêm thành công một đoàn khoa");
      } catch(error) {
        console.log(error);
        toastError(error.response.data || error.message);
      }
    }

    //Xử lý thêm mới đoàn viên và validate bằng formik
    const formik = useFormik({
      initialValues: {
        maKhoa: '',
        tenKhoa: '',
        email: '',
        dienThoai: '',
      },
      validationSchema: Yup.object({
        maKhoa: Yup.string().required("Tên khoa không được để trống"),
        tenKhoa: Yup.string().required("Tên khoa không được để trống").min(3,"Tên phải có tối thiểu 3 ký tự"),
        email: Yup.string().required('Email không được để trống').matches(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/, 'Hãy nhập đúng định dạng email'),
        dienThoai: Yup.string().required('Điện thoại không được để trống').matches(/^[0-9]{10}$/, 'Hãy nhập đúng định dạng số điện thoại'),
      }),
      onSubmit: (values) => {
      handleNewKhoa(values);
      formik.resetForm();
      }
    })




 // Xử lý popup modal
 const modal = document.querySelector('.js-modal');
 //hàm hiển thị modal tạo mới(thêm class open vào modal)
  function showModal() {
     modal.classList.add('open');
 }
 //hàm ẩn modal mua vé (gỡ bỏ class open vào modal)
  function closeModal() {
     modal.classList.remove('open');
 }
 const toogelModal = (show) => show ? showModal('open') : closeModal('open');


 //Hàm xử lý xóa đoàn viên
 const handleDeleteKhoa = async (id) => {
  await deleteKhoa(id);
  getAllKhoas();
 }

  return (
    <>
     <section className='main'>
       <HeaderKhoa toogelModal={toogelModal}
         handleSearchKhoa={handleSearchKhoa}
         searchKhoa={searchKhoa}
         setSearchKhoa={setSearchKhoa}
       />
       <ListKhoa   dataKhoa = {dataKhoa} 
        currentPageKhoa = {currentPageKhoa} 
        getAllKhoas = {getAllKhoas}
        handleDeleteKhoa={handleDeleteKhoa}
       />
    </section>

    <div className="modal js-modal" id="modal">
        <form onSubmit={formik.handleSubmit} className="form" id="form-1">
        <div onClick={() => toogelModal(false)} className="modal-close js-modal-close">
        <UilX/>
        </div>
          <h3 className="heading">Thêm mới khoa</h3>      
          <div className="spacer"></div>
      
          <div className="form-group">
            <div className="form-group__containt">

            <div className="form-group__item">
                <label htmlFor="maKhoa" className="form-label">Mã khoa</label>
                <input 
                  value={formik.values.maKhoa} 
                  id="maKhoa" name="maKhoa" 
                  onChange={formik.handleChange} 
                  placeholder='VD: CNTT'
                  type="text" className="form-control"/>
                {formik.errors.maKhoa && (
                 <span className="form-message">{formik.errors.maKhoa}</span>
                )}
              </div>

              <div className="form-group__item">
                <label htmlFor="tenKhoa" className="form-label">Tên khoa</label>
                <input 
                       value={formik.values.tenKhoa} 
                       id="tenKhoa" 
                       name="tenKhoa" 
                       onChange={formik.handleChange} 
                       type="text" placeholder="VD: Công nghệ thông tin" className="form-control"/>
                {formik.errors.tenKhoa && (
                 <span className="form-message">{formik.errors.tenKhoa}</span>
                )}
              </div>
           
            </div>
           
          </div>
               
          <div className="form-group">
           <div className="form-group__containt">
              <div className="form-group__item">
                <label htmlFor="email" className="form-label">Email</label>
                <input 
                  value={formik.values.email} 
                  id="email" name="email" 
                  onChange={formik.handleChange} 
                  placeholder='VD: test@gmail.com'
                  type="text" className="form-control"/>
                {formik.errors.email && (
                 <span className="form-message">{formik.errors.email}</span>
                )}
              </div>
              <div className="form-group__item">
                <label htmlFor="dienThoai" className="form-label">Điện thoại</label>
                <input 
                  value={formik.values.dienThoai} 
                  id="dienThoai" name="dienThoai" 
                  onChange={formik.handleChange}
                  type="text" placeholder="VD: 0123456789" className="form-control"/>
                {formik.errors.dienThoai && (
                 <span className="form-message">{formik.errors.dienThoai}</span>
                )}
              </div>
           </div>
          </div>
  
          <button type="submit" className="form-submit">Thêm</button>
        </form>
    </div>
    </>
  )
}

export default IndexKhoa