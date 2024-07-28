import React from 'react'
import {Routes, Route } from 'react-router-dom'
import SoTayList from './SoTayList'

const SoTayComponent = () => {
  return (
    <Routes path='/sotay' element={<SoTayComponent/>}>
        <Route path='/:id' element={ <SoTayList/>}/>
    </Routes>
  )
}

export default SoTayComponent