import React from 'react';
import { Route, Routes } from 'react-router-dom';
import IndexKhoa from './Index/IndexKhoa';
import DetailKhoa from './Detail/DetailKhoa';
import EditKhoa from './Edit/EditKhoa';

const KhoaComponent = () => {
  return (
    <>
    
    <Routes path='/khoa' element={<KhoaComponent/>}>
        <Route path='/' element={<IndexKhoa/>}/>
        <Route path='/edit/:id' element={<EditKhoa/>}/>
        <Route path='/detail/:id' element={<DetailKhoa/>}/>
    </Routes>
    </>
  )
}

export default KhoaComponent