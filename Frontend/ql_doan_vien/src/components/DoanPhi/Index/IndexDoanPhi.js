import React,{ useEffect, useState } from 'react'
import HeaderDoanPhi from '../HeaderDoanPhi/HeaderDoanPhi';
import ListDoanPhi from '../ListDoanPhi/ListDoanPhi';
import { useFormik } from "formik";
import * as Yup from 'yup';
import { toastError, toastSuccess } from "../../api/ToastService";
import { UilX } from "@iconscout/react-unicons";
import { deleteDoanPhi, getDoanPhis, saveDoanPhi } from '../../api/DoanPhiService';
import { getKhoaSelect } from "../../api/KhoaService";
import { getChiDoanSelectByKhoa } from "../../api/ChiDoanService";
import { getDoanVienSelectByChiDoan } from '../../api/DoanVienService';
import { useNavigate } from 'react-router-dom';

const IndexDoanPhi = () => {
  const tokenLogin = window.localStorage.getItem('token');
  const navigate = useNavigate();
    const [dataDoanPhi,setDataDoanPhi] = useState({});
    const[currentPageDoanPhi, setCurrentPageDoanPhi] = useState(0);
    const [searchDoanPhi, setSearchDoanPhi] = useState('');
    const [khoaList, setKhoaList] = useState([]);
    const [selectedKhoa, setSelectedKhoa] = useState('');
    const [chiDoanList, setChiDoanList] = useState([]);
    const [selectedChiDoan, setSelectedChiDoan] = useState('');
    const [doanVienList, setDoanVienList] = useState([]);
    const [selectedDoanVien, setSelectedDoanVien] = useState('');
     //Xử lý load đoàn phí
     const getAllDoanPhis = async (page = 0, size = 6, search) => {
        try{
            setCurrentPageDoanPhi(page);
         const { data } = await getDoanPhis(searchDoanPhi,page,size);
         setDataDoanPhi(data);
         console.log(data);
        } catch(error) {
            console.log(error);
        }
    };
  
    useEffect(() => {
      if(!tokenLogin) {
        navigate('/')
      }
      getAllDoanPhis();
  }, []);
  
    //Xử lý search
    const handleSearchDoanPhi = (searchDoanPhi) => {
      getAllDoanPhis(0,6,searchDoanPhi);
      console.log('Đang tìm kiếm đoàn viên với từ khóa:', searchDoanPhi);
    };


    //Xử lý thêm mới đoàn phí
    const handleNewDoanPhi = async (values) => {
        try {
          const { data } = await saveDoanPhi(values);
          console.log(data);   
          getAllDoanPhis();
          setSelectedChiDoan('');
          setSelectedKhoa('');
          toastSuccess(data);
        } catch(error) {
          console.log(error);
          toastError(error.response.data || error.message);
        }
      }
  
      //Xử lý thêm mới đoàn viên và validate bằng formik
      const formik = useFormik({
        initialValues: {
            tenPhi: '',
            ngayNop: '',
            soTien: '',
            doanVien_id: '',
        },
        validationSchema: Yup.object({
            tenPhi: Yup.string().required("Tên không được để trống").min(3,"tên phải có tối thiểu 3 ký tự"),
            ngayNop: Yup.string().required('Ngày nộp không được để trống'),
          soTien: Yup.string().required('Số tiền nộp không được để trống').matches(/^[0-9]+$/, 'Hãy nhập đúng định dạng số'),
          doanVien_id: Yup.string().required('Đoàn viên không được để trống'),
        }),
        onSubmit: (values) => {
        handleNewDoanPhi(values);
        formik.resetForm();
        }
      })

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


//Xử lý thay đổi select khoa
const handleChangeChiDoan = (e) => {
    setSelectedChiDoan(e.target.value); // Cập nhật giá trị được chọn vào state
};

    
  //Xử lý load chi đoàn vào select-option
  const getDoanVienByChiDoan = async () => {
    try {
      if(selectedChiDoan) {
        const {data} = await getDoanVienSelectByChiDoan(selectedChiDoan);
        setDoanVienList(data);
        console.log(data);
      }
    } catch (error) {
      console.log(error);
    }
}

useEffect(() => {
    getDoanVienByChiDoan();
}, [selectedChiDoan]);


    //Hàm xử lý xóa đoàn viên
    const handleDeleteDoanPhi = async (id) => {
      await deleteDoanPhi(id);
      getAllDoanPhis();
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
        <HeaderDoanPhi 
         toogelModal={toogelModal}
         handleSearchDoanPhi={handleSearchDoanPhi}
         searchDoanPhi={searchDoanPhi}
         setSearchDoanPhi={setSearchDoanPhi}/>
        <ListDoanPhi
             dataDoanPhi = {dataDoanPhi} 
             currentPageDoanPhi = {currentPageDoanPhi} 
             getAllDoanPhis = {getAllDoanPhis}
             handleDeleteDoanPhi = {handleDeleteDoanPhi}
        />
   </section>
   <div className="modal js-modal" id="modal">
        <form onSubmit={formik.handleSubmit} className="form" id="form-1">
        <div onClick={() => toogelModal(false)} className="modal-close js-modal-close">
        <UilX/>
        </div>
          <h3 className="heading">Thêm mới đoàn phí</h3>      
          <div className="spacer"></div>
      
          <div className="form-group">
            <div className="form-group__containt">
              <div className="form-group__item" style={{width: "100%"}}>
                <label htmlFor="tenPhi" className="form-label">Tên phí</label>
                <input 
                       value={formik.values.tenPhi} 
                       id="tenPhi" 
                       name="tenPhi" 
                       onChange={formik.handleChange} 
                       type="text" placeholder="VD: phí hk1" className="form-control"/>
                {formik.errors.tenPhi && (
                 <span className="form-message">{formik.errors.tenPhi}</span>
                )}
              </div>                 
            </div>
           
          </div>
               
          <div className="form-group">
           <div className="form-group__containt">
           <div className="form-group__item">
                <label htmlFor="ngayNop" className="form-label">Ngày nộp</label>
                <input 
                  value={formik.values.ngayNop} 
                  id="ngayNop" name="ngayNop" 
                  onChange={formik.handleChange} 
                  type="date" className="form-control"/>
                {formik.errors.ngayNop && (
                 <span className="form-message">{formik.errors.ngayNop}</span>
                )}
              </div>
              <div className="form-group__item">
                <label htmlFor="soTien" className="form-label">Số tiền</label>
                <input 
                  value={formik.values.soTien} 
                  id="soTien" name="soTien" 
                  onChange={formik.handleChange}
                  type="text" placeholder="VD: 50000" className="form-control"/>
                {formik.errors.soTien && (
                 <span className="form-message">{formik.errors.soTien}</span>
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
                  value={selectedChiDoan}
                  className="form-control" 
                  onChange={handleChangeChiDoan}>
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
                <label htmlFor="doanVien_id" className="form-label">Đoàn viên</label>
                <select  
                  disabled={!selectedChiDoan || !selectedKhoa}
                  value={formik.values.doanVien_id}
                  name="doanVien_id" 
                  className="form-control" 
                  onChange={formik.handleChange}>
                 <option value="">-- Chọn đoàn viên --</option>
                {doanVienList.map((doanvien) => (
                    <option key={doanvien.id} value={doanvien.id}>
                        {doanvien.tenDoanVien}
                    </option>
                ))}
                </select>
                {formik.errors.doanVien_id && (
                 <span className="form-message">{formik.errors.doanVien_id}</span>
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

export default IndexDoanPhi