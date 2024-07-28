import React, {useEffect, useState} from "react";
import "./HeaderDoanVien.css"
import { UilSearch,
  UilPlus,
} from '@iconscout/react-unicons'
const HeaderDoanVien = ({toogelModal,  searchDoanVien, searchTerm, setSearchTerm}) => {
  const handleChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    searchDoanVien(searchTerm);
  };

    return (
        <div className="heading">
          <form onSubmit={handleSubmit} className="search">
            <label>
                <input id="search-input" 
                      value={searchTerm}
                      onChange={handleChange}
                      type="text" placeholder="Tìm ở đây..."/>
                <button  type="submit"> <UilSearch/></button>
            </label>
        </form>
        <button type="button" onClick={() => toogelModal(true)} className="btnAdd">
           <UilPlus/> <span>Thêm</span>
        </button>
        </div>
      );
}
 
export default HeaderDoanVien;