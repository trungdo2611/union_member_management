import React from "react";
import "./DoanVien.css";
import { UilEye,
    UilTrashAlt,
    UilNotebooks
} from '@iconscout/react-unicons'
import { Link } from "react-router-dom";
import Swal from 'sweetalert2';
const DoanVien = ({doanvien, deleteDoanVien}) => {
    const handleSweetAlert = (tenDoanVien) => {
        Swal.fire({
            title: "Xóa đoàn viên ?",
            text: `Bạn có chắc muốn xóa đoàn viên ${tenDoanVien}?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#ff919d",
            confirmButtonText: "Có, xóa đi!",
            cancelButtonText: "Không , chờ chút!"
          }).then((result) => {
            if (result.isConfirmed) {
                deleteDoanVien(doanvien.id);
              Swal.fire({
                title: "Đã xóa!",
                text: "Đoàn viên đã bị xóa.",
                icon: "success"
              });
            }
          });
    }
    return ( 
        <div className="DoanVien">
            <div className="image-content">
                <span className="overlay"></span>
                <div className="card-image">
                        <img className="card-img" src={doanvien.hinhAnh} alt={doanvien.tenDoanVien}/>
                </div>
            </div>

            <div className="card-content">
                <h2 className="card-name">{doanvien.tenDoanVien}</h2>
                <h4>Đoàn khoa: {doanvien.tenKhoa}</h4>
                <h5>Chi Đội: {doanvien.tenChiDoan}</h5>
                <p>Email: {doanvien.email.substring(0,20)}</p>
                <p>Chức vụ: {doanvien.tenChucVu} - Quyền: {doanvien.tenQuyen}</p>
                <p> Ngày vào đoàn: {doanvien.ngayVaoDoan}</p>
                {/* <p>Giới tính: nam - Dân tộc: Kinh</p>
                <p>Nơi sinh: Mỹ - Ngày sinh: 26/11/2003</p> */}
                <div className="icons">
                    <Link title="xem chi tiết" to={`/qldv/doanvien/detail/${doanvien.id}`} className="item__icon"><UilEye/></Link>
                    <Link title="chỉnh sửa" to={`/qldv/doanvien/edit/${doanvien.id}`} className="item__icon"><UilNotebooks/></Link>
                    <Link onClick={()=>handleSweetAlert(doanvien.tenDoanVien)} title="xóa" className="item__icon"><UilTrashAlt/></Link>
                </div>
            </div>
        </div>
     );
}
 
export default DoanVien;