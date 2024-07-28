import React from 'react'
import { Route, Routes } from 'react-router-dom'
import IndexDoanPhi from './Index/IndexDoanPhi'
import DetailDoanPhi from './Detail/DetailDoanPhi'
import EditDoanPhi from './Edit/EditDoanPhi'
const DoanPhiComponent = () => {
  return (  
    <Routes path='/doanphi' element={<DoanPhiComponent/>}>
      <Route path='/' element={<IndexDoanPhi/>}/>
      <Route path='/edit/:id' element={<EditDoanPhi/>}/>
      <Route path='/detail/:id' element= {<DetailDoanPhi/>}/>
    </Routes>
  )
}

export default DoanPhiComponent