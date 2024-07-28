import React, { useState } from 'react';
import './sidebar.css'
import Logo from "../../imgs/logo-doan-thanh-nien.png";
import {SidebarData} from "../Data/Data";
import { UilSignOutAlt } from "@iconscout/react-unicons";
import { Link, useNavigate } from 'react-router-dom';
const Sidebar = () => {
    const [selected, setSelected] = useState(0);
    const navigate = useNavigate();
    const handleLogOut = () => {
      window.localStorage.clear();
      navigate('/');
    }
    return (
        <div className='Sidebar'>
            {/* logo */}
      <div className="logo">
        <img src={Logo} alt="logo" />
        <span>
          QL<span>Đ</span>V
        </span>
      </div>

      {/* {menu} */}
      <div className='menu'> 
        {
            SidebarData.map((item, index) => {
                return(
                    <Link
                    to={item.href}
                    key={index} 
                    className={selected === index ? "menuItem active" : "menuItem"}
                    onClick={() => setSelected(index)}
                    >
                        <item.icon/>
                        <span>
                            {item.heading}
                        </span>
                      
                    </Link>
                )
            })
        }
         {/* signoutIcon */}
         <div className="menuItem" onClick={handleLogOut}>
          <UilSignOutAlt />
          <span> Đăng xuất</span>
        </div>
      </div>
        </div>
      );
}
 
export default Sidebar;