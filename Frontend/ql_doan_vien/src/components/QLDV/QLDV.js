import React from 'react'
import { Route, Routes } from "react-router-dom";
import Sidebar from '../sidebar/sidebar';
import Home from '../Home/Home';
import DoanVienComponent from '../DoanVien/DoanVienComponent';
import ChiDoiComponent from "../ChiDoi/ChiDoiComponent.jsx";
import KhoaComponent from "../Khoa/KhoaComponent.jsx";
import DoanPhiComponent from "../DoanPhi/DoanPhiComponent.jsx";
import Statistical from '../statistical/statistical';
import UserInfo from '../User-Info/UserInfo.js';
import SoTayComponent from '../SoTay/SoTayComponent.jsx';
const QLDV = () => {
  return (
    <div className='AppGlass'>      
        <Sidebar/>  
        <UserInfo/>
        <Routes path='/qldv' element={<QLDV/>}> 
            <Route path='/' element={<Home/>}/>
            <Route path="/doanvien/*" element={<DoanVienComponent/>}/>
            <Route path="/chidoi/*" element={<ChiDoiComponent/>}/>
            <Route path="/khoa/*"  element={ <KhoaComponent/>}/>
            <Route path="/doanphi/*" element={<DoanPhiComponent/>}/>
            <Route path="/thongke" element={ <Statistical/>}/> 
            <Route path='/sotay/*' element={<SoTayComponent/>}/>
        </Routes>

    </div>

  )
}

export default QLDV