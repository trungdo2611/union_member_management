import React from 'react';
import { UilEye,UilTrashAlt,UilNotebooks} from '@iconscout/react-unicons';
import { Link } from "react-router-dom";
import  './ListChiDoi.css';
import Swal from 'sweetalert2';

const ListChiDoi = ({dataChiDoi, currentPageChiDoi, getAllChiDois, handleDeleteChiDoi}) => {

    const handleSweetAlert = (tenChiDoan, id) => {
        Swal.fire({
            title: "Xóa chi đội ?",
            text: `Bạn có chắc muốn xóa chi đội ${tenChiDoan}?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#ff919d",
            confirmButtonText: "Có, xóa đi!",
            cancelButtonText: "Không , chờ chút!"
          }).then((result) => {
            if (result.isConfirmed) {
                handleDeleteChiDoi(id);
              Swal.fire({
                title: "Đã xóa!",
                text: "Chi đội đã bị xóa.",
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
                <h1 className="data-table-title titleChiDoi"> Danh sách Chi Đoàn</h1>
            </div>
            <div className="data-table-diagram">
            {dataChiDoi?.content?.length === 0 && <div>Không tìm thấy chi đội nào</div>}
            {dataChiDoi?.content?.length > 0 &&
                <table>
                    <thead> 
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Mã chi đoàn</th>
                            <th scope="col">Tên chi đoàn</th>
                            <th scope="col">Tên Khoa</th>
                            <th scope="col">Địa chỉ</th>
                            <th scope="col">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            dataChiDoi.content.map((data,index) => {
                                    return (
                                        <tr key={data.id}>
                                            <td>{index + 1}</td>
                                            <td>{data.maChiDoan}</td>
                                            <td>{data.tenChiDoan}</td>
                                            <td>{data.tenKhoa}</td>
                                            <td>{data.diaChi}</td> 
                                            <td>
                                                <Link title="xem chi tiết" to={`/qldv/chidoi/detail/${data.id}`} className="item__icon icontable"><UilEye/></Link>
                                                <Link title="chỉnh sửa" to={`/qldv/chidoi/edit/${data.id}`} className="item__icon icontable"><UilNotebooks/></Link>
                                                <Link onClick={()=>handleSweetAlert(data.tenChiDoan, data.id)} title="xóa" className="item__icon icontable"><UilTrashAlt/></Link>
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
                
            {dataChiDoi?.content?.length > 0 && dataChiDoi?.totalPages > 0 &&
                <div className="pagination">
              
                     <a onClick={() => getAllChiDois(currentPageChiDoi -1)} className={0 === currentPageChiDoi ? 'disablePage' : ''}>&laquo;</a>
                                        
                    {dataChiDoi && [...Array(dataChiDoi.totalPages).keys()].map((page, index) => 
                    <a onClick={() => getAllChiDois(page)} className={currentPageChiDoi === page ? 'activePage' : ''} key={page}>{page +1}</a>)}
                
                    <a onClick={() => getAllChiDois(currentPageChiDoi + 1)} className={dataChiDoi.totalPages === currentPageChiDoi + 1 ? 'disablePage' : ''}>&raquo;</a>
                    
                </div>
                }
                
   </div>
    </>
  );
}

export default ListChiDoi