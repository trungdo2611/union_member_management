import React,{ useEffect, useState } from 'react'
import { useFormik } from "formik";
import * as Yup from 'yup';
import { toastError, toastSuccess } from "../../api/ToastService";
import { UilX } from "@iconscout/react-unicons";
import { getKhoaSelect } from "../../api/KhoaService";
import HearderChiDoi from '../HeaderChiDoi/HearderChiDoi';
import ListChiDoi from '../ListChiDoi/ListChiDoi';
import { deleteChiDoan, getChiDoans, saveChiDoan } from '../../api/ChiDoanService';
import { useNavigate } from 'react-router-dom';
const IndexChiDoi = () => {
    const navigate = useNavigate();
    const [dataChiDoi,setDataChiDoi] = useState({});
    const[currentPageChiDoi, setCurrentPageChiDoi] = useState(0);
    const [searchChiDoi, setSearchChiDoi] = useState('');
    const [khoaList, setKhoaList] = useState([]);
    const tokenLogin = window.localStorage.getItem('token');
    //Xử lý load chi đội
    const getAllChiDois = async (page = 0, size = 6, search) => {
      try{
          setCurrentPageChiDoi(page);
       const { data } = await getChiDoans(searchChiDoi,page,size);
       setDataChiDoi(data);
      } catch(error) {
          console.log(error);
      }
  };

  useEffect(() => {
    if(!tokenLogin) {
      navigate('/')
    }
    getAllChiDois();
}, []);

  //Xử lý search
  const searchChiDois = (searchChiDoi) => {
    getAllChiDois(0,6,searchChiDoi);
  };

  //Xử lý load khoa vào select-option 
  const getAllKhoaSelect = async () => {
    try{
      const { data } = await getKhoaSelect();
      setKhoaList(data);
    } catch(error) {
      console.log(error);
    }
  }

  useEffect(() => {
   getAllKhoaSelect();
}, []);

    //Xử lý thêm mới chi đoàn
    const handleNewChiDoan = async (values) => {
      try {
        const { data } = await saveChiDoan(values);
        getAllChiDois();
        toastSuccess(data);
      } catch(error) {
        console.log(error);
        toastError(error.response.data || error.message);
      }
    }

    //Xử lý thêm mới đoàn viên và validate bằng formik
    const formik = useFormik({
      initialValues: {
        tenChiDoan: '',
        diaChi: '',
        dienThoai: '',
        khoa_id: '',
      },
      validationSchema: Yup.object({
        tenChiDoan: Yup.string().required("Tên không được để trống").min(3,"Tên phải có tối thiểu 3 ký tự"),
        diaChi: Yup.string().required('Địa chỉ không được để trống').min(3,"Địa chỉ phải có tối thiểu 3 ký tự"),
        dienThoai: Yup.string().required('Điện thoại không được để trống').matches(/^[0-9]{10}$/, 'Hãy nhập đúng định dạng số điện thoại'),
        khoa_id: Yup.string().required('Khoa không được để trống'),
      }),
      onSubmit: (values) => {
      handleNewChiDoan(values);
      formik.resetForm();
      }
    })


    //Hàm xử lý xóa đoàn viên
  const handleDeleteChiDoi = async (id) => {
    await deleteChiDoan(id);
    getAllChiDois();
}

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

  return (
    <>
    <section className='main'>
        <HearderChiDoi 
          toogelModal={toogelModal}
         searchChiDois={searchChiDois}
         searchChiDoi={searchChiDoi}
         setSearchChiDoi={setSearchChiDoi}
        />
        <ListChiDoi 
        dataChiDoi = {dataChiDoi} 
        currentPageChiDoi = {currentPageChiDoi} 
        getAllChiDois = {getAllChiDois}
        handleDeleteChiDoi = {handleDeleteChiDoi}
        />
    </section>

    <div className="modal js-modal" id="modal">
        <form onSubmit={formik.handleSubmit} className="form" id="form-1">
        <div onClick={() => toogelModal(false)} className="modal-close js-modal-close">
        <UilX/>
        </div>
          <h3 className="heading">Thêm mới chi đoàn</h3>      
          <div className="spacer"></div>
      
          <div className="form-group">
            <div className="form-group__containt">
              <div className="form-group__item">
                <label htmlFor="tenChiDoan" className="form-label">Tên chi đội</label>
                <input 
                       value={formik.values.tenChiDoan} 
                       id="tenChiDoan" 
                       name="tenChiDoan" 
                       onChange={formik.handleChange} 
                       type="text" placeholder="VD: Công nghệ thông tin" className="form-control"/>
                {formik.errors.tenChiDoan && (
                 <span className="form-message">{formik.errors.tenChiDoan}</span>
                )}
              </div>
           
              <div className="form-group__item">
                <label htmlFor="khoa_id" className="form-label">Khoa</label>
                <select 
                   value={formik.values.khoa_id}
                   name="khoa_id" 
                   onChange={formik.handleChange}
                  className="form-control">
                    <option value="">-- Chọn khoa --</option>
                {khoaList.map((khoa) => (
                    <option key={khoa.id} value={khoa.id}>
                        {khoa.tenKhoa}
                    </option>
                ))}
                </select>
                {formik.errors.khoa_id && (
                 <span className="form-message">{formik.errors.khoa_id}</span>
                )}
              </div>    
            </div>
           
          </div>
               
          <div className="form-group">
           <div className="form-group__containt">
           <div className="form-group__item">
                <label htmlFor="diaChi" className="form-label">Địa chỉ</label>
                <input 
                  value={formik.values.diaChi} 
                  id="diaChi" name="diaChi" 
                  onChange={formik.handleChange} 
                  placeholder='VD: Bình Thuận'
                  type="text" className="form-control"/>
                {formik.errors.diaChi && (
                 <span className="form-message">{formik.errors.diaChi}</span>
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

export default IndexChiDoi