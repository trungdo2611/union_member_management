import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './Home.css'
import avatar1 from '../../imgs/chibi-doan-02.png';
import avatar2 from '../../imgs/chibi-doan-03.png';
const Home = () => {
  const navigate = useNavigate();
  const tokenLogin = window.localStorage.getItem('token');

 useEffect(() => {
  if(!tokenLogin) {
    navigate('/')
  }
 }) 
  return (
    <section className='main mainHome'>
     <div className='grow'>
     <h1>Chào mừng đến với website quản lý đoàn viên</h1>
     </div>

     <div className='imgDoanVienChiBi'>
      <img className='doanVienChiBi' src={avatar1} alt=''/>
      <div className='boxTitle'>
        <p>Quản lý thông tin đoàn viên 1 cách thuận tiện !</p>
      </div>
      <img className='doanVienChiBi' src={avatar2} alt=''/>
     </div>
    </section>
  )
}

export default Home