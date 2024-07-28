import React from 'react'
import { Route, Routes } from 'react-router-dom'
import IndexChiDoi from './Index/IndexChiDoi'
import DetailChiDoi from './Detail/DetailChiDoi'
import EditChiDoi from './Edit/EditChiDoi'

const ChiDoiComponent = () => {
  return (
    <>
    <Routes path='/chidoi' element={<ChiDoiComponent/>}>
        <Route path='/' element={<IndexChiDoi/>}/>
        <Route path='/edit/:id' element={<EditChiDoi/>}/>
        <Route path='/detail/:id' element= {<DetailChiDoi/>}/>
    </Routes>
    </>
  )
}

export default ChiDoiComponent