import React,{useState, useEffect} from 'react';
import { thongKeDoanPhi } from '../../api/DoanPhiService';

const DoanPhiThongKe = () => {
    const [tkDoanPhi, setTkDoanPhi] = useState([]);
    //Xử lý lấy chi tiết tổng số đoàn viên trong khoa
  const fetchThongKeDoanPhi = async () => {
  try{
    const {data} = await thongKeDoanPhi();
    setTkDoanPhi(data);
  } catch(error) {
    console.log(error);
  }
  }

  useEffect(() => {
    fetchThongKeDoanPhi();
  },[]);

  return (
    <section className = "content-area-table">
    <div className="data-table-info">
        <h1 className="data-table-title"> Danh sách số đoàn viên đã đóng đoàn phí</h1>
    </div>
    <div className="data-table-diagram">
        <table>
            <thead> 
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Tên đoàn viên</th>
                    <th scope="col">Khoa</th>
                    <th scope="col">Chi đoàn</th>
                    <th scope="col">Số tiền đã đóng</th>
                </tr>
            </thead>
            <tbody>
                { tkDoanPhi &&
                    tkDoanPhi.map((dataItem, index) => {
                            return (
                                <tr key={dataItem.id}>
                                    <td>{index + 1}</td>
                                    <td>{dataItem.tenDoanVien}</td>
                                    <td>{dataItem.tenKhoa}</td>
                                    <td>{dataItem.tenChiDoan}</td>
                                    <td>{dataItem.tongSoTien} VNĐ</td>
                                </tr>
                            )
                    })
                }
            </tbody>
        </table>
    </div>
</section>
  )
}

export default DoanPhiThongKe