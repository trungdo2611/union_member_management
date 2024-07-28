import React, {useEffect, useState} from 'react';
import { useFormik } from "formik";
import * as Yup from 'yup';
import { loginAdmin } from '../api/LoginService';
import { toastError, toastSuccess } from "../api/ToastService";
import { useNavigate } from 'react-router-dom';
const Login = () => {
  const navigate = useNavigate();
  //Xử lý đăng nhập
  const handleLogin = async (values) => {
    try {
        const {data} = await loginAdmin(values);
       window.localStorage.setItem('token', data.token);
       window.localStorage.setItem('tenDoanVien', data.tenDoanVien);
       window.localStorage.setItem('tenPhanQuyen', data.tenPhanQuyen);
       window.localStorage.setItem('hinhAnh', data.hinhAnh);
       navigate('qldv/');
       toastSuccess('Đăng nhập thành công');
    } catch(error) {
      console.log(error.response.data);
      toastError(error.response.data);
    }
  }

  //Xử lý validate bằng formik
  const formik = useFormik({
    initialValues: {
      email: '',
      password: '',
    },
    validationSchema: Yup.object({
      email: Yup.string().required('Email không được để trống').matches(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/, 'Hãy nhập đúng định dạng email'),
      password: Yup.string().required("Mật khẩu không được để trống"),
    }),
    onSubmit: (values) => {
    handleLogin(values);
    formik.resetForm();
    
    }
  })

  return (
    <form onSubmit={formik.handleSubmit} className="form" id="form-1" style={{width: "30rem"}}>
      <h3 className="heading">Đăng nhập</h3>  
      <p className="desc">Chào mừng bạn đến với web quản lý đoàn viên ❤️</p>    
      <div className="spacer"></div>
             
      <div className="form-group">
       <div className="form-group__containt">
          <div className="form-group__item" style={{width: "100%"}}>
            <label htmlFor="email" className="form-label">Email</label>
            <input 
              value={formik.values.email} 
              id="email" name="email" 
              onChange={formik.handleChange} 
              placeholder='VD: Nhập email'
              type="text" className="form-control"/>
            {formik.errors.email && (
             <span className="form-message">{formik.errors.email}</span>
            )}
          </div>      
       </div>
      </div>

      <div className="form-group">
        <div className="form-group__containt">

        <div className="form-group__item" style={{width: "100%"}}>
            <label htmlFor="password" className="form-label">Mật khẩu</label>
            <input 
              value={formik.values.password} 
              id="password" name="password" 
              onChange={formik.handleChange} 
              placeholder='VD: Nhập mật khẩu'
              type="password" className="form-control"/>
            {formik.errors.password && (
             <span className="form-message">{formik.errors.password}</span>
            )}
          </div>

        </div>
       
      </div>

      <button type="submit" className="form-submit">Đăng nhập</button>
    </form>
  )
}

export default Login