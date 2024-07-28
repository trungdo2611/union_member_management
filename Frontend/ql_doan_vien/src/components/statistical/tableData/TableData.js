import React,{useState, useEffect} from 'react';
import './tableData.css'
import { thongKeDoanVien } from '../../api/DoanVienService';

const TableData = () => {
  const [tkDoanVien, setTkDoanVien] = useState([]);
    //Xử lý lấy chi tiết tổng số đoàn viên trong khoa
  const fetchThongKeDoanVien = async () => {
  try{
    const {data} = await thongKeDoanVien();
    setTkDoanVien(data);
  } catch(error) {
    console.log(error);
  }
  }

  useEffect(() => {
  fetchThongKeDoanVien();
  },[]);

 return ( 
     <section className = "content-area-table">
         <div className="data-table-info">
             <h1 className="data-table-title"> Danh sách tổng số đoàn viên</h1>
         </div>
         <div className="data-table-diagram">
             <table>
                 <thead> 
                     <tr>
                         <th scope="col">STT</th>
                         <th scope="col">Đoàn khoa</th>
                         <th scope="col">Số đoàn viên</th>
                     </tr>
                 </thead>
                 <tbody>
                     { tkDoanVien &&
                         tkDoanVien.map((dataItem, index) => {
                                 return (
                                     <tr key={dataItem.id}>
                                         <td>{index + 1}</td>
                                         <td>{dataItem.tenKhoa}</td>
                                         <td>{dataItem.totalDoanVien}</td>
                                     </tr>
                                 )
                         })
                     }
                 </tbody>
             </table>
         </div>
     </section>
  );
}

export default TableData