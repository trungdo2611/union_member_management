import React,{useState, useEffect} from 'react';
import "./Cards.css"
import  Card from "../Cards/Card/Card";
import {UilUserCheck ,UilUsersAlt ,UilNotes} from "@iconscout/react-unicons";
import { totalDoanVien } from '../../api/DoanVienService';
import { countDoanVienInDoanPhi, totalDoanPhi } from '../../api/DoanPhiService';
    
const Cards = ({ onCardClick }) => {
    const [totalDV, setTotalDV] = useState(0);
    const [totalDVInDP, setToTalDVInDP] = useState(0);
    const [totalDP, setToTalDP] = useState(0);

    //Hàm load tổng số đoàn viên
    const fetchTotalDoanVien = async () => {
        try{    
                const {data} = await totalDoanVien();
                console.log(data);
                setTotalDV(data);
        } catch(error) {
            console.log(error);
        }
    }

    useEffect(() => {
        fetchTotalDoanVien();
    },[]);

     //Hàm load tổng số đoàn viên trong đoàn phí
     const fetchTotalDVInDP = async () => {
        try{    
                const {data} = await countDoanVienInDoanPhi();
                console.log(data);
                setToTalDVInDP(data);
        } catch(error) {
            console.log(error);
        }
    }

    useEffect(() => {
        fetchTotalDVInDP();
    },[]);

 //Hàm load tổng số đoàn phí
 const fetchTotalDoanPhi = async () => {
    try{    
            const {data} = await totalDoanPhi();
            console.log(data);
            setToTalDP(data);
    } catch(error) {
        console.log(error);
    }
}

useEffect(() => {
    fetchTotalDoanPhi();
},[]);

    return (  
        <section className='Cards'>
           <Card
            onClick={() => onCardClick('totalDoanVien')}
           cardInfo= {{
            title: "Tổng số đoàn viên",
            value: totalDV,
            viewInfo: "xem chi tiết",
            icon : <UilUsersAlt/>
           }}
           />

            <Card 
            onClick={() => onCardClick('doanVienDoanPhi')}
           cardInfo= {{
            title: "Số đoàn viên đã đóng đoàn phí",
            value: totalDVInDP,
            viewInfo: "xem chi tiết",
            icon : <UilUserCheck/>
           }}
           />

            <Card 
           cardInfo= {{
            title: "Tổng số đoàn phí",
            value: `${totalDP} VNĐ`, 
            icon : <UilNotes/>
           }}
           />

        </section>
    );
}
 
export default Cards;