import React, { useEffect, useState } from 'react'

const UserInfo = () => {
    const userName = window.localStorage.getItem('tenDoanVien');
    const avatar = window.localStorage.getItem('hinhAnh');
    const role = window.localStorage.getItem('tenPhanQuyen');
    const [userInfo, setUserInfo] = useState({
        token: '',
        name: '',
        quyen: '',
        hinhAnh: '',
    });

    const handleUser = () => { 
            setUserInfo({
                name: userName,
                quyen: role,
                hinhAnh: avatar
            });

    }

    useEffect(() => {
        handleUser();
    },[]);

  return (
  <>
        <div className="info-user">
            <span>Chào mừng người dùng {userInfo.name} - Quyền : {userInfo.quyen} </span>
            <img src={userInfo.hinhAnh} alt={userInfo.name}/>
        </div>
 
  </>
    
  )
}

export default UserInfo
