import React from 'react';
import Swal from 'sweetalert2';
import { UilEye,UilTrashAlt,UilNotebooks} from '@iconscout/react-unicons';
import { Link } from "react-router-dom";

const ListDoanPhi = ({dataDoanPhi, currentPageDoanPhi, getAllDoanPhis, handleDeleteDoanPhi}) => {
    const handleSweetAlert = (id) => {
        Swal.fire({
            title: "Xóa đoàn phí ?",
            text: `Bạn có chắc muốn xóa chứ?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#ff919d",
            confirmButtonText: "Có, xóa đi!",
            cancelButtonText: "khoan , chờ chút!"
          }).then((result) => {
            if (result.isConfirmed) {
                handleDeleteDoanPhi(id);
              Swal.fire({
                title: "Đã xóa!",
                text: "Xóa thành công!.",
                icon: "success"
              });
            }
          });
    }
  return (
    <div className="doanvien__list">
    <div className="doanVien__contain">
    <section className = "content-area-table tableChiDoi">
    <div className="data-table-info">
        <h1 className="data-table-title titleChiDoi"> Danh sách đoàn phí</h1>
    </div>
    <div className="data-table-diagram">
    {dataDoanPhi?.content?.length === 0 && <div>Không tìm thấy đoàn phí nào</div>}
    {dataDoanPhi?.content?.length > 0 &&
        <table>
            <thead> 
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Mã đoàn viên</th>
                    <th scope="col">Tên đoàn viên</th>
                    <th scope="col">Tên phí</th>
                    <th scope="col">Số tiền</th>
                    <th scope="col">Ngày nộp</th>
                    <th scope="col">Hành động</th>
                </tr>
            </thead>
            <tbody>
                {
                    dataDoanPhi.content.map((data,index) => {
                            return (
                                <tr key={data.id}>
                                    <td>{index + 1}</td>
                                    <td>{data.maDoanVien}</td>
                                    <td>{data.tenDoanVien}</td>
                                    <td>{data.tenPhi}</td>
                                    <td>{data.soTien}</td> 
                                    <td>{data.ngayNop}</td> 
                                    <td>
                                        <Link title="xem chi tiết" to={`/qldv/doanphi/detail/${data.id}`} className="item__icon icontable"><UilEye/></Link>
                                        <Link title="chỉnh sửa" to={`/qldv/doanphi/edit/${data.id}`} className="item__icon icontable"><UilNotebooks/></Link>
                                        <Link onClick={()=>handleSweetAlert(data.id)} title="xóa" className="item__icon icontable"><UilTrashAlt/></Link>
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
        
    {dataDoanPhi?.content?.length > 0 && dataDoanPhi?.totalPages > 0 &&
        <div className="pagination">
      
             <a onClick={() => getAllDoanPhis(currentPageDoanPhi -1)} className={0 === currentPageDoanPhi ? 'disablePage' : ''}>&laquo;</a>
                                
            {dataDoanPhi && [...Array(dataDoanPhi.totalPages).keys()].map((page, index) => 
            <a onClick={() => getAllDoanPhis(page)} className={currentPageDoanPhi === page ? 'activePage' : ''} key={page}>{page +1}</a>)}
        
            <a onClick={() => getAllDoanPhis(currentPageDoanPhi + 1)} className={dataDoanPhi.totalPages === currentPageDoanPhi + 1 ? 'disablePage' : ''}>&raquo;</a>
            
        </div>
        }
        
</div>
  )
}

export default ListDoanPhi