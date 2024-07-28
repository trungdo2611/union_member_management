import React from 'react';
import Swal from 'sweetalert2';
import { UilEye,UilTrashAlt,UilNotebooks} from '@iconscout/react-unicons';
import { Link } from "react-router-dom";

const ListKhoa = ({dataKhoa, currentPageKhoa, getAllKhoas, handleDeleteKhoa}) => {
    const handleSweetAlert = (tenKhoa, id) => {
        Swal.fire({
            title: "Xóa đoàn khoa ?",
            text: `Bạn có chắc muốn xóa đoàn khoa ${tenKhoa}?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#ff919d",
            confirmButtonText: "Có, xóa đi!",
            cancelButtonText: "Không , chờ chút!"
          }).then((result) => {
            if (result.isConfirmed) {
                handleDeleteKhoa(id);
              Swal.fire({
                title: "Đã xóa!",
                text: "Đoàn khoa đã bị xóa.",
                icon: "success"
              });
            }
          });
    }

  return (
   <>
   <div className="doanvien__list">
            <div className="doanVien__contain">
            <section className = "content-area-table tableChiDoi">
            <div className="data-table-info">
                <h1 className="data-table-title titleChiDoi"> Danh sách Đoàn Khoa</h1>
            </div>
            <div className="data-table-diagram">
            {dataKhoa?.content?.length === 0 && <div>Không tìm thấy đoàn khoa nào</div>}
            {dataKhoa?.content?.length > 0 &&
                <table>
                    <thead> 
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Mã Khoa</th>
                            <th scope="col">Tên Khoa</th>
                            <th scope="col">Email</th>
                            <th scope="col">Điện thoại</th>
                            <th scope="col">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            dataKhoa.content.map((data,index) => {
                                    return (
                                        <tr key={data.id}>
                                            <td>{index + 1}</td>
                                            <td>{data.maKhoa}</td>
                                            <td>{data.tenKhoa}</td>
                                            <td>{data.email}</td>
                                            <td>{data.dienThoai}</td> 
                                            <td>
                                                <Link title="xem chi tiết" to={`/qldv/khoa/detail/${data.id}`} className="item__icon icontable"><UilEye/></Link>
                                                <Link title="chỉnh sửa" to={`/qldv/khoa/edit/${data.id}`} className="item__icon icontable"><UilNotebooks/></Link>
                                                <Link onClick={()=>handleSweetAlert(data.tenKhoa, data.id)} title="xóa" className="item__icon icontable"><UilTrashAlt/></Link>
                                            </td>                                       
                                        </tr>                                     
                                    )      
                            })    
                        }
                    </tbody>
                </table>
}
            </div>
        </section>    
            </div>
                
            {dataKhoa?.content?.length > 0 && dataKhoa?.totalPages > 0 &&
                <div className="pagination">
              
                     <a onClick={() => getAllKhoas(currentPageKhoa -1)} className={0 === currentPageKhoa ? 'disablePage' : ''}>&laquo;</a>
                                        
                    {dataKhoa && [...Array(dataKhoa.totalPages).keys()].map((page, index) => 
                    <a onClick={() => getAllKhoas(page)} className={currentPageKhoa === page ? 'activePage' : ''} key={page}>{page +1}</a>)}
                
                    <a onClick={() => getAllKhoas(currentPageKhoa + 1)} className={dataKhoa.totalPages === currentPageKhoa + 1 ? 'disablePage' : ''}>&raquo;</a>
                    
                </div>
                }
                
   </div>
   </>
  )
}

export default ListKhoa