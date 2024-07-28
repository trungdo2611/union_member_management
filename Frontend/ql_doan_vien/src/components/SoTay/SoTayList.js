import React, {useState, useEffect} from 'react'
import {Link, useParams } from 'react-router-dom'
import { getSoTayList } from '../api/SoTayService';
import './SoTay.css';
import { UilArrowLeft} from '@iconscout/react-unicons';

const SoTayList = () => {
  const {id} = useParams();
  const [sotays, setSoTays] = useState([]);
    //Xử lý lấy danh sách sổ tay theo đoàn viên
  const fetchSoTayList = async (id) => {
  try{
    const {data} = await getSoTayList(id);
    setSoTays(data);
  } catch(error) {
    console.log(error);
  }
  }

  useEffect(() => {
   fetchSoTayList(id);
  },[]);

  return (
    <section className = "content-area-table tabelSoTay">
      <div className='BackArrow'>
      <Link className='Arrow' to={`/qldv/doanvien/detail/${id}`}>< UilArrowLeft/></Link>
    </div>
    <div className="data-table-info">
        <h1 className="data-table-title"> Danh sách nội dung sổ tay</h1>
    </div>
    <div className="data-table-diagram">
    {sotays?.length === 0 && <div>Không tìm thấy nội dung sổ tay nào</div>}
        <table>
            <thead> 
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Nội dung</th>
                </tr>
            </thead>
            <tbody>
                { sotays &&
                    sotays.map((dataItem, index) => {
                            return (
                                <tr key={dataItem.id}>
                                    <td>{index + 1}</td>
                                    <td>{dataItem.noiDung}</td>
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

export default SoTayList