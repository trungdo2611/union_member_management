import React, {useState, useEffect} from 'react';
import "./statistical.css"
import Cards from '../statistical/Cards/Cards';
import TableData from './tableData/TableData.js';
import DoanPhiThongKe from './TableDoanPhi/DoanPhiThongKe.js';
import { useNavigate } from 'react-router-dom';
const Statistical = () => {
    const tokenLogin = window.localStorage.getItem('token');
    const navigate = useNavigate();
    const [selectedCard, setSelectedCard] = useState('totalDoanVien');
    useEffect(() => {
        if(!tokenLogin) {
          navigate('/')
        }
       }) 
    // Hàm xử lý sự kiện khi nhấn vào card con
    const handleCardClick = (cardType) => {
        setSelectedCard(cardType);
    };
    return (  
        <div className='Statistical'>
            <h1>Thống kê</h1>
            <Cards onCardClick={handleCardClick}/>
            {selectedCard === 'totalDoanVien' && <TableData />}
            {selectedCard === 'doanVienDoanPhi' && <DoanPhiThongKe />}
        </div>
    );
}
 
export default Statistical;