import React from 'react'
import IndexDoanVien from './Index/IndexDoanVien'
import { Route, Routes } from 'react-router-dom'
import EditDoanVien from './Edit/EditDoanVien'
import DetailDoanVien from './Detail/DetailDoanVien'
const DoanVienComponent = () => {
  return (
    <>    
    <Routes path='/doanvien' element={<DoanVienComponent/>}>
        <Route path='/' element={<IndexDoanVien/>}/>
        <Route path='/edit/:id' element={<EditDoanVien/>}/>
        <Route path='/detail/:id' element={<DetailDoanVien/>}/>
    </Routes>
    </>
  )
}

export default DoanVienComponent