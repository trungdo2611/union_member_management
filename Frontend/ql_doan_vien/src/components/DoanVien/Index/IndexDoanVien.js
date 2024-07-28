import React,{ useEffect, useRef, useState } from "react";
import ListDoanVien from "../ListDoanVien/ListDoanVien";
import { deleteDoanVien, getDoanViens, saveDoanVien, updatePhoto } from "../../api/DoanVienService";
import HeaderDoanVien from "../HeaderDoanVien/HeaderDoanVien";
import './IndexDoanVien.css';
import { UilX } from "@iconscout/react-unicons";
import { getKhoaSelect } from "../../api/KhoaService";
import { getChiDoanSelectByKhoa } from "../../api/ChiDoanService";
import { useFormik } from "formik";
import * as Yup from 'yup';
import { toastError, toastSuccess } from "../../api/ToastService";
import { useNavigate } from 'react-router-dom';
const IndexDoanVien = () => {
    const tokenLogin = window.localStorage.getItem('token');
    const navigate = useNavigate();
    const [khoaList, setKhoaList] = useState([]);
    const [selectedKhoa, setSelectedKhoa] = useState('');
    const [chiDoanList, setChiDoanList] = useState([]);
    const fileRef = useRef();
    const [data,setData] = useState({});
    const[currentPage, setCurrentPage] = useState(0);
    const [file,setFile] = useState(undefined);
    const [searchTerm, setSearchTerm] = useState('');

    //Xử lý thêm mới đoàn viên
    const handleNewDoanVien = async (values) => {
      try {
        const { data } = await saveDoanVien(values);
        console.log(data);
        const formData = new FormData();
        formData.append('id', data.doanVienId);
        formData.append('file', file, file.name);  
        const {data: photoURL} = await updatePhoto(formData);
        console.log(photoURL);
        setFile(undefined);
        fileRef.current.value = null; 
        setSelectedKhoa('');
        getAllDoanViens();
        toastSuccess(data.message);
      } catch(error) {
        console.log(error);
        toastError(error.response.data || error.message);
      }
    }

    //Xử lý thêm mới đoàn viên và validate bằng formik
    const formik = useFormik({
      initialValues: {
        tenDoanVien: '',
        ngaySinh: '',
        gioiTinh: '',
        email: '',
        dienThoai: '',
        ngayVaoDoan: '',
        chiDoan_id: '',
        chucVu_id: '',
        danToc: '',
      },
      validationSchema: Yup.object({
        tenDoanVien: Yup.string().required("Tên không được để trống").min(3,"Họ và tên phải có tối thiểu 3 ký tự"),
        ngaySinh: Yup.string().required('Ngày sinh không được để trống'),
        gioiTinh: Yup.string().required('Giới tính không được để trống'),
        email: Yup.string().required('email không được để trống').matches(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/, 'Hãy nhập đúng định dạng email'),
        dienThoai: Yup.string().required('Điện thoại không được để trống').matches(/^[0-9]{10}$/, 'Hãy nhập đúng định dạng số điện thoại'),
        ngayVaoDoan: Yup.string().required('Ngày vào đoàn không được để trống'),
        chiDoan_id: Yup.string().required('Chi đoàn không được để trống'),
        chucVu_id: Yup.string().required('Chức vụ không được để trống'),
        danToc: Yup.string().required('Dân tộc không được để trống'),
      }),
      onSubmit: (values) => {
      handleNewDoanVien(values);
      formik.resetForm();
      }
    })

    //Xử lý preview file 
    useEffect(() => {
      return () => {
        file && URL.revokeObjectURL(file.preview);
      }
    },[file])
    const handlePreivewFile = (e) => {
       const fileHinhAnh = e.target.files[0];
       fileHinhAnh.preview = URL.createObjectURL(fileHinhAnh);
      setFile(fileHinhAnh);
    }

    //Xử lý load đoàn viên
    const getAllDoanViens = async (page = 0, size = 6, search) => {
        try{
            setCurrentPage(page);
         const { data  } = await getDoanViens(searchTerm,page, size);
         setData(data);
                   
        } catch(error) {
            console.log(error);
        }
    };

    useEffect(() => {
      if(!tokenLogin) {
        navigate('/')
      }  
      getAllDoanViens();
  
  }, []);

 

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

  //Xử lý thay đổi select khoa
  const handleChangeKhoa = (e) => {
    setSelectedKhoa(e.target.value); // Cập nhật giá trị được chọn vào state
};

  //Xử lý load chi đoàn vào select-option
    const getChiDoanByKhoa = async () => {
        try {
          if(selectedKhoa) {
            const {data} = await getChiDoanSelectByKhoa(selectedKhoa);
            setChiDoanList(data);
            console.log(data);
          }
        } catch (error) {
          console.log(error);
        }
    }

    useEffect(() => {
      getChiDoanByKhoa();
   }, [selectedKhoa]);

  //Xử lý search
  const searchDoanVien = (searchTerm) => {
    // const {data} =  await getDoanViens(searchTerm, page, size);
    // setData(data);
    getAllDoanViens(0,6,searchTerm);
    console.log('Đang tìm kiếm đoàn viên với từ khóa:', searchTerm);
  };

  //Hàm xử lý xóa đoàn viên
  const handleDeleleDoanVien = async (id) => {
        await deleteDoanVien(id);
        getAllDoanViens();
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
         <section className="main">
        <HeaderDoanVien toogelModal={toogelModal}
                        searchDoanVien={searchDoanVien}
                        searchTerm={searchTerm}
                        setSearchTerm={setSearchTerm}
       />
        <ListDoanVien data={data} currentPage= {currentPage} getAllDoanViens={getAllDoanViens} deleteDoanVien={handleDeleleDoanVien}/>
        </section>

        <div className="modal js-modal" id="modal">
        <form onSubmit={formik.handleSubmit} className="form" id="form-1">
        <div onClick={() => toogelModal(false)} className="modal-close js-modal-close">
        <UilX/>
        </div>
          <h3 className="heading">Thêm mới đoàn viên</h3>      
          <div className="spacer"></div>
      
          <div className="form-group">
            <div className="form-group__containt">
              <div className="form-group__item">
                <label htmlFor="tenDoanVien" className="form-label">Tên đầy đủ</label>
                <input 
                       value={formik.values.tenDoanVien} 
                       id="tenDoanVien" 
                       name="tenDoanVien" 
                       onChange={formik.handleChange} 
                       type="text" placeholder="VD: Xuân Trung" className="form-control"/>
                {formik.errors.tenDoanVien && (
                 <span className="form-message">{formik.errors.tenDoanVien}</span>
                )}
              </div>
           
                <div className="form-group__item">
                      <label htmlFor="email" className="form-label">Email</label>
                      <input 
                        value={formik.values.email} 
                        id="email" name="email" 
                        onChange={formik.handleChange} 
                        type="text" placeholder="VD: email@domain.com" className="form-control"/>
                       {formik.errors.email && (
                 <span className="form-message">{formik.errors.email}</span>
                )}
                </div>
            </div>
           
          </div>
      
          <div className="form-group">
           <div className="form-group__containt">
              <div className="form-group__item">
                <label htmlFor="ngaySinh" className="form-label">Ngày sinh</label>
                <input 
                  value={formik.values.ngaySinh} 
                  id="ngaySinh" name="ngaySinh" 
                  onChange={formik.handleChange} 
                  type="date" placeholder="" className="form-control"/>
                {formik.errors.ngaySinh && (
                 <span className="form-message">{formik.errors.ngaySinh}</span>
                )}
              </div>

              <div className="form-group__item">
                <label htmlFor="ngayVaoDoan" className="form-label">Ngày vào đoàn</label>
                <input 
                  value={formik.values.ngayVaoDoan} 
                  id="ngayVaoDoan" name="ngayVaoDoan" 
                  onChange={formik.handleChange} 
                  type="date" placeholder="" className="form-control"/>
               {formik.errors.ngayVaoDoan && (
                 <span className="form-message">{formik.errors.ngayVaoDoan}</span>
                )}
              </div>
           </div>
          </div>

          <div className="form-group">
           <div className="form-group__containt">
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

              <div className="form-group__item">
                <label htmlFor="danToc" className="form-label">Dân tộc</label>
                <input 
                  value={formik.values.danToc} 
                  id="danToc" name="danToc" 
                  onChange={formik.handleChange}
                  type="text" placeholder="VD: Ê đê" className="form-control"/>
               {formik.errors.danToc && (
                 <span className="form-message">{formik.errors.danToc}</span>
                )}
              </div>
  
           </div>
          </div>


          <div className="form-group">
           <div className="form-group__containt form-group__item--select">
              <div className="form-group__item">
                <label htmlFor="" className="form-label">Khoa</label>
                <select 
                  value={selectedKhoa} onChange={handleChangeKhoa}
                  className="form-control">
                    <option value="">-- Chọn khoa --</option>
                {khoaList.map((khoa) => (
                    <option key={khoa.id} value={khoa.id}>
                        {khoa.tenKhoa}
                    </option>
                ))}
                </select>
                <span className="form-message"></span>
              </div>

              <div className="form-group__item">
                <label htmlFor="chiDoan_id" className="form-label">Chi đoàn</label>
                <select  
                  disabled={!selectedKhoa}
                  value={formik.values.chiDoan_id}
                  name="chiDoan_id" 
                  className="form-control" 
                  onChange={formik.handleChange}>
                 <option value="">-- Chọn chi đoàn --</option>
                {chiDoanList.map((chiDoan) => (
                    <option key={chiDoan.id} value={chiDoan.id}>
                        {chiDoan.tenChiDoan}
                    </option>
                ))}
                </select>
                {formik.errors.chiDoan_id && (
                 <span className="form-message">{formik.errors.chiDoan_id}</span>
                )}
              </div>

           </div>
          </div>

          <div className="form-group">
           <div className="form-group__containt form-group__item--select">
             
              <div className="form-group__item">
                <label htmlFor="chucVu_id" className="form-label">Chức vụ</label>
                <select 
                  value={formik.values.chucVu_id}
                  id="chucVu_id" name="chucVu_id" 
                  className="form-control" 
                  onChange={formik.handleChange}> 
                  <option value=''>--Chọn--</option>               
                  <option value={1}>Bí Thư</option>
                  <option value={2}>Phó Bí Thư</option>
                  <option value={3}>Đoàn Viên</option>
                </select>
                {formik.errors.chucVu_id && (
                 <span className="form-message">{formik.errors.chucVu_id}</span>
                )}
              </div>

              <div className="form-group__item">
                <label htmlFor="gioiTinh" className="form-label">Giới tính</label>
                <select 
                  value={formik.values.gioiTinh} 
                  id="gioiTinh" 
                  name="gioiTinh" 
                  className="form-control" 
                  onChange={formik.handleChange}>
                  <option value=''>--Chọn--</option>  
                  <option value={"true"}>Nam</option>
                  <option value={"false"}>Nữ</option>
                </select>
                {formik.errors.gioiTinh && (
                 <span className="form-message">{formik.errors.gioiTinh}</span>
                )}
              </div>
      
           </div>
          </div>

          
          <div className="form-group">
            <label htmlFor="hinhAnh" className="form-label">Chọn hình ảnh</label>
            {file && (
              <img src={file.preview} alt="" width={"240px"}/>
            )}
            <input 
            required
            ref={fileRef} id="hinhAnh" name="hinhAnh" 
            onChange={handlePreivewFile} type="file" className="form-control"/>
            <span className="form-message"></span>
          </div>
       
          <button type="submit" className="form-submit">Thêm</button>
        </form>
        </div>
        </>
     );
}
 
export default IndexDoanVien;